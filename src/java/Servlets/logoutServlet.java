package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");  
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("userExists",null);
            session.removeAttribute("userExists");
            session.invalidate();
        }
        
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.sendRedirect("index.jsp"); 
    }

    @Override
    public String getServletInfo() {
        return "Manages logout";
    }

}

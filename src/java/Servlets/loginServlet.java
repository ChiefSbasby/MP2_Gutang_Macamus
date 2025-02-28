package Servlets;

import Models.AuthenticationException;
import Models.NullValueException;
import Models.loginCheck;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loginServlet extends HttpServlet {

    private ResultSet rsUser, rsPass,  rs;
    private int logCheck;
    Connection con;
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        try {	
                Class.forName(config.getInitParameter("jdbcClassName"));
                String username = config.getInitParameter("dbUserName");
                String password = config.getInitParameter("dbPassword");
                StringBuffer url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                        .append("://")
                        .append(config.getInitParameter("dbHostName"))
                        .append(":")
                        .append(config.getInitParameter("dbPort"))
                        .append("/")
                        .append(config.getInitParameter("databaseName"));
                con = 
                  DriverManager.getConnection(url.toString(),username,password);
        } catch (SQLException sqle){
                System.out.println("SQLException error occured - " 
                        + sqle.getMessage());
        } catch (ClassNotFoundException nfe){
                System.out.println("ClassNotFoundException error occured - " 
                + nfe.getMessage());
        }
    } 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Main login checker try catch clause                                  - Mico
        try{
            
        // Creates new session                                                  - Sbasby
        HttpSession session = request.getSession();
        if (!(session.isNew())) {
                session.invalidate();
                session = request.getSession();
        }
        
        // Gets user inputs                                                     - Mico
            String username = request.getParameter("username");     
            String password = request.getParameter("password");
            session.setAttribute("userExists",username);
            
            
        // Catches completely empty inputs and empty password inputs            - Mico
            if("".equals(username) && ("".equals(password))){
                throw new NullValueException("");
            }
            if("".equals(username) && !("".equals(password))){
                request.getRequestDispatcher("errors/error_5.jsp").forward(request,response);
            }
            if(!"".equals(username) && "".equals(password)){
                request.setAttribute("error1","The password is missing!");
                request.getRequestDispatcher("errors/error_1.jsp").forward(request,response);
                response.sendRedirect("errors/error_1.jsp");
            }

            // table for login checker                                          - Mico
            PreparedStatement user = con.prepareStatement("SELECT * FROM APP.USER_INFO WHERE USERNAME = ?");
            user.setString(1, username);
            rsUser = user.executeQuery();
            String un = null,ps = null, rl = null;

            
            while(rsUser.next()){
                un = rsUser.getString("USERNAME");
                ps = rsUser.getString("PASSWORD");
                rl = rsUser.getString("ROLE");
            }
            
            PreparedStatement pass = con.prepareStatement("SELECT * FROM APP.USER_INFO WHERE PASSWORD = ?");
            pass.setString(1, password);
            rsPass = pass.executeQuery();
            
            String un2=null,ps2=null;
            while(rsPass.next()){
                un2 = rsPass.getString("USERNAME");
                ps2 = rsPass.getString("PASSWORD");
            }
            
            logCheck = new loginCheck(un, ps, un2, ps2).logCheck();

            // Checks if log check is below 0 to continue                       - Mico
            if (logCheck>0){
                throw new AuthenticationException(null);
            }
            else {
                // Table attribute                                              - Mico
                PreparedStatement passed = con.prepareStatement("SELECT * FROM APP.USER_INFO WHERE USERNAME = ? AND PASSWORD = ?");
                passed.setString(1, username);
                passed.setString(2, password);
                rs = passed.executeQuery();
                while(rs.next()){
                    // Sets session password and username
                    session.setAttribute("password",rs.getString("PASSWORD"));
                    username = rs.getString("USERNAME");
                    rl = rs.getString("ROLE");
                }
                session.setAttribute("username",username);
                session.setAttribute("role",rl);     

                if(rl.equals("Guest")){
                    response.sendRedirect("guestTable");
                }else if(rl.equals("Admin")){
                    response.sendRedirect("adminTable");
                }
            }
            
        } catch (NullValueException e){
            response.sendRedirect("errors/noLoginCredentials.jsp");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationException ex) {
            switch(logCheck) {
                    case 1:     // checks again for null/empty inputs
                        request.setAttribute("error1","The user is not in our database!");
                        request.getRequestDispatcher("errors/error_1.jsp").forward(request,response);
                        response.sendRedirect("errors/error_1.jsp");
                        break;
                    case 2:     // if only the password is wrong
                        RequestDispatcher err2 = request.getRequestDispatcher("errors/error_2.jsp");
                        err2.forward(request, response);
                        response.sendRedirect("errors/error_2.jsp");
                        break;                     
                }
        }
    }

    @Override
    public String getServletInfo() {
        return "Login servlet";
    }
}

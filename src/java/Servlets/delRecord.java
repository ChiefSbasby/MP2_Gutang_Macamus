/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sabby
 */
public class delRecord extends HttpServlet {

    ResultSet rsCurrent, rsNew;
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
        try{
            HttpSession session = request.getSession(); 
            
            String userDel = request.getParameter("delEmailRecord");
            String currentUser = (String) session.getAttribute("username");
            PreparedStatement curr = con.prepareStatement("SELECT * FROM APP.USER_INFO");
            rsCurrent = curr.executeQuery();   
            String check = null;
            
            if("".equals(userDel)){
                session.setAttribute("getAlert", "empty");
                getServletContext().getRequestDispatcher("/delRecordPage.jsp").forward(request, response);
                response.sendRedirect("delRecordPage.jsp");
            }
            if(userDel.equals(currentUser)){
                    session.setAttribute("getAlert", "currentuser");
                    getServletContext().getRequestDispatcher("/delRecordPage.jsp").forward(request, response);
                    response.sendRedirect("delRecordPage.jsp");
            }
            while(rsCurrent.next()){
                if(userDel.equals(rsCurrent.getString("USERNAME"))){
                    check = userDel;
                }
            }
            
            if(check == null){
                session.setAttribute("getAlert", "notreal");
                getServletContext().getRequestDispatcher("/delRecordPage.jsp").forward(request, response);
                response.sendRedirect("delRecordPage.jsp");
            }
            
            PreparedStatement del = con.prepareStatement("DELETE FROM APP.USER_INFO WHERE USERNAME = ?");
            del.setString(1, userDel);
            del.executeUpdate();
            
            
            rsNew = con.prepareStatement("SELECT * FROM APP.USER_INFO ORDER BY USERNAME").executeQuery();
            session.setAttribute("tblrone",rsNew);
            session.setAttribute("getAlert", "congratsdelete");
            getServletContext().getRequestDispatcher("/successAdmin.jsp").forward(request, response);
            response.sendRedirect("successAdmin.jsp");
        } catch(SQLException e){
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

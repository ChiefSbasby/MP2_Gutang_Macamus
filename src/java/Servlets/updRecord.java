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

/**
 *
 * @author sabby
 */
public class updRecord extends HttpServlet {

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
        
        // This servlet Updates an existing record                              - Mico
        try  {
            String userCheck = request.getParameter("updEmailRecord");
            String updPass = request.getParameter("updPassRecord");
            String updRole = request.getParameter("updRole");
            String rectochange= null;
            PreparedStatement curr = con.prepareStatement("SELECT * FROM APP.USER_INFO");
            rsCurrent = curr.executeQuery();    
            while(rsCurrent.next()){
                if(userCheck.equals(rsCurrent.getString("USERNAME"))){
                    rectochange = rsCurrent.getString("USERNAME");
                }
            }
            
            if ("".equals(userCheck)){
                request.setAttribute("getAlert","noname");
                getServletContext().getRequestDispatcher("/updRecordPage.jsp").forward(request, response);
                response.sendRedirect("updRecordPage.jsp");
            }
            else if(rectochange == null){
                request.setAttribute("getAlert","notreal");
                getServletContext().getRequestDispatcher("/updRecordPage.jsp").forward(request, response);
                response.sendRedirect("updRecordPage.jsp");
            }
            else{
                if(request.getParameter("upd").equals("Update Password")){
                    if (!"".equals(updPass)){
                        if(updPass.equals(request.getParameter("updConfPassRecord"))){
                            PreparedStatement upd = con.prepareStatement("UPDATE APP.USER_INFO SET PASSWORD = ? WHERE USERNAME = ?");
                            upd.setString(1,updPass);
                            upd.setString(2,userCheck);
                            upd.executeUpdate();

                            request.setAttribute("getAlert","congrats");
                            getServletContext().getRequestDispatcher("/updRecordPage.jsp").forward(request, response);
                            response.sendRedirect("updRecordPage.jsp");
                        }
                        else{
                            request.setAttribute("getAlert","notsame");
                            getServletContext().getRequestDispatcher("/updRecordPage.jsp").forward(request, response);
                            response.sendRedirect("updRecordPage.jsp");
                        }
                    }
                    else{
                            request.setAttribute("getAlert","emptypass");
                            getServletContext().getRequestDispatcher("/updRecordPage.jsp").forward(request, response);
                            response.sendRedirect("updRecordPage.jsp");
                    }
                }
                else if (request.getParameter("upd").equals("Update Role")){
                    if (updRole==null){
                        request.setAttribute("getAlert","empty");
                        getServletContext().getRequestDispatcher("/updRecordPage.jsp").forward(request, response);
                        response.sendRedirect("updRecordPage.jsp");
                    }
                    else{
                        PreparedStatement upd = con.prepareStatement("UPDATE APP.USER_INFO SET ROLE = ? WHERE USERNAME = ?");
                        upd.setString(1,updRole);
                        upd.setString(2,userCheck);
                        upd.executeUpdate();

                        request.setAttribute("getAlert","congratsrole");
                        getServletContext().getRequestDispatcher("/updRecordPage.jsp").forward(request, response);
                        response.sendRedirect("updRecordPage.jsp");
                    }
                }
            }
        }
        catch(SQLException e){
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

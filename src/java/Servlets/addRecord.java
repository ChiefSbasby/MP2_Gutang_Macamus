package Servlets;

import java.io.IOException;
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

public class addRecord extends HttpServlet {
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
        
        // This Servlet adds a record when conditions are met                   - Mico
        try{
            String newUser=request.getParameter("addEmailRecord");
            String newPass=request.getParameter("addPassRecord");
            String newRole=request.getParameter("addRole");
            
            // Checks if any input is left empty                                - Mico
            if (newUser == null || newPass == null || newRole == null){
                request.setAttribute("getAlert","nuh uh");
                getServletContext().getRequestDispatcher("/addRecordPage.jsp").forward(request, response);
                response.sendRedirect("addRecordPage.jsp");
            }
            
            // Checks the current table if another username in the
            // same role already exists                                         - Mico
            PreparedStatement curr = con.prepareStatement("SELECT * FROM APP.USER_INFO");
            rsCurrent = curr.executeQuery();    
            while(rsCurrent.next()){
                if(newUser.equals(rsCurrent.getString("USERNAME"))
                && newRole.equals(rsCurrent.getString("ROLE"))){
                    request.setAttribute("getAlert","samedude");
                    getServletContext().getRequestDispatcher("/addRecordPage.jsp").forward(request, response);
                    response.sendRedirect("addRecordPage.jsp");
                }
            }
            
            // Adds the record                                                  - Mico
            PreparedStatement add = con.prepareStatement("INSERT INTO APP.USER_INFO (USERNAME,PASSWORD,ROLE) VALUES(?,?,?)");
            add.setString(1,newUser);
            add.setString(2,newPass);
            add.setString(3,newRole);
            add.executeUpdate();
            rsNew = con.prepareStatement("SELECT * FROM APP.USER_INFO").executeQuery();
            request.setAttribute("getAlert","congrats");
            request.setAttribute("tblrone",rsNew);
            getServletContext().getRequestDispatcher("/successAdmin.jsp").forward(request, response);
            response.sendRedirect("successAdmin.jsp");
            
        }catch (SQLException e){
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Adds a record to the database";
    }

}

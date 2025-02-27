package Servlets;

import Models.NullValueException;
import Models.loginCheck;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class loginServlet extends HttpServlet {

    private ResultSet rs,  rsAll;
    private int logCheck;
    Connection con;
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        try {	
                Class.forName(config.getInitParameter("jdbcClassName"));
                //System.out.println("jdbcClassName: " + config.getInitParameter("jdbcClassName"));
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
            String username = request.getParameter("username");     //gets input values from index.jsp
            String password = request.getParameter("password");
            
            
        // Catches completely empty inputs and empty password inputs            - Mico
            if("".equals(username)){
                if ("".equals(password)) {throw new NullValueException("");}
                if (!"".equals(password)) {response.sendRedirect("errors/error_5.jsp");}
            }
            if((username != null && password == null) || 
                    (!"".equals(username) && "".equals(password))){
                response.sendRedirect("errors/error_1.jsp");
            }

            // login checker                                                    - Mico
            PreparedStatement user = con.prepareStatement("SELECT * FROM APP.USER_INFO WHERE USERNAME = ? AND PASSWORD = ?");
            user.setString(1, username);
            user.setString(2, password);
            rs = user.executeQuery();
            String un = null,ps = null,rl = null;

            PreparedStatement allUser = con.prepareStatement("SELECT * FROM APP.USER_INFO");
            rsAll = allUser.executeQuery();     // to get the list of accounts
            
            /*
            boolean usernameexists = false;
            boolean passwordcorrect = false;
            while(rs.next()){
                ps = rs.getString("PASSWORD");
                un = rs.getString("USERNAME");
                rl = rs.getString("ROLE");
            usernameexists = true;
            passwordcorrect = false;
                if(username.equals(un)){
                    usernameexists = true;                
                    if(password.equals(ps)){
                        passwordcorrect = true;
                        break;
                    }
                    break;
                }
            }
            
            
            if (usernameexists == true && passwordcorrect == false) {
                response.sendRedirect("errors/error_2.jsp");
            } else if(usernameexists == false && passwordcorrect == false){
                response.sendRedirect("errors/error_3.jsp");
            } else if(usernameexists == false) {
                response.sendRedirect("errors/error_1.jsp");
            } else if(usernameexists == true && passwordcorrect == true){
                request.setAttribute("username",username);
                request.setAttribute("password",password);
                request.setAttribute("role",rl);                
                request.setAttribute("tblrone", rsAll);
                
                if(rl == "Guest"){
                    getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
                    //response.sendRedirect("success.jsp");
                }else{
                    getServletContext().getRequestDispatcher("/successAdmin.jsp").forward(request, response);
                }
            }
            */
            
            
            while(rs.next()){
                un = rs.getString("USERNAME");
                ps = rs.getString("PASSWORD");
                rl = rs.getString("ROLE");
                /*
                if(username.equals(un) && password.equals(ps)){
                    request.setAttribute("username",username);
                    request.setAttribute("password",password);
                    request.setAttribute("role",rl);                
                    request.setAttribute("tblrone", rsAll);

                    getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
                    response.sendRedirect("success.jsp");
                } else if("".equals(un) || un == null){    // checks again for null/empty inputs
                    response.sendRedirect("errors/error_1.jsp");
                } else if(!password.equals(ps) &&  username.equals(un)){  // correct username, wrong password
                    response.sendRedirect("errors/error_2.jsp");
                } else {    // wrong username, wrong password
                    response.sendRedirect("errors/error_3.jsp");
                } 
                */
                
                logCheck = new loginCheck(username,password,un,ps).logCheck();
                switch(logCheck) {
                    case 1:     // checks again for null/empty inputs
                        RequestDispatcher err1 = request.getRequestDispatcher("errors/error_1.jsp");
                        err1.forward(request, response);
                        break;
                    case 2:     // if only the password is wrong
                        RequestDispatcher err2 = request.getRequestDispatcher("errors/error_2.jsp");
                        err2.forward(request, response);
                        break;
                    case 3:     // if both username and password are wrong
                        RequestDispatcher err3 = request.getRequestDispatcher("errors/error_3.jsp");
                        err3.forward(request, response);
                        break;
                    case -1:
                        request.setAttribute("username",username);
                        request.setAttribute("password",password);
                        request.setAttribute("role",rl);                
                        request.setAttribute("tblrone", rsAll);
                        
                        if(rl.equals("Guest")){
                            getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
                        }else if(rl.equals("Admin")){
                            getServletContext().getRequestDispatcher("/successAdmin.jsp").forward(request, response);
                        }
                        break;                        
                }
            }
            
        } catch (NullValueException e){
            response.sendRedirect("errors/noLoginCredentials.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Login servlet";
    }// </editor-fold>
}

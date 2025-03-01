<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="CSS/success.css"/>
        <title>Homepage</title>
    </head>
    <body>
        <%
            //prevents going back to this page after logout
            response.setHeader("Cache-Control","no-cache,no-store, must-revalidate");
            if(session.getAttribute("userExists")==null){
                    response.sendRedirect("errors/error_session.jsp");
            }
        %>
        <header>
            <p>
            <% out.print(getServletContext().getInitParameter("Subject")); %> /   
            <% out.print(getServletContext().getInitParameter("Section")); %>    
            <% out.print(getServletContext().getInitParameter("First")); %>   &  
            <% out.print(getServletContext().getInitParameter("Second"));%>
            </p>
        </header>
            <main>
                <section class="table">
                    <h1><% out.print("Hola, " + session.getAttribute("role") 
                            + " " + session.getAttribute("username") + "!"); %></h1>
                    <table class="Guest">
                        <tr>
                            <th>USERNAME</th>
                            <th>ROLE</th> 
                        </tr>
                        <% 
                            ResultSet rs = (ResultSet) request.getAttribute("guestTable");
                            while(rs.next()){
                        %>
                        <tr>
                            <td><% out.print(rs.getString("USERNAME"));%></td>
                            <td><%out.print(rs.getString("ROLE"));%></td>
                        </tr>
                        <%}%>
                    </table>
                    <form action="logoutServlet" method="POST">
                        <input class="logoutbutt" type="submit" value="Logout">
                    </form>
                </section>
            </main>
        <footer>
                <h3>  <% out.print(getServletContext().getAttribute("date")); %> </h3>
                <h3>  <% out.print(getServletContext().getInitParameter("MPNumber")); %> </h3>
        </footer>
    </body>
</html>

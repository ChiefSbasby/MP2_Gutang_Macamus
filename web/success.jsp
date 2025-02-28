<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
    </head>
    <body>
        <%
            //prevents going back to this page after logout
            response.setHeader("Cache-Control","no-cache,no-store, must-revalidate");
            if(session.getAttribute("userExists")==null){
                    response.sendRedirect("index.jsp");
            }
        %>
        <header><p>
            <% out.print(getServletContext().getInitParameter("Subject")); %> /   
            <% out.print(getServletContext().getInitParameter("Section")); %>   <br>   
            <% out.print(getServletContext().getInitParameter("First")); %> <br>      
            <% out.print(getServletContext().getInitParameter("Second"));%>
            </p></header>
        <p><% out.print("Hola, " + session.getAttribute("role") + " " + session.getAttribute("username") + "!"); %></p>
        <table>
            <tr styles="border:1px;">
                <th>USERNAME</th>
                <th>ROLE</th> 
            </tr>
            <% 
                ResultSet rs = (ResultSet) session.getAttribute("tblrone");
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
        <footer>
                <h3>  <% out.print(getServletContext().getAttribute("date")); %> </h3>
                <h3>  <% out.print(getServletContext().getInitParameter("MPNumber")); %> </h3>
        </footer>
    </body>
</html>

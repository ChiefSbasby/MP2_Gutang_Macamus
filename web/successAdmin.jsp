<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header><p>
            <% out.print(getServletContext().getInitParameter("Subject")); %> /   
            <% out.print(getServletContext().getInitParameter("Section")); %>   <br>   
            <% out.print(getServletContext().getInitParameter("First")); %> <br>      
            <% out.print(getServletContext().getInitParameter("Second"));%>
            </p></header>
        <p><% out.print("Hola, " + request.getAttribute("role") + " " + request.getAttribute("username") + "!"); %></p>
        <table>
            <tr styles="border:1px;">
                <th>USERNAME</th>
                <th>PASSWORD</th> 
                <th>ROLE</th> 
            </tr>
            <% 
                ResultSet rs = (ResultSet) request.getAttribute("tblrone");
                while(rs.next()){
            %>
            <tr>
            <td><% out.print(rs.getString("USERNAME"));%></td>
            <td><%out.print(rs.getString("PASSWORD"));%></td>
            <td><%out.print(rs.getString("ROLE"));%></td>
            </tr>
            <%}%>
        </table>
        
        <button type="button" onclick="window.location='addRecordPage.jsp'">Add a Record</button>
        <button type="button" onclick="window.location='updRecordPage.jsp'">Update a Record</button>
        <button type="button" onclick="window.location='delRecordPage.jsp'">Delete a Record</button>             
        <p>ur in the admin page</p>
        
        <button type="button" action="logoutServlet" method="GET">Logout</button>
        <footer>
                <h3>  <% out.print(getServletContext().getAttribute("date")); %> </h3>
                <h3>  <% out.print(getServletContext().getInitParameter("MPNumber")); %> </h3>
        </footer>
    </body>
</html>

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
        
        <form name="addRecord">
            <fieldset>
                <legend>Add a Record</legend>
                <label for="addEmail">Email: </label>
                <input type="text" id="addEmailRecord">

                <label for="addPassword">Password: </label>
                <input type="password" id="addPassRecord">

                <label for="addRole">Role: </label>
                <input type="radio" id="addGuest" name="addRole" value="Guest">
                <label for="addRoleGuest">Guest</label>
                <input type="radio" id="addAdmin" name="addRole" value="Admin">
                <label for="addRoleAdmin">Admin</label>
                <button type="button">Add Record</button>
            </fieldset>
        </form>
        
        <form name="updRecord">
            <fieldset>
                <legend>Update Existing Record</legend>
                <label for="addEmail">Email: </label>
                <input type="text" id="addEmailRecord">
                <p>*Email required before updating</p>

                <label for="updPassword">Password: </label>
                <input type="password" id="updPassRecord">
                <label for="confPassword">Confirm Password: </label>
                <input type="password" id="updConfPassRecord">
                <button type="button">Update Password</button>

                <label for="addRole">Role: </label>
                <input type="radio" id="updGuest" name="updRole" value="Guest">
                <label for="updRoleGuest">Guest</label>
                <input type="radio" id="updAdmin" name="updRole" value="Admin">
                <label for="updRoleAdmin">Admin</label>
                <button type="button">Update Role</button>
            </fieldset>
        </form>
        
        <form name="delRecord"> 
            <fieldset>
                <legend>Delete Existing Record</legend>
                <label for="delEmail">User Email: </label>
                <input type="text" id="delEmailRecord">
                <button type="button">Delete Record</button>
            </fieldset>
        </form>
        <p>ur in the admin page</p>
        <footer>
                <h3>  <% out.print(getServletContext().getAttribute("date")); %> </h3>
                <h3>  <% out.print(getServletContext().getInitParameter("MPNumber")); %> </h3>
        </footer>
    </body>
</html>

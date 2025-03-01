<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="CSS/crud.css"/>
        <title>Update a Record</title>
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
            <% out.print(getServletContext().getInitParameter("Section")); %>   
            <% out.print(getServletContext().getInitParameter("First")); %>     &
            <% out.print(getServletContext().getInitParameter("Second"));%>
            </p></header>
        <div>
            <form action="updRecord" name="updRecord">
                <fieldset>
                    <legend>Update Existing Record</legend>
                    <label for="addEmail">Email: </label>
                    <input class="field" type="text" name="updEmailRecord" placeholder="Email">
                    <p id="req">*Email required before updating</p><br>

                    <label for="updPassword">Password: </label>
                    <input class="field" type="password" name="updPassRecord" placeholder="Password"> <br>
                    <label for="confPassword">Confirm Password: </label>
                    <input class="field" type="password" name="updConfPassRecord" placeholder="Confirm Password"> <br>
                    <input type="submit" name="upd" value="Update Password">

                    <br><br>
                    <label for="addRole">Role: </label>
                    <input type="radio" id="updGuest" name="updRole" value="Guest">
                    <label for="updRoleGuest">Guest</label>
                    <input type="radio" id="updAdmin" name="updRole" value="Admin">
                    <label for="updRoleAdmin">Admin</label> <br>
                    <input type="submit" name="upd" value="Update Role">
                </fieldset>
            </form>
            <br>
            <form action="adminTable" method="POST">
                <input class="backbutt" type="submit" value="Back">
            </form>
        </div>
        <footer>
                <h3>  <% out.print(getServletContext().getAttribute("date")); %> </h3>
                <h3>  <% out.print(getServletContext().getInitParameter("MPNumber")); %> </h3>
        </footer>
    </body>
    <script>
        var Msg = '<%=session.getAttribute("getAlert")%>';
        <%session.setAttribute("getAlert", null);%>
        if (Msg == "notreal"){
            function alertName(){
                alert("That user doesn't exist in the database >:(");
            }
            window.onload = alertName;
        }
        else if (Msg == "congrats"){
            function alertName(){
                alert("Password update success :)");
            }
            window.onload = alertName;
        }
        else if (Msg == "congratsrole"){
            function alertName(){
                alert("Role update success :)");
            }
            window.onload = alertName;
        }
        else if (Msg == "samedude"){
            function alertName(){
                alert("You can't update your own role >:(");
            }
            window.onload = alertName;
        }
        else if (Msg == "noname"){
            function alertName(){
                alert("Input a name first >:(");
            }
            window.onload = alertName;
        }
        else if (Msg == "notsame"){
            function alertName(){
                alert("Confirmation Password and new Password is not the same >:(");
            }
            window.onload = alertName;
        }
        else if (Msg == "emptypass"){
            function alertName(){
                alert("Password is empty >:(");
            }
            window.onload = alertName;
        }
        else if (Msg == "empty"){
            function alertName(){
                alert("Role is empty >:(");
            }
            window.onload = alertName;
        }
        
        <%session.setAttribute("getAlert", null);%>
    </script>
</html>

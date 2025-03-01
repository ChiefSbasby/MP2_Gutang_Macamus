<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="CSS/crud.css"/>
        <title>Add a Record</title>
    </head> 
    <body>
        
        <header><p>
            <% out.print(getServletContext().getInitParameter("Subject")); %> /   
            <% out.print(getServletContext().getInitParameter("Section")); %>   
            <% out.print(getServletContext().getInitParameter("First")); %>     &
            <% out.print(getServletContext().getInitParameter("Second"));%>
            </p></header>
        <%
            //prevents going back to this page after logout
            response.setHeader("Cache-Control","no-cache,no-store, must-revalidate");
            if(session.getAttribute("userExists")==null){
                    response.sendRedirect("index.jsp");
            }
        %>
        <div>
            <form action="addRecord" name="addRecord">
                <fieldset>
                    <legend>Add a Record</legend>
                    <label for="addEmail">Email: </label>
                    <input class="field" type="text" name="addEmailRecord" placeholder="Email"> <br><br>

                    <label for="addPassword">Password: </label>
                    <input class="field" type="password" name="addPassRecord" placeholder="Password"> <br><br>

                    <label for="addRole">Role: </label>
                    <input type="radio" id="addGuest" name="addRole" value="Guest">
                    <label for="addRoleGuest">Guest</label>
                    <input type="radio" id="addAdmin" name="addRole" value="Admin">
                    <label for="addRoleAdmin">Admin</label> <br><br>
                    <button type="submit">Add Record</button>
                </fieldset>
            </form>
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
        if (Msg == "samedude"){
            function alertName(){
                alert("That user is already in the database >:(");
            }
            window.onload = alertName;
        }
        else if (Msg == "nuh uh"){
            function alertName(){
                    alert("DO NOT leave any fields empty >:(");
                }
                window.onload = alertName;
        }
    </script>
</html>

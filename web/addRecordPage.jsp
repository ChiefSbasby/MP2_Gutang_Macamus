<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add a Record</title>
    </head> 
    <body>
        <form action="addRecord" name="addRecord">
            <fieldset>
                <legend>Add a Record</legend>
                <label for="addEmail">Email: </label>
                <input type="text" name="addEmailRecord" placeholder="Email">

                <label for="addPassword">Password: </label>
                <input type="password" name="addPassRecord" placeholder="Password">

                <label for="addRole">Role: </label>
                <input type="radio" id="addGuest" name="addRole" value="Guest">
                <label for="addRoleGuest">Guest</label>
                <input type="radio" id="addAdmin" name="addRole" value="Admin">
                <label for="addRoleAdmin">Admin</label>
                <button type="submit">Add Record</button>
            </fieldset>
        </form>
        <button action="back" type="button" onclick="history.back()">Back</button>
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update a Record</title>
    </head>
    <body>
        <form action="updRecord" name="updRecord">
            <fieldset>
                <legend>Update Existing Record</legend>
                <label for="addEmail">Email: </label>
                <input type="text" name="updEmailRecord" placeholder="Email">
                <p>*Email required before updating</p>

                <label for="updPassword">Password: </label>
                <input type="password" name="updPassRecord" placeholder="Password"> <br><br>
                <label for="confPassword">Confirm Password: </label>
                <input type="password" name="updConfPassRecord" placeholder="Confirm Password"> <br><br>
                <input type="submit" name="upd" value="Update Password">
                
                <br><br>
                <label for="addRole">Role: </label>
                <input type="radio" id="updGuest" name="updRole" value="Guest">
                <label for="updRoleGuest">Guest</label>
                <input type="radio" id="updAdmin" name="updRole" value="Admin">
                <label for="updRoleAdmin">Admin</label> <br><br>
                <input type="submit" name="upd" value="Update Role">
            </fieldset>
        </form>
        <br>
        <button action="back" type="button" onclick="history.back()">Back</button>
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

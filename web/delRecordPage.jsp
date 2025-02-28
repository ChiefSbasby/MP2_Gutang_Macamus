<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete a Record</title>
    </head>
    <body>
        <%
            //prevents going back to this page after logout
            response.setHeader("Cache-Control","no-cache,no-store, must-revalidate");
            if(session.getAttribute("userExists")==null){
                    response.sendRedirect("index.jsp");
            }
        %>
        <form action="delRecord" name="delRecord"> 
            <fieldset>
                <legend>Delete Existing Record</legend>
                <label for="delEmail">User Email: </label>
                <input name="delEmailRecord" type="text" id="delEmailRecord">
                <button type="submit" >Delete Record</button>
            </fieldset>
        </form>
        <form action="adminTable" method="POST">
            <input class="backbutt" type="submit" value="Back">
        </form>
    </body>
    <script>
        var Msg = '<%=session.getAttribute("getAlert")%>';
        <%session.setAttribute("getAlert", null);%>
        if (Msg == "currentuser"){
            function alertName(){
                alert("You can't delete the account YOU are currently using >:(");
            }
            window.onload = alertName;
        }
        else if (Msg == "notreal"){
            function alertName(){
                alert("User already doesn't exist in the database >:(");
            }
            window.onload = alertName;
        }
        else if (Msg == "empty"){
            function alertName(){
                alert("DO NOT leave any fields empty >:(");
            }
            window.onload = alertName;
        }
        <%session.setAttribute("getAlert", null);%>
    </script>
</html>

<%-- 
    Document   : index
    Created on : Feb 15, 2025, 4:16:18 PM
    Author     : Gutang n Macamus :3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="CSS/login.css"/>
        <title>MP2</title>
    </head>
    <body>
        <% // invalidates any previous sessions
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        response.setHeader("Pragma","no-cache");
        session.setAttribute("userExists", null);
        session.invalidate();
        %>
        <main>
            <div class="bubblebox">
                <h1>Conspicuous DataBase Website</h1>
                <form action="login" method="POST">
                    <label id="email" for="email">Email: </label> 
                    <input class="field" name="username" type="username"/><br><br>
                    <label for="pass">Password: </label>
                    <input class="field" name="password" type="password"/><br><br>
                    <input class="loginbutt" type="submit" value="Log In">
                </form>
            </div>
        </main>
    </body>
</html>

<%-- 
    Document   : error_1
    Created on : Feb 15, 2025, 4:40:47 PM
    Author     : gutang n macamus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/MP2_Gutang_Macamus/CSS/error.css"/>
        <title>Login Error!</title>
    </head>
    <body>
        <main>
            <div class="bubblebox">
                <h1><% out.print(request.getAttribute("error1"));%> </h1>
                <button onclick="history.back()">Retry</button>
            </div>
        </main>
    </body>
</html>

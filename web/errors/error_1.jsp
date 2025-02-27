<%-- 
    Document   : error_1
    Created on : Feb 15, 2025, 4:40:47 PM
    Author     : gutang n macamus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error!</title>
    </head>
    <body>
        <h1><% out.print(request.getAttribute("error1"));%> </h1>
    </body>
</html>

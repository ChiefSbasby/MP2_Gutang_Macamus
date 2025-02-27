<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a Record</title>
    </head>
    <body>
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
                <button type="submit">Add Record</button>
            </fieldset>
        </form>
        <button type="button" onclick="history.back()">Back</button>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update a Record</title>
    </head>
    <body>
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
                <button type="submit">Update Role</button>
            </fieldset>
        </form>
        <button type="button" onclick="history.back()">Back</button>
    </body>
</html>

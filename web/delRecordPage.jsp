<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete a Record</title>
    </head>
    <body>
        <form name="delRecord"> 
            <fieldset>
                <legend>Delete Existing Record</legend>
                <label for="delEmail">User Email: </label>
                <input type="text" id="delEmailRecord">
                <button type="submit">Delete Record</button>
            </fieldset>
        </form>
        <button type="button" onclick="history.back()">Back</button>
    </body>
</html>

<%-- 
    Document   : Login
    Created on : Apr 16, 2020, 4:39:25 PM
    Author     : n.riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HenryBooks Login</title>
    </head>
    <body>
        <h1>Welcome to the Inventory System</h1>
        <h2>Please enter your user id and password</h2>
        <form action="Login" method="post">
            <table>
                <tr>
                    <td>User ID:</td>
                    <td><input type="text" name="userid" id="userid"
                            value="${empty u.userid ? cookie.uid.value 
                                     : u.userid}">
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" id="password">
                    </td>
                </tr>
            </table>
            <input type="submit" value="Login">
        </form>
        <br>
        ${msg}
    </body>
</html>
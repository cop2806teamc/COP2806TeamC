<%-- 
    Document   : login.jsp
    Created on : Apr 6, 2014, 5:16:30 PM
    Author     : Zhou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Titan Bank</title>
    </head>
    <body>
        <h1>Welcome to Titan Bank!</h1>
        <form action="userLogin?option=login" method="post" id="loginForm">
            <fieldset>
                <legend>Log in</legend>
                <div>
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username" required="required">
		</div>
                <div>
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" required="required">
		</div>
                <div>
                    <label for="submit"></label>
                    <input type="submit" value="Log in">
                </div>
            </fieldset>
        </form>
    </body>
</html>

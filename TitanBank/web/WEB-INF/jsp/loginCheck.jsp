<%-- 
    Document   : loginCheck
    Created on : Apr 13, 2014, 3:26:06 PM
    Author     : Zhou
--%>
<%@page import="edu.spcollege.tbk.domain.*, edu.spcollege.tbk.domain.user.*,"%>
<%
        AuthenticationService AuthServ = new AuthenticationService(new UserRepository());

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Customer loginCustomer = null;
        try {
            // must be user / user
            loginCustomer = AuthServ.login(username, password);
        } catch (UserNotFoundException ex) {
            loginCustomer = null;
        } catch (InvalidPasswordException ex) {
            loginCustomer = null;
        }
        
        if (loginCustomer == null) {
%>
            <jsp:forward page="loginError.jsp" />
<%
        } else {
            session.setAttribute("loginStatus", true);
            session.setAttribute("username", username);

            response.sendRedirect("index.htm");

        }
%>

<%-- 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Check Page</title>
    </head>
    <body>
        <h1>username= <%=username%></h1>
    </body>
</html>
--%>
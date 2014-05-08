<%-- 
    Document   : checkOrderConfirm
    Created on : May 5, 2014, 3:59:10 AM
    Author     : Zhou
--%>

<%@page import="edu.spcollege.tbk.domain.bankaccount.*"%>
<%! BankAccount orderAccount; %>
<%
    orderAccount = (BankAccount) request.getAttribute("orderAccount");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Titan Bank: Check Order Confirm</title>
    </head>
    
    <body>
        <jsp:include page="stdnav.jsp" />
        <h2>Your check order request for <%=orderAccount.getAccountName()%> has been confirmed.</h2>
        <div class="main">
        </div> <!-- class="main" -->

        <jsp:include page="stdfooter.jsp" />
        
    </body>
</html>

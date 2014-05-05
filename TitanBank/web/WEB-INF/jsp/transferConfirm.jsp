<%-- 
    Document   : transferConfirm
    Created on : May 4, 2014, 6:51:30 PM
    Author     : Zhou
--%>

<%@page import="edu.spcollege.tbk.domain.*, edu.spcollege.tbk.domain.transfer.*, java.util.List, java.util.Date"%>
<%@page import="java.text.DateFormat, java.text.ParseException, java.text.SimpleDateFormat"%>

<%! List<TransferRequest> transferRequests; %>
<%
    transferRequests = (List<TransferRequest>) request.getAttribute("transferRequests");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Titan Bank: Transfer Confirm</title>
    </head>
    
    <body>
        <jsp:include page="stdnav.jsp" />
        <h2>Your transfer has been confirmed</h2>
        <div class="main">
            <jsp:include page="transfersList.jsp" />
        </div> <!-- class="main" -->

        <jsp:include page="stdfooter.jsp" />
        
    </body>
</html>


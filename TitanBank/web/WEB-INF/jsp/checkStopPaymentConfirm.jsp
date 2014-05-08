<%-- 
    Document   : checkStopPaymentConfirm
    Created on : May 5, 2014, 4:28:36 AM
    Author     : Zhou
--%>

<%@page import="edu.spcollege.tbk.domain.check.*, java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%! List<Check> stoppedChecks; %>
<%
    stoppedChecks = (List<Check>) request.getAttribute("stoppedChecks");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Titan Bank: Check Stop Payment Confirm</title>
    </head>
    
    <body>
        <jsp:include page="stdnav.jsp" />
        <h2>Your stop payment request has been confirmed.</h2>
        <div class="main">
<%
                String info = "";
                for (Check check : stoppedChecks) {
                    info += "<table border=\"1\"><tr><th>Number</th><th>Check Account</th><th>Date</th><th>Pay To</th><th>Amount</th><th>Check Status</th></tr>";
                    info += "<tr><td>" + check.getCheckNumber().toString() + "</td>";
                    info += "<td>" + check.getAccount().getAccountName() + "</td>";
                    info += "<td>" + (check.getDate().getMonth()+1) + "/" + check.getDate().getDate() + "/" + (check.getDate().getYear()+1900) + "</td>";
                    info += "<td>" + check.getPayTo() + "</td>";
                    info += "<td>" + check.getAmountString() + "</td>";
                    info += "<td>" + check.getStatus().toString()+ "</td>";
                    info += "</tr></table><br>";
                }
                out.println(info);
%>
        </div> <!-- class="main" -->

        <jsp:include page="stdfooter.jsp" />
        
    </body>
</html>

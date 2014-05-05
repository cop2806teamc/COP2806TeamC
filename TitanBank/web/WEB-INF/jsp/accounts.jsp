<%-- 
    Document   : accounts
    Created on : Apr 13, 2014, 5:27:16 PM
    Author     : Zhou
--%>

<%@page import="edu.spcollege.tbk.domain.*, edu.spcollege.tbk.domain.bankaccount.*, java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<%! List<BankAccount> bankAccounts; %>
<%
    bankAccounts = (List<BankAccount>) request.getAttribute("bankAccounts");
%>
    

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Titan Bank: Accounts</title>
    </head>
    
    <body>
        <jsp:include page="stdnav.jsp" />
        
        <div class="main">
            <h2>Account Summary</h2>
            <div>
            <table border="1">
<%
                String info = "";
                for(BankAccount account : bankAccounts) {
                    info += "<tr><td>" + account.getAccountName() + "</td>";
                    info += "<td>" + account.getBalanceString() + "</td></tr>";
                }
                out.println(info);
%>
            </table>
            </div>
        </div> <!-- class="main" -->
        
        <jsp:include page="stdfooter.jsp" />
    </body>
</html>



<%-- 
    Document   : checks
    Created on : May 4, 2014, 10:16:52 PM
    Author     : Zhou
--%>

<%@page import="edu.spcollege.tbk.domain.*, edu.spcollege.tbk.domain.bankaccount.*, edu.spcollege.tbk.domain.check.*, java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%! List<BankAccount> bankAccounts; %>
<%! List<Check> checks; %>
<%
    bankAccounts = (List<BankAccount>) request.getAttribute("bankAccounts");
    checks = (List<Check>) request.getAttribute("checks");
%>
    

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Titan Bank: Checks</title>
        <script type="text/javascript">
            window.onload = initChecks;
            function initChecks() {
                // for stop payment form
                var stopPaymentForm = document.getElementById("checksSearchForm");
                var pendingChecks = document.getElementsByClassName("pendingCheck");
                var stopPaymentButton = document.getElementById("stopPaymentButton");
                
                for (var i = 0; i < pendingChecks.length; i++) {
                    pendingChecks[i].onchange = function() { 
                        for (var i = 0; i < pendingChecks.length; i++) {
                            if (pendingChecks[i].checked) {
                                stopPaymentButton.disabled = false;
                                return;
                            }
                            stopPaymentButton.disabled = true;
                        }
                    }
		}
                
                // for check order
                var checkOrderButton = document.getElementById("checkOrderButton");
                var checkOrderAccount = document.getElementById("checkOrderAccount");
                checkOrderAccount.onchange = function() {
                    if (checkOrderAccount.selectedIndex <= 0) {
                        checkOrderButton.disabled = true;
                    } else {
                        checkOrderButton.disabled = false;
                    }
                }
            }
        </script>
    </head>
    
    <body>
        <jsp:include page="stdnav.jsp" />
        
        <div class="main">
            <h2>Checks Summary</h2>

            <div>
            <form action="checksSearch" method="post" id="checksSearchForm">
                <fieldset>
                    <legend>Search Check</legend>
                    <div>
                        <div>
                            <label for="checkNumber">Check Number: </label>
                            <input type="text" name="checkNumber" id="checkNumber">
                        </div>
                        <div>
                            <label for="bankAccount">Bank Account: </label>
                            <select name="bankAccount" id="bankAccount">
                                <option></option>
<%
                                for (BankAccount account : bankAccounts) {
                                    out.println("<option value=\"" + account.getAccountNumber() + "\">" + account.getAccountName()  +  "</option>");
                                }
%>
                            </select>
                        </div>
                    </div>
                    <div>
                        <label for="submit"></label>
                        <input type="submit" value="Search">
                    </div>
                </fieldset>
            </form>
            </div>


            <br><br>


            <div>
            <form action="checkStopPayment" method="post" id="checkStopPaymentForm">
                <fieldset>
                    <legend>Check Records</legend>
<%
                String info = "";
                for (Check check : checks) {
                    if (check.getStatus() == CheckStatus.PENDING) {
                        info += "<input type=\"checkbox\" class=\"pendingCheck\" name=\"pendingCheck\" value=\"" + check.getId() + "\">";
                    } else {
                        info += "<input type=\"checkbox\" disabled=\"true\" value=\"" + check.getId() + "\">";
                    }
                    info += "<table border=\"1\"><tr><th>Number</th><th>Check Account</th><th>Date</th><th>Pay To</th><th>Amount</th><th>Check Status</th></tr>";
                    info += "<tr><td>" + check.getCheckNumber().toString() + "</td>";
                    info += "<td>" + check.getAccount().getAccountName() + "</td>";
                    info += "<td>" + check.getDate().toString() + "</td>";
                    info += "<td>" + check.getPayTo() + "</td>";
                    info += "<td>" + check.getAmount() + "</td>";
                    info += "<td>" + check.getStatus().toString()+ "</td>";
                    info += "</tr></table><br>";
                }
                out.println(info);
%>
                <label for="submit"></label>
                <input type="submit" id="stopPaymentButton" disabled="true" value="Stop Payment">
                </fieldset>
            </form>
            </div>


            <br><br>


            <div>
            <form action="checkOrder" method="post" id="checkOrderForm">
                <fieldset>
                    <legend>Check Order</legend>
                    <div>
                        <div>
                            <label for="checkOrderAccount">Bank Account: </label>
                            <select name="checkOrderAccount" id="checkOrderAccount">
                                <option>Select your account...</option>
<%
                                for (BankAccount account : bankAccounts) {
                                    out.println("<option value=\"" + account.getAccountNumber() + "\">" + account.getAccountName()  +  "</option>");
                                }
%>
                            </select>
                        </div>
                    </div>
                    <div>
                        <label for="submit"></label>
                        <input type="submit" id="checkOrderButton" disabled="true" value="Order">
                    </div>
                </fieldset>
            </form>
            </div>
        </div> <!-- class="main" -->

        <jsp:include page="stdfooter.jsp" />
    </body>
</html>
<%-- 
    Document   : transfers
    Created on : Apr 13, 2014, 8:26:36 PM
    Author     : Zhou
--%>

<%@page import="edu.spcollege.tbk.domain.*, edu.spcollege.tbk.domain.bankaccount.*, edu.spcollege.tbk.domain.transfer.*, java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<%! List<BankAccount> bankAccounts; %>
<%! List<TransferRequest> transferRequests; %>
<%
    bankAccounts = (List<BankAccount>) request.getAttribute("bankAccounts");
    transferRequests = (List<TransferRequest>) request.getAttribute("transferRequests");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Titan Bank: Transfers</title>
<%--        <script src="../js/transfers.js" type="text/javascript"></script> --%>
        <script type="text/javascript">
            window.onload = initRequestForm;
            function initRequestForm() {
                var transferRequestForm = document.getElementById("transferRequestForm");
                var from = document.getElementById("fromAccount");
                var to = document.getElementById("toAccount");
                var amount = document.getElementById("transferAmount");
                var sendOn = document.getElementById("sendOn");
                var activeDate = document.getElementById("activeDate");
                
                var today = new Date();
                activeDate.value = (today.getMonth()+1) + "/" + today.getDate() + "/" + today.getFullYear();
                activeDate.readOnly = true;
                
                sendOn.onchange = function() {
                    if (sendOn.selectedIndex > 0) {
                        activeDate.readOnly = false;
                    } else {
                        var today = new Date();
                        activeDate.value = (today.getMonth()+1) + "/" + today.getDate() + "/" + today.getFullYear();
                        activeDate.readOnly = true;
                        
                    }
                }
                
                transferRequestForm.onsubmit = function() {
                    if (from.selectedIndex <= 0 || to.selectedIndex <= 0) {
                        alert("Please select a valid account.");
                        return false;
                    }
                    
                    if (from.selectedIndex == to.selectedIndex) {
                        alert("Please select different accounts.");
                        return false;
                    }
                    
                    var amt = parseFloat(amount.value);
                    if (!amt) {
                        alert('Please input a valid amount.');
                        return false;
                    }
                    if (amt <= 0) {
                        alert('Please input a positive amount.');
                        return false;
                    }
                }
            }
        </script>
    </head>
    
    <body>
        <jsp:include page="stdnav.jsp" />
        
        <div class="main">
            <h2>Transfers</h2>
            <div>
            <form action="transferConfirm" method="post" id="transferRequestForm">
                <fieldset>
                    <legend>Transfer Request</legend>
                    <div>
                        <label for="fromAccount">From Account: </label>
                        <select name="fromAccount" id="fromAccount">
                            <option>Select your account...</option>
<%
                            for (BankAccount account : bankAccounts) {
                                out.println("<option value=\"" + account.getAccountNumber() + "\">" + account.getAccountName()  +  "</option>");
                            }
%>
                        </select>
                    </div>
                    <div>
                        <label for="toAccount">To Account: </label>
                        <select name="toAccount" id="toAccount">
                            <option>Select your account...</option>
<%
                            for (BankAccount account : bankAccounts) {
                                out.println("<option value=\"" + account.getAccountNumber() + "\">" + account.getAccountName()  +  "</option>");
                            }
%>
                        </select>
                    </div>
                    <div>
                        <label for="transferAmount">Amount: $</label>
                        <input type="text" name="transferAmount" id="transferAmount" required="required">
                    </div>
                    <div>
                        <label for="sendOn">Send On: </label>
                        <select name="sendOn" id="sendOn">
<%
                            out.println("<option value=\"" + ScheduleStatus.IMMEDIATE + "\">" + ScheduleStatus.IMMEDIATE.toString()  +  "</option>");
                            out.println("<option value=\"" + ScheduleStatus.FUTURE + "\">" + ScheduleStatus.FUTURE.toString()  +  "</option>");
%>
                        </select>
                    </div>
                    <div>
                        <label for="activeDate">Active Date: </label>
                        <input type="date" name="activeDate" id="activeDate" required="required">
                    </div>
                    <div>
                        <label for="submit"></label>
                        <input type="submit" value="Transfer">
                    </div>
                </fieldset>
            </form>
            </div>
                        
            <br>
            <jsp:include page="transfersList.jsp" />
            <br>
            
        </div> <!-- class="main" -->

        <jsp:include page="stdfooter.jsp" />
        
    </body>
</html>

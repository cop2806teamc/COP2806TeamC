<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="edu.spcollege.tbk.domain.*"%>
<%! String customerName; %>
<%
    customerName = (String) session.getAttribute("customerName");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Titan Bank</title>
    </head>
    
    <body>
        <jsp:include page="stdnav.jsp" />
        
        <div class="main">
            <p>You are logged In.</p>
            <p>Welcome to Titan Bank, <%=customerName%></p>
        </div> <!-- class="main" -->
    
        <jsp:include page="stdfooter.jsp" />
    </body>
</html>

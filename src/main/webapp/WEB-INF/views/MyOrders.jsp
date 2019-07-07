<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="UserHomeMainPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="photos/css/MyOrders.css">
<!------ Include the above in your HEAD tag ---------->
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="list" items="${orderlist}">



 ${list.userOrderId}<br>

${list.grandTotal}<br>
Shipping<br> 
${list.add.apartmentnumber}<br>
${list.add.id}<br>
${list.add.streetname}<br><br><br><br>
</c:forEach>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="UserHomeMainPage.jsp" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sform" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<sform:form modelAttribute="userorder">
grandTotal:${userorder.grandTotal}<br>
date:${userorder.date}<br>

email:${userorder.user.email}<br>
userName:${userorder.user.userName}
</sform:form>
</body>
</html>
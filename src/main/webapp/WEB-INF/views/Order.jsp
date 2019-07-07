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
<body><sform:form action="ordered" method="post" modelAttribute="place">
<h1>Hello !! your Order are</h1>


<sform:input  path="id" cssClass="formStyle" type="hidden" />
 <sform:input  path="apartmentnumber" cssClass="formStyle" placeholder="apartmentnumber" />
  <sform:input  path="streetname" cssClass="formStyle" placeholder="streetname" />
   <sform:input  path="city" cssClass="formStyle" placeholder="city" />
    <sform:input  path="state" cssClass="formStyle" placeholder="state" />
     <sform:input  path="country" cssClass="formStyle" placeholder="country" />
<sform:input  path="zipcode" cssClass="formStyle" placeholder="zipcode" />
<input type="submit" value="submit">
</sform:form>
</body>
</html>
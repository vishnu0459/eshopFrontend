<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sform" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
div{
  max-width: 500px;
  margin: auto;
  background: white;
  padding: 10px;
}
</style>
</head>
<body>
<td><img src="photos/project/${proDetails.productId}.jpg" height="300" width="300"></td>
</br>
ProductName:- ${proDetails.productName}
</br>
ProductDescription:- ${proDetails.productDescription}
</br>
ProductPrice:- ${proDetails.productPrice}
</br>
ProductSupplier:- ${proDetails.productSupplier}
</br>
In Stock:- ${proDetails.stock}
</br>
<sform:form action="cartProductss"  method="post" modelAttribute="cart" >
<sform:input path="cartId" value="${cart.cartId}" type="hidden" />
<sform:input path="productId" value="${proDetails.productId}" type="hidden" />
<sform:input path="productName" value="${proDetails.productName}" type="hidden" />
<sform:input path="productPrice" value="${proDetails.productPrice}" type="hidden" />
<sform:input path="productSupplier" value="${proDetails.productSupplier}" type="hidden" />
Quantity: <sform:input type="number" path="quantity" value="${cart.quantity}" min="1" max="15" />
<c:if test="${proDetails.stock<5}"> 
<p> Hurry up! less stock only  <c:out value="  ${proDetails.stock} "/>  left  </p></c:if>
<input type="submit" value="AddToCart"/>
</sform:form>
</body>
</html>
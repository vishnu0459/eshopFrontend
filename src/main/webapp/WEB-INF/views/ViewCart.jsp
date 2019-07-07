<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ include file="UserHomeMainPage.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<form>
<div class="container">
	<table id="cart" class="table table-hover table-condensed">
	
    				<thead>
						<tr>
							<th style="width:50%">Product</th>
							<th style="width:10%">Price</th>
							<th style="width:8%">Quantity</th>
							<th style="width:22%" class="text-center">Subtotal</th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<c:set var="grandPrice" value="${0}"></c:set>
						<c:forEach var="c" items="${cartlist}">
						
						
					<tbody>
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs">
									<img alt="abc" src="photos/project/${c.productId}.jpg"  class="img-responsive"/></div>
									<div class="col-sm-10">
										
										<p>${c.productDescription} <br>${c.productName} <br>${c.productSupplier}</p>
									</div>
								</div>
							</td>
							<td data-th="Price">${c.productPrice}</td>
							<td data-th="Quantity">
								<input type="number" class="form-control text-center" value="${c.quantity}">
							</td>
							<td data-th="Subtotal" class="text-center">${c.cartTotalPrice}</td>
							<c:set var="grandPrice" value="${grandPrice+c.cartTotalPrice}"></c:set>
							
							<td ><a href="#" class="actions" data-th="">
								<button class="btn btn-info btn-sm"><i class="fa fa-refresh"></i></button></a>
							<a href="editCartProduct?cart_id=${c.cartId}" class="actions" data-th="">
								edit</a>
								<a href="deleteCartProduct?cart_id=${c.cartId}" class="actions" data-th="">
								delete</a>							
							</td>
							
						</tr>
					</tbody>
					</c:forEach>
					<tfoot>
					<tr class="visible-xs">
							
						</tr>
					
						
						<tr class="visible-xs">
						
						
							<td class="text-center"><strong>Total 1.99</strong></td>
					
						</tr>
						
						<tr>
							<td><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center"><strong>Total ${grandPrice}.00</strong></td>
							<td><a href="placeorder" class="btn btn-success btn-block">PlaceOrder <i class="fa fa-angle-right"></i></a></td>
						
						</tr>
					</tfoot>
					
				</table>
</div>
 </form>
</body>



</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
	integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopAdd.css" type="text/css">

<title>Products</title>

</head>
<body>
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />

		
	<!-- shopping product -->
	<div class="container">
		<div class="contorl  my-2 d-flex justify-content-end">
			<a href='products/add'>新增商品</a>
			<a href='showUpdate'>編輯刪除商品</a>
			<button type="button" class="btn btn-outline-light letter-spacing"
				data-toggle="modal" data-target="#cart">
				<i class="fa fa-shopping-cart"></i><span class="badge total-count "></span>
			</button>
			<button class="clear-cart btn btn-outline-danger ml-2">
				<i class="far fa-trash-alt"></i>
			</button>
		</div>

	
		<div class="row">
		<!-- product card -->
			<c:forEach var="product" items="${products}">
				<div class="col-md-3 col-sm-6">
				<div class="product-grid">
					<div class="product-image">
						<a href="<spring:url value='/comm/product?id=${product.bidId}' />"> <img class="pic-1" src="<c:url value='comm/picture/${product.bidId}' />"  /> <img
							class="pic-2" src="" />
						</a>
					</div>
					<div class="product-content">
						<h3 class="title">
							<a href="#">${product.bidItemName}</a>
						</h3>
						<div class="price">
							價格:${product.bidPrice}
							<!-- <span>$1000</span> -->
						</div>
						<a data-name="${product.bidItemName}" data-price="${product.bidPrice}" class="add-to-cart"
							href="">+ Add To Cart</a>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		
</div>
	
<!-- modale_shopping window -->

	<div class="modal fade" id="cart" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Cart</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table class="show-cart table">

					</table>
					<div>
						Total price: $<span class="total-cart"></span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<a href="./shopCheckout.jsp" type="button" class="btn btn-primary">Order now</a>
				</div>
			</div>
		</div>
	</div>
	
	<!-- footer -->
	<div class="footer-bottom  bg-dark text-light">
		<div class="container-fluid">
			<p class="pull-left">Copyright@ 2020 by Car98 Group</p>
		</div>
	</div>
	
	
	
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/javascript/shopAdd.js"></script>
</body>
</html>


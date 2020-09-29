<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
		integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopAdd.css" type="text/css">

	<title>Products</title>

</head>

<body>
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />


	<!-- shopping product -->
	<div class="container">
		<div class="contorl  my-2 d-flex justify-content-end">
			<FONT color='red' size='-1'>
				購物車內有
				<c:out value="${ShoppingCart.itemNumber}" default="0" />項商品
				金額小計(OK):
				<c:out value="${ShoppingCart.subtotal}" default="0" /> 元
			</FONT>
			<!-- <a href='products/add'>新增商品</a>
			<a href='showUpdate'>編輯刪除商品</a> -->
			<button type="button" class="btn btn-outline-light letter-spacing" 
				onclick="location.href='/Car98-mvc/comm/showCartContent'">
				<i class="fa fa-shopping-cart"></i><span class="badge total-count "></span>
			</button>
			<button class="clear-cart btn btn-outline-danger ml-2"
				onclick="location.href='/Car98-mvc/comm/removeShoppingCart'">
				<i class="far fa-trash-alt"></i>
			</button>
		</div>


		<div class="row">
			<!-- product card -->
			<c:forEach var="product" items="${products}">
				<div class="col-md-3 col-sm-6">
					<div class="product-grid">
						<div class="product-image">
							<a href="<spring:url value='/comm/product?id=${product.value.bidId}' />"> <img class="pic-1"
									src="<c:url value='comm/picture/${product.value.bidId}' />" /> <img class="pic-2" />
							</a>
						</div>
						<div class="product-content">
							<h3 class="title">
								<a href="#">${product.value.bidItemName}</a>
							</h3>
							<div class="price">
								價格:${product.value.bidPrice}
							</div>
							<!-- <button type="button" class="btn btn-primary" data-toggle="modal"
								data-target="#proModal">
								more
							</button> -->
							<FORM action="<c:url value='BuyBid.do' />" method="POST">
								<input name='qty' type="hidden" value='1' />
								<Input type='hidden' name='bidId' value='${product.value.bidId}'>
								<Input type='submit' value='加入購物車'>
								<!-- <a data-name="${product.value.bidItemName}" data-price="${product.value.bidPrice}"
									class="add-to-cart" href="">+ Add To Cart</a> -->
							</FORM>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>

	</div>

	<!-- modale_shopping window -->

	<div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Cart</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table class="show-cart table">
						<c:forEach varStatus="vs" var="anEntry" items="${ShoppingCart.content}">
							<div>
								<div>${anEntry.value.bidName}</div>
								<form action="<c:url value='BuyBid.do' />" method="POST">
									<input name='qty' type="number" value='${anEntry.value.quantity}' min='1'
										max="10" />
									<Input type='hidden' name='bidId' value='${anEntry.value.bidId}'>
									<Input type='submit' value='加入購物車'>
								</form>
							</div>
						</c:forEach>
					</table>
					<div>
						Total price: $
						<c:out value="${ShoppingCart.subtotal}" default="0" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					<a href="<c:url value='/comm/checkout' />" type="button" class="btn btn-primary">Order now</a>
				</div>
			</div>
		</div>
	</div>
	<!-- modale_product window -->
	<!-- <div class="modal fade bd-example-modal-lg" id="proModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg"" role="document">
		  <div class="modal-content">
			<div class="modal-header">
			  <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
			  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			  </button>
			</div>
			<div class="modal-body">
			    <div class="row justify-content-center align-items-center row-width mx-auto">
					<div class="col-md-6">
						<img width='300' height='300' src="<c:url value='comm/picture/${product.bidId}'/>" />
					</div>
					<div class="col-md-6">
						<div>
							<h2>產品資料</h2>
							<h3>${product.bidItemName}</h3>
							<p>賣家ID: ${product.memberBean.name}</p>
							<p>單價: ${product.bidPrice}</p>
							<p>商品分類: ${product.bidItemBean.bidCategory}</p>
							<p>上架時間: ${product.bidTime}</p>
							<p>商品內容: ${product.bidFormat}</p>
							<p>
								<strong>商品編號: </strong> <span class='label label-warning'>
									${product.bidId} </span>
							</p>
						</div>
						<div>
							<p>
							<FORM action="<c:url value='BuyBid.do' />" method="POST">
								<input name='qty' type="hidden" value='1' />
								<Input type='hidden' name='bidId' value='${product.bidId}'>
								<Input type='submit' value='加入購物車'>
								<a href='#' class='btn btn-warning btn-large'>
									<span class='glyphicon-shopping-cart glyphicon'>
									</span>加入購物車
								</a> 
							</FORM>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			  <button type="button" class="btn btn-primary">Save changes</button>
			</div>
		  </div>
		</div>
	  </div> -->
	
	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp" />





	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/javascript/shopAdd.js"></script>
</body>

</html>
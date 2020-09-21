<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Product</title>
</head>
<body>
	<section>
		<div>
			<div class="container" style="text-align: center">
				<h2>產品資料</h2>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
		<img width='300' height='300' 
                     src="<c:url value='comm/picture/${product.bidId}'/>" />
			<div class="col-md-5">
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
				<p>
					<a href="<spring:url value='/comm/products' />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span>返回
					</a> <a href='#' class='btn btn-warning btn-large'> <span
						class='glyphicon-shopping-cart glyphicon'></span>加入購物車
					</a>
				</p>
			</div>
		</div>
	</section>
	<div align='center'>
		<a href="<c:url value='/'/>">回首頁</a>
	</div>
</body>
</html>

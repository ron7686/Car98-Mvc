<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
    .container-height{
    height:500px;
}
.row-width{
    width: 100%;
}

body{
      background-image: url(../image/Desktop.png);
      background-position: center;
      background-attachment: fixed;
      background-repeat:no-repeat;
      background-size: cover;
    }
</style>
<title>Product</title>
</head>

<body>
<!-- navbar -->
<jsp:include page="/fragment/topIndex.jsp" />
<div class="container d-flex text-white container-height">
    <div class="row justify-content-center align-items-center row-width mx-auto">
        <div class="col-md-6">
            <img width='300' height='300' 
                         src="<c:url value='comm/picture/${product.bidId}'/>" />
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
                <a href="<spring:url value='/comm/products' />" class="btn btn-default">
                    <span class="glyphicon-hand-left glyphicon text-white">返回</span>
                </a> <a href='#' class='btn btn-warning btn-large'> <span
                    class='glyphicon-shopping-cart glyphicon'></span>加入購物車
                </a>
            </p>
            </div>
        </div>
    </div>
</div>
<div align='center'>
    <a href="<c:url value='/'/>">回首頁</a>
</div>
</body>
</html>
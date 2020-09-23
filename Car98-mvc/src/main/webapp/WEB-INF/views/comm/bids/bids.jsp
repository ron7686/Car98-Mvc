<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript'
	src='${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js'></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
	body{
		background-color: rgba(236, 234, 234, 0.5);;
}
</style>
<title></title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">購物後台</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		  <div class="navbar-nav">
			<a class="nav-link " href="<c:url value='/'/>">首頁</a>
			<a class="nav-link active" href="<c:url value='/comm/showUpdate'/>">商品資料</a>
			<a class="nav-link" href="<c:url value='/comm/products/add'/>">新增商品資料</a>
			<a class="nav-link" href="#">商品編輯</a>
		  </div>
		</div>
	  </nav> 
	<div align='center'>
		<h3>商品資料</h3>
		<a href='products/add'>新增商品資料</a> &nbsp;&nbsp;&nbsp;<a
			href="<c:url value='/'/> ">回首頁</a>
		<hr>
		<form method='POST'>
			<input type='hidden' name='_method' value='DELETE'>
		</form>

		<c:choose>
			<c:when test="${empty bids}">
	    沒有任何商品資料<br>
			</c:when>
			<c:otherwise>
				<div class="table-responsive-lg table-hover">
				<table class="table table-striped" border=1 cellpadding="3" cellspacing="1">
					<thead>
					<tr>
						<th scope="col" width='56'>編號</th>
						<th scope="col" width='140'>品名</th>
						<th scope="col" width='180'>內容</th>
						<th scope="col" width='130'>價格</th>
						<th scope="col" width='64'>數量</th>
						<th scope="col" width='56'>上架日期</th>
						<th scope="col" width='56'>照片</th>
						<th scope="col" colspan='3' width='72'>資料維護</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var='bid' items='${bids}'>
						<tr>
							<td style="text-align: center">${bid.bidId}</td>
							<td style="text-align: center">${bid.bidItemName}</td>
							<td style="text-align: right">${bid.bidFormat}</td>
							<td style="text-align: center">${bid.bidPrice}</td>
							<td style="text-align: center">${bid.bidStock}</td>
							<td style="text-align: center">${bid.bidTime}</td>
							<td style="text-align: center">${bid.bidItemBean.bidCategory}</td>
							<td><img width='60' height='72'
								src="<c:url value='comm/picture/${bid.bidId}'/>"/></td>
							<td><a
								href="${pageContext.request.contextPath}/comm/products/add/${bid.bidId}">編輯</a></td>
							<td><a class='deletelink'
								href="${pageContext.request.contextPath}/comm/products/add/${bid.bidId}">刪除</a>
							</td>
						</tr>
					</c:forEach>
				   </tbody>
				</table>
			</div>
				<a href="<spring:url value='/comm/products' />"
					class="btn btn-default"> <span
					class="glyphicon-hand-left glyphicon"></span>返回
				</a>
			</c:otherwise>
		</c:choose>
		<hr>
	</div>
	<script type='text/javascript'>
		$(document).ready(function() {
			$('.deletelink').click(function() {
				if (confirm('確定刪除此筆紀錄? ')) {
					var href = $(this).attr('href');
					$('form').attr('action', href).submit();
				}
				return false;

			});
		})
	</script>
</body>
</html>
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
<title></title>
</head>
<body>
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
				<table border='1' cellpadding="3" cellspacing="1">
					<tr>
						<th width='56'>編號</th>
						<th width='140'>品名</th>
						<th width='180'>內容</th>
						<th width='130'>價格</th>
						<th width='64'>數量</th>
						<th width='56'>上架日期</th>
						<th width='56'>照片</th>
						<th colspan='2' width='72'>資料維護</th>
					</tr>
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
				</table>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type='text/javascript' src='${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js'></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<style>
		body {
			background-color: rgba(236, 234, 234, 0.5);
			;
		}

		.footer-bottom {
			margin-top: 1em;
			padding-top: 10px;
			padding-bottom: 5px;

		}

		.footer-bottom p.pull-left {
			padding-top: 6px;
			font-size: 0.5em;
			display: flex;
			align-items: center;
			justify-content: center;
		}
		.container-height {
			height: 100vh;
		}
	</style>
	<title></title>
</head>

<body>
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />
	<div class="container-height" align='center '>
		<h3>訂單清單</h3>

		<form method='POST'>
			<input type='hidden' name='_method' value='DELETE'>
		</form>

		<c:choose>
			<c:when test="${empty memberOrders}">
				沒有任何商品資料<br>
			</c:when>
			<c:otherwise>
				<div class="table-responsive-lg table-hover">
					<table class="table table-striped" border=1 cellpadding="3" cellspacing="1">
						<thead>
							<tr>
								<th scope="col" width='200'>訂單編號</th>
								<th scope="col" width='320'>訂購日期</th>
								<th scope="col" width='180'>總金額</th>
								<th scope="col" width='360'>送貨地址</th>
								<th scope="col" colspan='3' width='72'>資料維護</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var='memOrders' items='${memberOrders}'>
								<tr>
									<td style="text-align: center">${memOrders.orderNo}</td>
									<td style="text-align: center">${memOrders.buyDay}</td>
									<td style="text-align: right">${memOrders.totalPrice}</td>
									<td style="text-align: center">${memOrders.address}</td>
									<td>
										<a class="btn btn-primary"
											href="${pageContext.request.contextPath}/comm/ProcessOrder/${memOrders.orderNo}">
											<i class="fa fa-cog"></i>明細</a>
									</td>
									<td>
										<a class="deletelink btn btn-danger"
											href="${pageContext.request.contextPath}/comm/ProcessOrder/${memOrders.orderNo}">
											<i class="fa fa-trash-alt "></i>刪除</a>

									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<a href="<spring:url value='/comm/products' />" class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span>返回
				</a>
			</c:otherwise>
		</c:choose>
		<hr>
	</div>
	<script type='text/javascript'>
		$(document).ready(function () {
			$('.deletelink').click(function () {
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
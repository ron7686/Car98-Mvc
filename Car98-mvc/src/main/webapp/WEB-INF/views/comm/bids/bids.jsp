<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type='text/javascript' src='${pageContext.request.contextPath}/javascript/jquery-3.5.1.min.js'></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
		integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous" />
	<style>
		body {
			background-color: rgba(236, 234, 234, 0.5);
			height: auto;
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
	</style>
	<title></title>
</head>

<body>
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />
	<!-- <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">購物後台</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
			aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
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
	</nav> -->
	<div align='center'>
		<h3>商品資料</h3>
		<!-- <a href='products/add'>新增商品資料</a> &nbsp;&nbsp;&nbsp;<a href="<c:url value='/'/> ">回首頁</a>
		<hr> -->
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
								<th scope="col" width='70'>編號</th>
								<th scope="col" width='140'>品名</th>
								<th scope="col" width='130'>價格</th>
								<th scope="col" width='64'>數量</th>
								<th scope="col" width='200' align='center'>上架日期</th>
								<th scope="col" width='200'>商品種類</th>
								<th scope="col" width='100'>照片</th>
								<th scope="col" colspan='3' width='72'>資料維護</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var='bid' items='${bids}'>
								<tr>
									<td style="text-align: center">${bid.bidId}</td>
									<td style="text-align: center">${bid.bidItemName}</td>
									<td style="text-align: center">${bid.bidPrice}</td>
									<td style="text-align: center">${bid.bidStock}</td>
									<td style="text-align: center">${bid.bidTime}</td>
									<td style="text-align: center">${bid.bidItemBean.bidCategory}</td>
									<td><img width='60' height='72' src="<c:url value='comm/picture/${bid.bidId}'/>" />
									</td>
									<td>
										<a class="btn btn-primary"
											href="${pageContext.request.contextPath}/comm/products/add/${bid.bidId}">
											<i class="fa fa-cog"></i> 編輯</a>
									</td>
									<td>
										<a class="deletelink btn btn-danger"
											href="${pageContext.request.contextPath}/comm/products/add/${bid.bidId}">
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
</body>

</html>
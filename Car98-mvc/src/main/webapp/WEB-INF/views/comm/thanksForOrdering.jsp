<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.container-height {
	height: 100vh;
}

.row-width {
	width: 100%;
}

body {
	background-image: url(../image/Desktop.png);
	background-position: center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: cover;
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
<title>Car98 購物</title>
</head>

<body>
	<c:set var="funcName" value="END" scope="session" />
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />


	<section class="py-5 container-height">
		<div class="container">
			<div class="h1 text-center mb-3 text-white">
			親愛的會員 ${LoginOK.name} 您好： <BR>
			結帳完成</div>
			<div class="row justify-content-center">
				<div class="col-md-8">
					<h3 class="text-center mb-3 text-white">購物記錄</h3>
					<!--動態新增區域 -->
					<table class="table">

					</table>
					<h3 class="text-center mb-3 text-white">個人資訊</h3>
					<!--動態新增區域 -->
					<table class="table">

					</table>
				</div>
			</div>
		</div>
	</section>

	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp" />

</body>

</html>
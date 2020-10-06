<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car方便</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
	integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc"
	crossorigin="anonymous" />
<style>
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

.bg-cover {
	background-size: cover;
}

.dropdown-item {
	font-size: 30px;
}

body {
	background-image: url(${pageContext.request.contextPath}/image/Desktop.png);
	background-position: center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<!-- nav -->
	<jsp:include page="/fragment/topIndex.jsp" />

	<form:form method='POST' modelAttribute="fuel" class='form-horizontal'
		enctype="multipart/form-data">
		<section class="text-white" style="border-radius: 6px;">
			<div class=" container text-center my-5">
				<div class="" style="background-color: rgba(27, 27, 27, 0.2);">
					<div class="row justify-content-center ailgn-items-center">
						<div class="col-12 col-md-6">
							<div class="h3 mt-5">加油記錄</div>
							<div class="form-check form-check-inline mb-3">
								<form:select path="fuelPriceBean.typeNo" class="custom-select">
									<form:option value="-1" label="請挑選" />
									<form:options items="${typeList}" />
								</form:select>
							</div>
							<div>
								<label for="gallon">本次加油</label>
								<form:input type="text" path="gallon" class="form-control"
									id="liter" placeholder="公升" />
								<label for="mileage">行駛里程</label>
								<form:input type="text" path="mileage" id="liter_total"
									class="form-control" placeholder="里程數" />
								<label for="time">日期</label>
								<form:input type="date" path="time" class="form-control"
									placeholder="日期" />
							</div>
							<div class="form-group">
								<div class='col-lg-offset-2 col-sm-10'>
									<input id="btnAdd" type='submit' class='btn btn-info'
										value="送出" />
								</div>
							</div>
							<div class="col-12 col-md-6">
								<form action="">
									<table id="message_area" class="">
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</form:form>

	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp" />

</body>
</html>
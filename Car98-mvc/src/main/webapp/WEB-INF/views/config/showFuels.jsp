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
	margin-bottom: 50px;
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
	background-image: url(../image/Desktop.png);
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
	
	<c:forEach var='fuels' items='${fuels}'>
		<tr>
			<td style="text-align: center">${fuels.fuelId}</td>
			<td style="text-align: center">${fuels.gallon}</td>
			<td style="text-align: center">${fuels.price}</td>
			<td style="text-align: center">${fuels.time}</td>
			<td style="text-align: center">${fuels.fuelPriceBean.typeNo}</td>
		</tr>
	</c:forEach>

	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp" />

</body>
</html>
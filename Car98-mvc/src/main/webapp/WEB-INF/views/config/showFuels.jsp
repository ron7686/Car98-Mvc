<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var='fuels' items='${fuel}'>
		<tr>
			<td style="text-align: center">${fuels.fuelId}</td>
			<td style="text-align: center">${fuels.gallon}</td>
			<td style="text-align: center">${fuels.price}</td>
			<td style="text-align: center">${fuels.time}</td>
			<td style="text-align: center">${fuels.fuelPriceBean.typeNo}</td>
		</tr>
	</c:forEach>
</body>
</html>
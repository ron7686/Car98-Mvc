<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<nav class="navbar  navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="<c:url value='/' />"><img
			src="${pageContext.servletContext.contextPath}/image/car98logo.png" width="50" height="50" alt=""></a>
	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
		data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
		<ul class="navbar-nav ml-auto">
<<<<<<< HEAD
			<li class="nav-item dropdown"><a class="nav-link"
					href="${pageContext.request.contextPath}/searchresource/SearchResource.jsp"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Car好康 </a>
			</li>
			<li class="nav-item dropdown"><a class="nav-link" href="/carEasy.html" id="navbarDropdownMenuLink"
					role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Car方便 </a>
=======
			<li class="nav-item"><a class="nav-link" href="<c:url value='searchresource' /> "> Car好康 </a>
				</li>
			<li class="nav-item dropdown"><a
				class="nav-link" href="/carEasy.html"
				id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Car方便 </a>
>>>>>>> 657731c7797bd749bb03bec295752e2c0b9ee61f
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="/carEasy.html">加油紀錄</a> <a class="dropdown-item" href="#">保養紀錄</a> <a
						class="dropdown-item" href="#">保險紀錄</a>
				</div>
			</li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/carRent">Car租車</a></li>
			<li class="nav-item dropdown"><a class="nav-link" href="#" id="navbarDropdownMenuLink" role="button"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Car帳單 </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="https://www.mvdis.gov.tw/m3-emv-vil/vil/penaltyQueryPay"
						target="_blank">罰單查詢</a> <a class="dropdown-item" href="https://parkingfee.pma.gov.taipei/"
						target="_blank">停車費查詢</a>
				</div>
			</li>
			<li class="nav-item dropdown"><a class="nav-link" href="<c:url value='/comm/products'/>"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Car好買 </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="<c:url value='/comm/products'/>" >拍賣</a> 
					<a class="dropdown-item" href="<c:url value='/comm/products/add'/>" >新增商品</a> 
					<a class="dropdown-item" href="<c:url value='/comm/showUpdate'/>" >編輯商品</a>
					<a class="dropdown-item" href="<c:url value='/comm/orderList'/>" >編輯訂單</a>
				</div>
			</li>
			<!-- <li class="nav-item"><a class="nav-link" href="<c:url value='/comm/products' />">Car好買</a></li> -->
			<li class="nav-item"><a class="nav-link" href="<c:url value='/forum/talktop.do' />">Car論壇</a></li>

			<c:if test="${empty LoginOK}">
				<li class="nav-item active"><a class="nav-link" href="<c:url value='/login' />">登入
						<span class="sr-only">(current)</span>

					</a></li>
			</c:if>
			<c:if test="${ ! empty LoginOK }">
				<li class="nav-item active"><a class="nav-link" href="<c:url value='/logout' />">登出 <span
							class="sr-only">(current)</span>
					</a></li>

			</c:if>
		</ul>
	</div>

	<c:if test="${! empty LoginOK }">
		<a class="navbar-brand" href="<c:url value='/management' />"><img
				style="width: 50px; height: 50px; border-radius: 50%;"
				src='${pageContext.request.contextPath}/init/getMemberImage?id=${LoginOK.memId}'>
		</a>

	</c:if>


</nav>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/css2?family=Sansita+Swashed:wght@300&display=swap" rel="stylesheet">
<style>
*{
font-family: 'Sansita Swashed', cursive;
font-weight:bolder;
}
.ul{
margin:0 auto;
}
.logout{
margin-right:auto;
}

</style>
<nav class="navbar  navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="<c:url value='/' />"><img
			src="${pageContext.servletContext.contextPath}/image/car98logo.png" width="60" height="60" alt=""></a>
			
	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
		data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon">
		</span>
	</button>
	<div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
		<ul class="navbar-nav ul">

			<li class="nav-item"><a class="nav-link" style="font-size:25px;" href="<c:url value='searchresource' /> "><i> Car好康 </i></a>
				</li>
			<li class="nav-item dropdown">
				<a class="nav-link" style="font-size:25px;" href="<c:url value='/config/fuels'/>"
				id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" 
				aria-expanded="false"><i> Car方便 </i></a>
				
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/config/fuels'/>"><i>加油紀錄</i></a>
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/config/fuels/add'/>"><i>新增加油</i></a>
					<a class="dropdown-item" style="font-size:20px;" href="#"><i>保養紀錄</i></a>
				</div>
			</li>
			
			<li class="nav-item"><a class="nav-link" style="font-size:25px;" href="${pageContext.request.contextPath}/carRent"><i>Car租車</i></a></li>
			<li class="nav-item dropdown"><a class="nav-link" style="font-size:25px;" href="#" id="navbarDropdownMenuLink" role="button"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i>Car帳單</i></a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" style="font-size:20px;" href="https://www.mvdis.gov.tw/m3-emv-vil/vil/penaltyQueryPay"
						target="_blank"><i>罰單查詢</i></a> <a class="dropdown-item" style="font-size:20px;" href="https://parkingfee.pma.gov.taipei/"
						target="_blank"><i>停車費查詢</i></a>
				</div>
			</li>
			<li class="nav-item dropdown">
					<a class="nav-link" style="font-size:25px;" href="<c:url value='/comm/products'/>"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"><i>Car好買</i></a>
					
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/comm/products'/>" ><i>拍賣</i></a> 
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/comm/products/add'/>" ><i>新增商品</i></a> 
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/comm/showUpdate'/>" ><i>編輯商品</i></a>
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/comm/orderList'/>" ><i>編輯訂單</i></a>
				</div>
			</li>
			<!-- <li class="nav-item"><a class="nav-link" href="<c:url value='/comm/products' />">Car好買</a></li> -->
			<li class="nav-item"><a class="nav-link" style="font-size:25px;" href="<c:url value='/forum/talktop.do' />"><i>Car論壇</i></a></li>

			<c:if test="${empty LoginOK}">
				<li class="nav-item active ml-auto"><a class="nav-link" style="font-size:25px;" href="<c:url value='/login' />"><i>登入</i>
						<span class="sr-only">(current)</span>

					</a></li>
			</c:if>
			<c:if test="${LoginOK.levels == 1}">
				<li class="nav-item active"><a class="nav-link" href="<c:url value='/userManager' />">管理員 <span
							class="sr-only">(current)</span>
					</a></li>
			</c:if>
		</ul>
	</div>

			<c:if test="${ ! empty LoginOK }">
				<a class="nav-link logout " style="font-size:25px;color:white;" href="<c:url value='/logout' />"><i>登出</i><span
							class="sr-only">(current)</span>
					</a>

			</c:if>
	<c:if test="${! empty LoginOK }">
		<a class="navbar-brand" href="<c:url value='/management' />"><img
				style="width: 50px; height: 50px; border-radius: 50%;"
				src='${pageContext.request.contextPath}/init/getMemberImage?id=${LoginOK.memId}'>
		</a>

	</c:if>


</nav>
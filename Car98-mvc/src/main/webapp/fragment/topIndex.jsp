<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
<style>
*{
font-family: 'Roboto', sans-serif;
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
			src="${pageContext.servletContext.contextPath}/image/car98logo.png" width="60" height="60" style=""alt=""></a>
			
	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
		data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon">
		</span>
	</button>
	<div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
		<ul class="navbar-nav ul">


			<li class="nav-item"><a class="nav-link" style="font-size:25px;" href="<c:url value='/car98Member' />">關於我們</a></li>
			<li class="nav-item"><a class="nav-link" style="font-size:25px;" href="<c:url value='/searchresource/map' /> "> Car好康</a>

				</li>
			<li class="nav-item dropdown">
				<a class="nav-link" style="font-size:25px;" href="<c:url value='/config/fuels'/>"
				id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" 
				aria-expanded="false">Car方便</a>
				
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/config/fuels'/>">加油紀錄</a>
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/config/fuels/add'/>">新增加油</a>
				</div>
			</li>
			
			<li class="nav-item"><a class="nav-link" style="font-size:25px;" href="${pageContext.request.contextPath}/carRent">Car租車</a></li>
			<li class="nav-item dropdown"><a class="nav-link" style="font-size:25px;" href="#" id="navbarDropdownMenuLink" role="button"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Car帳單</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" style="font-size:20px;" href="https://www.mvdis.gov.tw/m3-emv-vil/vil/penaltyQueryPay"
						target="_blank">罰單查詢</a> <a class="dropdown-item" style="font-size:20px;" href="https://parkingfee.pma.gov.taipei/"
						target="_blank">停車費查詢</a>
				</div>
			</li>
			<li class="nav-item dropdown">
					<a class="nav-link" style="font-size:25px;" href="<c:url value='/comm/products'/>"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Car好買</a>
					
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/comm/products'/>" >拍賣</a> 
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/comm/products/add'/>" >新增商品</a> 
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/comm/showUpdate'/>" >編輯商品</a>
					<a class="dropdown-item" style="font-size:20px;" href="<c:url value='/comm/orderList'/>" >編輯訂單</a>
				</div>
			</li>
			<!-- <li class="nav-item"><a class="nav-link" href="<c:url value='/comm/products' />">Car好買</a></li> -->
			<li class="nav-item"><a class="nav-link" style="font-size:25px;" href="<c:url value='/forum/talktop.do' />">Car論壇</a></li>

			<li class="nav-item"><a class="nav-link" style="font-size:25px;" href="http://localhost:8080/Car98-mvc/chatRoom.html">聊天室</a></li>
			<c:if test="${empty LoginOK}">
				<li class="nav-item active ml-auto"><a class="nav-link" style="font-size:25px;" href="<c:url value='/login' />"><i class="fas fa-sign-in-alt">登入</i>
						<span class="sr-only">(current)</span>

					</a></li>
			</c:if>
			<c:if test="${LoginOK.levels == 1}">
					<li class="nav-item"><a class="nav-link" style="font-size:25px;" href="<c:url value='/userManager' />">管理介面</a></li>
			</c:if>
		</ul>
	</div>

			<c:if test="${ ! empty LoginOK }">
				<a href="<c:url value='#' />" class="m-2 logout" onclick="logout()">
					<i class="fas fa-sign-in-alt text-white mt-2 mr-2" style="font-size:30px;"></i>
				</a>
			</c:if>
	<c:if test="${! empty LoginOK }">
		<a class="navbar-brand" href="<c:url value='/management' />"><img
				style="width: 50px; height: 50px; object-fit:cover; border-radius: 50%;"
				src='${pageContext.request.contextPath}/init/getMemberImage?id=${LoginOK.memId}'>
		</a>

	</c:if>
	<!-- 訊息畫面_Modal -->
<div class="modal fade" id="messages" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="exampleModalLabel" style="color: #0090d3"><b>訊息提示</b></h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
			<div class="text-center">
				<font color="red">${InsertOK} ${FlashMSG_farewell} ${sessionScope.timeOut}</font>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
        <a href="<c:url value='/' />"><button type="button" class="btn btn-primary">回首頁</button></a>
      </div>
    </div>
  </div>
</div>

	<%-- 新增(含註冊、分店與寵物新增)、登入、登出成功與使用逾時時，顯示提示視窗 --%>
<c:if test="${!empty InsertOK}">
	<script>
		$('#messages').modal('show');
		setTimeout(function() {
            $('#messages').modal('hide') // 3秒後，modal消失。
        }, 3000);
	</script>
</c:if>

<c:if test="${!empty FlashMSG_farewell}">
	<script>
		$('#messages').modal('show');
		setTimeout(function() {
            $('#messages').modal('hide') // 3秒後，modal消失。
        }, 3000);
	</script>
</c:if>

<script> 
	function logout(){ 
	    if (confirm("您確定要登出嗎？")){ 
	    	$('.logout').attr('href', '${pageContext.request.contextPath}/logout')
	    } 
	} 
</script>
</nav>
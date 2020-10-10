<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style type="text/css">
.control-label {
	color:white;
	font-size:20px;
}

fieldset {
	border: 5px outset rgb(152, 152, 150);
	width: 400px;
	margin: auto;
	border-radius: 10px;
	box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.7);
}

body {
	background-image: url(${pageContext.request.contextPath}/image/ado.jpg);
	background-position: center;
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
.error{
	color:red;
}
</style>
<title>Products</title>
</head>

<body>
	<jsp:include page="/fragment/topIndex.jsp" />
	<!-- <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">購物後台</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-link " href="<c:url value='/'/>">首頁</a> <a class="nav-link " href="<c:url value='/comm/showUpdate'/>">商品資料</a>
				<a class="nav-link active" href="<c:url value='/comm/products/add'/>">新增商品資料</a> <a class="nav-link"
					href="#">商品編輯</a>
			</div>
		</div>
	</nav> -->

	<section>
		<div class="container">
			<h1 style="text-align: center;color: white;">上架商品</h1>
		</div>
	</section>
	<hr>
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="bid" class='form-horizontal'
			enctype="multipart/form-data">
			<fieldset>

				<div class="form-group">
					<label class="control-label col-lg-4 col-sm-6" for='bidItemName'>
						品名 </label>
					<div class="col-lg-10">
						<form:input id="bidItemName" path="bidItemName" type='text'
							class='form:input-large' />
						<form:errors path="bidItemName" class='error' />
					</div>
				</div>



				<div class="form-group">
					<label class='control-label col-lg-4 col-sm-6' for="bidItemId">
						類型 </label>
					<div class='col-lg-10'>
						<form:select path="bidItemBean.bidItemId">
							<form:option value="-1" label="請挑選" />
							<form:options items="${categoryList}" />
						</form:select>
						<form:errors path="bidItemBean.bidItemId" class='error'  />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-4 col-sm-6" for='bidPrice'>
						價格 </label>
					<div class="col-lg-10">
						<form:input id="bidPrice" path="bidPrice" type='text'
							class='form:input-large' />
						<form:errors path="bidPrice" class='error'  />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-4 col-sm-6' for="bidStock">
						庫存 </label>
					<div class='col-lg-10'>
						<form:input id="bidStock" path="bidStock" type='text'
							class='form:input-large' />
						<form:errors path="bidStock" class='error' />
					</div>
				</div>
				<div class="form-group">
					<label class='control-label col-lg-4 col-sm-6' for="bidScore">
						評價 </label>
					<div class='col-lg-10'>
						<form:input id="bidScore" path="bidScore" type='hidden' value='10'
							class='form:input-large' />
						<form:errors path="bidScore" class='error' />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-4 col-sm-6' for="bidFormat">
						商品描述 </label>
					<div class='col-lg-10'>
						<form:textarea id="bidFormat" path="bidFormat" cols="45"
							class='form:input-large' rows="5" placeholder="請輸入內容...." />
						<form:errors path="bidFormat" class='error' />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-4 col-sm-6' for="productImage">
						圖片 </label>
					<div class='col-lg-10'>
						<form:input id="productImage" path="productImage" type='file'
							class='form:input-large' />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-6 col-sm-8 "  for='portfolioImage'>
						上傳多張圖片 </label>
					<div class="col-lg-10">
						<form:input path="bidPicBean.picImages" id="pfdPic" type='file' multiple="multiple" class='form:input-large ' /> 
				
					</div>
				</div>

				<div class="form-group">
					<div class='col-lg-offset-2 col-sm-10'>
						<input id="btnAdd" type='submit' class='btn btn-info' value="送出" />
					</div>
				</div>
			</fieldset>
		</form:form>

		<a href="<spring:url value='/comm/products' />"
			class="btn btn-info"> <span
			class="glyphicon-hand-left glyphicon"></span>返回
		</a>

	</section>
	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp" />


</body>

</html>
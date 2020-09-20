<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.css">

<style type="text/css">
fieldset {
	border: 1px solid #000000;
	width: 500px;
	margin: auto;
}

.custom-checkbox {
	margin-left: 20px;
}

.custom-checkbox {
	-webkit-appearance: none;
	background-color: rgba(255, 255, 255, 0.1);
	padding: 8px;
	border-radius: 2px;
	display: inline-block;
	position: relative;
	top: 6px;
}

.custom-checkbox:checked {
	background-color: rgba(17, 97, 237, 1);
}

.custom-checkbox:checked:after {
	content: '\2714';
	font-size: 10px;
	position: absolute;
	top: 1px;
	left: 4px;
	color: #fff;
}

.custom-checkbox:focus {
	outline: none;
}

.radio-size {
	width: 25px;
	height: 25px;
	vertical-align:middle;
}

</style>
<title>Products</title>
</head>
<body class="">
	<jsp:include page="/fragment/topIndex.jsp"></jsp:include>
	<section>
		<div class="container">
			<h1 style="text-align: center">註冊會員</h1>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #000000; background-color: #000000;">
	<section class="container registerbg">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="memberBean"
			class='form-horizontal mx-auto' align="center"
			enctype="multipart/form-data">
			<fieldset>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='name'>
						姓名 </label>
					<div class="col-lg-10 mx-auto">
						<form:input id="name" path="name" type='text'
							class='form:input-large form-control' />
						<form:errors path="name" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='id'> 暱稱
					</label>
					<div class="col-lg-10 mx-auto">
						<form:input id="id" path="id" type='text'
							class='form:input-large form-control' />
						<form:errors path="id" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='email'>
						email </label>
					<div class="col-lg-10 mx-auto">
						<form:input id="email" path="email" type='text'
							class='form:input-large form-control' />
						<form:errors path="email" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="phone">
						電話 </label>
					<div class='col-lg-10 mx-auto'>
						<form:input id="phone" path="phone" type='text'
							class='form:input-large form-control' />
						<form:errors path="phone" />
					</div>
				</div>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="password">
						密碼 </label>
					<div class='col-lg-10 mx-auto'>
						<form:input id="password" path="password" type='password'
							class='form:input-large form-control' />
						<form:errors path="password" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-6 col-lg-6' for="password1">
						確認密碼 </label>
					<div class='col-lg-10 mx-auto'>
						<form:input id="password1" path="password1" type='password'
							class='form:input-large form-control' />
						<form:errors path="password1" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="birth">
						生日 </label>
					<div class='col-lg-10 mx-auto'>
						<form:input id="birth" path="birth" type='date'
							class='form:input-large form-control' />
						<form:errors path="birth" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="sex">
						性別 </label>
					<div class="mx-auto row">
						<div class="col-10 mx-auto">
							<form:radiobutton path="sex" class='radio-size mx-3' value="M"
								label="男" id="gender" />
							<form:radiobutton path="sex" class='radio-size mx-3' value="F"
								label="女" id="gender" />
							<form:errors path="sex" />
						</div>

					</div>
				</div>

				<div class="form-group">
					<div class="mx-auto row">
					<label class='control-label col-lg-5 col-lg-5'
						for="memberMultipartFile"> 上傳大頭貼 </label>
						<div class="col-10">
							<form:input class="" id="memberMultipartFile"
								path="memberMultipartFile" type='file' />
							<p class="mt-3">
								<img id="image" src="">
							</p>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class='col-lg-offset-2 col-lg-10 mx-auto' align="center">
						<input id="btnAdd" type='submit' class='btn btn-primary'
							value="送出" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
	<jsp:include page="/fragment/footer.jsp"></jsp:include>
	<script
		src="${pageContext.request.contextPath}/javascript/registerForm.js"></script>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
	<script
		src="${pageContext.request.contextPath}/javascript/showRegisterImage.js"></script>
</body>
</html>

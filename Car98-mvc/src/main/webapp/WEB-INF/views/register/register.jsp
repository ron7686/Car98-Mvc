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
<style type="text/css">
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}
</style>
<title>Products</title>
</head>
<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">註冊會員</h1>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
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
					<label class='control-label col-lg-2 col-lg-2' for="password1">
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
						<form:input id="birth" path="birth" type='text'
							class='form:input-large form-control' />
						<form:errors path="birth" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="sex">
						性別 </label>
					<div class='col-lg-10 mx-auto'>
						<form:input id="sex" path="sex" type='text'
							class='form:input-large form-control' />
						<form:errors path="sex" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-6 col-lg-6 mx-auto'
						for="memberMultipartFile"> 上傳大頭貼 </label>
					<div class="mx-auto">
						<form:input id="memberMultipartFile" path="memberMultipartFile"
							type='file' />
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
</body>
</html>

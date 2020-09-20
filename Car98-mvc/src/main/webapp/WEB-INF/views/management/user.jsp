
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/all.css">
<style>
input[type="submit"]:disabled {
	background-color: red;
}

#carouselExampleFade .carousel-inner .carousel-item img {
	height: 720px;
}

.footer-bottom {
	margin-top: 1em;
	/* border-top: 1px solid #DDDDDD; */
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

#snackbar {
	visibility: hidden;
	min-width: 250px;
	margin-left: -125px;
	background-color: #333;
	color: #fff;
	text-align: center;
	border-radius: 2px;
	padding: 16px;
	position: fixed;
	z-index: 1;
	left: 50%;
	bottom: 30px;
	font-size: 17px;
}

#snackbar.show {
	visibility: visible;
	-webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
	animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@
-webkit-keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
-webkit-keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}

}
@
keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}
}

.radio-size {
	width: 25px;
	height: 25px;
	vertical-align:middle;
}
</style>

<title>會員資料</title>
</head>

<body class="">
	<jsp:include page="/fragment/topIndex.jsp"></jsp:include>
	<section>
		<div class="container">
			<h1 style="text-align: center">會員資料</h1>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #000000; background-color: #000000;">
	<section class="container registerbg">
		<!--    modelAttribute  三個地方要完全一樣  -->
		<form:form method='POST' modelAttribute="memberBean"
			class='form-horizontal mx-auto' align="center"
			enctype="multipart/form-data">
			<fieldset>

				<div class="form-group">

					<label for="exampleFormControlFile1s"> <form:input
							type="file" path="memberMultipartFile" id="image_file"
							style="display: none;" /> <form:errors
							path="memberMultipartFile" /> <img
						style="width: 350px; height: 350px; border-radius: 50%;"
						src='${pageContext.request.contextPath}/init/getMemberImage?id=${LoginOK.memId}'
						id="show_image">
					</label>
					<p class="mt-2">
						<img id="image">
					</p>
				</div>

				<div class="form-group">

					<div class="col-md-4 mt-1 mb-2">
						<label for="validationCustom01">姓名: </label>
						<form:input type="text" path="name" class="form-control"
							name="name" id="validationCustom01" placeholder="姓名"
							required="required" />
						<form:errors path="name" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-4 mt-1 mb-2">
						<label for="validationCustom02">暱稱: </label>
						<form:input type="text" path="id" class="form-control w-auto"
							name="memberId" id="validationCustom02" placeholder="暱稱"
							required="required" />
						<form:errors path="id" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-4 mt-1 mb-2">
						<label for="validationCustom02">手機號碼: </label>
						<form:input type="tel" class="form-control w-auto" path="phone"
							name="phone" id="validationCustom02" placeholder="手機號碼"
							pattern="[0-9]{4}[0-9]{3}[0-9]{3}" required="required" />
						<form:errors path="phone" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-4 mt-1 mb-2">
						<label for="exampleInputEmail1">Email address: </label>
						<form:input type="email" class="form-control"
							id="exampleInputEmail1" path="email" name="email"
							aria-describedby="emailHelp" placeholder="Enter email"
							pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$"
							disabled="true"/>
						<small id="emailHelp" class="form-text text-muted">We'll
							never share your email with anyone else.</small>
						<form:errors path="email" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-6 mb-2">
						<label for="validationCustom03">生日: </label>
<%-- 						<input type="date" class="form-control" value="${LoginOK.birth}" name="birth" disabled="disabled"/> --%>
						<form:input type="date" class="form-control" 
						value="${memberBean.birth}" path="birth" name="birth" disabled="true"/>
						<form:errors path="birth" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="sex">
						性別 </label>
					<div class="mx-auto row">
						<div class="col-10 mx-auto">
							<form:radiobutton path="sex" class='radio-size mx-3' value="M"
								label="男" id="gender"/>
							<form:radiobutton path="sex" class='radio-size mx-3' value="F"
								label="女" id="gender" />
							<form:errors path="sex" />
						</div>

					</div>
				</div>

				<div class="mb-3">
					<button class="btn btn-secondary" id="button" type="submit">確認送出</button>
				</div>
			</fieldset>
		</form:form>
	</section>

	<!-- Button trigger modal -->
	<div class="mt-2 mb-2 needs-validation mx-5 text-white">
		<button type="button" class="btn btn-secondary" data-toggle="modal"
			data-target="#staticBackdrop">修改密碼</button>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">修改密碼</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form:form action="<c:url value='/changePassword' />"
					modelAttribute="memberBean" method="POST" />
					<div class="modal-body">
						<div class="form-group w-50 mx-auto">
							<p class="text-secondary">新密碼</p>
							<input type="password" id="pwdId" class="form-control"
								name="password" required>
							<div class="valid-feedback">符合規則</div>
							<div class="invalid-feedback">需包含大小字母、數字、特殊符號、8個字符</div>
							<font color="red" size="-1">${ErrorMsg.passwordError}</font>
						</div>
						<div class="form-group w-50 mx-auto">
							<p class="text-secondary">確認密碼</p>
							<input type="password" id="cPwdId"
								class="form-control myCpwdClass" value="${param.memberPassword}"
								name="password1" required>
							<!-- 								pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}" -->
							<div id="cPwdValid" class="valid-feedback">密碼相符</div>
							<div id="cPwdInvalid" class="invalid-feedback">密碼不相同</div>
							<font color="red" size="-1">${MsgMap.passwordError}${MsgMap.errorPassword1Empty}</font>
						</div>
					</div>
					<div class="modal-footer justify-content-center">
						<button type="submit" class="btn btn-secondary"
							onclick="myFunction()">送出</button>
						<c:if test="${empty MsgMap}">
							<div id="snackbar">修改成功</div>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- footer -->
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
	<script>
		$("#show_image").click(function(e) {
			document.getElementById("image_file").click();
		});

		$("#image_file").on("change", function(event) {
			const file = event.target.files[0];
			let readFile = new FileReader();
			readFile.readAsDataURL(file);
			readFile.addEventListener("load", function(e) {
				let image = document.getElementById("show_image");
				image.src = this.result;

				// image.width = 500 ;
				image.style.maxWidth = "350px"; //css屬性
				image.style.maxHeight = "350px";
			});
		});
	</script>
	<c:if test="${empty MsgMap}">
		<script>
			function myFunction() {
				var x = document.getElementById("snackbar");
				x.className = "show";
				setTimeout(function() {
					x.className = x.className.replace("show", "");
				}, 3000);
			}
		</script>
	</c:if>
	<c:if test="${!empty MsgMap}">
		<script>
			$('#staticBackdrop').modal('show')
		</script>
	</c:if>
</body>

</html>
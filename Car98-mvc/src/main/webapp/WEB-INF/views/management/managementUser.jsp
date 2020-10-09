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
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link rel="stylesheet" href="<c:url value='/css/all.css'/>">
	<link
	rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
   />
	<style>
	 body{
      background-image: url(/Car98-mvc/image/carLogin.jpg);
      /* background-color:black ; */
      background-position: center;
      background-attachment: fixed;
      background-repeat:no-repeat;
      background-size: cover;
      /* background-image: linear-gradient(to left, #434343 0%, black 100%); */
    }
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

		@-webkit-keyframes fadein {
			from {
				bottom: 0;
				opacity: 0;
			}

			to {
				bottom: 30px;
				opacity: 1;
			}

		}

		@keyframes fadein {
			from {
				bottom: 0;
				opacity: 0;
			}

			to {
				bottom: 30px;
				opacity: 1;
			}

		}

		@-webkit-keyframes fadeout {
			from {
				bottom: 30px;
				opacity: 1;
			}

			to {
				bottom: 0;
				opacity: 0;
			}

		}

		@keyframes fadeout {
			from {
				bottom: 30px;
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
			vertical-align: middle;
		}

		fieldset {
			border: 1px solid #000000;
			width: 600px;
			margin: auto;
		}

		.input-w {
			width: 40%;
		}
		.text {
  border: none;
  width: 100%;
  padding: 10px 20px;
  display: block;
  height: 35px;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.1);
  border: 2px solid rgba(255, 255, 255, 0);
  overflow: hidden;
  margin-top: 15px;
  transition: all 0.5s ease-in-out;
  color: #fff;
}

.text:focus {
  outline: 0;
  border: 2px solid rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  background: rgba(0, 0, 0, 0);
  color: #fff;
}

.text:focus + span {
  opacity: 0.6;
}

input[type="text"],
input[type="date"],
input[type="tel"],
input[type="email"],
input[type="password"] {
  font-family: 'Montserrat', sans-serif;
  color: #fff;
}



input {
  display: inline-block;
  padding-top: 20px;
  font-size: 14px;
}


   label {
  display: inline-block;
  padding-top: 10px;
  padding-left: 5px;
}

.signin {
  background-color: #1161ed;
  color: #FFF;
  width: 100%;
  padding: 10px 20px;
  display: block;
  height: 40px;
  border-radius: 20px;
  margin-top: 30px;
  transition: all 0.5s ease-in-out;
  border: none;
  text-transform: uppercase;
}

.signin:hover {
  background: #4082f5;
  box-shadow: 0px 4px 35px -5px #4082f5;
  cursor: pointer;
}

.signin:focus {
  outline: none;
}

.login {
/*   position: relative; */
/*   height: 560px; */
/*   width: 405px; */
  margin: auto;
  padding: 60px 60px;
  background: linear-gradient(rgba(50, 50, 50, 0.5),rgba(50, 50, 50, 0.5)),url(https://picsum.photos/id/1004/5616/3744) no-repeat   center center;
  background-size: cover;
  box-shadow: 0px 30px 60px -5px #000;
}

.animate__animated .animate__fadeInDown {
  --animate-duration: 4s;
}
label{
	color: #FFF;
}
	</style>

	<title>會員資料</title>
</head>

<body class="">
	<jsp:include page="/fragment/topIndex.jsp"></jsp:include>
	<section>
		<div class="container">
			<h1 style="text-align: center;color:#fff;" >會員資料</h1>
		</div>
	</section>
	<hr style="height: 1px; border: none; color: #000000; background-color: #000000;">
	<section class="container registerbg">
		<!--    modelAttribute  三個地方要完全一樣  -->
		<form:form method='POST' modelAttribute="memberBean" action="/Car98-mvc/managementUser"
			class='form-horizontal mx-auto' align="center"
			enctype="multipart/form-data">
			<fieldset class="mx-auto login animate__animated animate__fadeInDown">
				<div
					class="d-flex justify-content-center align-items-center mt-3 mb-3">
					<label for="exampleFormControlFile1s" class="control-label">
						<form:input type="file" path="memberMultipartFile" id="image_file"
							style="display: none;" /> <form:errors
							path="memberMultipartFile" /> <img
						style="width: 350px; height: 350px; border-radius: 50%;"
						src='${pageContext.request.contextPath}/init/getMemberImage?id=${memId}'
						id="show_image">
					</label>
					<p class="mt-2">
						<img id="image">
					</p>
				</div>

				<div class="form-group">

					<label for="validationCustom01"
						class="control-label col-lg-2 col-lg-2"> 姓名: </label>
					<div class="d-flex justify-content-center align-items-center">
						<form:input type="text" path="name" class="text form-control input-w"
							name="name" id="validationCustom01" placeholder="姓名"
							required="required" />
						<form:errors path="name" />
					</div>
				</div>

				<div class="form-group">
					<label for="validationCustom02"
						class="control-label col-lg-2 col-lg-2"> 暱稱: </label>
					<div class="d-flex justify-content-center align-items-center">
						<form:input type="text" path="id" class="text form-control input-w"
							name="memberId" id="validationCustom02" placeholder="暱稱"
							required="required" />
						<form:errors path="id" />
					</div>
				</div>

				<div class="form-group">
					<label for="validationCustom02"
						class="control-label col-lg-6 col-lg-6"> 手機號碼: </label>
					<div class="d-flex justify-content-center align-items-center">
						<form:input type="tel" class="text form-control input-w" path="phone"
							name="phone" id="validationCustom02" placeholder="手機號碼"
							pattern="[0-9]{4}[0-9]{3}[0-9]{3}" required="required" />
						<form:errors path="phone" />
					</div>
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1"
						class="control-label col-lg-6 col-sm-12"> Email address: </label>
					<div class="d-flex justify-content-center align-items-center">
						<form:input type="email" class="text form-control input-w"
							id="exampleInputEmail1" path="email" name="email"
							aria-describedby="emailHelp" placeholder="Enter email"
							pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$"/>
						<form:errors path="email" />
					</div>
				</div>

				<div class="form-group">
					<label for="validationCustom03"
						class="control-label col-lg-6 col-sm-12"> 生日: </label>
					<div class="d-flex justify-content-center align-items-center">
						<%-- 						<input type="date" class="form-control" value="${LoginOK.birth}" name="birth" disabled="disabled"/> --%>
						<form:input type="date" class="text form-control input-w"
							value="${memberBean.birth}" path="birth" name="birth"/>
						<form:errors path="birth" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="sex">
						性別 </label>
					<div class="mx-auto row">
						<div class="col-10 mx-auto">
							<form:radiobutton path="sex" class='radio-size mx-3' value="M"
								label="男" id="gender1" />
							<form:radiobutton path="sex" class='radio-size mx-3' value="F"
								label="女" id="gender2" />
							<form:errors path="sex" />
						</div>

					</div>
				</div>
				<!-- 隱藏欄位 -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 						<input id='memId' name='memId' type="hidden"> -->
<!-- 					</td> -->
<!-- 				</tr> -->

				<div class="mb-3">
					<button class="signin btn btn-secondary" id="button" type="submit">確認送出</button>
				</div>
			</fieldset>
			<!-- Button trigger modal -->
			<div class="mt-2 mb-2 needs-validation mx-5 text-white">
				<button type="button" class="btn btn-secondary" data-toggle="modal"
					data-target="#staticBackdrop">修改密碼</button>
			</div>
		</form:form>
	</section>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content bg-dark">
				<div class="modal-header">
					<h5 class="modal-title text-white" id="staticBackdropLabel">修改密碼</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<form:form action="changePassword" modelAttribute="memberBean" method="POST">
					<div class="modal-body">
						<div class="form-group w-50 mx-auto">
							<p class="text-white">新密碼</p>
							<form:input path="password" type="password" id="pwdId" class="form-control" value=""
								name="password" required="required"
								pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}" />
							<form:errors path="password" />
						</div>
						<div class="form-group w-50 mx-auto">
							<p class="text-white">確認密碼</p>
							<form:input path="password1" type="password" id="pwdId" class="form-control"
								name="password1" required="required"
								pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}" />
							<form:errors path="password1" />
						</div>
					</div>
					<div class="modal-footer justify-content-center">
						<button type="submit" class="signin btn btn-secondary" onclick="myFunction()">送出</button>
						<c:if test="${empty errors}">
							<div id="snackbar">修改成功</div>
						</c:if>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp"></jsp:include>

	<script src="${pageContext.request.contextPath}/javascript/registerForm.js"></script>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
	<script>
		$("#show_image").click(function (e) {
			document.getElementById("image_file").click();
		});

		$("#image_file").on("change", function (event) {
			const file = event.target.files[0];
			let readFile = new FileReader();
			readFile.readAsDataURL(file);
			readFile.addEventListener("load", function (e) {
				let image = document.getElementById("show_image");
				image.src = this.result;

				// image.width = 500 ;
				image.style.maxWidth = "350px"; //css屬性
				image.style.maxHeight = "350px";
			});
		});
	</script>
	<script>
// 		$(document).ready(function (e) {
// 			// 取得前一頁傳回來的id
// 			let src = location.href;
// 			let n1 = src.length;
// 			let n2 = src.lastIndexOf("/");
// 			let memId = src.substr(n2 + 1, n1 - n2);
// 			$.ajax({
// 				url: 'http://localhost:8080/Car98-mvc/user/' + memId,
// 				type: 'GET',
// 				dataType: 'Json',
// 				success(data) {
// 					console.log(JSON.stringify(data));
// 					// alert(data.name);
// 					$('#memId').val(data.memId);
// 					$('#name').val(data.name);
// 					$('#memberId').val(data.id);
// 					$('#phone').val(data.phone);
// 					$('#email').val(data.email);
// 					$('#birth').val(data.birth);
// 					if (data.sex == "M") {
// 						$("#male").attr("checked", data.sex);
// 					} else {
// 						$("#female").attr("checked", data.sex);
// 					}
// 				}
// 			});
// 			$('#button').click(function (e) {
// 				let name = $("#name").val();
// 				let memId = $('#memId').val();
// 				let id = $('#memberId').val();
// 				let phone = $('#phone').val();
// 				let email = $('#email').val();
// 				let birth = $('#birth').val();
// 				let sex = $(".sex:checked").val();
// 				// e.preventDefault();
// 				let jsonPortfolio = {
// 					"memId": memId,
// 					"name": name,
// 					"id": id,
// 					"phone": phone,
// 					"email": email,
// 					"birth": birth,
// 					"sex": sex,
// 				}
				// alert(JSON.stringify(jsonPortfolio));
// 				$.ajax({
// 					url: 'http://localhost:8080/Car98-mvc/user/' + memId,
// 					type: 'PUT',
// 					contentType: "application/json",
// 					data: JSON.stringify(jsonPortfolio),
// 					dataType: "JSON",
// 					success(data){
// 					},
// 					complete(data) {
// 						alert("導回管理頁面");
// 						window.location.href = "http://localhost:8080/Car98-mvc/userManager";
// 					}
// 				});
// 				alert("送出資料");
// 				window.location.href = "http://localhost:8080/Car98-mvc/userManager";
// 			});
// 		});
	</script>
	<c:if test="${empty errors}">
		<script>
			function myFunction() {
				var x = document.getElementById("snackbar");
				x.className = "show";
				setTimeout(function () {
					x.className = x.className.replace("show", "");
				}, 3000);
			}
		</script>
	</c:if>
	<c:if test="${!empty erros}">
		<script>
			$('#staticBackdrop').modal('show')
		</script>
	</c:if>
</body>

</html>
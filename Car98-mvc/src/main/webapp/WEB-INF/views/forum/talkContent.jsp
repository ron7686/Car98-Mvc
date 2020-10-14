<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CarTalk Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
	integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/talkContent.css">

<style>
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

body {
	background-image: url(${pageContext.servletContext.contextPath}/image/Desktop.png);
	background-position: center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body onload="startTime()">
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />
	<!--Main Area -->
	<section class="my-5">

		<div class="container text-white main">
		<form:form modelAttribute="talkBean"  method="Post" enctype="multipart/form-data">
			<div class="row">
				<div class="col-12">
					<ul class="texttitle">
						<li class="mx-auto"><h2>發表新文章</h2></li>
						<li class="ml-auto"><span id="nowDateTimeSpan"></span></li>
					</ul>
				</div>
				<!-- 分類選項 -->
			 	
			 	<div class="col-12 classification">
					<form:select path="PostType" id="" class="select mt-2">
						<form:option value="討論">討論</form:option>
						<form:option value="分享＆心得">分享＆心得</form:option>
						<form:option value="求助＆問題">求助＆問題</form:option>
						<form:option value="公告">公告</form:option>
					</form:select>
						
					
				</div>
				<!-- 編輯列表 -->
				<div class="col-12  editor-list">

					<select class="font-area mt-2" name="" id="fontsize"
						data-toggle="tooltip" data-placement="bottom" title="字級"
						style="width: 63px">
						<option value="">字體</option>
						<option id="big" value="big">大</option>
						<option id="mid" value="mid">中</option>
						<option id="small" value="small">小</option>
					</select>

					<button type="button" id="Boldbtn" class="btn-light "
						data-toggle="tooltip" data-placement="bottom" title="粗體">
						<i class="fas fa-bold"></i>
					</button>
					<button type="button" id="Ibtn" class="btn-light "
						data-toggle="tooltip" data-placement="bottom" title="斜體">
						<i class="fas fa-italic"></i>
					</button>
					<button type="button" id="Ubtn" class="btn-light "
						data-toggle="tooltip" data-placement="bottom" title="底線">
						<b><u>U</u></b>
					</button>
					



				</div>
				<!-- 內文輸入 -->
				<div class="col-9">

				
						<form:input style="display:none" path="PostID"></form:input>
						<form:input class="title" path="PostTitle" type="text" name="title" style="width: 100%"
							placeholder="請輸入文章標題......." value="${param.title}" />
							<form:errors class="errormsg" path="PostTitle" > </form:errors>
							
						<form:textarea class="mt-2"   path="PostText"
							style="width: 100%" cols="100" rows="20"
							placeholder="請輸入內容......"></form:textarea>
						<form:errors class="errormsg" path="PostText" > </form:errors>
						<button type="button"  class="publish" id="btnpublish">發佈文章</button>
						
					
				</div>
				<!-- 插入附件 -->
				<div class="col-3">
					<ul class="annex ">
						<li>
							
								<div class="form-group ">
									<img src="${pageContext.servletContext.contextPath}/image/view.jpeg" width="50" height="50"> <label
										for="exampleFormControlFile1">插入圖片</label>
										 <form:input type="file" 
										class="form-control-file mt-2" id="btnImage" path="postMultipartFile"
										value="插入圖片" />
								</div>
							
						</li>
						<li>
							<img id="image" src="">
						</li>
					</ul>
				</div>
			</div>
			</form:form>
		</div>
		
	</section>
	<jsp:include page="/fragment/footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	<script>
	$(document).ready(function() {
			$("#btnpublish").on('click', function(e) {
				if (confirm("確定刪除此則留言?")) {
					return true;
				} else {
					return false;
				}
			});




	</script>
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
	<script src="${pageContext.servletContext.contextPath}/javascript/talkContent2.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>
	<script src="http://maps.google.com/maps/api/js"></script>
<script src="${pageContext.servletContext.contextPath}/javascript/talkContent.js"></script>
</body>
</html>
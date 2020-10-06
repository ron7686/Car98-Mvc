<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>MemberTalk Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />
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
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
	integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc"
	crossorigin="anonymous" />
<link rel="stylesheet" href="<%=path%>/css/talktalk.css">



<style>


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
	background-image: url(<%=path%>/image/Desktop.png);
	background-position: center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: cover;
}
.publish{
background-image: url(http://bit.ly/2gPLxZ4);
background-position: center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />
	<!-- page bar -->
	<br>
	<div class="col-12  ">
		<nav class="page">
			<ul class="pagination justify-content-end ">

				<c:if test="${pageNo > 3}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=1">1</a></li>
					<li class="page-item mt-2"><a href=""><i
							class="fas fa-caret-left"></i><i class="fas fa-caret-left"></i><i
							class="fas fa-caret-left"></i><i class="fas fa-caret-left"></i><i
							class="fas fa-caret-left"></i></a></li>
				</c:if>
				<c:if test="${pageNo > 2}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${pageNo-2}">${pageNo-2}</a></li>
				</c:if>
				<c:if test="${pageNo > 1}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${pageNo-1}">${pageNo-1}</a></li>
				</c:if>
				<li class="page-item active"><a class="page-link" href="#">${pageNo}</a></li>
				<c:if test="${pageNo < lastPage}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${pageNo+1}">${pageNo+1}</a></li>
				</c:if>
				<c:if test="${pageNo+1 < lastPage}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${pageNo+2}">${pageNo+2}</a></li>
				</c:if>
				<c:if test="${pageNo+2 < lastPage}">
					<li class="page-item mt-2"><a href=""><i
							class="fas fa-caret-right"></i><i class="fas fa-caret-right"></i><i
							class="fas fa-caret-right"></i><i class="fas fa-caret-right"></i><i
							class="fas fa-caret-right"></i></a></li>
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${lastPage}">${lastPage}</a></li>
				</c:if>
			</ul>
		</nav>
	</div>

	<!-- 文章標題 -->
	<div class="container publish">
		<c:if test="${pageNo <2}">
			<div class="row ">
				<div class="col-12 text-white">${TalkBean.postTitle}</div>
				<!-- 作者資訊 -->
				<div class="col-2 author">
					<br> <a href="#"><img class="photo"
						src='${pageContext.request.contextPath}/getPostMemberImage?postID=${TalkBean.postID}'></a>
					<div class="">${TalkBean.memberBean.id}</div>
					<div class="account">${TalkBean.memberBean.email}</div>

				</div>
				<!-- 文章內容 -->

				<div class="col-10">
					
						<div>樓主 </div><a href="#" style="color:white;">發表於${TalkBean.postTime}</a>
						
						
					<!-- 按讚功能 -->
					<c:if test="${loh.likeOrHate==0 || empty loh.likeOrHate}">
					<div class=" article-mood  float-right">
						<ul class="like-or-hate ">
							
							<li id="like1" class="fas"><a href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=1" data-toggle="tooltip" data-placement="bottom" title="Like"><i class="fas fa-thumbs-up fa-2x">${TalkBean.postLike}</i></a></li>
							
							<li id="hate1" class="fas"><a href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=2" data-toggle="tooltip" data-placement="bottom" title="Hate"><i class="fas fa-thumbs-down fa-2x">${TalkBean.postHate}</i></a></li>
						</ul>
					</div>
					</c:if>
					<c:if test="${loh.likeOrHate==1}">
					<div class=" article-mood float-right">
						<ul class="like-or-hate">
							
							<li id="like1" class="fas"
								><a href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=0" data-toggle="tooltip" data-placement="bottom" title="Like"><i class="fas fa-thumbs-up fa-2x" style="color:red">${TalkBean.postLike}</i></a></li>
							
							<li id="hate1" class="fas"
								><a href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=2" data-toggle="tooltip" data-placement="bottom" title="Hate"><i class="fas fa-thumbs-down fa-2x">${TalkBean.postHate}</i></a></li>
						</ul>
					</div>
					</c:if>
					<c:if test="${loh.likeOrHate==2}">
					<div class="article-mood float-right">
						<ul class="like-or-hate">
							
							<li id="like1" class="fas"><a href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=1" data-toggle="tooltip" data-placement="bottom" title="Like"><i class="fas fa-thumbs-up fa-2x">${TalkBean.postLike}</i></a></li>
							
							<li id="hate1" class="fas"><a href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=0" data-toggle="tooltip" data-placement="bottom" title="Hate"><i class="fas fa-thumbs-down fa-2x" style="color:red">${TalkBean.postHate}</i></a></li>
						</ul>
					</div>
					</c:if>
					
					<div class="col-12 article-content">
						${TalkBean.postText} 
					</div>
						<img class="picture" 
							src="https://picsum.photos/id/222/500/400" alt="">
				</div>

			</div>
			<div class="btn-toolbar" role="toolbar"
				aria-label="Toolbar with button groups">
				<div class="btn-group mr-2" role="group" aria-label="First group">
				</div>
			</div>
	</c:if>
	</div>
<!-- 	</div> -->
	<!-- 留言內容 -->
	<c:forEach var="comment" items="${CommentBean}">
		<div class="container">
			<div class="row">
				<!-- 作者資訊 -->
				<div class="col-2 author">
					<br> <a href="#"><img class="photo"
						src='${pageContext.request.contextPath}/getComMemberImage?id=${LoginOK.memId}&comId=${comment.comId}'></a>
					<div class="">${comment.memberBean.id}</div>
					<div class="account">${comment.memberBean.email}</div>

				</div>
				<!-- 文章內容 -->
				<div class="col-10">
					
						<div>${comment.floor}樓</div> 
						<a href="#" style="color:white">發表於
								${comment.comTime}</a>

						
					
					<!-- 按讚功能 -->
					<div class=" article-mood float-right">
						<ul class="like-or-hate">
							<li id="like1" button class="fas fa-thumbs-up fa-2x"
								type="button">12</li>
							<li id="hate1" button class="fas fa-thumbs-down fa-2x"
								type="button">1</li>

							<%-- 							<c:if test="${CommentBean.memId}==${LoginOK.memId}"> --%>
							
							<%-- 							</c:if> --%>
						</ul>
					</div>
					<div class="col-12 article-content">
						${comment.comText}<br>
						<c:if test="${!empty comment.fileName}">
						<img class="commentPic" 
						     src='${pageContext.request.contextPath}/getComImage?id=${LoginOK.memId}&comId=${comment.comId}'>
						</c:if>
					</div>
					<div class="update  col-2 float-right ">
					        <a id="" class="updateButton m-2"
								href="${pageContext.request.contextPath}/forum/updateCom?postID=${TalkBean.postID}&comId=${comment.comId}">編輯</a>
							<a id="" class="deleteButton m-2"
								href="${pageContext.request.contextPath}/forum/deleteCom?postID=${TalkBean.postID}&comId=${comment.comId}">刪除</a>
				</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<!-- 留言輸入 -->
	<div class="container">
		<div class="row">
			<div class="col-12 mt-2">
				<form:form class="comment" method="POST"
					modelAttribute="commentBean" enctype="multipart/form-data">
					<form:textarea id="comments" path="comText" class="" cols="68"
						rows="5" placeholder="請輸入內容...." />
					<label class='control-label col-lg-5 col-lg-5'
						for="commentMultipartFile"> 上傳圖片 </label>
					<p class="mt-3">
						<img id="image" src="">
					</p>
					<form:input type="file" class="form-control-file"
						id="commentMultipartFile" path="commentMultipartFile" />
					<input class="float-right" type="submit" value="送出" />
				</form:form>

			</div>
		</div>
	</div>

	<hr>

	<div class="col-12  ">
		<nav class="page">
			<ul class="pagination justify-content-end ">

				<c:if test="${pageNo > 3}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=1">1</a></li>
					<li class="page-item mt-2"><a href=""><i
							class="fas fa-caret-left"></i><i class="fas fa-caret-left"></i><i
							class="fas fa-caret-left"></i><i class="fas fa-caret-left"></i><i
							class="fas fa-caret-left"></i></a></li>
				</c:if>
				<c:if test="${pageNo > 2}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${pageNo-2}">${pageNo-2}</a></li>
				</c:if>
				<c:if test="${pageNo > 1}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${pageNo-1}">${pageNo-1}</a></li>
				</c:if>
				<li class="page-item active"><a class="page-link" href="#">${pageNo}</a></li>
				<c:if test="${pageNo < lastPage}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${pageNo+1}">${pageNo+1}</a></li>
				</c:if>
				<c:if test="${pageNo+1 < lastPage}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${pageNo+2}">${pageNo+2}</a></li>
				</c:if>
				<c:if test="${pageNo+2 < lastPage}">
					<li class="page-item mt-2"><a href=""><i
							class="fas fa-caret-right"></i><i class="fas fa-caret-right"></i><i
							class="fas fa-caret-right"></i><i class="fas fa-caret-right"></i><i
							class="fas fa-caret-right"></i></a></li>
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/talktalk?postID=${TalkBean.postID}&pageNo=${lastPage}">${lastPage}</a></li>
				</c:if>
			</ul>
		</nav>
	</div>

	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$(".deleteButton").on('click', function(e) {
				if (confirm("確定刪除此則留言?")) {
					return true;
				} else {
					return false;
				}
			});
		})
	</script>
	<script src="${pageContext.request.contextPath}/javascript/talktalk.js"></script>
	<script src="${pageContext.request.contextPath}/javascript/talktalk2.js"></script>
</body>
</html>
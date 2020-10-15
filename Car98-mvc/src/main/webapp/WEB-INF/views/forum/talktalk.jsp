<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String path = request.getContextPath();
%>
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
<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script> -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
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
	background-image:
		url(${pageContext.servletContext.contextPath}/image/olav-tvedt-yq-efMJMuPg-unsplash.jpg);
	background-position: center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: cover;
}

.publish {
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
	<c:if test="${pageNo <2}">
		<div class="container publish">
			<div class="row ">
				<div class="col-12 text-white postitle">${TalkBean.postTitle}</div>
				<!-- 作者資訊 -->
				<div class="col-2 author">
					<br> <a
						href="<%=path%>/management?memId=${TalkBean.memberBean.memId}"><img
						class="photo"
						src='${pageContext.request.contextPath}/getPostMemberImage?postID=${TalkBean.postID}'></a>
					<div class="">${TalkBean.memberBean.id}</div>
					<div class="account">${TalkBean.memberBean.email}</div>

				</div>
				<!-- 文章內容 -->

				<div class="col-10">
					<div>樓主</div>
					<a href="#" style="color: white;">發表於${TalkBean.postTime}</a>

					<!-- 按讚功能 -->
					<c:if test="${loh.likeOrHate==0 || empty loh.likeOrHate}">
						<div class=" article-mood  float-right">
							<ul class="like-or-hate ">

								<li id="like1 " class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=1"
									data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x"></i></a>${TalkBean.postLike}</li>

								<li id="hate1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=2"
									data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x"></i></a>${TalkBean.postHate}</li>
							</ul>
						</div>
					</c:if>
					<c:if test="${loh.likeOrHate==1}">
						<div class=" article-mood float-right">
							<ul class="like-or-hate">

								<li id="like1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=0"
									data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x" style="color: red"></i></a>${TalkBean.postLike}</li>

								<li id="hate1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=2"
									data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x"></i></a>${TalkBean.postHate}</li>
							</ul>
						</div>
					</c:if>
					<c:if test="${loh.likeOrHate==2}">
						<div class="article-mood float-right">
							<ul class="like-or-hate">

								<li id="like1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=1"
									data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x"></i></a>${TalkBean.postLike}</li>

								<li id="hate1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=0"
									data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x" style="color: red"></i></a>${TalkBean.postHate}</li>
							</ul>
						</div>
					</c:if>

					<div class="col-12 article-content">${TalkBean.postText}</div>
					<c:if test="${!empty TalkBean.postFileName}">
						<img class="picture mb-3 mt-3"
							src="${pageContext.request.contextPath}/getpostPic?postID=${TalkBean.postID}"
							alt="">
					</c:if>

					<c:if test="${TalkBean.memberBean.memId == LoginOK.memId}">
						<div class=" float-right mr-4">
							<a class="text-secondary btn btn-light " type="submit"
								href="<%=path%>/talkContent?postId=${TalkBean.postID}">編輯</a> <a
								class="text-secondary btn btn-light ml-4" id="delbtn"
								type="button"
								href="<%=path%>/deletePost?postId=${TalkBean.postID}">刪除</a>
						</div>
					</c:if>
				</div>

			</div>
			<div class="btn-toolbar" role="toolbar"
				aria-label="Toolbar with button groups">
				<div class="btn-group mr-2" role="group" aria-label="First group">
				</div>
			</div>
		</div>
	</c:if>
	<!-- 	</div> -->

	<p class="space mt-5"></p>

	<!-- 留言內容 -->
	<c:forEach var="comment" items="${CommentBean}">
		<div class="container" id="talkcontent">
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
					<a href="#" style="color: white">發表於 ${comment.comTime}</a>

					<!-- 按讚功能 -->
					<c:if
						test="${comment.comLikeOrHateBean.comLikeOrHate==0||empty comment.comLikeOrHateBean.comLikeOrHate}">
						<div class=" article-mood float-right">
							<ul class="like-or-hate">

								<li id="like1" class="fas"><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=1"
									data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x"></i></a>${comment.comLike}</li>

								<li id="hate1" class="fas"><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=2"
									data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x"></i></a>${comment.comHate}</li>

							</ul>
						</div>
					</c:if>

					<c:if test="${comment.comLikeOrHateBean.comLikeOrHate==1}">
						<div class="article-mood float-right">
							<ul class="like-or-hate">


								<li id="like1" class="fas"><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=0"
									data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x" style="color: red"></i></a>${comment.comLike}</li>

								<li id="hate1" class="fas"><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=2"
									data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x""></i></a>${comment.comHate}</li>

							</ul>
						</div>
					</c:if>
					<c:if test="${comment.comLikeOrHateBean.comLikeOrHate==2}">
						<div class="article-mood float-right">
							<ul class="like-or-hate">


								<li id="like1" class="fas"><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=1"
									data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x""></i></a>${comment.comLike}</li>

								<li id="hate1" class="fas"><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=0"
									data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x" style="color: red"></i></a>${comment.comHate}</li>
							</ul>
						</div>
					</c:if>

					<!-- <form id="updateForm${comment.comId}" name="updateForm${comment.comId}" 
						action="<%=path%>/updateCom?postId=${TalkBean.postID}&comId=${comment.comId}" method="post" enctype="multipart/form-data">
						<div class="col-12 article-content">
							<div id="processUpdateDiv${comment.comId}">${comment.comText}</div>
							<br>

							<div id="updateDiv${comment.comId}" name="updateDiv${comment.comId}" class="mb-2" hidden="true">
								<textarea id="updateText${comment.comId}" name="updateText${comment.comId}" rows="4" cols="68">${comment.comText}
							</textarea>
							</div>


							<div id="oldPicture${comment.comId}">
								<c:if test="${!empty comment.fileName}">
									<img class="commentPic mb-3" id="picImg${comment.comId}" name="picImg${comment.comId}"
										src='${pageContext.request.contextPath}/getComImage?id=${LoginOK.memId}&comId=${comment.comId}'>
								</c:if>
							</div>

							<div id="newPicture${comment.comId}" name="newPicture${comment.comId}" style="display: none;">
								<label for="exampleFormControlFile1s" class="control-label">
									<input type="file" class="form-control-file float-right "
									onclick="updatePic(this)" path="commentMultipartFile"
									id="comMultipartFile${comment.comId}" name="comMultipartFile${comment.comId}"/> <img
									class="commentPic mb-3" id="updatePicImg${comment.comId}" name="updatePicImg${comment.comId}"
									src='${pageContext.request.contextPath}/getComImage?id=${LoginOK.memId}&comId=${comment.comId}'>

								</label>
							</div>
						</div>

						<c:if test="${comment.memberBean.memId == LoginOK.memId}">
							<div class="update  col-3 float-right mb-3 d-flex">
								<button id="updateButton${comment.comId}" type="button"
									class="updateButton ml-5 btn btn-light "
									onclick="openTextarea(this)">編輯</button>
								<input id="updateFinish${comment.comId}" hidden="true" value="送出"
									type="submit" class="updateButton btn btn-light"/> -->
					<!-- 									onclick="updateConfirm(this)" -->
					<!-- <button id="cancel${comment.comId}" hidden="true"
									class="cancelButton btn btn-light" onclick="cancel(this)">取消</button>
								<a id="deleteButton${comment.comId}"
									class="deleteButton ml-4 btn btn-light " type="button"
									href="${pageContext.request.contextPath}/deleteCom?postID=${TalkBean.postID}&comId=${comment.comId}">刪除</a>
							</div>
						</c:if>

					</form> -->



					<div class="col-12 article-content">
						<div id="processUpdateDiv${comment.comId}">${comment.comText}</div>
						<br>

						<div id="updateDiv${comment.comId}" class="mb-2" hidden="true">
							<textarea id="updateText${comment.comId}" rows="4" cols="68">${comment.comText}
							</textarea>
						</div>

						<div id="oldPicture${comment.comId}">
							<c:if test="${!empty comment.fileName}">
								<img class="commentPic mb-3" id="picImg${comment.comId}"
									src='${pageContext.request.contextPath}/getComImage?id=${LoginOK.memId}&comId=${comment.comId}'>
							</c:if>
						</div>

						<div id="newPicture${comment.comId}" style="display: none;">
							<label for="exampleFormControlFile1s" class="control-label">
								<input type="file" class="form-control-file float-right "
								onclick="updatePic(this)" path="commentMultipartFile"
								id="comMultipartFile${comment.comId}" /> <input type="hidden"
								name="commentImg" id="commentImg${comment.comId}"> <img
								class="commentPic mb-3" id="updatePicImg${comment.comId}"
								src='${pageContext.request.contextPath}/getComImage?id=${LoginOK.memId}&comId=${comment.comId}'>

							</label>
						</div>
					</div>



					<c:if test="${comment.memberBean.memId == LoginOK.memId}">
						<div class="update  col-3 float-right mb-3 d-flex">
						

							<!-- Button trigger modal -->
							<!-- <div class="mt-2 mb-2 needs-validation mx-5 text-white">
								<button type="button" class="btn btn-secondary"
									data-toggle="modal" data-target="#staticBackdrop">編輯</button>
							</div> -->


							<button id="updateButton${comment.comId}" type="button"
								class="updateButton ml-5 btn btn-light " onclick="openTextarea(this)">編輯</button>
							<button id="updateFinish${comment.comId}" hidden="true"
								class="updateButton btn btn-light" onclick="updateConfirm(this)">送出</button>
							<button id="cancel${comment.comId}" hidden="true"
								class="cancelButton btn btn-light" onclick="cancel(this)">取消</button>	
							<a id="deleteButton${comment.comId}" class="deleteButton ml-4 btn btn-light " type="button"
								href="${pageContext.request.contextPath}/deleteCom?postID=${TalkBean.postID}&comId=${comment.comId}">刪除</a>

						</div>
					</c:if>
				</div>
			</div>
		</div>
	</c:forEach>

	<!-- Modal -->
	<!-- <div class="modal fade" id="staticBackdrop" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content bg-dark">
				<div class="modal-header">
					<h5 class="modal-title text-white" id="staticBackdropLabel">編輯</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="container">
					<div class="row">
						<div class="col-12 mt-1 message">
							<form:form class="comment" action="updateCom" method="POST"
								modelAttribute="commentBean" enctype="multipart/form-data">
								<form:textarea id="comments" path="comText" class="mt-2 "
									cols="120" rows="3" placeholder="請輸入內容...." />

								<label class='control-label ' for="commentMultipartFile">
									上傳圖片 </label>

								<form:input type="file" class="form-control-file float-right "
									id="commentMultipartFile" path="commentMultipartFile" />


								<p class="mt-3 ">
									<img id="image" src="">
								</p>

								<button class="float-right btn btn-light  mb-3" type="submit"
									id="confirm">送出</button>
							</form:form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div> -->


	<!-- 留言輸入 -->
	<div class="container">
		<div class="row">
			<div class="col-12 mt-1 message">
				<form:form class="comment" method="POST"
					modelAttribute="commentBean" enctype="multipart/form-data">
					<form:textarea id="comments" path="comText" class="mt-2 "
						cols="120" rows="3" placeholder="請輸入內容...." />

					<label class='control-label ' for="commentMultipartFile">上傳圖片</label>

					<form:input type="file" class="form-control-file float-right "
						id="commentMultipartFile" path="commentMultipartFile" />


					<p class="mt-3 ">
						<img id="image" src="">
					</p>

					<button class="float-right btn btn-light  mb-3" type="submit"
						id="confirm">送出</button>
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

			$("#delbtn").on('click', function(e) {
				if (confirm("確定刪除此文?")) {
					return true;
				} else {
					return false;
				}
			});
		})

		var _baseUrl = 'CommentController/';

		// jquery 的 hide() show() 是針對css 做出display:none 跟 inline   hidden是hidden
		var openTextarea = function(value) {
			var idNo = value.id.substring(12);
			$('#processUpdateDiv' + idNo).hide(); //舊textarea
			document.getElementById('updateDiv' + idNo).hidden = false; //新textarea
			$('#updateButton' + idNo).hide(); //編輯按鈕
			document.getElementById('updateFinish' + idNo).hidden = false; //送出按鈕
			document.getElementById('cancel' + idNo).hidden = false; //取消按紐
			$('#oldPicture' + idNo).hide();
			$('#newPicture' + idNo).show();
		};

		var cancel = function(value) {
			var idNo = value.id.substring(6);
			$('#processUpdateDiv' + idNo).show(); //舊textarea
			document.getElementById('updateDiv' + idNo).hidden = true; //新textarea
			$('#updateButton' + idNo).show(); //編輯按鈕
			document.getElementById('updateFinish' + idNo).hidden = true; //送出按鈕
			document.getElementById('cancel' + idNo).hidden = true; //取消按紐
			$('#oldPicture' + idNo).show();
			$('#newPicture' + idNo).hide();

		}

		var updateConfirm = function(value) {
			var updateMapping = 'updateCom'
			var idNo = value.id.substring(12);
			var textValue = $('#updateText' + idNo).val().trim();
			var reqParams = getReqParams(); // 取得網址的參數
			var postId = reqParams.postID;
			var pageNo = reqParams.pageNo;
			var fileName = $('#comMultipartFile' + idNo).val(); //取檔名

			bean = {
				"comId" : parseInt(idNo),
				"comText" : textValue,
				"postID" : parseInt(postId),
				"pageNo" : parseInt(pageNo),
			};
			// bean.comId = parseInt(idNo);
			// bean.comText = textValue;
			// bean.postID = parseInt(getPostID());
			console.log(bean);

			ajaxPost(updateMapping, bean, function() {
				console.log('傳送成功');
			});

		}

		var getReqParams = function() {
			var reqParams = {};
			//先取得網址字串，假設此頁網址為「index.aspx?id=U001&name=GQSM」
			var url = location.href;
			//再來用去尋找網址列中是否有資料傳遞(QueryString)
			if (url.indexOf('?') != -1) {
				//在此直接將各自的參數資料切割放進ary中
				var ary = url.split('?')[1].split('&');

				//此時ary的內容為：
				//ary[0] = 'id=U001'，ary[1] = 'name=GQSM'

				//下迴圈去搜尋每個資料參數
				for (i = 0; i <= ary.length - 1; i++) {
					//如果資料名稱為id的話那就把他取出來
					if (ary[i].split('=')[0] == 'postID') {
						reqParams.postID = ary[i].substring(7);
					}

					if (ary[i].split('=')[0] == 'pageNo') {
						reqParams.pageNo = ary[i].substring(7);
					}

				}
				return reqParams;
			}
		}

		var ajaxPost = function(action, postData, fn) {
			console.log(_baseUrl + action);
			console.log(postData);
			$.ajax({
				url : action,
				type : "POST",
				contentType : "application/json",
				data : JSON.stringify(postData),
				// data: _instant.model.mainForm,
				success : function(data) {
					if (!!data.Message) {
						console.log('success');
					}
					if (!!data.errorMessage) {
						console.log('success');
						return;
					}
					// 					alert(data);
				},
				error : function(data) {
					console.log('fail');
					// 					alert("error")
				},

				complete : function(data) {
					window.location.href = "/Car98-mvc/talktalk?postID="
							+ postData.postID + "&pageNo=" + postData.pageNo;
				}
			});

		};

		var updatePic = function(value) {
			var idNo = value.id.substring(16);
			// document.getElementById("comMultipartFile" + idNo).click();

			$("#comMultipartFile" + idNo).on("change", function(event) {
				$('#newPicture' + idNo).show();
				const file = event.target.files[0];
				let readFile = new FileReader();
				readFile.readAsDataURL(file);
				readFile.addEventListener("load", function(e) {
					let image = document.getElementById("updatePicImg" + idNo);

					image.src = this.result;

					// image.width = 500 ;
					image.style.maxWidth = "350px"; //css屬性
					image.style.maxHeight = "350px";
				});
			});

		};

		// $("#comPic").click(function (e) {
		// 	document.getElementById("comMultipartFile").click();
		// });
	</script>

	<script src="${pageContext.request.contextPath}/javascript/talktalk.js"></script>
	<script
		src="${pageContext.request.contextPath}/javascript/talktalk2.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>


</body>
</html>
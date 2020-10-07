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
	background-image: url(<%=path%>/image/Desktop.png);
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

					<div>樓主</div>
					<a href="#" style="color: white;">發表於${TalkBean.postTime}</a>


					<!-- 按讚功能 -->
					<c:if test="${loh.likeOrHate==0 || empty loh.likeOrHate}">
						<div class=" article-mood  float-right">
							<ul class="like-or-hate ">

								<li id="like1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=1"
									data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x">${TalkBean.postLike}</i></a></li>

								<li id="hate1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=2"
									data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x">${TalkBean.postHate}</i></a></li>
							</ul>
						</div>
					</c:if>
					<c:if test="${loh.likeOrHate==1}">
						<div class=" article-mood float-right">
							<ul class="like-or-hate">

								<li id="like1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=0"
									data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x" style="color: red">${TalkBean.postLike}</i></a></li>

								<li id="hate1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=2"
									data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x">${TalkBean.postHate}</i></a></li>
							</ul>
						</div>
					</c:if>
					<c:if test="${loh.likeOrHate==2}">
						<div class="article-mood float-right">
							<ul class="like-or-hate">

								<li id="like1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=1"
									data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x">${TalkBean.postLike}</i></a></li>

								<li id="hate1" class="fas"><a
									href="<%=path%>/like?postId=${TalkBean.postID}&loh=${loh.aa}&tf=0"
									data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x" style="color: red">${TalkBean.postHate}</i></a></li>
							</ul>
						</div>
					</c:if>

					<div class="col-12 article-content">${TalkBean.postText}</div>
					<c:if test="${!empty TalkBean.postFileName}">
					<img class="picture" src="${pageContext.request.contextPath}/getpostPic?id=${LoginOK.memId}&postID=${TalkBean.postID}"
						alt="">
						</c:if>
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
					<a href="#" style="color: white">發表於 ${comment.comTime}</a>



					<!-- 按讚功能 -->
					<c:if
						test="${comment.comLikeOrHateBean.comLikeOrHate==0||empty comment.comLikeOrHateBean.comLikeOrHate}">
						<div class=" article-mood float-right">
							<ul class="like-or-hate">

								<li id="like1" ><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=1" data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x" ">${comment.comLike}</i></a></li>

								<li id="hate1" ><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=2" data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x" >${comment.comHate}</i></a></li>



							</ul>
						</div>
					</c:if>

					<c:if test="${comment.comLikeOrHateBean.comLikeOrHate==1}">
						<div class="article-mood float-right">
							<ul class="like-or-hate">


								<li id="like1" ><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=0" data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x" style="color: red">${comment.comLike}</i></a></li>

								<li id="hate1" ><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=2" data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x" ">${comment.comHate}</i></a></li>

							</ul>
						</div>
					</c:if>
					<c:if test="${comment.comLikeOrHateBean.comLikeOrHate==2}">
						<div class="article-mood float-right">
							<ul class="like-or-hate">


								<li id="like1" ><a
									href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=1" data-toggle="tooltip" data-placement="bottom" title="Like"><i
										class="fas fa-thumbs-up fa-2x" ">${comment.comLike}</i></a></li>

								<li id="hate1" >
								<a	href="<%=path%>/comlike?postId=${TalkBean.postID}&comId=${comment.comId}&comLohId=${comment.comLikeOrHateBean.comLohId}&tf=0" data-toggle="tooltip" data-placement="bottom" title="Hate"><i
										class="fas fa-thumbs-down fa-2x" ">${comment.comHate}</i></a></li>
							</ul>
						</div>
					</c:if>







				

					<div class="col-12 article-content">
						<div id="processUpdateDiv${comment.comId}">${comment.comText}</div>
						<br>
<<<<<<< HEAD
						<div id="updateDiv${comment.comId}" hidden="true">
							<textarea id="updateText${comment.comId}" rows="5" cols="68">
							${comment.comText}
							<c:if test="${!empty comment.fileName}">
							<img class="commentPic"
										src='${pageContext.request.contextPath}/getComImage?id=${LoginOK.memId}&comId=${comment.comId}'>
							</c:if>
							</textarea>
						</div>
=======
						<!-- 						<div id="updateDiv" hidden="true"> -->
						<%-- 						<textarea rows="20" cols="20">${comment.comText}</textarea>						 --%>
						<!-- 						</div> -->

>>>>>>> 0632830a689d5d3e299ed00895738bc495151c79
						<c:if test="${!empty comment.fileName}">
							<img class="commentPic"
								src='${pageContext.request.contextPath}/getComImage?id=${LoginOK.memId}&comId=${comment.comId}'>
						</c:if>
					</div>
					<!-- Button trigger modal -->
<<<<<<< HEAD
					<c:if test="${comment.memberBean.memId == LoginOK.memId}">
						<div class="update  col-2 float-right ">
							<!-- 記得加回編輯的<a> tag	data-toggle="modal" data-target="#staticBackdrop" -->
							<%-- 						<a id="updateButton" class="updateButton m-2" href="${pageContext.request.contextPath}/forum/updateCom?postID=${TalkBean.postID}&comId=${comment.comId}">編輯</a> --%>
							<button id="updateButton${comment.comId}"
								class="updateButton m-2" onclick="openTextarea(this)">編輯</button>
							<button id="updateFinish${comment.comId}" hidden="true"
								class="updateButton m-2" onclick="confirm(this)">送出</button>
							<a id="" class="deleteButton m-2"
								href="${pageContext.request.contextPath}/forum/deleteCom?postID=${TalkBean.postID}&comId=${comment.comId}">刪除</a>
						</div>
					</c:if>
=======
					<%-- 					<c:if test="${comment.memberBean.id == LoginOK.memId}"> --%>
					<div class="update  col-2 float-right ">

						<!-- 記得加回編輯的<a> tag	data-toggle="modal" data-target="#staticBackdrop" -->
						<a id="updateButton" class="updateButton m-2" data-toggle="modal" data-target="#staticBackdrop"

							href="${pageContext.request.contextPath}/forum/updateCom?postID=${TalkBean.postID}&comId=${comment.comId}">編輯</a>
						<!-- <button id="updateButton" class="updateButton m-2" 
							href="">編輯</button> -->
						<a id="" class="deleteButton m-2"
							href="${pageContext.request.contextPath}/forum/deleteCom?postID=${TalkBean.postID}&comId=${comment.comId}">刪除</a>
					</div>
					<%-- 					</c:if> --%>
>>>>>>> 0632830a689d5d3e299ed00895738bc495151c79

				</div>
			</div>
		</div>
	</c:forEach>

	<!-- Modal -->
	<!-- 	<div class="modal fade" id="staticBackdrop" data-backdrop="static" -->
	<!-- 		data-keyboard="false" tabindex="-1" -->
	<!-- 		aria-labelledby="staticBackdropLabel" aria-hidden="true"> -->
	<!-- 		<div class="modal-dialog"> -->
	<!-- 			<div class="modal-content"> -->
	<!-- 				<div class="modal-header"> -->
	<!-- 					<h5 class="modal-title" id="staticBackdropLabel">編輯留言</h5> -->
	<!-- 					<button type="button" class="close" data-dismiss="modal" -->
	<!-- 						aria-label="Close"> -->
	<!-- 						<span aria-hidden="true">&times;</span> -->
	<!-- 					</button> -->
	<!-- 				</div> -->

	<%-- 				<form:form action="updateComment" modelAttribute="commentBean" --%>
	<%-- 					method="POST"> --%>
	<!-- 					<div class="modal-body"> -->
	<!-- 						<div class="form-group w-50 mx-auto"> -->
	<!-- 							<p class="text-secondary"></p> -->
	<%-- 							<form:input path="comText" type="test" id="commentText" --%>
	<%-- 								class="form-control" value="" name="comText" required="required" --%>
	<%-- 								pattern="" /> --%>
	<%-- 							<form:errors path="comText" /> --%>
	<!-- 						</div> -->
	<!-- 						<div class="modal-footer justify-content-center"> -->
	<!-- 							<button type="submit" class="btn btn-secondary" -->
	<!-- 								onclick="myFunction()">送出</button> -->
	<%-- 							<c:if test="${empty errors}"> --%>
	<!-- 								<div id="snackbar">修改成功</div> -->
	<%-- 							</c:if> --%>
	<!-- 						</div> -->
	<!-- 					</div> -->
	<%-- 				</form:form> --%>
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->



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

		var _baseUrl = 'CommentController/';

		var openTextarea = function(value) {
			var idNo = value.id.substring(12);
			$('#processUpdateDiv' + idNo).hide();
			document.getElementById('updateDiv' + idNo).hidden = false;
			$('#updateButton' + idNo).hide();
			document.getElementById('updateFinish' + idNo).hidden = false;
		};

		var confirm = function(value) {
			var bean = [];
			var updateMapping = 'updateCom'
			var idNo = value.id.substring(12);
			var textValue = $('#updateText' + idNo).val().trim();
			console.log(textValue);
			bean.comId = idNo;
			bean.comText = textValue;
			bean.postID = getPostID();
			console.log(bean);
			ajaxPost(updateMapping, bean, function() {
				console.log('傳送成功');

			});

		}

		var getPostID = function() {
			//先取得網址字串，假設此頁網址為「index.aspx?id=U001&name=GQSM」
			var url = location.href;
			//再來用去尋找網址列中是否有資料傳遞(QueryString)
			if (url.indexOf('?') != -1) {
				var id = "";
				//在此直接將各自的參數資料切割放進ary中
				var ary = url.split('?')[1].split('&');
				//此時ary的內容為：
				//ary[0] = 'id=U001'，ary[1] = 'name=GQSM'

				//下迴圈去搜尋每個資料參數
				for (i = 0; i <= ary.length - 1; i++) {
					//如果資料名稱為id的話那就把他取出來
					if (ary[i].split('=')[0] == 'postID') {
						id = ary[i].substring(7);
					}
				}
				return id;
			}
		}

		
		var ajaxPost = function(action, postData, fn) {
			console.log(_baseUrl + action);
			$.ajax({
				url :  action,
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
					fn(data);
				},
				error : function(data) {
					console.log('fail');
				},
			});
		};
	</script>

	<script src="${pageContext.request.contextPath}/javascript/talktalk.js"></script>
	<script
		src="${pageContext.request.contextPath}/javascript/talktalk2.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car論壇</title>
<link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
      crossorigin="anonymous"
    />
   <link rel="stylesheet" href="<%=path%>/css/animate.css">
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
      integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="<%=path%>/css/BSTalk.css">
    
    <style>
      #carouselExampleFade .carousel-inner .carousel-item img{
        height:720px;       
        }
      .footer-bottom{
      margin-top: 1em;
      /* border-top: 1px solid #DDDDDD; */
      padding-top: 10px;
      padding-bottom: 5px;
     
    }
    .footer-bottom p.pull-left{
      padding-top: 6px;
      font-size: 0.5em;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .bg-cover{
      background-size: cover;
    }
    body{
      background-image: url(${pageContext.servletContext.contextPath}/image/olav-tvedt-yq-efMJMuPg-unsplash.jpg);
      background-position: center;
      background-attachment: fixed;
      background-repeat:no-repeat;
      background-size: cover;
    }
    </style>
  </head>
  <body>
    <!-- navbar -->
    <jsp:include page="/fragment/topIndex.jsp" />
      <!-- mainCar Area -->
      <section class="my-5">
        
      <div class="container text-white ">
        <div class="row">
            <div class="col-12 head" id="headtitle">
                汽車交流論壇
            </div>
            <form action="" class="d-flex m-auto">
            <c:forEach var="aBean" items="${abean}">
            <div class="col-2 border mt-2">
                <a href="<%=path%>/talktalk?postID=${aBean.postID}&pageNo=1">
                <img src="${pageContext.request.contextPath}/getpostPic?postID=${aBean.postID}" class="img-fluid topPic"  alt="" ></a>
            </div>
            </c:forEach>
            
            </form>
            
            <div class="col-12 publish ">
                     <button class="btn text-white bg-primary  " onclick="window.location.href='<c:url value='/talkContent' />' ">發帖</a></button>
                <nav aria-label="Page navigation" class="publishitem">
                    <ul class="pagination justify-content-end ">

                      <c:if test="${pageNo > 3}">
                      <li class="page-item"><a class="page-link" href="<%=path%>/forum/talktop.do?pageNo=1&search=${search}&type=${type}">1</a></li>
                      <li class="page-item mt-2"><a href=""><i class="fas fa-caret-left"></i><i class="fas fa-caret-left"></i><i class="fas fa-caret-left"></i><i class="fas fa-caret-left"></i><i class="fas fa-caret-left"></i></a></li>
                      	</c:if>
                      	<c:if test="${pageNo > 2}">
                      <li class="page-item"><a class="page-link" href="<%=path%>/forum/talktop.do?pageNo=${pageNo-2}&search=${search}&type=${type}">${pageNo-2}</a></li>
                      	</c:if>
                      	<c:if test="${pageNo > 1}">
                      <li class="page-item"><a class="page-link" href="<%=path%>/forum/talktop.do?pageNo=${pageNo-1}&search=${search}&type=${type}">${pageNo-1}</a></li>
                     	</c:if>
                      <li class="page-item active"><a class="page-link" href="#">${pageNo}</a></li>
                      <c:if test="${pageNo < lastPage}">
                      <li class="page-item"><a class="page-link" href="<%=path%>/forum/talktop.do?pageNo=${pageNo+1}&search=${search}&type=${type}">${pageNo+1}</a></li>
                      </c:if>
                      <c:if test="${pageNo+1 < lastPage}">
                      <li class="page-item"><a class="page-link" href="<%=path%>/forum/talktop.do?pageNo=${pageNo+2}&search=${search}&type=${type}">${pageNo+2}</a></li>
                      </c:if>
                      <c:if test="${pageNo+2 < lastPage}">
                      <li class="page-item mt-2"><a href=""><i class="fas fa-caret-right"></i><i class="fas fa-caret-right"></i><i class="fas fa-caret-right"></i><i class="fas fa-caret-right"></i><i class="fas fa-caret-right"></i></a></li>
                      <li class="page-item"><a class="page-link" href="<%=path%>/forum/talktop.do?pageNo=${lastPage}&search=${search}&type=${type}">${lastPage}</a></li>
                   	  </c:if>
                    </ul>
                  </nav>
            </div>
            
            <div class="col-12 ">
                <ul class="classification ">
                    <a class="classification-item-a" id="cia" href="<%=path%>/forum/talktop.do"><li class="classification-item">全部</li></a>
                    <a class="classification-item-a" id="cia1" href="<%=path%>/forum/talktop.do?type=1"><li class="classification-item">討論</li></a>
                    <a class="classification-item-a" id="cia2" href="<%=path%>/forum/talktop.do?type=2"><li class="classification-item">分享＆心得</li></a>
                    <a class="classification-item-a" id="cia3" href="<%=path%>/forum/talktop.do?type=3"><li class="classification-item">求助＆問題</li></a>
                    <a class="classification-item-a" id="cia4" href="<%=path%>/forum/talktop.do?type=4"><li class="classification-item">公告</li></a>
<%--                 <form:form class="ml-auto" method="POST" modelAttribute="search"> --%>
					<form class="ml-auto">
					<input type="text" name="search" id="search" placeholder="關鍵字....."/><button onClick="window.location.href='<%=path%>/forum/talktop.do'"><i class="fas fa-search"></i></button>
					</form>
<%-- 				</form:form> --%>
                </ul>
            </div>
<!--             <div class="col-12 filter"> -->
<%--                 <form class="mt-1">篩選： --%>
<!--                     <select name="" id="searchtime"> -->
<!--                     <option value="alltime">發佈時間</option> -->
<!--                     <option value="oday">一天</option> -->
<!--                     <option value="tday">兩天</option> -->
<!--                     <option value="oweek">一週</option> -->
<!--                     <option value="tweek">兩週</option> -->
<!--                     <option value="amonth">一個月</option> -->
<!--                     <option value="tday">兩個月</option> -->
<!--                     </select> -->
<%--                     </form> --%>
<!--             </div> -->
            <div class="col-12 main ">
                <table class="maintalk mt-1 ">
                    <tr>
                        <td class="sort ">分類</td>
                        <td class="pic">圖片</td>
                        <th class="title">內文標題</th>
                        <td class="author">        
                        作者/發佈時間
                        </td>
                        <td class="respon">回應/查看</td>
                        <td class="lastupdate">最後更新</td>
                    </tr>
                </table>
                
            </div>
    		
            <div class="col-12 content">
                <table class="maintalk mt-1 talk">
                <c:forEach var="aBean" items="${abean}">
				  <tr>
                        <td class="sort "><a class="text-light" href="<%=path%>/forum/talktop.do?type=${aBean.postType}"><i class="fab fa-discourse"></i>${aBean.postType}</a></td>
<%--                         <td class="pic "><c:if test="${!empty TalkBean.postFileName}"><img src="<%=path%>/getpostPic?postID=${aBean.postID}" class="contentimage " alt=""></c:if></td> --%>
						<td class="pic "><img src="<%=path%>/getpostPic?postID=${aBean.postID}" class="contentimage " alt=""></td>
                        <th class="title itemtitle "><a class="subtitle ml-3" href="<%=path%>/talktalk?postID=${aBean.postID}&pageNo=1">${aBean.postTitle}</a></th>
                        <td class="author"><a class="talkname" href="${pageContext.request.contextPath}/management?memId=${aBean.memberBean.id}">${aBean.memberBean.id}</a><br>${aBean.postTime} </td>
                        <td class="respon">${aBean.postCom}/<br>${aBean.postView}</td>
                        <td class="lastupdate"><a class="talkname" href="<%=path%>/management?memId=${aBean.commentbean.memberBean.id}">${aBean.commentbean.memberBean.id}</a><br><a  class="time" href="">${aBean.commentbean.comTime}</a></td>
                  </tr>
				 </c:forEach>
                </table>
            </div>
        </div>
        
       </div> 
    
    </section>

    

    <jsp:include page="/fragment/footer.jsp"></jsp:include>
   
      <script
      src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
      integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
      integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
      crossorigin="anonymous"
    ></script>
    
    <script src="${pageContext.servletContext.contextPath}/javascript/BSTalk.js"></script>
    <script src="${pageContext.servletContext.contextPath}/javascript/textillate.js"></script>
    <script src="${pageContext.servletContext.contextPath}/javascript/lettering.js"></script>
      </body>
    </html>
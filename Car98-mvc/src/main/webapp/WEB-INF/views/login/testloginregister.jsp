<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
      crossorigin="anonymous"
    />
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
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
      integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc"
      crossorigin="anonymous"
    />
    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/register.css">
    <script src="http://maps.google.com/maps/api/js"></script>
    <script src="${pageContext.servletContext.contextPath}/javascript/register.js"></script>
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
      /* background-image: url(./image/Desktop.png); */
      /* background-color:black ; */
      background-position: center;
      background-attachment: fixed;
      background-repeat:no-repeat;
      background-size: cover;
      background-image: linear-gradient(to left, #434343 0%, black 100%);
    }



   
    </style>
  </head>
  <body>
    <!-- navbar -->
   <jsp:include page="/fragment/topIndex.jsp" />
      <!-- main Area -->
      <section class="my-5">
        
      <div class="container text-white">
        <div class="row">
            <div class="col-3">
                
            </div>
            <div class="col-6 ">
                <div id="tab-demo">
                    <ul class="tab-title  d-flex ml-auto">
                        <li class="col-6"><a  class="memlogin" href="#tab01"><b>登入</b></a></li>
                        <li class="col-6"><a  class="memregister" href="#tab02"><b>註冊</b></a></li>
                        
                    </ul>
                    <div id="tab01" class="tab-inner text-white ">

                 <!-- 會員登入 -->
                        <form:form method="POST" modelAttribute="loginBean">
					<div class="form-group  mt-5 ">
						<label for="email" class="email">Email</label> 
						

						<form:input class="form-control text" path="user" size="10" /><br>
    	         		<form:errors  path="user" cssClass="error" /><br>
						<div class="invalid-feedback">請輸入Email</div>
					</div>
					<div class="form-group mt-5">
						<label for="password" class="pswd">密碼</label> 
						<form:input class="form-control text" type="password" path="password"  />
             			<form:errors  path="password" cssClass="error" /><br>
             			<form:errors  path="invalidCredentials" cssClass="error" /><br>             			
						<div class="invalid-feedback">請輸入密碼</div>

					</div>
					<div class="my-3 text-center mt-5 ">
						<input type="submit" value="登入" class="btn btn-secondary ml-2 signin">
					</div>
					<div class="my-3 text-center">
						<form:checkbox path="rememberMe" class="" />
							<label for="validationCustom04">記住密碼</label>
					</div>
					<div class="my-3 text-center">
						 <a href="#" class="text-light ml-2">忘記密碼</a>
					</div>

					<div class="my-3 text-center">
						<a href="#" class="text-light"><i
							class="fab fa-facebook-square fa-2x"></i></a> <a href="#"
							class="text-light"><i class="fab fa-google fa-2x"></i></a>
					</div>
				</form:form>
                    </div>
                    <div id="tab02" class="tab-inner text-white">

                 <!-- 註冊會員 -->
          <form:form method='POST' modelAttribute="memberBean" action="register"
			class='form-horizontal mx-auto' 
			enctype="multipart/form-data">
			<fieldset>
                <div class="form-row ">
				<div class="form-group col-lg-6 ">
                    
					<label class="control-label ml-3 " for='name' >
						姓名 </label>
					<div class="col-10  p-0 ">
						<form:input id="name" path="name" type='text'
							class='form:input-large form-control ml-3 ' />
						<form:errors class="error ml-3" path="name" />
                    </div>
                </div>
                    <div class="form-group col-lg-6 ">
                    <label class="control-label  nickname  " for='id'> 暱稱
					</label>
					<div class="col-10  p-0  nickname">
						<form:input id="id" path="id" type='text'
							class='form:input-large form-control '  />
						<form:errors class="error" path="id" />                
                </div>
				</div>
            </div>		
                <div class="form-row ">
				<div class="form-group col-lg-12">
					<label class="control-label ml-3 " for='email'>
						email </label>
					<div class="col-lg-11 email ">
						<form:input id="email" path="email" type='text'
							class='form:input-large form-control ' />
						<form:errors class="error" path="email" />
					</div>
				</div>
            </div>
            <div class="form-row ">
				<div class="form-group col-lg-12">
					<label class='control-label ml-3 ' for="phone">
						電話 </label>
					<div class='col-lg-11 phone'>
						<form:input id="phone" path="phone" type='text'
							class='form:input-large form-control ' />
						<form:errors class="error" path="phone" />
					</div>
                </div>
            </div>
                <div class="form-row ">
				<div class="form-group col-lg-6">
					<label class='control-label ml-3 ' for="password">
						密碼 </label>
					<div class='col-10  p-0 '>
						<form:input id="password" path="password" type='password'
							class='form:input-large form-control ml-3' 
							pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}" />
						<form:errors class="error ml-3" path="password" />
                    </div>
                </div>
				<div class="form-group col-lg-6">
					<label class='control-label  psw' for="password1">
						確認密碼 </label>
					<div class='col-10 p-0'>
						<form:input id="password1" path="password1" type='password'
							class='form:input-large form-control psw' 
							pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}"/>
						<form:errors class="error" path="password1" />
					</div>
                </div>
            
				<div class="form-group col-12">
					<label class='control-label ml-3 ' for="birth">
						生日 </label>
					<div class='col-lg-11 birth'>
						<form:input id="birth" path="birth" type='date'
							class='form:input-large form-control ' />
						<form:errors class="error" path="birth" />
					</div>
				</div>
            </div>
				<div class="form-group ">
					<label class='control-label col-lg-2 col-lg-2' for="sex">
						性別 </label>
					<div class="mx-auto row">
						<div class="col-10 mx-auto">
							<form:radiobutton path="sex" class='radio-size mx-3' value="M"
								label="男" id="gender" />
							<form:radiobutton path="sex" class='radio-size mx-3' value="F"
								label="女" id="gender" />
							<form:errors class="error" path="sex" />
						</div>

					</div>
				</div>

				<div class="form-group ">
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
                    </div>
                    
                </div>
            </div>
            <div class="col-3">
                

            </div>
            
        </div>
       </div> 
       
    
    </section>

    

    <jsp:include page="/fragment/footer.jsp"></jsp:include>


<script
		src="${pageContext.request.contextPath}/javascript/showRegisterImage.js"></script>
      </body>
    </html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Shop information</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
		integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopCheck.css" type="text/css">
</head>

<body>
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />
	<!-- 結帳畫面 -->
	<FORM action="<c:url value='memberOrders' />" method="POST">
		<section class="py-5">
			<div class="container">
				<div class="h1 text-center mb-3 text-secondary">訂單明細</div>
				<div class="row justify-content-center mt-5">
					<div class="col-md-8">
						<div class="accordion" id="accordionExample">
							<!-- 購物車商品記錄<p class="text-white" id="result"></p>  -->
							<div class="card border-0">
								<div class="card-header d-flex border" id="headingOne">
									<a class="btn btn-link text-primary" data-toggle="collapse" href="#collapseOne"
										role="button" aria-expanded="false" aria-controls="collapseOne">顯示購物車細節</a>
								</div>
							</div>
							<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
								data-parent="#accordionExample">
								<div class="card-body pt-0" id="product-table">
									<c:forEach varStatus="vs" var="anEntry" items="${memberOrders}">
										<!-- <p class="text-white" id="result"></p>
                                        <p class="text-white" id="totalresult"></p> -->
										<div>
											<span style="color: white">${anEntry.bidName}</span><br>
											<span style="color: white">賣家: ${anEntry.sellMan}</span> <span
												style="color: white; margin-left: 500px">$:
												${anEntry.unitPrice}</span>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<h5 class="text-center bg-light py-3 my-5">訂購人資料</h5>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="name" class="text-white">姓名</label> <span type="name" class="form-control"
									id="name" name="BuyName" placeholder="姓名" required>${memberOrder.memId}</span>
							</div>
							<div class="form-group col-md-6">
								<label for="Email" class="text-white">Email</label> 
								<span type="email" class="form-control" id="Email" name="Email" placeholder="Email"
									required>${memberOrder.email}</span>
							</div>
							<div class="form-group col">
								<label for="Address" class="text-white">地址</label> <span type="text"
									class="form-control" id="ShippingAddress" name="ShippingAddress"
									placeholder="ShippingAddress" required>${memberOrder.address}</span>
							</div>
						</div>
						 <div class="text-right">
                            <a class="btn btn-secondary" href="<c:url value='/comm/orderList' />">返回</a>
                        </div>
					</div>
				</div>
			</div>
		</section>
	</FORM>
	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp" />


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/javascript/addProduct.js"></script>
	<script type="text/javascript">
		function reconfirmOrder() {
			var sa = document.getElementById('ShippingAddress').value;
			if (sa === "") {
				window.alert('出貨地址不能是空白');
				return;
			}
			if (confirm("確定送出此份訂單 ? ")) {
				// 接收此資料的Servlet會使用 finalDecision 參數的值
				document.forms[0].finalDecision.value = "ORDER";
				document.forms[0].action = "<c:url value='/comm/ProcessOrder' />";
				document.forms[0].method = "POST";
				document.forms[0].submit();
				return;
			} else {
				return;
			}
		}
	</script>
</body>

</html>
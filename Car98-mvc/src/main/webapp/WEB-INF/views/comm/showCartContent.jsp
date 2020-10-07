<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
		integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shoplist.css" type="text/css">
	<script type="text/javascript">
		function confirmDelete(n) {
			if (confirm("確定刪除此項商品 ? ")) {
				document.forms[0].action = "<c:url value='UpdateItem.do?cmd=DEL&bidId=" + n + "' />";
				document.forms[0].method = "POST";
				document.forms[0].submit();
			} else {

			}
		}
		function modify(key, qty, index) {
			var x = "newQty" + index;
			var newQty = document.getElementById(x).value;
			if (newQty < 0) {
				window.alert('數量不能小於 0');
				return;
			}
			if (newQty == 0) {
				window.alert("請執行刪除功能來刪除此項商品");
				document.getElementById(x).value = qty;
				return;
			}
			if (newQty == qty) {
				window.alert("新、舊數量相同，不必修改");
				return;
			}
			if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ")) {
				document.forms[0].action = "<c:url value='UpdateItem.do?cmd=MOD&bidId=" + key + "&newQty=" + newQty + "' />";
				document.forms[0].method = "POST";
				document.forms[0].submit();
			} else {
				document.getElementById(x).value = qty;
			}
		}
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : event.keyCode
			if (charCode > 31 && (charCode < 48 || charCode > 57)) {
				return false;
			}
			return true;
		}
		function Checkout(qty) {
			if (qty == 0) {
				alert("無購買任何商品，不需結帳");
				return false;
			}
			if (confirm("再次確認訂單內容 ? ")) {
				return true;
			} else {
				return false;
			}
		}
		function Abort() {
			if (confirm("確定放棄購物 ? ")) {
				return true;
			} else {
				return false;
			}
		}
	</script>

	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<title>購物清單</title>
</head>

<body>
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />

	<c:set var="funcName" value="CHE" scope="session" />
	<c:choose>
		<c:when test="${ShoppingCart.subtotal > 0}">
			<c:set var="subtotalMessage" value="金額小計:${ShoppingCart.subtotal} 元" />
			<c:set var="subtotal" value="${ShoppingCart.subtotal}" />
		</c:when>
		<c:otherwise>
			<c:set var="subtotalMessage" value="金額小計:  0 元" />
			<c:set var="subtotal" value="0" />
		</c:otherwise>
	</c:choose>


	<div class="shopping-cart">
		<!-- Title -->
		<div class="title">
			購物清單
		</div>



		<table>
			<c:if test="${ShoppingCart.content == null}">
				<div class="title">
					購物車內沒有商品
				</div>
			</c:if>
			<c:forEach varStatus="vs" var="anEntry" items="${ShoppingCart.content}">
				<div class="item">
					<div class="buttons">
						<button class='delete-btn' name="delete" onclick="confirmDelete(${anEntry.key})">
							<i class="fas fa-trash-alt"></i>
						</button>
						<button class='like-btn' name="update"
							onclick="modify(${anEntry.key}, ${anEntry.value.quantity}, ${vs.index})">
							<i class="fa fa-check-square" aria-hidden="true"></i>
						</button>

					</div>
					<!-- PHOTO -->
					<div class="image">
						<img style='height: 80px; width: 80px;' src="<c:url value='comm/picture/${anEntry.value.bidId}' />">
					</div>
					<!-- product -->
					<div class="description">
						<span>${anEntry.value.bidName}</span>
						<span>賣家: ${anEntry.value.sellMan}</span>
					</div>
					<!-- product + - -->
					<div class="quantity">
						<!-- <button class="plus-btn" type="button" name="button">
									<img src="${pageContext.servletContext.contextPath}/image/plus.svg" alt="">
								</button> -->
						<Input id="newQty${vs.index}" style="width: 60px; text-align: center" name="newQty"
							type="number" min='1' max='10' value="<fmt:formatNumber value='${anEntry.value.quantity}' />" name="qty"
							onkeypress="return isNumberKey(event)" />
						<!-- <button class="minus-btn">
									<img src="${pageContext.servletContext.contextPath}/image/minus.svg" alt="">
								</button> -->
					</div>
					<div class="total-price">$
						<fmt:formatNumber value="${anEntry.value.unitPrice * anEntry.value.quantity}"
							pattern="#,###,###" />
					</div>
				</div>
				<!-- <tr height='16'>
							<td>${anEntry.value.bidName}</td>
							<td style="text-align: center;">${anEntry.value.sellMan}</td>
							<td style="text-align: right;"><fmt:formatNumber
									value="${anEntry.value.unitPrice}" pattern="#,###" />元</td>
							<td style="text-align: right;">
								<Input id="newQty${vs.index}"
								style="width: 60px; text-align: center" name="newQty" type="number"
								value="<fmt:formatNumber value='${anEntry.value.quantity}' />"
								name="qty" onkeypress="return isNumberKey(event)" />
							</td>
							<td style="text-align: right;"><fmt:formatNumber
									value="${anEntry.value.unitPrice * anEntry.value.quantity}"
									pattern="#,###,###" />元</td>
							<td><Input type="button" name="update" value="修改"
								onclick="modify(${anEntry.key}, ${anEntry.value.quantity}, ${vs.index})">
								<Input type="button" name="delete" value="刪除"
								onclick="confirmDelete(${anEntry.key})"></td>
						</tr> -->
			</c:forEach>


		</table>

		<div class='sel'>
				<div>合計金額：</div>
				<div>
					<fmt:formatNumber value="${subtotal}" pattern="#,###,###" />元</div>
			
		</div>
		<table class='sel'>
			<tr height='80'>
				<td>
					<table>
						<tr>
							<td width="265" align='center'><a class="btn btn-primary"
									aria-label="View 3 items in your shopping cart"
									href="<c:url value='/comm/products' />"><i class="fa fa-shopping-cart fa-lg"
										aria-hidden="true"></i>繼續購物</a></td>
							<td width="265" align='center'><a class="btn btn-success" href="<c:url value='checkout' />"
									onClick="return Checkout(${subtotal});"><i
										class="fa fa-credit-card fa-lg"></i>再次確認</a></td>
							<td width="265" align='center'><a class="btn btn-danger" href="<c:url value='abort' />"
									onClick="return Abort();"><i class="fa fa-trash-alt fa-lg"></i>放棄購物</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div style='text-align: center;'>
		<c:if test='${not empty OrderErrorMessage}'>
			<font color='red'>${OrderErrorMessage}</font>
			<c:remove var="OrderErrorMessage" />
		</c:if>
	</div>

	<form>
		<input type="hidden" name="a" />
	</form>
	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp" />
</body>

</html>
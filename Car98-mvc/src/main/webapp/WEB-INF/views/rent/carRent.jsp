<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CarRent Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
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
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/BSRent.css">
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>

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
	background-image:
		url(${pageContext.servletContext.contextPath}/image/Desktop.png);
	background-position: center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: cover;
}

#map {
	height: 500px;
	width: 100%;
}
</style>

</head>

<body>
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />
	<!-- RentCar Area -->
	<section class="my-5">
		<div class="container-fluid text-white">
			<div class="mainitem">汽 車</div>
			<div class="row">
				<div class="col-md-4">
					<form action="">
						地區： <select name="area" id="areaitem" style="width: 250px"
							onchange="queryArea()">
							<option value="">選擇地區</option>
						</select>
					</form>
				</div>
				<div class="col-md-4">
					<form action="">
						價格： <select name="price" id="priceitem" style="width: 250px"
							onchange="queryArea()">
							<option value="isHoliday=true&isWeekday=true&min=0&max=99999">選擇價格</option>
							<!--               <optgroup label="平日時租"> -->
							<%--                 <option value="min<100">100以下</option> --%>
							<%--                 <option value="min>101&max<300">101~300</option> --%>
							<!--                 <option value="weekHour3">301~700</option> -->
							<!--                 <option value="weekHour4">701以上</option> -->
							<!--               </optgroup> -->
							<!--               <optgroup label="假日時租"> -->
							<!--                 <option value="holiHour1">100以下</option> -->
							<!--                 <option value="holiHour2">101~300</option> -->
							<!--                 <option value="holiHour3">301~700</option> -->
							<!--                 <option value="holiHour4">701以上</option> -->
							<!--               </optgroup> -->
							<optgroup label="平日日租">
								<option value="isHoliday=false&isWeekday=true&min=0&max=1000">1000以下</option>
								<option value="isHoliday=false&isWeekday=true&min=1000&max=3000">1001~3000</option>
								<option value="isHoliday=false&isWeekday=true&min=3000&max=4000">3001~4000</option>
								<option value="isHoliday=false&isWeekday=true&min=4000&max=6000">4001以上</option>
							</optgroup>
							<optgroup label="假日日租">
								<option value="isHoliday=true&isWeekday=false&min=0&max=1000">1000以下</option>
								<option value="isHoliday=true&isWeekday=false&min=1000&max=3000">1001~3000</option>
								<option value="isHoliday=true&isWeekday=false&min=3000&max=4000">3001~4000</option>
								<option value="isHoliday=true&isWeekday=false&min=4000&max=6000">4001以上</option>
							</optgroup>
						</select>
					</form>
				</div>
				<div class="col-md-4">
					<form action="">
						車型： <select name="car" id="caritem" style="width: 250px"
							onchange="queryArea()">
							<option value="">選擇車型</option>
						</select>
					</form>
				</div>

				<div id="map"></div>
				<script>
					var map, geocoder;

					function initMap() {
						geocoder = new google.maps.Geocoder();
						map = new google.maps.Map(document
								.getElementById('map'), {
							zoom : 17
						});

						var address = '台北市大安區忠孝東路四段1號';
						geocoder.geocode({
							'address' : address
						},

						// 					  for(var i=0; i<a.length; i++) {
						// 					      geocoder.geocode({
						// 					          'address': a[i]
						// 					      }, 

						function(results, status) {
							if (status == 'OK') {
								map.setCenter(results[0].geometry.location);
								var marker = new google.maps.Marker({
									map : map,
									position : results[0].geometry.location
								});
							} else {
								console.log(status);
							}
						});
					}
				</script>
				<button id="buttontext">12358765432</button>
			</div>
		</div>
		</div>
	</section>
	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp"></jsp:include>

	<script
		src="${pageContext.servletContext.contextPath}/javascript/BSRent.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQYmuo5h9pGY0c83EpRPJKTSUoLsk64FA&callback=initMap"
		async defer></script>
	<script>
	// $('#buttontext').click(function(e){
	// 	let city = this.parentNode.parentNode.childNodes[1].innerHTML;;
	// 	alert(city);
	// 	$.ajax({
	// 		method: "GET",
	// 		url: "/Car98-mvc/getStoreList?isHoliday="+true+"&isWeekday="+true+"&min="+0+"&max="+99999,
	// 		contentType: "application/json",
	// 		success: function (res) {
	// 			alert("Hello");
	// 			alert(res);
	// 		},complete: function (res) {
	// 			alert("Hello");
	// 			let s= JSON.stringify(res);
	// 			alert(s);
	// 		}
	// 	});
	// 	alert("Heelo");
	// });
	</script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>CarRent Page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />
	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
		integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/BSRent.css">
	<!--   <script src="http://maps.google.com/maps/api/js"></script> -->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQYmuo5h9pGY0c83EpRPJKTSUoLsk64FA&callback=initMap"
		async defer></script>
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>

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

		#map {
			height: 500px;
			width: 100%;
		}
	</style>
	<script>
		// 由於有預設selected，為了避免一打開網頁的情況下，
		// 執行trigger("change")時出現alert，因此我們先設變數初值為0
		var x = 0;
		var y = 0;
		function change_areaitem() {
			if (x === 1) {
				if (areaitem.value === "selectarea") {
					alert("請選擇地區");
				} else {
					alert("您選擇的地區是：" + areaitem.value);
					//$("#areaitem").find(":selected").val();
					$('#areaitem option:selected').val();
				}
			} else {
				x = 1;
			}
		}

		function change_caritem() {
			if (y === 1) {
				if (areaitem.value === "selectarea" && caritem.value === "selectmodel") {
					alert("請先選擇地區，謝謝！");
				} else if (caritem.value !== "selectmodel" && areaitem.value === "selectarea") {
					alert("請先選擇地區，謝謝！");
				} else {
					alert("您選擇的車型是：" + caritem.value);
					$('#caritem option:selected').val();
				}
			} else {
				y = 1;
			}
		}
	</script>
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
						地區： <select name="area" id="areaitem" style="width: 250px" onChange="change_areaitem()">
							<option value="selectarea">選擇地區</option>
							<!-- 							<optgroup label="台北市"> -->
							<!-- 								<option value="台北市-中正區">中正</option> -->
							<!-- 								<option value="台北市-中山區">中山</option> -->
							<!-- 								<option value="台北市-萬華區">萬華</option> -->
							<!-- 								<option value="台北市-內湖區">內湖</option> -->
							<!-- 							</optgroup> -->
							<!-- 							<optgroup label="新北市"> -->
							<!-- 								<option value="dist5">樹林</option> -->
							<!-- 								<option value="dist6">板橋</option> -->
							<!-- 								<option value="dist7">中和</option> -->
							<!-- 								<option value="dist8">汐止</option> -->
							<!-- 							</optgroup> -->
						</select>
					</form>
				</div>
				<div class="col-md-4">
					<form action="">
						價格： <select name="price" id="priceitem" style="width: 250px">
							<option value="selectprice">選擇價格</option>
							<optgroup label="平日時租">
								<option value="weekHour1" selected>100以下</option>
								<option value="weekHour2">101~300</option>
								<option value="weekHour3">301~700</option>
								<option value="weekHour4">701以上</option>
							</optgroup>
							<optgroup label="假日時租">
								<option value="holiHour1">100以下</option>
								<option value="holiHour2">101~300</option>
								<option value="holiHour3">301~700</option>
								<option value="holiHour4">701以上</option>
							</optgroup>
							<optgroup label="平日日租">
								<option value="weekDay1">1000以下</option>
								<option value="weekDay2">1001~3000</option>
								<option value="weekDay3">3001~4000</option>
								<option value="weekDay4">4001以上</option>
							</optgroup>
							<optgroup label="假日日租">
								<option value="holiDay1">1000以下</option>
								<option value="holiDay2">1001~3000</option>
								<option value="holiDay3">3001~4000</option>
								<option value="holiDay4">4001以上</option>
							</optgroup>
						</select>
					</form>
				</div>
				<div class="col-md-4">
					<form action="">
						車型： <select name="car" id="caritem" style="width: 250px" onChange="change_caritem()">
							<option value="selectmodel" selected>選擇車型</option>
							<!-- 				              <optgroup label="BENZ"> -->
							<!-- 				                <option value="model1">C100</option> -->
							<!-- 				                <option value="model2">C200</option> -->
							<!-- 				                <option value="model3">C300</option> -->
							<!-- 				              </optgroup> -->
							<!-- 				              <optgroup label="BMW"> -->
							<!-- 				                <option value="model4">320i</option> -->
							<!-- 				                <option value="model5">520i</option> -->
							<!-- 				                <option value="model6">720i</option> -->
							<!-- 				              </optgroup> -->
							<!-- 				              <optgroup label="AUDI"> -->
							<!-- 				                <option value="model7">A6</option> -->
							<!-- 				                <option value="model8">A7</option> -->
							<!-- 				                <option value="model9">A8</option> -->
							<!-- 				              </optgroup> -->
						</select>
					</form>
				</div>
				<!--         <div class="col-md-12 text-white"> -->
				<!--           <iframe src="https://maps.google.com?output=embed&q=北科大" width="100%" height="460"> -->
				<!--           </iframe> -->
				<!--           <div  class="col-12" id="map" style="width: 800px;height: 600px;"> -->
				<!--         </div> -->

				<div id="map"></div>
				<script>
					function initMap() {
						var uluru = {
							lat: 25.042464,
							lng: 121.533795
						};
						var map = new google.maps.Map(document
							.getElementById('map'), {
							zoom: 17,
							center: uluru
						});
						var marker = new google.maps.Marker({
							position: uluru,
							map: map
						});
						// 取得使用者的位置
						if (navigator.geolocation) {
							navigator.geolocation.getCurrentPosition(function (position) {
								var position = {
									lat: position.coords.latitude,
									lng: position.coords.longitude
								};
								var marker = new google.maps.Marker({
									position,
									// icon:'marker.png',
									map,
								});
								map.setZoom(17);
								map.setCenter(position);
							});
						} else {
							// Browser doesn't support Geolocation
							alert("未允許或遭遇錯誤！");
						}
					}
					
				</script>

			</div>

		</div>

		</div>
	</section>


	<!-- About -->
	<!-- <div class="jumbotron-fluid bg-dark">
        <h1 class="display-5 text-light pt-2 pl-2">About</h1>
        <p class="lead text-light pl-2">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
        <a class="btn btn-primary mb-2 ml-2 btn-sm" href="#" role="button">Learn more</a>
      </div> -->
	<!-- card -->
	<!-- <div class="container mt-4 mb-5">
    <div class="row">
      <div class="col-sm-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Special title treatment</h5>
            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
            <a href="#" class="btn btn-primary">Go somewhere</a>
          </div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Special title treatment</h5>
            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
            <a href="#" class="btn btn-primary">Go somewhere</a>
          </div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Special title treatment</h5>
            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
            <a href="#" class="btn btn-primary">Go somewhere</a>
          </div>
        </div>
      </div>
    </div>
  </div> -->
	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp"></jsp:include>
	<script src="${pageContext.servletContext.contextPath}/javascript/BSRent.js"></script>
</body>

</html>
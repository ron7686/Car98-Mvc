<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		function change_vitem() {
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
		<form action="">
			<div class="row">
				<div class="col-md-3 ">
 					<!--<form action="<c:url value='/dist' />" method="POST">-->
						地區： <select name="area" id="areaitem" style="width: 200px" onchange="queryArea()">
							<option value="selectarea" class="test123" selected>選擇地區</option>
						</select>
					
				</div>
				<div class="col-md-3">
					
						價格： <select name="price" id="priceitem" style="width: 200px" onchange="queryArea()">
							<option value="min=0000&max=9999" selected>選擇價格</option>
							<optgroup label="平日時租">
								<option value="0-100">100以下</option>
								<option value="101-300">101~300</option>
								<option value="301-700">301~700</option>
								<option value="701-1500">701以上</option>
							</optgroup>
							<optgroup label="假日時租">
								<option value="0-100">100以下</option>
								<option value="101-300">101~300</option>
								<option value="301-700">301~700</option>
								<option value="701-1500">701以上</option>
							</optgroup>
							<optgroup label="平日日租">
								<option value="min=0000&max=1000">1000以下</option>
								<option value="min=1001&max=3000">1001~3000</option>
								<option value="min=3001&max=4000">3001~4000</option>
								<option value="min=4001&max=6000">4001以上</option>
							</optgroup>
							<optgroup label="假日日租">
								<option value="min=0000&max=1000">1000以下</option>
								<option value="min=1001&max=3000">1001~3000</option>
								<option value="min=3001&max=4000">3001~4000</option>
								<option value="min=4001&max=6000">4001以上</option>
							</optgroup>
						</select>
					
				</div>
				<div class="col-md-3 ">
					
						車型： <select name="car" id="caritem" style="width: 200px" onchange="queryArea()">
							<option value="" selected>選擇車型</option>
						</select>
					
				</div>
				
				<div class="col-md-3  ">
					<input type="submit" value="開始搜尋" class="start">
				
				</div>
<!-- 				<tr> -->
<!-- 				    <td colspan="3" align="center"><input type="button" id="testButton" name="submit"  -->
<!-- 				    value="提交" style="width: 60px" onclick="submitForms()" /></td> -->
<!-- 			    </tr> -->
				<!--         <div class="col-md-12 text-white"> -->
				<!--           <iframe src="https://maps.google.com?output=embed&q=北科大" width="100%" height="460"> -->
				<!--           </iframe> -->
				<!--           <div  class="col-12" id="map" style="width: 800px;height: 600px;"> -->
				<!--         </div> -->
				<div id="map"></div>
			</div>
</form>
		</div>

		
	</section>
	<!-- footer -->
	<jsp:include page="/fragment/footer.jsp"></jsp:include>
				<script>
				    function submitForms(){
				       document.getElementById("areaitem").submit();
				       document.getElementById("priceitem").submit();
				       document.getElementById("caritem").submit();
				    }
				    
					var map, geocoder;

					function initMap() {
					  geocoder = new google.maps.Geocoder();
					  map = new google.maps.Map(document.getElementById('map'), {
					    zoom: 17
					  });
// 					  xhr = new XMLHttpRequest();
// 					  var addr = JSON.parse(xhr.responseText))
// 					  var address = addr.city + addr.district + addr.street;
					  var address = '台北市大安區忠孝東路四段1號';
					  geocoder.geocode( { 'address': address}, function(results, status) {
					    if (status == 'OK') {
					      map.setCenter(results[0].geometry.location);
					      var marker = new google.maps.Marker({
					          map: map,
					          position: results[0].geometry.location
					      });
					    } else {
					      console.log(status);
					    }
					  });
					}
				</script>
	<script src="${pageContext.servletContext.contextPath}/javascript/BSRent.js"></script>
</body>

</html>
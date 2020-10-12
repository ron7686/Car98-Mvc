<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CarSearch Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />
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
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/SearchResource.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.10/vue.min.js"></script>

<script
	src="${pageContext.servletContext.contextPath}/javascript/SearchResource.js"></script>
	<script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQYmuo5h9pGY0c83EpRPJKTSUoLsk64FA&callback=initAutocomplete&libraries=places&v=weekly"
      defer
    ></script>
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
</style>
</head>
<body>
	<!-- navbar -->
	<jsp:include page="/fragment/topIndex.jsp" />
	<!-- Search Area -->
	<section class="my-5">
		<div class="container text-white mainarea">
			<div class="row">
				<div class="col-12 maintitle ">
					<h2>Car好康</h2>
				</div>

				<div class="col-md-12 mb-2">
					
						搜尋：<input id="pac-input" class="controls" type="text"
							placeholder="請輸入搜尋項目">

					
				</div>

				<div class="" id="map" class="mt-2"
					style="width: 100%; height: 500px;"></div>

			</div>
		</div>
	</section>
	<jsp:include page="/fragment/footer.jsp"></jsp:include>
	
	<script>
    function initAutocomplete() {
    	  const map = new google.maps.Map(document.getElementById("map"), {
    	    center: { lat: 25.042576, lng: 121.535654 },
    	    zoom: 13,
    	    mapTypeId: "roadmap",
    	  });
    	  // Create the search box and link it to the UI element.
    	  const input = document.getElementById("pac-input");
    	  const searchBox = new google.maps.places.SearchBox(input);
    	  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
    	  // Bias the SearchBox results towards current map's viewport.
    	  map.addListener("bounds_changed", () => {
    	    searchBox.setBounds(map.getBounds());
    	  });
    	  let markers = [];
    	  // Listen for the event fired when the user selects a prediction and retrieve
    	  // more details for that place.
    	  searchBox.addListener("places_changed", () => {
    	    const places = searchBox.getPlaces();

    	    if (places.length == 0) {
    	      return;
    	    }
    	    // Clear out the old markers.
    	    markers.forEach((marker) => {
    	      marker.setMap(null);
    	    });
    	    markers = [];
    	    // For each place, get the icon, name and location.
    	    const bounds = new google.maps.LatLngBounds();
    	    places.forEach((place) => {
    	      if (!place.geometry) {
    	        console.log("Returned place contains no geometry");
    	        return;
    	      }
    	      const icon = {
    	        url: place.icon,
    	        size: new google.maps.Size(71, 71),
    	        origin: new google.maps.Point(0, 0),
    	        anchor: new google.maps.Point(17, 34),
    	        scaledSize: new google.maps.Size(25, 25),
    	      };
    	      // Create a marker for each place.
    	      markers.push(
    	        new google.maps.Marker({
    	          map,
    	          icon,
    	          title: place.name,
    	          position: place.geometry.location,
    	        })
    	      );

    	      if (place.geometry.viewport) {
    	        // Only geocodes have viewport.
    	        bounds.union(place.geometry.viewport);
    	      } else {
    	        bounds.extend(place.geometry.location);
    	      }
    	    });
    	    map.fitBounds(bounds);
    	  });
    	}
    	      
    </script>
	

</body>
</html>
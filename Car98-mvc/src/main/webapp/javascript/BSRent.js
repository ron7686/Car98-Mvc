$(document).ready(function () {
	getAreaDataOptions();
	getCarDataOptions();
	$("#areaitem").select2({
		templateSelection: format1,
		escapeMarkup: function (a) {
			return a;
		}
	});
	$("#priceitem").select2({
		templateSelection: format2,
		escapeMarkup: function (b) {
			return b;
		}
	});
	$("#caritem").select2({
		templateSelection: format3,
		escapeMarkup: function (c) {
			return c;
		}
	});
});

function getAreaDataOptions() {
	$.ajax({
		method: "GET",
		url: "/Car98-mvc/area",
		contentType: "application/json",
		success: function (ado) {
			var last_city = "";
		
			for (i = 0; i < ado.length; i++) {
				if (last_city != ado[i][0]) {
					$("#areaitem").append(
						"<optgroup label=" + ado[i][0] + ">"
						+ "</optgroup>"
					);
				}
				$("optgroup[label=" + ado[i][0] + "]").append(
					"<option value="+"city=" +ado[i][0]+"&district="+ ado[i][1] + " >"
					+ ado[i][1]
					+ "</option>"
				);
				last_city = ado[i][0];
			}
		}
	})
}

function getCarDataOptions() {
	$.ajax({
		method: "GET",
		url: "/Car98-mvc/cartype",
		contentType: "application/json",
		success: function (res) {
			var last_brand = "";
			for (i = 0; i < res.length; i++) {
				if (last_brand != res[i][1]) {
					$("#caritem").append(
						"<optgroup label=" + res[i][1] + ">"
						+ "</optgroup>"
					);
				}
				$("optgroup[label=" + res[i][1] + "]").append(
					"<option value="+"carBrand=" + res[i][1] + "&carType="+ res[i][2] +">"
					+ res[i][2]
					+ "</option>"
				);
				last_brand = res[i][1];
			}
		}
	})
}
/* 選擇地區，價格及車型 */

function format1(item1) {
	opt1 = $("#areaitem").find(":selected");
	sel1 = opt1.text();
	og1 = opt1.closest("optgroup").attr("label");
	return (og1 ? og1 + " - " : "") + item1.text;
}

function format2(item2) {
	opt2 = $("#priceitem").find(":selected");
	sel2 = opt2.text();
	og2 = opt2.closest("optgroup").attr("label");
	return (og2 ? og2 + " - " : "") + item2.text;
}

function format3(item3) {
	opt3 = $("#caritem").find(":selected");
	sel3 = opt3.text();
	og3 = opt3.closest("optgroup").attr("label");
	return (og3 ? og3 + " - " : "") + item3.text;
}

function queryArea() {
	var qUrl = "/Car98-mvc/getStoreList?";
	if ($("#areaitem").val().length>0) {
		qUrl = qUrl +  $("#areaitem").val() + "&";
	}
	if ($("#priceitem").val().length>0) {
		qUrl = qUrl + $("#priceitem").val() + "&";
	}
	if ($("#caritem").val().length > 0) {
		qUrl = qUrl + $("#caritem").val();
	}
	if (qUrl[qUrl.length-1]=="&") {
		qUrl = qUrl.substring(0,qUrl.length-1);
	}
	console.log(qUrl);
	$.ajax({
		method: "POST",
		url: qUrl,
		success: function (res) {
			//console.log(res);
			rLength(res);
			test(res);
		}
	})
}

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 25.042576, lng: 121.535654 },
    zoom: 13,
  });
}

let map, markers, markerStores;

function test(list) {
  clearMarkers();
  markers = [];
  markerStores = {};
  let index = 0;

  // 1. get address
  // 2. address -> Lat Lng
  // 3. Lat Lng -> add Marker and Info Window

  function loop(c) {
    let address = list[c].city + list[c].district + list[c].street;
    let store = list[c].store;
    _geocoder(address, getGeoCallback(address, store));
  }

  // 由於Google Map API有查詢限制，尤其是地址轉緯經度，
  // 故設每660毫秒內，顯示一筆資料，防止over_query_limit發生
  let timer = setInterval(() => {
    while (index < list.length) {
      let address =
        list[index].city + list[index].district + list[index].street;
      if (!markerStores[address]) {
        markerStores[address] = true;
        loop(index);
        return;
      } else {
        console.log("duplicate");
      }
      index++;
    }
    console.log("clear");
    clearInterval(timer);
  }, 660);
}

function rLength(list){
	tempMarkerStores = {};
	for(let i=0;i<list.length;i++){
		let address = list[i].city + list[i].district + list[i].street;
		tempMarkerStores[address]=true;
	}
	$("#result").text("符合條件：共 " + Object.keys(tempMarkerStores).length + " 筆資料");
	$("#result").attr('type','text');
}

function getGeoCallback( address, store ) {
  return function (position) {
	// 標記設定
    const markerOptions = {
      position: position,
      map: map,
      zIndex: 1,
	};
	
	// 新建訊息視窗
    const infoWindow = new google.maps.InfoWindow({
      content:
        "<h4>租車行：</h4>" +
        "<a href='https://www.google.com.tw/maps?q="+address+"' target='_blank'><h5>" + store + "</h5></a><br>" +
        "<h4>地址：</h4>" +
        "<a href='https://www.google.com.tw/maps?q="+address+"' target='_blank'><h5>" + address + "</h5></a>",
	});
	
	// 新建標記
    const marker = new google.maps.Marker(markerOptions);
    markers.push(marker);
    marker.setMap(map);
    marker.addListener("mouseover", function () {
		marker.forceOpen=false;
    	infoWindow.open(map, marker);
	});
	marker.addListener("click", function () {
		marker.forceOpen=true;
    	infoWindow.open(map, marker);
	});
	marker.addListener("mouseout", function () {
		if (marker.forceOpen==false){
		infoWindow.close();
		}
	});

	// 新建標記後，自動調整畫面的縮放比例
	console.log("length:"+markers.length);
	var bounds = new google.maps.LatLngBounds();
	for (var i=0; i<markers.length; i++) {
		if(markers[i]){
			bounds.extend(markers[i].position);
		}else{
			console.log(i);
			console.log(markers[i]);
		}
	}
	map.fitBounds(bounds);
  };
}

// Address -> LanLng
function _geocoder(address, callback) {
  var geocoder = new google.maps.Geocoder();
  geocoder.geocode(
    {
      address: address,
    },
    function (results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        callback(results[0].geometry.location); //用一個 callback 就不用每次多寫上面這段
      }else{
		  console.log(status);
	  }
    }
  );
}

// 更新下拉式選單後，之前的標記便清除！
function clearMarkers() {
  if (markers && markers.length !== 0) {
    markers.forEach((marker) => marker.setMap(null));
    markers.length = 0;
  }
}
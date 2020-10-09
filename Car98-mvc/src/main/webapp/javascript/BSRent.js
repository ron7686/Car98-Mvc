$(document).ready(function () {
	// storeData();
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
			// console.log(ado);
			// console.log(ado[0][0]);
			// console.log(ado[0][1]);
			var last_city = "";
		
			for (i = 0; i < ado.length; i++) {
				if (last_city != ado[i][0]) {
					$("#areaitem").append(
						"<optgroup label=" + ado[i][0] + ">"
						+ "</optgroup>"
					);
				}
				$("optgroup[label=" + ado[i][0] + "]").append(
					"<option value="+"city=" +ado[i][0]+"&district="+ ado[i][1] + ">"
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
		// console.log(res);
		// console.log(res[0][0]);
		// console.log(res[0][1]);
		// console.log(res[0][2]);
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
		method: "GET",
		url: qUrl,
		success: function (res) {

		}
	})
}

// function storeData() {
// 	$.ajax({
// 		method: "GET",
// 		url: "/Car98-mvc/getStoreList",
// 		contentType: "application/json",
// 		success: function (res) {
// 		console.log(res);
// 		}
// 	})
// }
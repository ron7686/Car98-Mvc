$(document).ready(function() {
    getCityDistOptions();
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

function getCityDistOptions() {
	$.ajax({
		method : "GET",
		url : "/Car98-mvc/dist",
		contentType: "application/json",
		success : function(cd) {
			//console.log(cd);
			var last_city = "";
			// 先設一個變數last_city
			for (j = 0; j < cd.length; j++) {
				// 新增optgroup：city
				if (last_city != cd[j].city){
					$("#areaitem").append(
						"<optgroup label=" + cd[j].city + ">"
						                   + "</optgroup>"
						                   );
				}	
				// 新增option：district，option的爸爸為新產生的<optgroup></optgroup>
				$("optgroup[label=" + cd[j].city + "]").append(
				   "<option value=" + cd[j].city+"-"+cd[j].district + ">"
				   + cd[j].district
				   + "</option>"
				);
				last_city = cd[j].city;
			}
			// 預設第二個option value為selected
			$("#areaitem option:eq(1)").attr("selected", true);
			$("#areaitem").trigger("change");
		}
	})
}

function getCarDataOptions() {
	$.ajax({
		method : "GET",
		url : "/Car98-mvc/cartype",
		contentType: "application/json",
		success : function(res) {
			//console.log(res);
			var last_brand = "";
			// 先設一個變數last_brand
			for (i = 0; i < res.length; i++) {
				// 新增optgroup：carBrand
				if (last_brand != res[i].carBrand){
					$("#caritem").append(
						"<optgroup label=" + res[i].carBrand + ">"
						                   + "</optgroup>"
						                   );
				}	
				// 新增option：carType，option的爸爸為新產生的<optgroup></optgroup>
				$("optgroup[label=" + res[i].carBrand + "]").append(
				    "<option value=" + res[i].carBrand+"-"+res[i].carType + ">"
				     + res[i].carType
				     + "</option>"
				     );	
				
				last_brand = res[i].carBrand;	
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
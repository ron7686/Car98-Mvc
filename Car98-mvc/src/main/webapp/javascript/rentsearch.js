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

						// 					  for(var x=0; x<a.length; x++) {
						// 					      geocoder.geocode({
						// 					          'address': a[x]
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
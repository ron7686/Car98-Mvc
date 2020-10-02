<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!--    bootstrap  -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>
<!-- bootbox code -->
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js'>
</script>
</head>

<body>
	<div id="createtable" class="table-responsive"></div>

	<script>
          $(document).ready(function () {
               $.ajax({
                    url: 'http://localhost:8080/Car98-mvc/User',
                    type: 'GET',
                    dataType: 'json',
                    success(data) {
                         let table = $("<table border=\"1\" class='table'>");
                         table.appendTo($("#createtable"));
                         th = $(`<tr><th>編輯修改</th><th>會員ID</th><th>Email</th><th>姓名</th>
                                   <th>暱稱</th><th>電話</th><th>生日</th><th>性別</th><th>最後登入時間</th>
                                   <th>刪除</th</tr>`);
                         th.appendTo(table);
                         for (let i = 0; i < data.length; i++) {
                              tr = $(`<tr>`);
                              tr.appendTo(table);
                              td = $(`<td><a><img width='40' height='40' src="/Car98-mvc/image/editor.png"></a></td>`
                                   + `<td>` + data[i].memId
                                   + `</td>` + `<td>` + data[i].email + `</td>`
                                   + `</td>` + `<td>` + data[i].name + `</td>`
                                   + `</td>` + `<td>` + data[i].id + `</td>`
                                   + `</td>` + `<td>` + data[i].phone + `</td>`
                                   + `</td>` + `<td>` + data[i].birth + `</td>`
                                   + `</td>` + `<td>` + data[i].sex + `</td>`
                                   + `</td>` + `<td>` + data[i].loginTime + `</td>`
                                   + `<td><a class="deleteUser"><img width='40' height='40' src="/Car98-mvc/image/delete.png"></a></td>` + `</tr>`
                              );
                              // td = $(`<td>${data[i].memId}
                              //      </td><td>${data[i].email}</td>
                              //      </td><td>${data[i].name}</td>
                              //      </td><td>${data[i].id}</td>
                              //      </td><td>${data[i].phone}</td>
                              //      </td><td>${data[i].birth}</td>
                              //      </td><td>${data[i].sex}</td>
                              //      </td><td>${data[i].fileName}</td>`);
                              //                                         td += $(`<td>${data[i].name}</td>`);
                              //                                         td += $(`<td>${data[i].id}</td>`);
                              //                                         td += $(`<td>${data[i].phone}</td>`);
                              //                                         td += $(`<td>${data[i].birth}</td>`);
                              //                                         td += $(`<td>${data[i].sex}</td>`);
                              //                                         td += $(`<td>${data[i].fileName}</td>`);
                              td.appendTo(tr);
                         }
                         //                                    tr.appendTo(th);
                         $("#createtable").append("</table>");
                    },
               });
               $('.deleteUser').click(function(e) {
            	   bootbox.alert({
            		    message: "This is an alert with a callback!",
            		    callback: function () {
            		        console.log('This was logged in the callback!');
            		    }
            		});
				});
          });
     </script>
</body>

</html>
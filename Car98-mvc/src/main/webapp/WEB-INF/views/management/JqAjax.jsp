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

	<!-- JS dependencies -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<!--    bootstrap  -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
		src='https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js'></script>

</head>
<body>
	<div id="createtable" class="table-responsive"></div>
	<script>
          $(document).ready(function() {
               $.ajax({
                    url: 'http://localhost:8080/Car98-mvc/user',
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
                              td = $(`<td><a class="editor" href="#"><img width='40' height='40' src="/Car98-mvc/image/editor.png"></a></td>`
                                   + `<td>` + data[i].memId
                                   + `</td>` + `<td>` + data[i].email + `</td>`
                                   + `</td>` + `<td>` + data[i].name + `</td>`
                                   + `</td>` + `<td>` + data[i].id + `</td>`
                                   + `</td>` + `<td>` + data[i].phone + `</td>`
                                   + `</td>` + `<td>` + data[i].birth + `</td>`
                                   + `</td>` + `<td>` + data[i].sex + `</td>`
                                   + `</td>` + `<td>` + data[i].loginTime + `</td>`
                                   + `<td><a class="deleteUser" href="#"><img width='40' height='40' src="/Car98-mvc/image/delete.png" /></a></td></tr>`
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
          });
     </script>
     <script type="text/javascript">
		$("#createtable").on('click','.editor',function(e){
// 			alert(this.parentNode.parentNode.childNodes[1].innerHTML);
               // 導過去個人修改頁面
               let memId = this.parentNode.parentNode.childNodes[1].innerHTML;
			window.location.href = "/Car98-mvc/memberAx/"+memId;
		});
	 </script>
	 <script type="text/javascript">
	     $("#createtable").on('click','.deleteUser',function(e) {
	  	 e.preventDefault();
	  	 bootbox.confirm({
				title : "確認刪除",
				message : "確定要刪除嗎?",
				buttons : {
					cancel : {
						label : '<i class="fa fa-times"></i> 取消'
					},
					confirm : {
						label : '<i class="fa fa-check"></i> 確定'
					}
				},
				callback : function(result) {
// 					if(result){
// 						$.ajax({
//                                    url:'http://localhost:8080/Car98-mvc/User',
//                                    type:'DELETE',
//                                    dataType:'json',
//                                    complete(data){
//                                         console.log(this.parent());
//                                    }
//                               });
// 					}
				}
			});
		});
     </script>
</body>

</html>
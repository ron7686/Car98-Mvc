<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>

	<button id="loadButton">Load Data</button>
	<div id="feedback"></div>
	<script>
        function doFirst(){
            // 先跟畫面產生關連
            loadButton = document.getElementById('loadButton');
            feedback = document.getElementById('feedback');
            // 再建事件聆聽功能
            loadButton.addEventListener('click',function(){
                // step 1: 建立AJAX物件
                let xhr = new XMLHttpRequest();
                // step 2: 發出請求並傳送出去
                xhr.open('GET','http://localhost:8080/Car98-mvc/showUser',true);  // false:同步 | true:非同步
                xhr.send();
                //step 3:接收Server端傳回來的資料(文字檔格式)
                xhr.onreadystatechange = function() {
    				if (xhr.readyState == 4 && xhr.status == 200) {
    					feedback.innerHTML= showBooks(xhr.responseText);
    				}
    			}
            });
        }
        function showBooks(bookData) {
            let users = JSON.parse(bookData);

            let result = "<table border='1'><tr><th colspan='7'>會員資料</th></tr>";
    		result += "<tr><th width='100' align='center'>會員編號</th><th width='80' align='center'>姓名</th>";
    		result += "<th width='120' align='center'>暱稱</th><th width='48' align='center'>手機號碼</th>";
    		result += "<th width='60' align='center'>Email</th><th width='52' align='center'>性別</th>";
    		result += "<th width='80' align='center'>大頭貼</th></tr>";
    		
            for (var i = 0; i < users.length; i++) {
            	let user = users[i];
            	result += "<tr><td width='60'>" + user.memId + "</td>"
				+ "<td>" + user.name + "</td>"
				+ "<td>" + user.id + "</td>"
				+ "<td>" + user.phone + "</td>"
				+ "<td>" + user.email + "</td>"
				+ "<td>" + user.sex+ "</td>"
// 				+ "<td>" + "<img width='40' height='48' src='" + url + "'></td>"
				+ "</tr>";
           	}
            result += "</table>";
    		return result;
        }
        window.addEventListener('load',doFirst);
    </script>
</body>
</html>
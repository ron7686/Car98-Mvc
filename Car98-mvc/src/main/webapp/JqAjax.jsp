<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
          crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.min.js"></script>
</head>
<body>
     <div align='center'>
          <button id="ajaxButton" class="btn btn-info">
               Ajax
          </button>
          <p id="feedback"></p>

          <script>
               $(document).ready(function(){
                    $('#ajaxButton').click(function(){
                         axios.get('http://localhost:8080/Car98-mvc/showUser').then((result)=>{
                              for(let i=0;i<result.data.length;i++){
                                   console.log(result.data[i]);
                              }
                         }).catch((err)=>{

                         })
                    })
               });
          </script>
     </div>
</body>
</html>
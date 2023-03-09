<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  
  <script>
  let allEmpData = "";
  
  $(function(){
	  
	  outputEmp();
	  
  })
  
  function outputAllEmp(){
	   $.ajax({
	          url: "getAllEmp.do", // 데이터가 송수신될 서버의 주소 (서블릿의 매핑주소 작성)
	          type: "GET", // 통신 방식 (GET, POST, PUT, DELETE)
	          dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
	          success: function (data) {
	            // 통신이 성공하면 수행할 함수(콜백 함수)
	            console.log(data);
	            allEmpData = data;
	            if (data.status == "fail") {
	              alert("데이터를 불러오지 못했습니다!");
	            } else if (data.status == "success") {
	              outputEntireEmployees(allEmpData);
	            }
	          },
	         
	        });
  }
  
  </script>
  
</head>
<body>
<div class="container">
  <h2>Striped Rows</h2>
  <p class="respSummary"> 응답 받은 데이터 갯수 :<span class="respCnt"> <span> </span> </span> 응답 일시 :<span class=""> <span> </span> </span></p>            
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>john@example.com</td>
      </tr>
      <tr>
        <td>Mary</td>
        <td>Moe</td>
        <td>mary@example.com</td>
      </tr>
      <tr>
        <td>July</td>
        <td>Dooley</td>
        <td>july@example.com</td>
      </tr>
    </tbody>
  </table>
</div>
</body>
</html>
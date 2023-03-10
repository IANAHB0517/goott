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
	  
	  getAllEmp();
	  
  })
  
  function getAllEmp(){
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
	   					outEmps(allEmpData);
	              
	            }
	          },
	         
	        });
  }
  
  // json으로 가져온 사원들 출력
  function outEmps(json){
	  $(".respCnt").html(json.cnt);
	  $(".respDate").html(json.outputDate);
		// 테이블 헤드 입력	  
	  let outputTable = "<thead><tr><th>사번</th><th>이름</th><th>매니저</th><th>입사일</th><th>급여</th><th>커미션</th><th>부서번호</th></tr></thead><tbody>";
	  
	  //테이블 바디 입력
	  $.each(json.emps, function(i, e) {
		  outputTable += "<tr class='emp'>";
			  outputTable += "<td>" + e.ENAME + "</td>";
			  outputTable += "<td>" + e.JOB + "</td>";
			  
			  //매니저 번호를 이름으로 변경
			  let mag = e.MGR;
			  let magName = "";
			  $.each(json.emps, function (i, item) {
				  if (mag == item.ENAME) {
		              // 직속상관을 찾은경우
		              magName = item.ENAME;
		            }
			  });
			  
			  outputTable += "<td>" + magName + "</td>";
			  
			  outputTable += "<td>" + e.HIREDATE + "</td>";
			  outputTable += "<td>" + e.SAL + "</td>";
			  
			  if (e.COMM == 0.0) {
			  outputTable += "<td></td>";  
			  } else {
			  outputTable += "<td>" + e.COMM  * 100 + "</td>";				  
			  }
			  
			  
			  outputTable += "<td>" + e.DEPTNO + "</td>";
			  outputTable += "</tr>";
	  })
	  
	  outputTable += "</tbody>";
	  
	  $(".table-striped").html(outputTable)
	  
  }
  
  
  
  
  </script>
  
   <style>
     .table{
     margin : 5px;
     border : 1px solid black;
     }
     
      .emp:hover {
        background-color: yellow;
      }
     
      .icon {
        border-radius: 24px;
        background-color: gray;
        position: fixed;
        right: 15px;
        bottom: 10px;
      }

      .icon:hover {
        background-color: green;
      }
    </style>
  
</head>
<body>
<div class="container">
  <h2>Scott EMP</h2>
  <p class="respSummary"> 응답 받은 데이터 갯수 :<span class="respCnt"> <span> </span> </span> 응답 일시 :<span class="respDate"> <span> </span> </span></p>            
  <table class="table table-striped">
   
  </table>
  
  <div class="writerIcon">
        <img src="imgs/write.png" class="icon" />
      </div>
</div>
</body>
</html>
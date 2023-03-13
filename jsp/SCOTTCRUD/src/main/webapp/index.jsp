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
	  
	  
	  // 사원 입력 버튼 클릭시
	  $(".writerIcon").click(function (){
		  $("#writeModal").show(100);
	  });
	  
	  // 사원 저장
	  $("#addEmp").click(function (){
		  alert("사원 저장");
		  
		  let ENAME = $("#writeEmpName").val()
		  let JOB = $("#writeJobId").val()
		  let MGR = $("#writeMng").val()
		  let HIREDATE = $("#writeHdate").val()
		  let SAL = $("#writeSal").val()
		  let COMM = $("#writeComm").val()
		  let DEPTNO = $("#writeDept").val()
		  
		  let emp = {
			"ENAME" : ENAME ,
			"JOB" : JOB ,
			"MGR" : MGR ,
			"HIREDATE" : HIREDATE ,
			"SAL" : SAL ,
			"COMM" : COMM ,
			"DEPTNO" : DEPTNO
		  }
		  
		  console.log(emp)
	  });
	  
	  
	  //모달 닫기
	  $(".writeCloseBtn").click(function (){
		  $("#writeModal").hide();
	  });
	  
	  
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
	  let outputTable = "<thead><tr><th>사번</th><th>이름</th><th>직급</th><th>매니저</th><th>입사일</th><th>급여</th><th>커미션</th><th>부서번호</th></tr></thead><tbody>";
	  
	  //테이블 바디 입력
	  $.each(json.emps, function(i, e) {
		  outputTable += "<tr class='emp'>";
		  
			  outputTable += "<td>" + e.EMPNO + "</td>";
			  outputTable += "<td>" + e.ENAME + "</td>";
			  outputTable += "<td>" + e.JOB + "</td>";
			  
			  //매니저 번호를 이름으로 변경
			  let mag = e.MGR;
			  let magName = "";
			  $.each(json.emps, function (i, item) {
				  if (mag == item.EMPNO) {
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
  
  // 사원 입력
  function addEmp(){
	  
	 
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


<!-- The Modal -->
<div class="modal" id="writeModal" style="display: none">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="btn-close writeCloseBtn" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      <label for="writeEmpName" class="form-label">이름</label>
        <input type="text" id="writeEmpName" class="writeModalInput"  />
      </div>
      
      <div class="modal-body">
      <label for="writeJobId" class="form-label">직급</label>
        <input type="text" id="writeJobId" class="writeModalInput"  />
      </div>
      
      <div class="modal-body">
      <label for="writeMng" class="form-label">매니저</label>
        <input type="text" id="writeMng" class="writeModalInput"  />
      </div>
      
      <div class="modal-body">
      <label for="writeHdate" class="form-label">채용일</label>
        <input type="date" id="writeHdate" class="writeModalInput"  />
      </div>
      
      <div class="modal-body">
      <label for="writeSal" class="form-label">급여 : $<span id="writeSalary"></span></label> </label>
        <div id="salInput" style="display: none"></div>
      </div>
      
      <div class="modal-body">
      <label for="writeComm" class="form-label">상여금</label>
        <input type="number" id="writeComm" class="writeModalInput orm-control" min="0.01" max="0.99" step="0.01"  />
      </div>
      
      <div class="modal-body">
      <label for="writeDept" class="form-label">부서</label>
        <input type="text" id="writeDept" class="writeModalInput"  />
      </div>
      
      
      
      

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" id="addEmp" class="btn btn-success" data-bs-dismiss="modal">Add</button>
        <button type="button" class="btn btn-danger writeCloseBtn" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
</body>
</html>
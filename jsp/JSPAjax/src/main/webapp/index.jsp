<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <title>Employees CRUD with Ajax</title>
    <script>
      let jobs = null; // 수정과 생성때 모두 필요하기 때문에 전역으로  만들어둠
      let inputJobId = "";
      let inputSal = "";
      let allEmpData = "";
      let inputManagerId = "";
      let allDeptData = "";
      let inputDeptId = "";
      let order = "EMPLOYEE_ID";
      let searchName = null;

      $(document).ready(function () {
        getAllEmployees(searchName, order);
        getJobsData();
        getDeptInfo();
        
   		 // 정렬 순서 버튼 클릭시
      	$(".orderRadio").click(function () {
      		order = $(this).val();
      		console.log(order);
      		
      		getAllEmployees(searchName, order);
      	});
        
        // 사원 이름으로 검색시
        $(".searchName").keyup(function (){
        	searchName = $(this).val();
        	if (searchName.length > 1) {
        		// alert(searchName);
        		getAllEmployees(searchName, order);
        	}
        });

        $(".icon").click(function () {
          // 사원 입력시 필요한 부가 정보를 바인딩

          // 직급정보 동적 바인딩 및 출력
          $("#writeJobId").html(makeJobSelection());
       	  // 매니저정보 동적 바인딩 및 출력
          $("#writeMANAGER").html(makeMNGSelection());
          // 부서 정보 동적 바인딩 및 출력
          $("#writeDEPARTMENT").html(makeDptSelection());

          $("#writeModal").show(500);
        });

        // 사원 수정 버튼 클릭시
        $(".modiBtn").click(function () {
        	// 1) 유저가 입력한 값을 input 태그로부터 가져오기
            let modiFirstName = $("#modiFirstName").val();
            let modiLastName = $("#modiLastName").val();
            let modiEmail = $("#modiEmail").val();
            let modiPhoneNumber = $("#modiPhoneNumber").val();
            let modiHireDate = $("#modiHireDate").val();
            let modiJobId = $("#modiJobId").val();
            let modiSalary = Number($("#modiSal").val());  // modiSal 안되면 이거 해보기
            let modiCOMMISSION_PCT = $("#modiCOMMISSION_PCT").val();
            let modiMANAGER = $("#modiMANAGER").val();
            let modiDEPARTMENT = $("#modiDEPARTMENT").val();
        	let empNo = $("#modiEmpNo").val();
            
            		
                        let modiEmp = {
                        		"Employee_id" : empNo,
                          "FIRST_NAME": modiFirstName,
                          "LAST_NAME": modiLastName,
                          "EMAIL": modiEmail,
                          "PHONE_NUMBER": modiPhoneNumber,
                          "HIRE_DATE": modiHireDate,
                          "JOB_ID": modiJobId,
                          "SALARY": modiSalary,
                          "COMMISSION_PCT": modiCOMMISSION_PCT,
                          "MANAGER_ID": modiMANAGER,
                          "DEPARTMENT_ID": modiDEPARTMENT
                        };
            		
            
            console.log(modiEmp)
            
            modifyEmployee(modiEmp);
        });
        
      	
      
        // 사원 저장 버튼 클릭시
        $(".writeBtn").click(function () {
          // 1) 유저가 입력한 값을 input 태그로부터 가져오기
          let writeFirstName = $("#writeFirstName").val();
          let writeLastName = $("#writeLastName").val();
          let writeEmail = $("#writeEmail").val();
          let writePhoneNumber = $("#writePhoneNumber").val();
          let writeHireDate = $("#writeHireDate").val();
          let writeJobId = inputJobId;
          let writeSalary = Number($("#writeSal").val());
          let writeCOMMISSION_PCT = $("#writeCOMMISSION_PCT").val();
          let writeMANAGER = inputManagerId;
          let writeDEPARTMENT = inputDeptId;

          // 2) 유효성 검사하기
          let isValid = false;
          if (writeSalary == 0) {
            alert("급여를 선택하지 않았습니다");
            console.log(writeSalary);
          } else {
            isValid = true;
            console.log(writeSalary);
          }

          // 3) 유효성이 검증된 데이터를 ajax로 데이터를 서블릿으로 보내기
          if (isValid) {
            let emp = {
              FIRST_NAME: writeFirstName,
              LAST_NAME: writeLastName,
              EMAIL: writeEmail,
              PHONE_NUMBER: writePhoneNumber,
              HIRE_DATE: writeHireDate,
              JOB_ID: writeJobId,
              SALARY: writeSalary,
              COMMISSION_PCT: writeCOMMISSION_PCT,
              MANAGER_ID: writeMANAGER,
              DEPARTMENT_ID: writeDEPARTMENT,
            };
            console.log(emp);

            saveEmp(emp);
          }
        });

    
       
        // 직급정보 입력시
        $("#writeJobId").change(function () {
          if ($(this).val() != "") {
            // alert($(this).val()); // 선택한 옵션의 id 값을 가져와야 함

            let index = document.getElementById("writeJobId").selectedIndex;
            inputJobId = document
              .getElementById("writeJobId")
              .options[index].getAttribute("id");
            // options 속성 : select 태그 안에 있는 option 태그들 (배열 처럼 운용)
            // selectedIndex 속성 : 유저가 선택한 option 태그 번째.

            let salInfo = getSalInfo();
            let output = makeSalInput(salInfo, null);
            
            $("#salInput").html(output);
            $("#salInput").show();
          }
        });

        
        // 모달창 닫기
        $(".writeCloseBtn").click(function () {
          $("#writeModal").hide();
        });
        
        $(".modiCloseBtn").click(function () {
            $("#modiModal").hide();
          });
        
        $(".delCloseBtn").click(function () {
            $("#delModal").hide();
          });
        
        // 삭제 컨트롤러단 호출
        $(".delBtn").click(function () {
        	let empNo = $("#remEmpNO").html();
        	
        	 $.ajax({
                 url: "delEmp.do", // 데이터가 송수신될 서버의 주소 (서블릿의 매핑주소 작성)
                 type: "GET", // 통신 방식 (GET, POST, PUT, DELETE)
                 data : { "empNo" : empNo}, // 서블릿에 전송할 데이터
                 dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
                 success: function (data) {
                   // 통신이 성공하면 수행할 함수(콜백 함수)
                   console.log(data);
                   allEmpData = data;
                   if (data.status == "fail") {
                     alert("삭제 실패!");
                   } else if (data.status == "success") {
                	   getAllEmployees(); // 데이터 갱신
                	   $("#delModal").hide();
                   }
                 },
                 error: function () {},
                 complete: function () {},
               });
        	
        })

        $("#writeMANAGER").change(function () {
          if ($(this).val() != "") {
            let index = document.getElementById("writeMANAGER").selectedIndex;
            inputManagerId = document
              .getElementById("writeMANAGER")
              .options[index].getAttribute("id");
          }
          console.log(inputManagerId);
        });

        $("#writeDEPARTMENT").change(function () {
          if ($(this).val() != "") {
            let index =
              document.getElementById("writeDEPARTMENT").selectedIndex;
            inputDeptId = document
              .getElementById("writeDEPARTMENT")
              .options[index].getAttribute("id");
            console.log(inputDeptId);
          }
        });
      });

      function getSalInfo(){
    	  let minSal = 0;
          let maxSal = 0;
          // 선택한 직급의 최소 최대 급여를 얻어옴
          $.each(jobs.JOBS, function (i, e) {
            if (inputJobId == e.JOB_ID) {
              minSal = e.MIN_SALARY;
              maxSal = e.MAX_SALARY;
            }
          });
          console.log(minSal, maxSal);
          
          return {"minSal" : minSal,
        	  "maxSal" : maxSal};
      }
      
      function changeSal(sal) {
        $("#writeSalary").html(sal); // span 태그에 선택한 값을 넣어줌
      }

      // 급여정보를 동적으로 태그에 바인딩
      function makeSalInput(salInfo, mode) {
    	  let output = "";
    	  
    	  
    	  if (mode == null){
    		   output =
    	          "<input type='range' class='form-range' min='" +
    	          salInfo.minSal +
    	          "' max='" +
    	          salInfo.maxSal +
    	          "' id='writeSal' onchange='changeSal(this.value);' step='10'>";
    	  } else if (mode ="modi"){
    		   output =
    	          "<input type='range' class='form-range' min='" +
    	          salInfo.minSal +
    	          "' max='" +
    	          salInfo.maxSal +
    	          "' id='modiSal' onchange='modiChangeSal(this.value);' step='10'>";
    		  
    	  }
    	  
       
          
          return output;
        
      }
      
      function modiChangeSal(sal) {
    	  $("#writeSalary").html(sal);
      }

      //직급 정보를 동적으로 select 태그에 바인딩
      function makeJobSelection() {
        let output = "<option value=''> -- 직급을 선택하세요 --- </option>";
        $.each(jobs.JOBS, function (i, etc) {
          output +=
            "<option id = '" + etc.JOB_ID + "'value='"+ etc.JOB_ID + "'>" + etc.JOB_TITLE + "</option>";
          //output += "<option>" + etc.JOB_TITLE + "</option>";
        });

        return output;
        
      }

      //매니저 정보를 동적으로 select 태그에 바인딩
      function makeMNGSelection() {
        let output = "<option value=''> -- 매니저를 선택하세요 --- </option>";

        $.each(allEmpData.employees, function (i, etc) {
          output +=
            "<option id = '" + etc.EMPLOYEE_ID + "' value = '" + etc.EMPLOYEE_ID + "'>" +
            etc.FIRST_NAME +
            ", " +
            etc.LAST_NAME +
            "</option>";
        });

        return output;
      }

      //부서 정보를 동적으로 select 태그에 바인딩
      function makeDptSelection() {
        let output = "<option value=''> -- 부서를 선택하세요 --- </option>";
        $.each(allDeptData.depts, function (i, etc) {
          output +=
            "<option id = '" + etc.DEPARTMENT_ID + "' value='"+ etc.DEPARTMENT_ID + "'>" +
            etc.DEPARTMENT_NAME +
            "</option>";
        });

    
        
        return output
      }

      // 모든 직원 데이터를 얻어오는 함수
      function getAllEmployees(searchName, order) {
    	  let url ="getAllEmployees.do";
    	  
    	  if (searchName != null || order != null){
    		 url += "?";
    		  
    		  if(searchName != null) {
    			  url +=  "searchName=" + searchName + "&";
    		  }
    		  if(order != null) {
    			  url +=  "order=" + order  ;    			  
    		  }
    	  } 
    	  console.log(url);
        $.ajax({
          url: url, // 데이터가 송수신될 서버의 주소 (서블릿의 매핑주소 작성)
          type: "GET", // 통신 방식 (GET, POST, PUT, DELETE)
          dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
          success: function (data) {
            // 통신이 성공하면 수행할 함수(콜백 함수)
            console.log(data);
            allEmpData = data;
            if (data.status == "fail") {
              alert("데이터를 불러오지 못했습니다!");
            } else if (data.status == "success") {
              outputEntireEmployees(allEmpData, searchName);
            }
          },
          error: function () {},
          complete: function () {},
        });
      }
      
      // 모든 부서 데이터를 얻어오는 함수
      function getDeptInfo() {
        $.ajax({
          url: "getAllDept.do", // 데이터가 송수신될 서버의 주소 (서블릿의 매핑주소 작성)
          type: "GET", // 통신 방식 (GET, POST, PUT, DELETE)
          dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
          success: function (data) {
            // 통신이 성공하면 수행할 함수(콜백 함수)

            if (data.status == "fail") {
              alert("데이터를 불러오지 못했습니다!");
            } else if (data.status == "success") {
              console.log(data);
              allDeptData = data;
            }
          },
        });
      }

      function getJobsData() {
        $.ajax({
          url: "getJobsData.do", // 데이터가 송수신될 서버의 주소 (서블릿의 매핑주소 작성)
          type: "post", // 통신 방식 (GET, POST, PUT, DELETE)
          dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
          success: function (data) {
            // 통신이 성공하면 수행할 함수(콜백 함수)
            jobs = data;
            console.log(data);
          },
          error: function () {},
          complete: function () {},
        });
      }
	
      // 모든 직원 데이터 파싱하여 출력하는 함수
      function outputEntireEmployees(json, sn) {
        $("#outputCnt").html("데이터 : " + json.count + "개");
        $("#outputDate").html("출력 일시 : " + json.outputDate);

        let output = "<table class='table table-striped empInfo'>";
        output +=
          "<thead><tr><th>사번</th><th>이름</th><th>이메일</th><th>전화번호</th><th>입사일</th><th>직급</th><th>급여</th><th>커미션(%)</th>" +
          "<th>직속상관</th>" +
          // + "<th>소속 부서번호</th>"
          "<th>소속 부서명</th><th>수정하기</th><th>삭제하기</th></tr></thead><tbody>";

        $.each(json.employees, function (i, item) {
          output += "<tr class='emp'>";
          output += "<td>" + item.EMPLOYEE_ID + "</td>";
          if (sn == null) {
        	   output += "<td>" + item.FIRST_NAME + ", " + item.LAST_NAME + "</td>";
          } else if (sn != null){
        	  let fullName = item.FIRST_NAME + ", " + item.LAST_NAME
        	  let fi = fullName.indexOf(sn);
        	          	  
        	  let fName = fullName.split(sn)[0]
        	  let bName = fullName.split(sn)[1];
        	  
        	  console.log(fullName, sn, fName, bName);
        	  
        	  
        	   output += "<td>" + fName + "<mark>" + sn +"</mark>" + bName + "</td>";
          }
               output += "<td>" + item.EMAIL + "</td>";
          output += "<td>" + item.FIRST_NAME + ", " + item.LAST_NAME + "</td>";
          output += "<td>" + item.EMAIL + "</td>";
          output += "<td>" + item.PHONE_NUMBER + "</td>";
          output += "<td>" + item.HIRE_DATE + "</td>";
          output += "<td>" + item.JOB_ID + "</td>";
          output += "<td>$" + item.SALARY + "</td>";
          if (item.COMMISSION_PCT == 0.0) {
            output += "<td></td>";
          } else {
            output += "<td>" + item.COMMISSION_PCT * 100 + "%</td>";
          }

          let mngId = item.MANAGER_ID;
          let mngName = "";
          $.each(json.employees, function (i, item) {
            if (mngId == item.EMPLOYEE_ID) {
              // 직속상관을 찾았다.
              mngName = item.FIRST_NAME + ", " + item.LAST_NAME;
            }
          });

          output += "<td>" + mngName + "</td>";
          // output += "<td>" + item.DEPARTMENT_ID + "</td>";
          output += "<td>" + item.DEPARTMENT_NAME + "</td>";

          // empmanage를 제이쿼리로 이벤트 를 감지하면 둘중 누구를 클릭한 건지 알기가 쉽지 않기 때문에
          // 온클릭으로 작성하는게 더 편하다
          // 이클립스 자동 줄 바꿈 alt + shift + y
          output += "<td><img class='empmanage' src='imgs/modify.png' onclick='modModalShow(" + item.EMPLOYEE_ID + ");'></td>"
          output += "<td><img class='empmanage' src='imgs/delete.png' onclick='delModalShow(" + item.EMPLOYEE_ID + ");'></td>"
          output += "</tr>";
        });

        output += "</table>";

        $(".empInfo").html(output);
      }
      
      
      function saveEmp(emp) {
          console.log(emp);
          console.log(JSON.stringify(emp));
          
          
          $.ajax({
             url : "saveEmp.do", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
             type : "get", // 통신 방식 (GET, POST, PUT, DELETE)
             data : emp, // 서블릿에 전송할 데이터
             dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
             success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
                console.log(data);
             
             if(data.status == "success"){
            	 $("#writeModal").hide();
            	 writeModalInit();
            	 getAllEmployees();
             } else if (data.status == "fail"){
            	 alert("저장에 실패 했습니다.");
             }
                
             }
          });
          
       }
      
      
      function writeModalInit(){
    	  $("#writeFirstName").val('');
          $("#writeLastName").val('');
          $("#writeEmail").val('');
          $("#writePhoneNumber").val('');
          $("#writeHireDate").val('');
          
          //let writeSalary = Number($("#writeSalary").html());
          $("#writeCOMMISSION_PCT").val('');
 
      }
      
      
      // 수정 모달 띄우기
      function modModalShow(empNo){
    	// alert(empNo);
    	
    	$.ajax({
             url : "getEmp.do", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
             type : "post", // 통신 방식 (GET, POST, PUT, DELETE)
             data : {
            	 "empNo" : empNo
             }, // 서블릿에 전송할 데이터
             dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
             success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
                console.log(data);
             
             if(data.status == "success"){
            	bindingDataModModal(data);
            	
             } else if (data.status == "fail"){
            	 alert("저장에 실패 했습니다.");
             }
                
             }
          });
    	
    	
    	  $("#modiModal").show(500);
    	  $("#modiEmpNo").val(empNo);
      }
      
      function delModalShow(empNo){
    	  //alert(empNo);
    	  $("#delModal").show(500);
    	  $("#remEmpNO").html(empNo);
      }
      
      // 수정할 사원 바인딩
      function bindingDataModModal(data){
    	  $("#modiFirstName").val(data.employee.FIRST_NAME);
    	  $("#modiLastName").val(data.employee.LAST_NAME);
    	  $("#modiEmail").val(data.employee.EMAIL);
    	  $("#modiPhoneNumber").val(data.employee.PHONE_NUMBER);
    	  
    	  $("#modiHireDate").val(data.employee.HIRE_DATE.split(" ")[0]);
    	  
    	  $("#modiJobId").html(makeJobSelection());
    	  $("#modiJobId").val(data.employee.JOB_ID);
    	  

    	  let minSal = 0;
          let maxSal = 0;
          // 선택한 직급의 최소 최대 급여를 얻어옴
          $.each(jobs.JOBS, function (i, e) {
            if (data.employee.JOB_ID == e.JOB_ID) {
              minSal = e.MIN_SALARY;
              maxSal = e.MAX_SALARY;
            }
            
           let salInfo =  {"minSal" : minSal, "maxSal" : maxSal};          
           
           
           $("#salModiInput").html(makeSalInput(salInfo, "modi"));
           $("#salModiInput").show();
            
          });

    	  $("#modiSalary").val(data.employee.SALARY);
    	  
    	  $("#modiCOMMISSION_PCT").val(data.employee.COMMISSION_PCT);
    	 
    	  $("#modiMANAGER").html(makeMNGSelection());
    	  if (data.employee.MANAGER_ID != "0") {
    	  $("#modiMANAGER").val(data.employee.MANAGER_ID);    		  
    	  }
    	  
    	  $("#modiDEPARTMENT").html(makeDptSelection());
    	  $("#modiDEPARTMENT").val(data.employee.DEPARTMENT_ID);
    	  
      }
      
      
      //사원 수정 정보를 서블릿으로 요청하는 함수
      function modifyEmployee(modiEmp){
    	  $.ajax({
              url : "ModiEmp.do", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
              type : "get", // 통신 방식 (GET, POST, PUT, DELETE)
              data : modiEmp, // 서블릿에 전송할 데이터
              dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
              success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
                 console.log(data);
              
              if(data.status == "success"){
             	 $("#modiModal").hide();
             	 getAllEmployees();
              } else if (data.status == "fail"){
             	 alert("수정에 실패 했습니다.");
              }
                 
              }
           });
    	  
    	  
      }
      
      
    </script>
    <style>
    .empmanage {
    width:24px;
    
    }
      .empInfo {
        font-size: 12px;
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
      <h1>Employees CRUD with Ajax</h1>
      
	<div class="searchEmp">
	<input type="text" class="form-control form-control-lg searchName" placeholder="input search Employees Name">
	</div>
	<!-- 숙제 -->
	<div class="orderMethod">
	<input class="form-check-input orderRadio" type="radio" id="radioNo" name="orderMethod" value="EMPLOYEE_ID" checked> 사번순
	<input class="form-check-input orderRadio" type="radio" id="radioName" name="orderMethod" value="FIRST_NAME"> 이름순
	<input class="form-check-input orderRadio" type="radio" id="radioHireDate" name="orderMethod" value="HIRE_DATE"> 입사일순
	</div>
      </div>
      
    

    <div class="row" style="border: 1px solid black; color: blue; margin: 10px">
      <div class="col-sm-3">
        <p id="outputCnt">....</p>
      </div>
      <div class="col-sm-9">
        <p id="outputDate">.....</p>
      </div>
      <div class="empInfo"></div>
      <div class="writerIcon">
        <img src="imgs/write.png" class="icon" />
      </div>
    </div>

    <!-- The Modal (사원 입력 모달) -->
    <div class="modal" id="writeModal" style="display: none">
      <div class="modal-dialog">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">사원 입력</h4>
            <button
              type="button"
              class="btn-close writeCloseBtn"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="mb-3 mt-3">
              <label for="writeFirstName" class="form-label"
                >First Name :</label
              >
              <input type="text" class="form-control" id="writeFirstName" />
            </div>
            <div class="mb-3 mt-3">
              <label for="writeLastName" class="form-label">Last Name :</label>
              <input type="text" class="form-control" id="writeLastName" />
            </div>
            <div class="mb-3 mt-3">
              <label for="writeEmail" class="form-label">Email :</label>
              <input type="text" class="form-control" id="writeEmail" />
            </div>
            <div class="mb-3 mt-3">
              <label for="writePhoneNumber" class="form-label"
                >Phone Number :</label
              >
              <input type="text" class="form-control" id="writePhoneNumber" />
            </div>
            <div class="mb-3 mt-3">
              <label for="writeHireDate" class="form-label">Hire Date :</label>
              <input type="date" class="form-control" id="writeHireDate" />
            </div>
            <div class="mb-3 mt-3">
              <label for="writeJobId" class="form-label">Job Id :</label>
              <select id="writeJobId"></select>
            </div>
            <div class="mb-3 mt-3">
              <label for="writeSalary" class="form-label"
                >Salary : $<span id="writeSalary"></span
              ></label>
              <div id="salInput" style="display: none"></div>
            </div>
            <div class="mb-3 mt-3">
              <label for="writeCOMMISSION_PCT" class="form-label"
                >Commission :</label
              >
              <input
                type="number"
                class="form-control"
                id="writeCOMMISSION_PCT"
                min="0.01"
                max="0.99"
                step="0.01"
              />
            </div>
            <div class="mb-3 mt-3">
              <label for="writeMANAGER" class="form-label">MANAGER :</label>
              <select id="writeMANAGER">
                <!-- 아이디는 사번, 옵션의 밸류는 이름, 성 -->
              </select>
            </div>
            <div class="mb-3 mt-3">
              <label for="writeDEPARTMENT" class="form-label"
                >DEPARTMENT :</label
              >
              <select id="writeDEPARTMENT">
                <!-- 아이디는 부서번호, 옵션의 밸류는 이름 -->
              </select>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-success writeBtn"
              data-bs-dismiss="modal"
            >
              Save
            </button>

            <button
              type="button"
              class="btn btn-danger writeCloseBtn"
              data-bs-dismiss="modal"
            >
              Close
            </button>
          </div>
        </div>
      </div>
    </div>
  
   <!-- The Modal (사원 수정 모달) -->
    <div class="modal" id="modiModal" style="display: none">
      <div class="modal-dialog">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">사원 수정</h4>
            <button
              type="button"
              class="btn-close modiCloseBtn"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
          
           <div class="mb-3 mt-3">
              <label for="modiEmpNo" class="form-label">EmployeeID :</label>
              <input type="text" class="form-control" id="modiEmpNo"  readonly/>
            </div>
          
            <div class="mb-3 mt-3">
              <label for="modiFirstName" class="form-label"
                >First Name :</label>
              <input type="text" class="form-control" id="modiFirstName" />
            </div>
            
            <div class="mb-3 mt-3">
              <label for="modiLastName" class="form-label">Last Name :</label>
              <input type="text" class="form-control" id="modiLastName" />
            </div>
            
            <div class="mb-3 mt-3">
              <label for="modiEmail" class="form-label">Email :</label>
              <input type="text" class="form-control" id="modiEmail" />
            </div>
            <div class="mb-3 mt-3">
              <label for="modiPhoneNumber" class="form-label"
                >Phone Number :</label
              >
              <input type="text" class="form-control" id="modiPhoneNumber" />
            </div>
            <div class="mb-3 mt-3">
              <label for="modiHireDate" class="form-label">Hire Date :</label>
              <input type="date" class="form-control" id="modiHireDate" />
            </div>
            <div class="mb-3 mt-3">
              <label for="modiJobId" class="form-label">Job Id :</label>
              <select id="modiJobId"></select>
            </div>
            <div class="mb-3 mt-3">
              <label for="modiSalary" class="form-label"
                >Salary : $<span id="modiSalary"></span
              ></label>
              <div id="salModiInput" style="display: none"></div>
            </div>
            <div class="mb-3 mt-3">
              <label for="modiCOMMISSION_PCT" class="form-label"
                >Commission :</label
              >
              <input
                type="number"
                class="form-control"
                id="modiCOMMISSION_PCT"
                min="0.01"
                max="0.99"
                step="0.01"
              />
            </div>
            <div class="mb-3 mt-3">
              <label for="modiMANAGER" class="form-label">MANAGER :</label>
              <select id="modiMANAGER">
                <!-- 아이디는 사번, 옵션의 밸류는 이름, 성 -->
              </select>
            </div>
            <div class="mb-3 mt-3">
              <label for="modiDEPARTMENT" class="form-label"
                >DEPARTMENT :</label
              >
              <select id="modiDEPARTMENT">
                <!-- 아이디는 부서번호, 옵션의 밸류는 이름 -->
              </select>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-success modiBtn"
              data-bs-dismiss="modal"
            >
              modify
            </button>

            <button
              type="button"
              class="btn btn-danger modiCloseBtn"
              data-bs-dismiss="modal"
            >
              Close
            </button>
          </div>
        </div>
      </div>
    </div>
   
   
    <!-- The Modal (사원 삭제 모달) -->
    <div class="modal" id="delModal" style="display: none">
    	<div class="modal-dialog">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">사원 삭제</h4>
            <button
              type="button"
              class="btn-close delCloseBtn"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
          <div class="delCehck">
         <h4><span id="remEmpNO"></span>번 사원을 정말 삭제 하시겠습니까?</h4>
         <h6>이 작업은 취소할 수 없습니다.</h6>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-danger delBtn"
              data-bs-dismiss="modal"
            >
              삭제
            </button>

            <button
              type="button"
              class="btn btn-danger delCloseBtn"
              data-bs-dismiss="modal"
            >
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
   
  </body>
</html>

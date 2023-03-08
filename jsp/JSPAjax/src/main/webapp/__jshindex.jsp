<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
   rel="stylesheet">
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<title>Insert title here</title>
<script>
   let jobs = null;
   let inputJobId = "";
   let inputSal = 0;
   let allEmpData = null;
   let allDeptData = null;
   
   let inputManagerId = "";
   let inputDeptId = "";
   
   
   $(document).ready(function() {
      getAllEmployees();
      getDeptInfo();
      getJobsData();
      
       // 사원입력 버튼을 클릭시
      $(".writeIcon").click(function() {
         // 사원 입력시 필요한 부가 정보를 바인딩
         makeJobSelection();
         makeManagerSelection();
         makeDeptSelection();
         
         $("#writeModal").show(500);
      });
      
      // 직급정보 입력시
      $("#writeJobID").change(function() {
         if ($(this).val() != '') {
            //alert($(this).val());
            // options 속성 : select 태그 안에 있는 option 태그들 (배열 처럼 운용)
               // selectedIndex 속성 : 유저가 선택한 option 태그 번째.
            let index = document.getElementById("writeJobID").selectedIndex;
            inputJobId = document.getElementById("writeJobID").options[index].getAttribute("id");
            
            let minSal = 0;
            let maxSal = 0;
            
            // 선택한 직급의 최소급여, 최대급여를 얻어옴 
            $.each(jobs.JOBS, function(i, e){
               if (inputJobId == e.JOB_ID) {
                  minSal = e.MIN_SALARY;
                  maxSal = e.MAX_SALARY;
               }
            });
            
            console.log(minSal, maxSal);
            
            makeSalInput(minSal, maxSal);
         }
         
      });

      $(".writecloseBtn").click(function() {
         $("#writeModal").hide();
      });
      
      
      $("#writeManger").change(function(){
         if($(this).val() != '') {
            let index = document.getElementById("writeManger").selectedIndex;
            inputManagerId = document.getElementById("writeManger").options[index].getAttribute("id");
         }
      });
      
      $("#writeDepartment").change(function(){
         if($(this).val() != '') {
            let index = document.getElementById("writeDepartment").selectedIndex;
            inputDeptId = document.getElementById("writeDepartment").options[index].getAttribute("id");
         }
      });
      
      //사원 저장 버튼 클릭시
      $(".writeBtn").click(function(){
         // 1) input 태그들로부터 유저가 입력한 값을 가져오기
         let writeFirstName = $("#writeFirstName").val();
         let writeLastName = $("#writeLastName").val();
         let writeEmail = $("#writeEmail").val();
         let writePhoneNumber = $("#writePhoneNumber").val();
         let writeHireDate = $("#writeHireDate").val();
         let writeJobId = inputJobId;
         let writeSal = Number($("#writeSalary").html());
         let writeComm = $("#writeComm").val();
         let writeManagerId = inputManagerId;
         let writeDeptId = inputDeptId;
         
         // 2) 유효성 검사하기
         let isValid = false;
         if(writeSal == 0) {
            alert("급여를 선택하지 않았습니다");
         } else {
            isValid = true;;
         }
         
         // 3) 유효성 검사에 통과하면 ajax로 데이터를 서블릿으로 보내기
         if (isValid) {
            let emp = {
                  "FIRST_NAME" : writeFirstName,
                  "LAST_NAME" : writeLastName,
                  "EMAIL" : writeEmail,
                  "PHONE_NUMBER" : writePhoneNumber,
                  "HIRE_DATE" : writeHireDate,
                  "JOB_ID" : writeJobId,
                  "SALARY" : writeSal,
                  "COMMISSION_PCT" : writeComm,
                  "MANAGER_ID" : writeManagerId,
                  "DEPARTMENT_ID" : writeDeptId
               };
            saveEmp(emp);
         }
         
         
         
      });
      
   });
   
   function saveEmp(emp) {
      console.log(emp);
   }
   
   function makeDeptSelection() {
      let output = "<option value=''>---부서를 선택하세요---</option>";
      $.each(allDeptData.depts, function (i, e) {
         output += "<option id='" + e.DEPARTMENT_ID + "'>" + e.DEPARTMENT_NAME + "</option>";
      });
      $("#writeDepartment").html(output);
   }
   
   // 매니저 정보를 동적으로 태그에 바인딩
   function makeManagerSelection() {
      let output = "<option value=''>---매니저를 선택하세요---</option>";
      $.each(allEmpData.employees, function (i, e) {
         output += "<option id='" + e.EMPLOYEE_ID + "'>" + e.FIRST_NAME + ", " + e.LAST_NAME + "</option>";
      });
      $("#writeManger").html(output);
   }
   
   // 급여값을 선택할 때
   function changeSal(sal) {
      $("#writeSalary").html(sal); // span태그에 넣어줌
   }
   
   // 급여정보를 동적으로 태그에 바인딩
   function makeSalInput(minSal, maxSal) {
      let output = "<input type='range' class='form-range' min='" + minSal +"' max='" 
         +  maxSal + "' id='writeSal' onchange='changeSal(this.value);' step='10'>";
      $("#salInput").html(output);
      $("#salInput").show();
      
   }
   
   // 직급 정보를 동적으로 select 태그에 바인딩
   function makeJobSelection() {
      let output = "<option value=''>--- 직급을 선택하세요 ---</option>";
      $.each(jobs.JOBS, function(i, elt) {
         output += "<option id='" + elt.JOB_ID + "'>" + elt.JOB_TITLE + "</option>";
      });
      
      $("#writeJobID").html(output);
   }
   
   // JOBS 테이블 정보를 얻어오는 함수
   function getJobsData() {
      $.ajax({
         url : "getJobsData.do", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
         type : "POST", // 통신 방식 (GET, POST, PUT, DELETE)
         dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
         success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
            console.log(data);
            jobs = data;
         }
      });
   }
   
   // 모든 부서 데이터를 얻어오는 함수
   function getDeptInfo() {
      $.ajax({
         url : "getAllDept.do", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
         type : "GET", // 통신 방식 (GET, POST, PUT, DELETE)
         dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
         success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
            if (data.status == "fail") {
               alert("데이터를 불러오지 못했습니다!");
            } else if (data.status == "success") {
               console.log(data);   
               allDeptData = data;
            }
         }
      });
   }

   // 모든 직원 데이터를 얻어오는 함수
   function getAllEmployees() {
      $.ajax({
         url : "getAllEmployees.do", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
         type : "GET", // 통신 방식 (GET, POST, PUT, DELETE)
         dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
         success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
            console.log(data);
            if (data.status == "fail") {
               alert("데이터를 불러오지 못했습니다!");
            } else if (data.status == "success") {
               allEmpData = data;
               outputEntireEmployees(data);
            }
         }
      });
   }

   function outputEntireEmployees(json) {
      $("#outputCnt").html("데이터 : " + json.count + "개");
      $("#outputDate").html("출력 일시 : " + json.outputDate);

      let output = "<table class='table table-striped empInfo'>";
      output += "<thead><tr><th>사번</th><th>이름</th><th>이메일</th><th>전화번호</th><th>입사일</th>"
            + "<th>직급</th><th>급여</th><th>커미션(%)</th><th>직속상관번호</th>"
            + "<th>소속부서명</th></tr></thead><tbody>";

      $.each(json.employees, function(i, item) {
         output += "<tr class='emp'>";
         output += "<td>" + item.EMPLOYEE_ID + "</td>";
         output += "<td>" + item.FIRST_NAME + ", " + item.LAST_NAME
               + "</td>";
         output += "<td>" + item.EMAIL + "</td>";
         output += "<td>" + item.PHONE_NUMBER + "</td>";
         output += "<td>" + item.HIRE_DATE + "</td>";
         output += "<td>" + item.JOB_ID + "</td>";
         output += "<td>$" + item.SALARY + "</td>";
         if (item.COMMISSION_PCT == "0.0") {
            output += "<td></td>";
         } else {
            output += "<td>" + item.COMMISSION_PCT * 100 + "%</td>";
         }

         let managerID = item.MANAGER_ID;
         let managerName = "";
         $.each(json.employees, function(i, item) {
            if (managerID == item.EMPLOYEE_ID) { // 직속상관을 찾았다!
               managerName = item.FIRST_NAME + ", " + item.LAST_NAME
            }
         });
         output += "<td>" + managerName + "</td>";

         //output += "<td>" + item.DEPARTMENT_ID + "</td>";
         output += "<td>" + item.DEPARTMENT_NAME + "</td>";

         output += "</tr>";
      });

      output += "</table>";
      $(".empInfo").html(output);
   }
</script>
<style>
.empInfo {
   font-size: 12px;
}

.emp:hover {
   background-color: yellow;
}

.writeIcon {
   width: 50px;
   height: 50px;
   border-radius: 25px;
   background-color: pink;
   position: fixed;
   right: 15px;
   bottom: 10px;
}
</style>
</head>
<body>
   <div class="container">
      <h1>Employees CRUD with Ajax</h1>
      <div class="row" style="padding: 10px; color: gray;">
         <div class="col-sm-3">
            <p id="outputCnt"></p>
         </div>
         <div class="col-sm-9">
            <p id="outputDate"></p>
         </div>
      </div>
      <div class="empInfo"></div>
      <div class="writeIcon">
         <img src="images/write.png" />
      </div>
   </div>

   <!-- The Modal -->
   <div class="modal" id="writeModal" style="display: none;">
      <div class="modal-dialog">
         <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
               <h4 class="modal-title">사원 입력</h4>
               <button type="button" class="btn-close writecloseBtn"
                  data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
               <div class="mb-3 mt-3">
                  <label for="writeFirstName" class="form-label">FirstName:</label> <input
                     type="text" class="form-control" id="writeFirstName"/>
               </div>
               
               <div class="mb-3 mt-3">
                  <label for="writeLastName" class="form-label">LastName:</label> <input
                     type="text" class="form-control" id="writeLastName"/>
               </div>
               
               <div class="mb-3 mt-3">
                  <label for="writeEmail" class="form-label">Email:</label> <input
                     type="text" class="form-control" id="writeEmail"/>
               </div>

               <div class="mb-3 mt-3">
                  <label for="writePhoneNumber" class="form-label">PhoneNumber:</label> <input
                     type="text" class="form-control" id="writePhoneNumber"/>
               </div>
               
               <div class="mb-3 mt-3">
                  <label for="writeHireDate" class="form-label">HireDate:</label>
                  <input type="date" class="form-control" id="writeHireDate" />
               </div>
               
               <div class="mb-3 mt-3">
                  <label for="writeJobID" class="form-label">JOB_ID:</label>
                  <select id="writeJobID">
                  </select>
               </div>
               
               <div class="mb-3 mt-3">
                  <label for="writeSalary" class="form-label">Salary: $<span id="writeSalary"></span>
                  </label>
                  <div id="salInput" style="display : none;"></div>
               </div>
               
               <div class="mb-3 mt-3">
                  <label for="writeComm" class="form-label">Commission : </label>
                  <input type="number" class="form-control" id="writeComm" min="0.01" max="0.99" 
                  step="0.01" />
               </div>
               
               <div class="mb-3 mt-3">
                  <label for="writeManger" class="form-label">Manager:</label>
                  <select id="writeManger">
                  </select>
               </div>
               
               <div class="mb-3 mt-3">
                  <label for="writeDepartment" class="form-label">Department :</label>
                  <select id="writeDepartment">
                  </select>
               </div>
               
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
               <button type="button" class="btn btn-info writeBtn">Save</button>
               <button type="button" class="btn btn-danger writecloseBtn"
                  data-bs-dismiss="modal">Close</button>
            </div>

         </div>
      </div>
   </div>
</body>
</html>
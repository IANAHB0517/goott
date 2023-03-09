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

      $(document).ready(function () {
        getAllEmployees();
        getJobsData();
        getDeptInfo();

        $(".icon").click(function () {
          // 사원 입력시 필요한 부가 정보를 바인딩

          makeJobSelection();
          makeMNGSelection();
          makeDptSelection();

          $("#writeModal").show(500);
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
          let writeSalary = Number($("#writeSalary").html());
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
            makeSalInput(minSal, maxSal);
          }
        });

        $(".writeCloseBtn").click(function () {
          $("#writeModal").hide();
        });

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

      function changeSal(sal) {
        $("#writeSalary").html(sal); // span 태그에 선택한 값을 넣어줌
      }

      // 급여정보를 동적으로 태그에 바인딩
      function makeSalInput(minSal, maxSal) {
        let output =
          "<input type='range' class='form-range' min='" +
          minSal +
          "' max='" +
          maxSal +
          "' id='writeSal' onchange='changeSal(this.value);' step='10'>";
        $("#salInput").html(output);
        $("#salInput").show();
      }

      //직급 정보를 동적으로 select 태그에 바인딩
      function makeJobSelection() {
        let output = "<option value=''> -- 직급을 선택하세요 --- </option>";
        $.each(jobs.JOBS, function (i, etc) {
          output +=
            "<option id = '" + etc.JOB_ID + "'>" + etc.JOB_TITLE + "</option>";
          //output += "<option>" + etc.JOB_TITLE + "</option>";
        });

        $("#writeJobId").html(output);
      }

      //매니저 정보를 동적으로 select 태그에 바인딩
      function makeMNGSelection() {
        let output = "<option value=''> -- 매니저를 선택하세요 --- </option>";

        $.each(allEmpData.employees, function (i, etc) {
          output +=
            "<option id = '" +
            etc.EMPLOYEE_ID +
            "'>" +
            etc.FIRST_NAME +
            ", " +
            etc.LAST_NAME +
            "</option>";
        });

        $("#writeMANAGER").html(output);
      }

      //부서 정보를 동적으로 select 태그에 바인딩
      function makeDptSelection() {
        let output = "<option value=''> -- 부서를 선택하세요 --- </option>";
        $.each(allDeptData.depts, function (i, etc) {
          output +=
            "<option id = '" +
            etc.DEPARTMENT_ID +
            "'>" +
            etc.DEPARTMENT_NAME +
            "</option>";
        });

        $("#writeDEPARTMENT").html(output);
      }

      // 모든 직원 데이터를 얻어오는 함수
      function getAllEmployees() {
        $.ajax({
          url: "getAllEmployees.do", // 데이터가 송수신될 서버의 주소 (서블릿의 매핑주소 작성)
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

      function outputEntireEmployees(json) {
        $("#outputCnt").html("데이터 : " + json.count + "개");
        $("#outputDate").html("출력 일시 : " + json.outputDate);

        let output = "<table class='table table-striped empInfo'>";
        output +=
          "<thead><tr><th>사번</th><th>이름</th><th>이메일</th><th>전화번호</th><th>입사일</th><th>직급</th><th>급여</th><th>커미션(%)</th>" +
          "<th>직속상관</th>" +
          // + "<th>소속 부서번호</th>"
          "<th>소속 부서명</th></tr></thead><tbody>";

        $.each(json.employees, function (i, item) {
          output += "<tr class='emp''>";
          output += "<td>" + item.EMPLOYEE_ID + "</td>";
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
      
      
      
    </script>
    <style>
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

    <!-- The Modal -->
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
  </body>
</html>

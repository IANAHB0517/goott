<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="../js/commonJS.js"></script>
    <title>회원 가입 페이지</title>

    <style>
      fieldset {
        padding: 10px;
        background-color: #eeeeee;
        margin-top: 20px;
        margin-bottom: 20px;
      }

      legend {
        background-color: gray;
        color: white;
        padding: 5px 10px;
      }

      .errMsg {
        padding: 10px;
      }

      #memberImgPreview {
        width: 250px;
      }
    </style>

    <script>
      $(document).ready(function () {
        if (getParameter("status") == "fail") {
          alert("회원가입 실패!");
        }

        // on()을 통해 2개 이상의 이벤트에 대해 호출 되도록 함.
        $("#userId").on("keyup blur", function () {
          let userId = $("#userId").val();
          if (userId.length >= 4) {
            duplicateUserId(userId);
          }
        });

        $("#pwd1").on("keyup blur", function () {
          validPwd1();
        });

        $("#pwd2").on("keyup blur", function () {
          validPwd2();
        });

        $("#email").on("keyup blur", function () {
          validEmail();
        });

        $("#mobile").on("keyup blur", function () {
          validMobile();
        });

        $("#memberImg").change(function (e) {
          if (validImg()) {
            // 이미지 파일인 경우에만
            let file = e.target.files[0];

            let reader = new FileReader(); // FileReader 객체 생성
            reader.onload = function (evt) {
              $("#memberImgPreview").attr("src", evt.target.result); // 파일의 실제 위치를 src 속성에 넣어줌
            }; // 파일을 다 읽었을 떄 호출 되는 콜백 함수

            reader.readAsDataURL(file); // 파일의 위치를 얻어온다.
          }
        });
      });
      function validImg() {
        let isValid = false;

        let fileName = $("#memberImg").val();
        let ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        let imgArr = ["gif", "jpg", "png", "jpeg", "jfif"];
        //console.log(fileName, ext);

        $.each(imgArr, function (i, elt) {
          if (ext == elt) {
            // 이미지 파일이다
            isValid = true;
          }
        });

        if (fileName != "" && !isValid) {
          alert("이미지 파일이 아닙니다");
          $("#memberImg").val("");
        } else if (fileName == "") {
          // 파일을 올리지 않았을 때로 true
          isValid = true;
        }

        return isValid;
      }

      function duplicateUserId(userId) {
        // 콘솔창에 DB 중복 여부 메세지가 뜨지 않았을때 메서드 호출에 문제가 있음을 의심했어야 한다.
        // 매개변수를 제대로 주지 않아서 메서드가 제대로 작동하지 못했고
        // maximum call stack size exceeded 라는 생소한 오류 메세지를 토해낸 것이다.
        // 오류 메세지가 생소하다고 해서 거기에 매몰 되지 말고 메서드의 호출 순서를 가장 먼저 되짚어보자

        $.ajax({
          url: "duplicateUserId.mem", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
          type: "get", // 통신 방식 (GET, POST, PUT, DELETE)
          data: {
            userId: userId,
          }, // 서블릿에 전송할 데이터
          dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
          success: function (data) {
            // 통신이 성공하면 수행할 함수(콜백 함수)
            console.log(data);

            if (data.status == "success") {
              if (data.duplicate == "no") {
                outputError(
                  "사용가능한 아이디 입니다",
                  $("#userId"),
                  "off",
                  "green"
                );
              } else if (data.duplicate == "yes") {
                outputError("중복된 아이디 입니다", $("#userId"), "on", "red");
              }
            } else if (data.status == "fail") {
              alert("잠시 후, 다시 시도해 주세요");
            }
          },
        });
      }

      function validCheck() {
        let allCheckOk = false; // 모든 유효성 검사

        let idValid = idCheck();
        let pwd1Valid = validPwd1();
        let pwd2Valid = validPwd2();
        let emailValid = validEmail();
        let mobileValid = validMobile();
        let imgValid = validImg();
        let isAgreed = false;

        let isAgreeTag = document.getElementById("agree");
        if (isAgreeTag.checked) {
          isAgreed = true;
        }

        if (
          idValid &&
          pwd1Valid &&
          pwd2Valid &&
          emailValid &&
          mobileValid &&
          imgValid &&
          isAgreed
        ) {
          allCheckOk = true;
        }

        console.log(
          idValid,
          pwd1Valid,
          pwd2Valid,
          emailValid,
          mobileValid,
          imgValid,
          isAgreed
        );
        return allCheckOk;
      }

      function validMobile() {
        let mobile = $("#mobile").val();

        let pattern = /^010-?([0-9]{3,4})-?([0-9]{4})$/;
        let isValid = false;
        if (!mobile.match(pattern)) {
          // email이 pattern과 일치 하지 않느냐?
          outputError("휴대폰 번호형식이 아니에요", $("#mobile"), "on", "red");
        } else {
          isValid = true;
          clearError($("#mobile"));
        }

        return isValid;
      }

      function validEmail() {
        // 이메일 : 이메일 주소 형식인지 아닌지 검사
        let email = $("#email").val();

        let pattern =
          /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        let isValid = false;
        if (!email.match(pattern)) {
          // email이 pattern과 일치 하지 않느냐?
          outputError("이메일 주소형식이 아니에요", $("#email"), "on", "red");
        } else {
          isValid = true;
          clearError($("#email"));
        }

        return isValid;
      }

      function validPwd2() {
        // 비밀번호가 서로 같은지
        let isVaild = false;
        let pwd1 = $("#pwd1").val();
        let pwd2 = $("#pwd2").val();

        if (pwd1 != pwd2) {
          outputError(
            "비밀 번호가 서로 맞지 않습니다",
            $("#pwd2"),
            "on",
            "red"
          );
        } else {
          isValid = true;
          clearError($("#pwd2"));
        }

        return isValid;
      }

      function validPwd1() {
        // 비밀번호 : 4자 이상 12자 이하 필수(비밀번호 확인과 동일할것)
        let pwd1 = $("#pwd1").val();
        let isValid = false;

        if (pwd1 == "") {
          outputError("비밀번호는 필수 입니다", $("#pwd1"), "on", "red");
        } else if (pwd1.length < 4 || pwd1.length > 13) {
          outputError(
            "비밀번호는  4자 이상 12자 이하로 입력하세요",
            $("#pwd1"),
            "on",
            "red"
          );
        } else {
          isValid = true;
          clearError($("#pwd1"));
        }

        return isValid;
      }

      // 회원 가입 버튼을 아래의 조건에 따라 유효성 검사를 하고,
      // 유효하면 1-1_sub.html 페이지에 데이터를 전송하자

      function idCheck() {
        // 아이디 : 4자 이상 8자 이하 필수(소문자로 저장, unique)
        let idCheck = false;
        let userId = $("#userId").val();

        if (userId == "" || userId.length < 1) {
          // 아이디가 입력되지 않았다
          outputError("아이디는 필수로 입력하세요!", $("#userId"), "on", "red");
        } else if (userId.length <= 3 || userId.length >= 9) {
          outputError(
            "4자 이상 8자 이하로 입력하세요!",
            $("#userId"),
            "on",
            "red"
          );
        } else {
          idCheck = true;
          // $("#userId").attr("readonly", "true"); // 요렇게 처리 하거나
          // 체크 이미지를 추가하거나 input 태그에 배경색을 칠하거나....
          clearError($("#userId"));
        }

        return idCheck;
      }

      // keyup과 onchange의 차이는 keyUp은 키가 입력될때마다 이벤트를 검사하고 onChange는

      function clearError(tagObj) {
        let errTag = $(tagObj).next();
        $(errTag).html("");
      }

      function outputError(errorMsg, tagObj, mode, color) {
        // errorMsg를 tagObj객체 다음 요소에 삽입시켜 출력한다.
        let errTag = $(tagObj).next();
        $(errTag).html(errorMsg);
        $(errTag).css("color", color);
        if (mode == "on") {
          $(tagObj).focus(); // 유효성 검사에 적합하지 않으면 해당 입력 칸을 벗어나지 못하도록함
        } else if (mode == "off") {
          $(errTag).hide(100);
        }
        // $(".errMsg").hide(3000);
      }

      // 비밀번호 : 4자 이상 12자 이하 필수(비밀번호 확인과 동일할것)
      // 이름 : 필수 항목
      // 이메일 : 이메일 주소 형식인지 아닌지 검사
      // 휴대전화 : 전화번호 형식인지 아닌지 검사(정규식)
      // 성별 : 남성 또는 여성 중에 하나가 반드시 체크 되어 있어야 한다.
      // 취미 : 여러개를 선택할 수 있다.
      // 직업 : 반드시 하나를 선택 하도록 한다
      // 메모 : 가입인사. 안써도 됨.
      // 가입 조항 동의서에 반드시 체크가 되어 있을 것.
    </script>
  </head>
  <body>
    <jsp:include page="../header.jsp"></jsp:include>
    <div class="container">
      <h1>회원 가입</h1>
      <form method="post" action="register.mem" enctype="multipart/form-data">
        <div class="mb-3 mt-3">
          <label for="userId">아이디:</label>
          <input
            type="text"
            class="form-control"
            id="userId"
            placeholder="Enter your name"
            name="userId"
          />
          <div class="errMsg"></div>
        </div>

        <div class="mb-3">
          <label for="pwd">Password:</label>
          <input
            type="password"
            class="form-control"
            id="pwd1"
            placeholder="Enter password"
            name="pwd"
          />
          <div class="errMsg"></div>
        </div>

        <div class="mb-3">
          <label for="pwd2">Password Confirm:</label>
          <input
            type="password"
            class="form-control"
            id="pwd2"
            placeholder="Enter password"
          />
          <div class="errMsg"></div>
        </div>

        <div class="mb-3 mt-3">
          <label for="email">이메일:</label>
          <input
            type="text"
            class="form-control"
            id="email"
            placeholder="Enter your 2매일"
            name="email"
          />
          <div class="errMsg"></div>
        </div>

        <div class="mb-3 mt-3">
          <label for="mobile">휴대전화:</label>
          <input
            type="text"
            class="form-control"
            id="mobile"
            placeholder="Enter your mobile Number"
            name="mobile"
          />
          <div class="errMsg"></div>
        </div>

        <fieldset>
          <legend>성별</legend>
          <div class="form-check">
            <input
              type="radio"
              class="form-check-input"
              id="radio1"
              name="gender"
              value="F"
            />
            <label class="form-check-label" for="radio1">여성</label>
          </div>
          <div class="form-check">
            <input
              type="radio"
              class="form-check-input"
              id="radio2"
              name="gender"
              value="M"
              checked
            />
            <label class="form-check-label" for="radio2">남성</label>
          </div>
        </fieldset>

        <fieldset>
          <legend>취미</legend>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              id="check1"
              name="hobby"
              value="study"
            />
            <label class="form-check-label">공부</label>
          </div>

          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              id="check2"
              name="hobby"
              value="music"
            />
            <label class="form-check-label">음악감상</label>
          </div>

          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              id="check3"
              name="hobby"
              value="travel"
            />
            <label class="form-check-label">여행</label>
          </div>
        </fieldset>

        <label for="sel1" class="form-label">직업 (select one):</label>
        <select class="form-select" id="sel1" name="job">
          <option value="">--직업을 선택하세요--</option>
          <option value="student">학생</option>
          <option value="creator">크리에이터</option>
          <option value="buildingOwner">건물주</option>
          <option value="officer">공무원</option>
        </select>

        <div class="form-check">
          <label class="form-check-label" for="memberImg">이미지 : </label>
          <input type="file" id="memberImg" name="memberImg" />
          <div>
            <img id="memberImgPreview" />
          </div>
        </div>
        <div class="form-check">
          <label for="comment">MEMO :</label>
          <textarea
            class="form-control"
            rows="5"
            id="memo"
            name="memo"
          ></textarea>
        </div>

        <div class="form-check">
          <input
            class="form-check-input"
            type="checkbox"
            id="agree"
            name="agree"
            value="Y"
          />
          <label class="form-check-label">가입조항에 동의 합니다.</label>
          <div class="errMsg"></div>
        </div>

        <div style="margin-top: 20px; text-align: center">
          <button
            type="submit"
            class="btn btn-success"
            onclick="return validCheck();"
          >
            회원가입
          </button>
          <button type="reset" class="btn btn-warning">취소</button>
        </div>
      </form>
    </div>
    <jsp:include page="../footer.jsp"></jsp:include>
  </body>
</html>

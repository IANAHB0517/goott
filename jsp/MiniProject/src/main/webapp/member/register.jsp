<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
            color: hotpink;
            padding: 10px;
        }
    </style>

    <script>
    
    $(document).ready(function(){
    	if(getParameter("status") == "fail"){
    		alert("회원가입 실패!");
    	}
    });
        // 회원 가입 버튼을 아래의 조건에 따라 유효성 검사를 하고,
        // 유효하면 1-1_sub.html 페이지에 데이터를 전송하자
        // 아이디 : 4자 이상 8자 이하 필수(소문자로 저장)
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
                <input type="text" class="form-control" id="userId" placeholder="Enter your name" name="userId" />
            </div>

            <div class="mb-3">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd1" placeholder="Enter password" name="pwd" />
            </div>

            <div class="mb-3">
                <label for="pwd2">Password Confirm:</label>
                <input type="password" class="form-control" id="pwd2" placeholder="Enter password" />
            </div>

            <div class="mb-3 mt-3">
                <label for="email">이메일:</label>
                <input type="text" class="form-control" id="email" placeholder="Enter your 2매일" name="email" />
            </div>

            <div class="mb-3 mt-3">
                <label for="mobile">휴대전화:</label>
                <input type="text" class="form-control" id="mobile" placeholder="Enter your mobile Number"
                    name="mobile" />
            </div>

            <fieldset>
                <legend>성별</legend>
                <div class="form-check">
                    <input type="radio" class="form-check-input" id="radio1" name="gender" value="F" />
                    <label class="form-check-label" for="radio1">여성</label>
                </div>
                <div class="form-check">
                    <input type="radio" class="form-check-input" id="radio2" name="gender" value="M" />
                    <label class="form-check-label" for="radio2">남성</label>
                </div>
            </fieldset>

            <fieldset>
                <legend>취미</legend>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="check1" name="hobby" value="study" />
                    <label class="form-check-label">공부</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="check2" name="hobby" value="music" />
                    <label class="form-check-label">음악감상</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="check3" name="hobby" value="travel" />
                    <label class="form-check-label">여행</label>
                </div>
            </fieldset>

            <label for="sel1" class="form-label">직업 (select one):</label>
            <select class="form-select" id="sel1" name="job">
                <option>--직업을 선택하세요--</option>
                <option value="student">학생</option>
                <option value="creator">크리에이터</option>
                <option value="buildingOwner">건물주</option>
                <option value="officer">공무원</option>
            </select>
            
            <div class="form-check">
                    <label class="form-check-label" for="memberImg">이미지 : </label>
                    <input type="file" id="memberImg" name="memberImg"  />
             </div>
 			<div class="form-check">
            	<label for="comment">MEMO :</label>
            	<textarea class="form-control" rows="5" id="memo" name="memo"></textarea>
			</div>
			
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="agree" name="agree" value="Y" />
                <label class="form-check-label">가입조항에 동의 합니다.</label>
            </div>

            <div style="margin-top: 20px; text-align: center;">
                <button type="submit" class="btn btn-success" >회원가입</button>
                <button type="reset" class="btn btn-warning">취소</button>
            </div>
        </form>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>
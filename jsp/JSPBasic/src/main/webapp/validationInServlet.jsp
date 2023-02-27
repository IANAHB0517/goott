<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>유효성 검사</title>
<script>
	function checkValid(){
		
		valid = false;
		korValid = false;
		engValid = false;
		mathValid = false;
		
		if (($("#korScore").val()) < 0 || ($("#korScore").val()) > 100){
			alert("국어 점수가 잘 못 되었습니다.");
		} else {
			alert("국어 점수 체크");
			korValid = true;
		}
		
		if (($("#engScore").val()) < 0 || ($("#engScore").val()) > 100){
			alert("영어 점수가 잘 못 되었습니다.");
		} else {
			alert("영어 점수 체크");
			engValid = true;
		}
		
		if (($("#mathScore").val()) < 0 || ($("#mathScore").val()) > 100){
			alert("수학 점수가 잘 못 되었습니다.");
		} else {
			alert("수학 점수 체크");
			mathValid = true;
		}
		
		if (korValid == true && engValid == true && mathValid == true){
			valid = true;
		}
		
		
		
		return valid;
	}
	
</script>
</head>
<body>
	<form action="ValidationInServlet" method="get">
		<div>
			국어 : <input type="text" name="kor" id="korScore"/>
		</div>
		<div>
			영어 : <input type="text" name="eng" id="engScore"/>
		</div>
		<div>
			수학 : <input type="text" name="math" id="mathScore"/>
		</div>
		<input type="submit" value="전송" onclick="return checkValid();"  />
	</form>
</body>
</html>
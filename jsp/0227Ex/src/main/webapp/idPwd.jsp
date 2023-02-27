<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Id 와 Pwd 를 Post로 보내기</title>
<script type="text/javascript">
	function vaildate(){
		idValide = false;
		pwdValide = false;
		valide = false;
		
		id = document.getElementById("id").value;
		pwd = document.getElementById("pwd").value;
		
		idPattern = /^[A-Za-z0-9]{4,8}$/;
		pwdPattern = /^[A-Za-z0-9]{4,12}$/;
		
		document.gete
		
		alert(id);
		alert(pwd);
		
		if (idPattern.test(id)){
			idValide = true;
			alert("아이디가 유효합니다");
		} else {
			alert("아이디가 유효하지 않습니다");
		}
		
		if (pwdPattern.test(pwd)){
			pwdValide = true;
			alert("비밀번호가 유효합니다");
		}else {
			alert("비밀번호가 유효하지 않습니다");
		}
		
		if ( idValide == true && pwdValide== true){
			valide = true;
		} 
		
		return valide;
	}
</script>
</head>
<body>
	<form action="PracticePost" method="post">
		<div>
			아이디 : <input type="text" name="id" id="id">
		</div>
		<div>
			비밀번호 : <input type="password" name="pwd" id="pwd">
		</div>
		<input type="submit" onclick="return vaildate()">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In Page</title>
<script type="text/javascript">

function getParameter(paramName) {

	  let returnVal = null;
	  let url = location.href;

	  if (url.indexOf("?") != -1) {
	  
	    let queryStr = url.split("?")[1];
	    console.log(queryStr);
	    let tmpAr = queryStr.split("&");
	    for (let tmp of tmpAr) {
	      if (tmp.split("=")[0] == paramName) {
	        returnVal = tmp.split("=")[1];
	        break; 
	      }
	    }
	  }

	  return returnVal;
	}
	
	window.onload = function(){
	let page = getParameter("page");
	
	if(page === "logInPage"){
	
	} else {
	alert("올바른 접근이 아닙니다!");
	location.href = 'index.jsp'
	}
		
		console.log(page);
	}
</script>
</head>
<body>
	<form action="LogIn.pg" method="post">
		<div>
			아이디 : <input type="text" name="userId" id="logInId">
		</div>
		<div>
			비밀번호 : <input type="password" name="userPwd" id="logInPwd">
		</div>
		<div>
			<input type="submit" value="로그인">
			<a href="signIn.jsp">회원가입</a>
		</div>
	</form>
</body>
</html>
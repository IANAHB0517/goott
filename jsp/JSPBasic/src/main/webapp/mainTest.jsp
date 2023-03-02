<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function getParameter(paramName) {
	  // 쿼리스트링에서 넘겨받은 paramName을 찾아 그 변수의 값을 return 한다.
	  // 만약 쿼리스트링에 paramName이 없다면 null을 return 한다.

	  let returnVal = null;
	  let url = location.href;

	  if (url.indexOf("?") != -1) {
	    // 쿼리스트링이 있을때
	    let queryStr = url.split("?")[1];
	    console.log(queryStr);
	    let tmpAr = queryStr.split("&");
	    for (let tmp of tmpAr) {
	      if (tmp.split("=")[0] == paramName) {
	        returnVal = tmp.split("=")[1];
	        break; // 해당 반복문 블럭 빠져나감
	      }
	    }
	  }

	  return returnVal;
	}
	
	window.onload = function(){
		let status = getParameter("status");
		if(status === "loginSuccess"){
			alert("로그인 성공!");
		}
		
	}
</script>
</head>
<body>
	<h1>mainTest.jsp</h1>
	
	<a href="loginTest1.jsp" >로그인하러 가기</a>
</body>
</html>
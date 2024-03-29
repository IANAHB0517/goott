<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
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

	<div>
		세션 아이디 :
		<%=session.getId()%></div>

	<%
	out.print("로그인한 유저 : " + (String) session.getAttribute("loginMember"));
	%>
	<div>
		<a href="helloJSP.jsp">다른 jsp로가기</a>
	</div>


	<c:choose>
		<c:when test="${sessionScope.loginMember != null}">
			<div>
				<form action="sessionLogOut.do" method="get">
					<input type="submit" value="로그아웃">
				</form>
				<!--  <a href="helloJSP.jsp"></a> -->
			</div>
		</c:when>
		<c:otherwise>
			<a href="loginTest2.jsp">로그인하러 가기</a>
		</c:otherwise>
	</c:choose>
</body>
</html>
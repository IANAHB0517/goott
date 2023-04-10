<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="resources/js/commonJS.js"></script>



</head>
<body>
	
	
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
	<h1>login.jsp</h1>
	
	<form method="post" action="login"> 
		<div style="margin-top: 20px; text-align: center;">
			<div class="mb-3 mt-3">
					<label for="userId">아이디:</label> <input type="text"
						class="form-control" id="userId" placeholder="Enter your name"
						name="userId" />
						<div class='errMsg'></div>
				</div>
				<div class="mb-3">
					<label for="pwd">Password:</label> <input type="password"
						class="form-control" id="pwd1" placeholder="Enter password"
						name="userPwd" />
					<div class="form-check">
  						<input class="form-check-input" type="checkbox" id="remember" name="remember">
  						<label class="form-check-labe">자동 로그인</label>
					</div>
						<div class='errMsg'></div>
	             <button type="submit" class="btn btn-success">로그인</button>
	             <button type="reset" class="btn btn-warning">재입력</button>
	        </div>
        </div>
	</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
 	$(function() {
 		$("#signInBtn").click(function(

 				if(allValidCheck()){
 					$.ajax({
 				          url: "signIn.mem", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
 				          type: "post", // 통신 방식 (GET, POST, PUT, DELETE)
 				          data: {
 				            memEmail: memEmail,
 				            memPassword: memPassword,
 				            memName: memName,
 				            memMobile: memMobile,
 				            
 				          }, // 서블릿에 전송할 데이터
 				          dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
 				          success: function (data) {
 				            // 통신이 성공하면 수행할 함수(콜백 함수)
 				            console.log(data);

 				            if (data.status == "success") {
 				           
 				              }
 				            } else if (data.status == "fail") {
 				              alert("잠시 후, 다시 시도해 주세요");
 				            }
 				          },
 				        });
 				}
 				
 		) {
 			
 		});
 	});
</script>	
</head>
<body>
	<jsp:include page="${contextPath }/header.jsp"></jsp:include>
	<div class="container">
		<h1>SignIn.jsp</h1>
		<form action="/action_page.php">
    <div class="mb-3 mt-3">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    <div class="mb-3">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd">
      <label for="pwdCheck">Password Check:</label>
      <input type="password" class="form-control" id="pwdCheck" placeholder="Enter password" >
    </div>
    
    <div class="mb-3">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
    </div>
    
    <div class="mb-3">
      <label for="mobile">mobile:</label>
      <input type="text" class="form-control" id="mobile" placeholder="Enter mobile" name="mobile">
    </div>
    
 
    <button type="submit" id="signInBtn" class="btn btn-primary">Submit</button>
  </form>
	</div>
	<jsp:include page="${contextPath }/footer.jsp"></jsp:include>
</body>
</html>
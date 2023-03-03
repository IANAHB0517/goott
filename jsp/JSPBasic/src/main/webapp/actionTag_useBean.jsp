<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 기본생성자를 이용해서 TestMemberVo 를 member1 이라는 이름으로 Bean(객체) 생성 -->
	<jsp:useBean id="member1" class="com.jspbasic.vo.TestMemberVo"></jsp:useBean>
	
	<%
	out.print(member1.toString());
	%>
	
	<hr/>
	
	<!-- 기본 생성자를 이용해서 객체를 생성하고, setProperty를 이용하여 setter -->
	<jsp:useBean id="member2" class="com.jspbasic.vo.TestMemberVo"></jsp:useBean>
		<jsp:setProperty name="member2" property="userId" value="hkd1234" />
		<jsp:setProperty name="member2" property="pwd" value="abcd" />
		<jsp:setProperty name="member2" property="email" value="abcd@abc.com" />
	<%
	out.print(member2.toString());
	%>
	
		<hr/>
		<!-- 생성된 Bean에서 getter 호출 -->
		<div>아이디 : <jsp:getProperty property="userId" name="member2"/></div>
</body>
</html>
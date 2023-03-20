<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  </head>
  <body>
    <c:set var="contextPath" value="<%=request.getContextPath()%>" />
    <nav
      class="navbar navbar-expand-sm bg-light justify-content-center headerBar"
    >
      <ul class="navbar-nav navbar-nav-top">
        <li class="nav-item">
          <a class="nav-link" href="${contextPath }/index.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${contextPath }/member/signIn.jsp">회원가입</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${contextPath }/member/login.jsp">로그인</a>
        </li>
      </ul>
    </nav>
  </body>
</html>

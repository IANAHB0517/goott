<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
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
	    	  console.log(returnVal);
	        break; 
	      }
	    }
	  }

	  return returnVal;
	}
    
    window.onload = function(){
    	let page = getParameter("page")
    	
    	console.log(curPage);
    	
    	
    }
    
    
    </script>


</head>
<body>

	<div class="p-5 bg-primary text-white text-center">
		<h1>My First Bootstrap 5 Page</h1>
		<p>Resize this responsive page to see the effect!</p>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<ul class="navbar-nav">
				<li class="nav-item see"><a class="nav-link "
					href="reviewBoard.jsp?page=see">글 보기</a></li>
				<li class="nav-item mod"><a class="nav-link "
					href="reviewBoard.jsp?page=mod">글 수정</a></li>
				<li class="nav-item write"><a class="nav-link "
					href="reviewBoard.jsp?page=write">글 작성</a></li>
				<li class="nav-item del"><a class="nav-link "
					href="reviewBoard.jsp?page=del">글 삭제</a></li>
			</ul>
		</div>
	</nav>

	<div class="container mt-5"></div>

	<div class="mt-5 p-4 bg-dark text-white text-center">
		<p>Footer</p>
	</div>

</body>
</html>
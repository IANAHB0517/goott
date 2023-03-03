<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Index</title>
<!-- <link rel="stylesheet" href="css/reset.css" /> -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/style.css" />
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
	        break; 
	      }
	    }
	  }

	  return returnVal;
	}
    
    window.onload = function(){
    	let status = getParameter("status")
    	if(status == "loginSuccess"){        	
        	outli = "<li><form action='LogOut.lo' method='get'><input type='submit' value='로그아웃'></form></li>";
        } else{
        	outli = "<li class='nav-item'><a class='nav-link' href='logIn.jsp?page=logInPage'>Log In</a></li>";
        }
    	$(".navbar-nav-top").append(outli);
    }
    
    
    </script>
</head>
<body>
	<div class="gridContainer">
		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>
		<%@ include file="article.jsp"%>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>

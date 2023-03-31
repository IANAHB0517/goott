<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	viewProduct.jsp
	
</h1>

<h2>제품코드 : ${product.productNo }</h2>
<h2>제품명 : ${product.productName }</h2>
<h2>가격 : ${product.price }</h2>
<h2>${product }</h2>


</body>
</html>

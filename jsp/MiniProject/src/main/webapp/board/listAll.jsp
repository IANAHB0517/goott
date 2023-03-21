<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<script>
	function goViewBoard(no){
		location.href='viewBoard.bo?no='+no;
	}
</script>
<style type="text/css">
	.board{
		margin-top:15px;
		margin-bottom:15px;
	}
	.writeBtn {
	float:right;
	maring-right:10px;
	}
	
	.paging {
	clear:both;
	}
	
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container">
	<h4 style="margin-top: 15px;">게시판 글 목록 페이지</h4>
	<div class="board">
	
	<!--${requestScope.boardList } 위와 같음-->
	
	<table class="table table-hover">
    <thead>
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>작성일</th>
        <th>조회수</th>
        <th>좋아요</th>
      </tr>
    </thead>
    <tbody>
     	<c:forEach var="board" items="${boardList }">
     		<tr onclick="goViewBoard(${board.no });">
     			<td>${board.no }</td>
     			<td>${board.title }</td>
     			<td>${board.writer }</td>
     			<td>
     			<fmt:formatDate value="${board.postDate }"/>
     			</td>
     			<td>${board.readcount }</td>
     			<td>${board.likecount }</td>
     		</tr>
     	
     	</c:forEach>
   
    </tbody>
  </table>
		<div class="btns">
		<button type="button" class="btn btn-primary writeBtn" onclick="location.href='writeBoard.jsp';">글쓰기</button>
		</div>
		<div class=paging>
		
		</div>
	</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 답글 쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
	$(function() {


	});


</script>
<style>
	.btns {
		float: right;
		margin-right: 15px;
		}
		
#blank {
	clear: both;
}
</style>
</head>
<body>
	<c:if test="${sessionScope.loginMember == null}"> <!-- "" 내부에 공백이 있는지 확인하자... -->
		<c:redirect url="../member/login.jsp"></c:redirect>
	</c:if>

	<jsp:include page="../header.jsp"></jsp:include>
			
	<div class="container">
		<h4 style="margin-top: 15px;">게시판 답글 쓰기 페이지</h4>


		<form method="post" action="reply.bo" >
			<!-- 답글 처리시 필요한 부모글의 데이터를 함께 넘겨준다 -->
			<!-- 이렇게 처리하면 DB에 한번 더 안가서 빠르게 처리 할 수 있다. 근데 이 와중에 누군가가 나보다 먼저 답글을 쓰면?-->
			<input type="hidden" name="pNo" value="${param.pNo}" /> <!-- 부모글의 글번호 -->
			<input type="hidden" name="pRef" value="${param.pRef}" /> <!-- 부모글의 참조번호 -->
			<input type="hidden" name="pStep" value="${param.pStep}" /> <!-- 부모글의 step번호 -->
			<input type="hidden" name="pRefOrder" value="${param.pRefOrder}" /> <!-- 부모글의 RefOrder번호 -->
			
			<div class="mb-3 mt-3">
				<label for="writer">글쓴이 :</label> <input type="text"
					class="form-control" id="writer"
					value="${sessionScope.loginMember.userId }" readonly name="writer" />
			</div>

			<div class="mb-3 mt-3">
				<label for="title">글제목 :</label> <input type="text"
					class="form-control" id="title" name="title" />
			</div>

			<div class="mb-3 mt-3">
				<label for="content">본 문 :</label>
				<textarea class="form-control" rows="20" id="content" name="content"></textarea>
			</div>

			<div style="margin-top: 20px;" class="btns">
				<button type="submit" class="btn btn-success">저장</button>
				<button type="button" class="btn btn-warning"
					onclick="location.href='viewBoard.bo?no=${param.pNo}';">취소</button>
			</div>
		</form>

	</div>
	<div id="blank"></div>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
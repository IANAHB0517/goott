<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 수정</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>

</script>
<style>
#ImgPreview {
	width: 250px;
}

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
	<jsp:include page="../header.jsp"></jsp:include>

	<c:set var="writer" value="${requestScope.board.writer }" />
	<c:set var="loginUser" value="${sessionScope.loginMember.userId }" />

	<div class="container">
		<h4 style="margin-top: 15px;">게시판 상세 조회 페이지</h4>



		<form>
			<div class="mb-3 mt-3">
				<label for="no">글 번호 :</label> <span> <input id="no"
					value="${board.no }" readonly>
				</span> <label for="writer">작성일 :</label> <span> <input
					value="${board.postDate }" readonly>
			</div>

			<div class="mb-3 mt-3">
				<label for="writer">제 목 :</label> <span> <input
					value="${board.title }">
				</span> <label for="writer">글쓴이 :</label> <span> <input
					value="${board.writer }" readonly>
				</span>
			</div>

			<div class="mb-3 mt-3">
				<label for="writer">본 문 :</label>
				<div>
					<textarea rows="20">
			${board.content }
				</textarea>
				</div>
			</div>

			<div class="form-check">
				<c:forEach var="file" items="${upFiles }">
					<c:choose>
						<c:when test="${file.thumbFileName !=''}">
							<img src="data:${file.fileExt};base64,${file.base64file }" />
						</c:when>
						<c:otherwise>
							<a href="${file.fileName}">업로드 된 파일</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
	</div>

	<div style="margin-top: 20px;" class="btns">
		<!-- c:if 를 사용하는 방법은 UX 측면에서 최선의 방법이 아니다 // 근데 나는 이게 최선의 방법 이지 않을까 싶다 -->
		<!-- 
		<c:if test="${sessionScope.loginMember != null}">
			<c:if test="${writer == loginUser }">
				<button type="submit" class="btn btn-success"
					onclick="location.href='modi.bo?no=' + ${requestScope.board.no };">수정</button>
				<button type="submit" class="btn btn-danger"
					onclick="location.href='delete.bo?no=' + ${requestScope.board.no };">삭제</button>
			</c:if>
			<button type="submit" class="btn btn-info" onclick="goReply();">답글
				달기</button>
		</c:if>

		<c:if test="${sessionScope.loginMember == null}">
			<c:if test="${writer != loginUser }">
				<button type="submit" class="btn btn-success"
					onclick="location.href='modi.bo?no=' + ${requestScope.board.no };"
					disabled>수정</button>
				<button type="submit" class="btn btn-danger"
					onclick="location.href='delete.bo?no=' + ${requestScope.board.no };"
					disabled>삭제</button>
			</c:if>
			<button type="submit" class="btn btn-info" onclick="goReply();"
				disabled>답글 달기</button>
		</c:if>
		 -->
		

		<button type="submit" class="btn btn-success"
			onclick="location.href='modiBoard?no=' + ${param.no };">수정</button>
		<button type="submit" class="btn btn-danger"
			onclick="location.href='delete?no=' + ${board.no };">삭제</button>
	
		 
		 

		<!-- 
		<button type="submit" class="btn btn-success" onclick="">저장</button>
		<button type="submit" class="btn btn-danger"
			onclick="isOwner('remove');">삭제</button>
		 -->
		 
		</form>
		<button type="button" class="btn btn-warning"
			onclick="location.href='listAll';">목록으로</button>
	</div>
	<div id="blank"></div>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
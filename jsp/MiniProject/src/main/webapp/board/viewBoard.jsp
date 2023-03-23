<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 조회</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
	$(function() {

		$("#imgFile").change(function(e) {
			if (validImg()) {
				// 이미지 파일인 경우에만
				let file = e.target.files[0];

				let reader = new FileReader(); // FileReader 객체 생성
				reader.onload = function(evt) {
					$("#ImgPreview").attr("src", evt.target.result); // 파일의 실제 위치를 src 속성에 넣어줌
				}; // 파일을 다 읽었을 떄 호출 되는 콜백 함수

				reader.readAsDataURL(file); // 파일의 위치를 얻어온다.
			}
		});

	});

	function validImg() {
		let isValid = false;

		let fileName = $("#imgFile").val();
		let ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		let imgArr = [ "gif", "jpg", "png", "jpeg", "jfif" ];
		//console.log(fileName, ext);

		$.each(imgArr, function(i, elt) {
			if (ext == elt) {
				// 이미지 파일이다
				isValid = true;
			}
		});

		if (fileName != "" && !isValid) {
			alert("이미지 파일이 아닙니다");
			$("#imgFile").val("");
		} else if (fileName == "") {
			// 파일을 올리지 않았을 때로 true
			isValid = true;
		}

		return isValid;
	}

	
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
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<c:set var="writer" value="${requestScope.board.writer }" />
	<c:set var="loginUser" value="${sessionScope.loginMember.userId }" />

	<div class="container">
		<h4 style="margin-top: 15px;">게시판 상세 조회 페이지</h4>




		<div class="mb-3 mt-3">
			<label for="no">글 번호 :</label> <span> <input id="no"
				value="${requestScope.board.no }" readonly>
			</span> <label for="writer">작성일 :</label> <span> <input
				value="${requestScope.board.postDate }" readonly>
			</span> <label for="writer">조회수 :</label> <span> <input
				value="${requestScope.board.readcount }" readonly>
			</span>
		</div>

		<div class="mb-3 mt-3">
			<label for="writer">제 목 :</label> <span> <input
				value="${requestScope.board.title }" readonly>
			</span> <label for="writer">글쓴이 :</label> <span> <input
				value="${requestScope.board.writer }" readonly>
			</span>
		</div>

		<div class="mb-3 mt-3">
			<label for="writer">본 문 :</label>
			<div>${requestScope.board.content }</div>



		</div>
		<c:if test="${requestScope.board.imgFile != '' }">
			<div class="form-check">
				<div>
					<img src="${contextPath}/${requestScope.board.imgFile }" />
				</div>
			</div>
		</c:if>



	</div>

	<div style="margin-top: 20px;" class="btns">

		<c:if test="${writer == loginUser }">
			<button type="submit" class="btn btn-success" onclick="location.href='modi.bo?no=' + ${requestScope.board.no };">수정</button>
			<button type="submit" class="btn btn-danger" onclick="location.href='delete.bo?no=' + ${requestScope.board.no };">삭제</button>
		</c:if>
		<button type="submit" class="btn btn-info">답글 달기</button>
		<button type="button" class="btn btn-warning"
			onclick="location.href='listAll.bo';">목록으로</button>
	</div>
	<div id="blank"></div>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
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
</style>
</head>
<body>
	<c:if test="${sessionScope.loginMember == null}"> <!-- "" 내부에 공백이 있는지 확인하자... -->
		<c:redirect url="../member/login.jsp"></c:redirect>
	</c:if>

	<jsp:include page="../header.jsp"></jsp:include>
			
	<div class="container">
		<h4 style="margin-top: 15px;">게시판 글 쓰기 페이지</h4>


		<form method="post" action="write.bo" enctype="multipart/form-data">

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

			<div class="form-check">
				<label class="form-check-label" for="">이미지 : </label> <input
					type="file" id="imgFile" name="imgFile" />
				<div>
					<img id="ImgPreview" />
				</div>
			</div>

			<div style="margin-top: 20px;" class="btns">
				<button type="submit" class="btn btn-success">저장</button>
				<button type="button" class="btn btn-warning"
					onclick="location.href='listAll.bo';">취소</button>
			</div>
		</form>

	</div>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
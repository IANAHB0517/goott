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

$(function() {
	

	
$(".fileDrop").on("dragenter dragover", function(evt){
	evt.preventDefault(); // 진행중인 이벤트 버블링 캔슬
});

$(".fileDrop").on("drop", function(evt){
	evt.preventDefault(); // 진행중인 이벤트 버블링 캔슬
	
	let files = evt.originalEvent.dataTransfer.files; // 드래그 드랍된 파일
	console.log(files);

	for(let f of files){ // ajax  한번에 하나의 파일씩 업로드 하도록 되어 있기 때문에 반복문을 돌려준다.

		
let formData = new FormData();
			formData.append("upfiles", f);

			$.ajax({ 
				url : "/board/upfiles", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성) // 기능을 구현할때 먼저 상의해서 정의해야함
				type : "POST", // 통신 방식 (GET, POST, PUT, DELETE)
				data : formData, // 서블릿에 전송할 데이터
				dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
				processData : false, // 보낼 데이터 wrapping(쿼리스트링형태로 보낸다) - false;
				// 일반적으로 form의 기본값은 application/x-www-form-urlencoded 인데 기본 값으로 전송 X
				contentType : false, // content type이 기본 값이 아님 
				async : false, // 동기식 전송
				success : function(data) {
					// 통신이 성공하면 수행할 함수(콜백 함수)
					console.log(data);
					if (data != null){
						displayUploadFile(data);
					}
				}
			});
		}
	});
});

function displayUploadFile (data){
	let target = $(".fileDrop");
	let output = "";
	if (data.image){
		output += "<img id='" + data.originFileName + "' class='ufile' src='/resources/upFiles/" + data.thumbImgName +"' />";
	output += "<img class='delFile' src='/resources/images/delete.png' width='20px' onclick='delFile(this);' />";
	} else {
		output += "<div><a id='"+ data.originFileName +"' href='/resources/upFiles" + data.fileNameWithExt + "'>" + data.originFileName + "</a>";
		output += "<img class='delFile' src='/resources/images/delete.png' width='20px' onclick='delFile(this);' /></div>";
	}

	
	$(".uploadFile").append(output);
}

	function delFile(fileId) {

		let removeId = $(fileId).prev().attr("id");

		$.ajax({
			url : "/board/remfile", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성) // 기능을 구현할때 먼저 상의해서 정의해야함
			type : "get", // 통신 방식 (GET, POST, PUT, DELETE)
			data : {
				"remFileName" : removeId // 삭제될 파일의 originalFileName
			}, // 서블릿에 전송할 데이터
			dataType : "text", // 수신받을 데이터 타입(MIME TYPE)
			success : function(data) {
				// 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
				if (data == "success") {
					$(fileId).prev().remove();
					$(fileId).remove();
				}

			}

		});

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

.fileDrop {
	width: 100%;
	height: 100px;
	padding: 20px;
	border: 1px dotted gray;
	font-size: 20px;
	font-color: #333;
	text-align: center;
	line-height: 50px;
}

#ImgPreview {
	width: 250px;
}

.btns {
	float: right;
	margin-right: 15px;
}

.ufile {
	margin: 5px;
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<c:set var="writer" value="${requestScope.board.writer }" />
	<c:set var="loginUser" value="${sessionScope.loginMember.userId }" />

	<div class="container">
		<h4 style="margin-top: 15px;">게시판 상세 조회 페이지</h4>



		<form method="post" action="modifyBoard">
			<div class="mb-3 mt-3">
				<label for="no">글 번호 :</label> <span> <input id="no"
					value="${board.no }" name="no" readonly>
				</span> <label for="writer">작성일 :</label> <span> <input
					value="${board.postDate }" readonly>
			</div>

			<div class="mb-3 mt-3">
				<label for="writer">제 목 :</label> <span> <input
					value="${board.title }" name="title">
				</span> <label for="writer">글쓴이 :</label> <span> <input
					value="${board.writer }" readonly name="writer">
				</span>
			</div>

			<div class="mb-3 mt-3">
				<label for="writer">본 문 :</label>
				<div>
					<textarea rows="20" name="content">
			${board.content }
				</textarea>
				</div>
			</div>

			<div class="form-check">
				<c:forEach var="file" items="${upFiles }">
					<c:choose>
						<c:when test="${file.thumbFileName !=''}">
							<img id="${file.fileName }"
								src="data:${file.fileExt};base64,${file.base64file }" />
							<img class='delFile' src='/resources/images/delete.png'
								width='20px' onclick='delFile(this);' />
						</c:when>
						<c:otherwise>
							<a id="${file.fileName }" href="${file.fileName}">업로드 된 파일</a>
							<img class='delFile' src='/resources/images/delete.png'
								width='20px' onclick='delFile(this);' />
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<div class="form-check">
					<div class="fileDrop">업로드 할 파일을 드래그 드랍 해 보세요!</div>
					<div class="uploadFile"></div>
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
					onclick="location.href='modiBoard?no=' + ${param.no };">저장</button>
				<button type="submit" class="btn btn-danger"
					onclick="location.href='delete?no=' + ${board.no };">삭제</button>




				<!-- 
		<button type="submit" class="btn btn-success" onclick="">저장</button>
		<button type="submit" class="btn btn-danger"
			onclick="isOwner('remove');">삭제</button>
		 -->


				<button type="button" class="btn btn-warning"
					onclick="location.href='listAll';">목록으로</button>
			</div>
		</form>
	</div>
	<div id="blank"></div>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
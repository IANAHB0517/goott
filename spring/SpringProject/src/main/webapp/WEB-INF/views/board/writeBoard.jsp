<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 쓰기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
	$(function() {
	
		$(".uploadFile").on("click", ".delFile", function(){
			let remTarget = $(this).prev();
			//alert($(remTarget).attr("id") + "를 삭제 시키자");
			
			remUploadFile($(remTarget).attr("id"),$(remTarget).attr("src") );
		});	
		
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
		output += "<img class='delFile' src='/resources/images/delete.png' width='20px' />";
		} else {
			output += "<div><a id='"+ data.originFileName +"' href='/resources/upFiles" + data.fileNameWithExt + "'>" + data.originFileName + "</a>";
			output += "<img class='delFile' src='/resources/images/delete.png' width='20px' /></div>";
		}
		
		
		$(".uploadFile").append(output);
	}
	
	function remUploadFile(originFileName){
				// console.log("originFileName : " + originFileName, "fileNameWithExt : " + fileNameWithExt);
		$.ajax({ 
			url : "/board/delfiles" , // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성) // 기능을 구현할때 먼저 상의해서 정의해야함
			type : "get", // 통신 방식 (GET, POST, PUT, DELETE)
			data : {
				"originFileName" : originFileName,
				
			}, // 서블릿에 전송할 데이터
			dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
			contentType : "text", // content type이 기본 값이 아님 
			success : function(data) {
				// 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
				if (!data.equals("")){
					alert("삭제 메서드 호출");
					removeCurTag(data);
					// front단에 있는 태그 지우는 메서드 호출
				}
			}
		});
	}
	
	function removeCurTag(id){
	
		$(id).attr("id").next().remove();
		$(id).attr("id").remove();
		alert("썸네일, 삭제 버튼 삭제 ");
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

.ufile {
	margin: 5px;
}
</style>
</head>
<body>
	<img src="/resources/images/new.png" />
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">
		<h4 style="margin-top: 15px;">게시판 글 쓰기 페이지</h4>


		<form method="post" action="">

			<div class="mb-3 mt-3">
				<label for="writer">글쓴이 :</label> <input type="text"
					class="form-control" id="writer" value="" name="writer" />
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
				<div class="fileDrop">업로드 할 파일을 드래그 드랍 해 보세요!</div>
				<div class="uploadFile"></div>
			</div>


			<div style="margin-top: 20px;" class="btns">
				<button type="submit" class="btn btn-success">저장</button>
				<button type="button" class="btn btn-warning" onclick="">취소</button>
			</div>
		</form>

	</div>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
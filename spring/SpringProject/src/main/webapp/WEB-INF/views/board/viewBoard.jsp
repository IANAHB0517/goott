<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>게시판 글 조회</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
      $(function () {
        getAllReplies(); // 현재 글의 모든 댓글 가져오기
      });

      $(function () {
        $("#imgFile").change(function (e) {
          if (validImg()) {
            // 이미지 파일인 경우에만
            let file = e.target.files[0];

            let reader = new FileReader(); // FileReader 객체 생성
            reader.onload = function (evt) {
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
        let imgArr = ["gif", "jpg", "png", "jpeg", "jfif"];
        //console.log(fileName, ext);

        $.each(imgArr, function (i, elt) {
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

      function saveReply() {
        let boardNo = "${board.no}";
        // let loginUser = '${sessionScope.loginMember.userId}';
        let replytext = $("#replyContent").val();
        let replier = "${sessionScope.loginMember.userId}";

        console.log(boardNo, replytext);
        let reply = {
          boardNo: boardNo,
          replytext: replytext,
          replier: replier,
        };

        JSON.stringify({
          // JSON 문자열로 바꾸는 함수
          boardNo: boardNo,
          replytext: replytext,
          replier: replier,
        });

        $.ajax({
          url: "/reply", // 뒤에서 reply 밑에 아무 것도 달려있지 않고 post 방식일때 받기로 했기때문에 아무것도 더하지 않는다
          type: "POST", // 통신 방식 (GET, POST, PUT, DELETE)
          headers: {
            "Content-Type": "application/json", // 보내는 데이터의 타입이 JSON임을 명시
            "X-HTTP-Method-Override": "POST", // PUT, DELETE, PATCH 등의 REST HTTP Method가 동작하지 않는 과거의
            //	웹브라우저 에서는 "POST" 방식으로 동작하도록 한다
          }, // 데이터를 전송할 때 packet Header에 추가할 내용
          data: JSON.stringify(reply), // 전송할 데이터를 Json 문자열로 바꾸어 전송
          dataType: "text", // 수신 받을 데이터 타입 Json 과 Text 모두 가능
          success: function (data) {
            // 통신이 성공하면 수행할 함수(콜백 함수)
            if (data == "success") {
              console.log(data);
              getAllReplies();
              $("#replyContent").val("");
            } 
          },
          error: function (data) {
            console.log(data);
            if (data.responseText == "notPermitted") {
              alert("댓글은 로그인 후에 작성할 수 있습니다");
            } else if (data.responseText == "notSaved") {
              alert("댓글 등록 실패 잠시후 다시 시도해 주세요");
            }
          },
        });
      }

      function getAllReplies() {
        console.log("전체 댓글 가져오기");

        let boardNo = "${board.no}";

        $.ajax({
          url: "/reply/all/" + boardNo, // 뒤에서 reply 밑에 아무 것도 달려있지 않고 post 방식일때 받기로 했기때문에 아무것도 더하지 않는다
          type: "GET", // 통신 방식 (GET, POST, PUT, DELETE)
          dataType: "json", // 수신 받을 데이터 타입 Json 과 Text 모두 가능
          async : false, // 동기 방식
          success: function (data) {
            // 통신이 성공하면 수행할 함수(콜백 함수)
              console.log(data);
            if (data.length > 0) {
            	displayAllReply(data);
            } else {
            	$(".allReplies").html("작성된 댓글이 없습니다");
            }
          },
          error: function (data) {
            console.log(data);
          },
        });
      }
      
      function displayAllReply(data){
    	  let output ="<div class='list-group replies'>";
    	  
    	  $.each(data, function(i, item) {
    		  output += "<a href='#' class='list-group-item list-group-item-action'>";
    		  output += "<div class='reply'>" + item.replytext + "</div>";
    		  output += "<div><span class='writer'>" + item.replier + "</span>";
    		  output += "<span class='postdate'>" + new Date(item.postdate).toLocaleString() + "</span>";
    		  output += "<div class='icons'><img src='/resources/images/modigear.png' class='icon' onclick='replyModi("+ item.replyNo +");' />";
    		  output += "<img src='/resources/images/deletetrash.png' class='icon' onclick='replyRem("+ item.replyNo +");'/></div>";
    		  output += "</div></a>";
    	  });
    	  output += "</div>"
    	  
    	  console.log(data)
    	      	
    	  $(".allReplies").html(output);
      }
      
      function replyModi(replyNo){
    	  console.log(replyNo);    	  
      }
      
      function replyRem(replyNo){
    	  console.log(replyNo);    	  
      }
    </script>
<style>
.icons {
	float: right;
	maring-right: 3px;
}

.icon {
	whith: 15px;
	margin-right: 5px;
}

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

.replyInputDiv {
	width: 80vw;
}

.writer {
	float: right;
	margin-top: 4px;
	margin-right: 3px;
}

.postdate {
	margin-left: 4px;
}

.replies {
	clear: right;
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<c:set var="writer" value="${requestScope.board.writer }" />
	<c:set var="loginUser" value="${sessionScope.loginMember.userId }" />

	<div class="container">
		<h4 style="margin-top: 15px">게시판 상세 조회 페이지</h4>

		<div class="mb-3 mt-3">
			<label for="no">글 번호 :</label> <span> <input id="no"
				value="${board.no }" readonly />
			</span> <label for="writer">작성일 :</label> <span> <input
				value="${board.postDate }" readonly />
			</span> <label for="writer">조회수 :</label> <span> <input
				value="${board.readcount }" readonly />
			</span>
		</div>

		<div class="mb-3 mt-3">
			<label for="writer">제 목 :</label> <span> <input
				value="${board.title }" readonly />
			</span> <label for="writer">글쓴이 :</label> <span> <input
				value="${board.writer }" readonly />
			</span>
		</div>

		<div class="mb-3 mt-3">
			<label for="writer">본 문 :</label>
			<div>${board.content }</div>
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

		<div style="margin-top: 20px" class="btns">
			<!-- c:if 를 사용하는 방법은 UX 측면에서 최선의 방법이 아니다 -->
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

		
		 -->
			<!-- 
		<button type="submit" class="btn btn-success"
			onclick="location.href='modiBoard?no=' + ${param.no };">수정</button>
		<button type="submit" class="btn btn-danger"
			onclick="location.href='delete?no=' + ${board.no };">삭제</button>
		 -->

			<button type="submit" class="btn btn-success"
				onclick="location.href='modiBoard?no=${param.no }&writer=${board.writer}'">
				수정</button>

			<button type="submit" class="btn btn-danger"
				onclick="location.href='delete?no='${board.no };'">삭제</button>

			<button type="button" class="btn btn-warning"
				onclick="location.href='listAll';">목록으로</button>
		</div>

		<div class="replyInputDiv">
			<div class="mb-3 mt-3">
				<label for="content">댓글 :</label>
				<textarea class="form-control" rows="5" id="replyContent"></textarea>
				<button type="button" class="btn btn-success" onclick="saveReply();">
					댓글달기</button>
				<button type="button" class="btn btn-danger" onclick="'">취소</button>
			</div>
		</div>
		<div class="allReplies" style="margin-top: 10px; padding: 10px;">

		</div>
	</div>
	<div id="blank"></div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

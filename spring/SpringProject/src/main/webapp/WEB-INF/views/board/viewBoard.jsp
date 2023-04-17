<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>게시판 글 조회</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
      $(function () {
        getAllReplies(); // 현재 글의 모든 댓글 가져오기
       // getLike();
        
       // setInterval(getAllReplies, 5000)
       
        $( "i" ).click(function() { // e 는 클릭된 곳의 이벤트 정보를 얻어오는 매개변수
            
        	  let user = '${sessionScope.loginMember.userId}';
          	 
        	  if(user === ''){
        		  alert('좋아요는 로그인 해야 가능 합니다');
        		  return false; // onclick에도 return을 주어서 로그인 하지 않았을때 하트를 눌렀을때의 모든 이벤트 자체를 false로 만들도록한다
        	  } else {
        	  
            console.log($(this).attr("class"));
            
            let isLike = $(this).attr("class") + "";
            if(isLike == 'press'){
            	likeClicked(false, user)
            } else {
            	likeClicked(true, user)
            	
            }
        	  }
            $( ".likeDiv i, .likeDiv span" ).toggleClass( "press", 1000);
          });
       
      });
      
      function likeClicked(isLike, user){
    	
 
        	  $.ajax({
                  url: "/board/like", // 뒤에서 reply 밑에 아무 것도 달려있지 않고 post 방식일때 받기로 했기때문에 아무것도 더하지 않는다
                  type: "post", // 통신 방식 (GET, POST, PUT, DELETE)
                  dataType: "text", // 수신 받을 데이터 타입 Json 과 Text 모두 가능
                  data: {
                	  "isLike" : isLike,
                	  "boardNo" : '${board.no}',
                	  "who" : user
                	  },
                  success: function (data) {
                  console.log(data);
                  if (data== true){
                	  
                  }
                  }
                });
        	  
          
    	  
    	  
      }
    	  
      
      

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
    	  let output ="<ul class='list-group replies'>";
    	  
    	  $.each(data, function(i, item) {
    		  output += "<li id='replyBody" + item.replyNo + "'; class='list-group-item'>";
    		  if(item.replyNo != item.ref){
    			  output += "<img class='repToreply' src='/resources/images/rep.png' width='30px' />"
    		  }
    		  output += "<p id='replyText" + item.replyNo + "'; class='reply'>" + item.replytext + "</p>";
    		  output += "<div><span id='reply" + item.replyNo+ "'; class='writer'>" + item.replier + "</span>";
    		  output += "<span class='postdate'>" + elapsedTime(item.postdate) + "</span>";
    		  if( item.replier == '${sessionScope.loginMember.userId}' ){    			  
    		  output += "<div id= icons" + item.replyNo+" class='icons'><img src='/resources/images/modigear.png' class='icon' onclick='replyModi("+ item.replyNo +");' />";
    		  output += "<img src='/resources/images/deletetrash.png' class='icon' onclick='replyRem("+ item.replyNo +");'/></div></div>";
    		  }
    		  output += '<button type="button" class="btn btn-success" onclick="addRep(this);">답글</button>';
    		  output += "</li>";
    		  
    		 // acceptable(item.replier, item.replyNo);
    	  });
    	  output += "</ul>"
    	  
    	  //console.log(data)
    	      	
    	  $(".allReplies").html(output);
    	  // 글이 언제 작성 되었는지를 알려 주는 알고리즘
    	  // postdate 로 얻어온 timestamp 값으로Date 객체를 생성
    	  function elapsedTime(date){
    		  let postDate = new Date(date); // 넘겨진 시간(댓글 작성 날짜)
    		  let current = new Date(); // 현재 시간
    		  
    		  let diff = (current - postDate) /1000; // 시간차를 second 단위로
    		 //console.log(diff);
    		  
    		  let times = [
    			  //{name : "년", ms : 60 * 60 * 24 * 365},
    			  //{name : "개월", ms : 60 * 60 * 24 * 30},
    			  {name : "일", ms : 60 * 60 * 24},
    			  {name : "시간", ms : 60 * 60},
    			  {name : "분", ms : 60 },
    		  ];
    		  
    		  for ( let val of times ) {
    			// 글 작성된 시간과 현재 시간의 초단위 시간 차가 기준 시간(val.ms으로 나누어봄)
    			  let betweenTime = Math.floor(diff / val.ms);
    			  
    			  if(betweenTime > 0){ // 몫이 있을 경우 
    				  if(diff > 60 * 60 * 24){
    					  return postDate.toLocaleDateString();
    				  }
    			  return betweenTime + val.name + "전";
    			  }
    		  }
    		  return "방금 전";
    	 }
    	  
    	  
      }
      
      // 댓글 수정
      function replyModi(replyNo){
    	  //console.log(replyNo);
    	  //console.log($("#reply" + replyNo).html());
    	  let user = '${sessionScope.loginMember.userId}';
    	 
    	  if(user === ''){
    		  alert('댓글 수정 삭제는 로그인 해야 가능 합니다');
    		  return;
    	  } else  {
    		  if (user !== $("#reply" + replyNo).html()){
    			  alert('댓글 수정 삭제는 본인 글만 가능합니다');
    			  return;
    		  } else {
    			  let inputArea = '<textarea class="form-control" rows="5" id="modiContent' + replyNo +'">'+
    			  $("#replyText" + replyNo).html() +'</textarea>'
    			  + '<button type="button" class="btn btn-success" onclick="modiReply(' + replyNo + ');">댓글수정</button>'
    			  + '<button type="button" class="btn btn-danger" onclick="modiCancle();">취소</button>';
    			  
    			  $("#replyBody" + replyNo).html(inputArea);
    		  }
    	  }
      }
      
      function replyRem(replyNo){
    	  console.log(replyNo);
    	  alert("개인 구현");
    	  getAllReplies();
      }
      
      function modiCancle(){
    	  getAllReplies();
      }
      
      function modiReply(replyNo){
    	  console.log(replyNo);
    	  
    	  let replytext = $("#modiContent" + replyNo).val();
    	  
    	  let reply = {
    			  "replyNo" : replyNo,
    			  "replytext" : replytext
    	  };
    	  
    	  console.log(JSON.stringify(reply))
    	  
    	  $.ajax({
              url: "/reply/" + replyNo, // 뒤에서 reply 밑에 아무 것도 달려있지 않고 post 방식일때 받기로 했기때문에 아무것도 더하지 않는다
              type: "PUT", // 통신 방식 (GET, POST, PUT, DELETE)
              dataType: "text", // 수신 받을 데이터 타입 Json 과 Text 모두 가능
              headers: {
                  "Content-Type": "application/json", // 보내는 데이터의 타입이 JSON임을 명시
                  "X-HTTP-Method-Override": "POST", // PUT, DELETE, PATCH 등의 REST HTTP Method가 동작하지 않는 과거의
                  //	웹브라우저 에서는 "POST" 방식으로 동작하도록 한다
                }, // 데이터를 전송할 때 packet Header에 추가할 내용
              data: JSON.stringify(reply), // 전송할 데이터를 Json 문자열로 바꾸어 전송
              async : false, // 동기 방식
              success: function (data) {
              console.log(data);
              if (data = 'success'){
            	  getAllReplies();            	  
              }
              },
              error: function (data) {
            	  getAllReplies();
            	  alert("댓글 수정에 실패 했습니다");
              },
            });
      }
      /*
      function acceptable(replier , replyNo){
    	  let userId = '${sessionScope.loginMember.userId}';
    	  console.log("로그인한 id : " + userId + " 댓글 작성자 : "+ replier);
    	  
    	  if(userId != replier){
    		  console.log("css 변경");
    		  console.log("id 명 : " + "icons"+replyNo);
    		  $("#icons"+replyNo).css("display","none");
    	  }
      }
      */
      function addRep(obj){
    	  console.log(obj);
    	  let rep = '<textarea class="form-control" rows="5" id="repContent"></textarea>' +
					'<button type="button" class="btn btn-success" onclick="saveRep(replyNo);">답글달기</button>' +
					'<button type="button" class="btn btn-danger" onclick="canRep()">취소</button>' ;
    		  
    		  
    	  $(obj).parent().html(rep);
      }
      
      function saveRep(replyNo){
    	  $.ajax({
              url: "/reply/" + replyNo, // 뒤에서 reply 밑에 아무 것도 달려있지 않고 post 방식일때 받기로 했기때문에 아무것도 더하지 않는다
              type: "PUT", // 통신 방식 (GET, POST, PUT, DELETE)
              dataType: "text", // 수신 받을 데이터 타입 Json 과 Text 모두 가능
              headers: {
                  "Content-Type": "application/json", // 보내는 데이터의 타입이 JSON임을 명시
                  "X-HTTP-Method-Override": "POST", // PUT, DELETE, PATCH 등의 REST HTTP Method가 동작하지 않는 과거의
                  //	웹브라우저 에서는 "POST" 방식으로 동작하도록 한다
                }, // 데이터를 전송할 때 packet Header에 추가할 내용
              data: JSON.stringify(reply), // 전송할 데이터를 Json 문자열로 바꾸어 전송
              async : false, // 동기 방식
              success: function (data) {
              console.log(data);
              if (data = 'success'){
            	  getAllReplies();            	  
              }
              },
              error: function (data) {
            	  getAllReplies();
            	  alert("댓글 수정에 실패 했습니다");
              },
            });
      }
      
  
      
    </script>
<style>
.repToreply {
	float: left;
}

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
/*새좋아요*/
body {
  margin:0;
  text-align:center;
  /*padding-top:120px;*/
  font-family:'open sans',sans-serif;
  background:#ddd;
  height:100%;
}

div.likeDiv {
  height:100px;
  margin:0 auto;
  position: relative;
}

i {
  cursor:pointer;
  padding:10px 12px 8px;
  background:#fff;
  border-radius:50%;
  display:inline-block;
  margin:0 0 15px;
  color:#aaa;
  transition:.2s;
}

i:hover {
  color:#666;
}

i:before {
  font-family:fontawesome;
  content:'\f004';
  font-style:normal;
}

span.likeSpan {
  position: absolute;
  bottom:70px;
  left:0;
  right:0;
  visibility: hidden;
  transition:.6s;
  z-index:-2;
  font-size:2px;
  color:transparent;
  font-weight:400;
}

i.press {
  animation: size .4s;
  color:#e23b3b;
}

span.press {
  bottom:120px;
  font-size:14px;
  visibility:visible;
  animation: fade 1s;
}

@keyframes fade {
  0% {color:#transparent;}
  50% {color:#e23b3b;}
  100% {color:#transparent;}
}

@keyframes size {
  0% {padding:10px 12px 8px;}
  50% {padding:14px 16px 12px;  
    margin-top:-4px;}
  100% {padding:10px 12px 8px;}
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
			<label for="no">글 번호 :</label>
			<span>
			<input id="no" value="${board.no }" readonly />
			</span>
			
				
				
				
			<label for="likecount">좋아요 : </label>
			<input id="likecount" value="${board.likecount }" readonly />
				<div class="likeDiv">
		 		
			 	<c:set var="isHeart" value="false"></c:set>
			
			<!-- 그냥 반복문으로 검사해서 현재유저가 좋아요 했는지를 검사해서
				true or false 로 저장해서 출력문을 정하면 될듯한데 지금 너무 복잡함-->
				<c:forEach items="${likeList}" var="likedUser">
					<c:if test="${isHeart==false}"></c:if>
					<c:choose>
						<c:when test="${sessionScope.loginMember.userId == likedUser.who}">
						<!-- 로그인한 유저가 이 글을 좋아요 했다면 -->
							<i class="press"></i>
							<span class="likeSpan press">liked!</span>
							<c:set var="isHeart" value="true"></c:set>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${isHeart==false }">
					<i></i>
					<span class="likeSpan">liked!</span>
				</c:if>
				
		 		</div>
		 		<div>이 글을
		 			<c:forEach items="${likeList}" var="likedUser">
		 				${likedUser.who },
		 			</c:forEach>
		 			가 좋아합니다.
		 		</div>
		 			
		 	
 			
			<label for="writer">작성일 :</label> <span> <input
				value="${board.postDate }" readonly />
			</span> <label for="writer">조회수 :</label> <span> <input
				value="${board.readcount }" readonly />
			</span>
			<div class="mb-3 mt-3">
			<!-- 
         <div>
            <input type="checkbox" id="like" onclick="return likeClicked();"/> <label for="like">
               <svg id="heart-svg" viewBox="467 392 58 57"
                  xmlns="http://www.w3.org/2000/svg">
        <g id="Group" fill="none" fill-rule="evenodd"
                     transform="translate(467 392)">
          <path
                     d="M29.144 20.773c-.063-.13-4.227-8.67-11.44-2.59C7.63 28.795 28.94 43.256 29.143 43.394c.204-.138 21.513-14.6 11.44-25.213-7.214-6.08-11.377 2.46-11.44 2.59z"
                     id="heart" fill="#AAB8C2" />
          <circle id="main-circ" fill="#E2264D" opacity="0" cx="29.5"
                     cy="29.5" r="1.5" />

          <g id="grp7" opacity="0" transform="translate(7 6)">
            <circle id="oval1" fill="#9CD8C3" cx="2" cy="6" r="2" />
            <circle id="oval2" fill="#8CE8C3" cx="5" cy="2" r="2" />
          </g>

          <g id="grp6" opacity="0" transform="translate(0 28)">
            <circle id="oval1" fill="#CC8EF5" cx="2" cy="7" r="2" />
            <circle id="oval2" fill="#91D2FA" cx="3" cy="2" r="2" />
          </g>

          <g id="grp3" opacity="0" transform="translate(52 28)">
            <circle id="oval2" fill="#9CD8C3" cx="2" cy="7" r="2" />
            <circle id="oval1" fill="#8CE8C3" cx="4" cy="2" r="2" />
          </g>

          <g id="grp2" opacity="0" transform="translate(44 6)">
            <circle id="oval2" fill="#CC8EF5" cx="5" cy="6" r="2" />
            <circle id="oval1" fill="#CC8EF5" cx="2" cy="2" r="2" />
          </g>

          <g id="grp5" opacity="0" transform="translate(14 50)">
            <circle id="oval1" fill="#91D2FA" cx="6" cy="5" r="2" />
            <circle id="oval2" fill="#91D2FA" cx="2" cy="2" r="2" />
          </g>

          <g id="grp4" opacity="0" transform="translate(35 50)">
            <circle id="oval1" fill="#F48EA7" cx="6" cy="5" r="2" />
            <circle id="oval2" fill="#F48EA7" cx="2" cy="2" r="2" />
          </g>

          <g id="grp1" opacity="0" transform="translate(24)">
            <circle id="oval1" fill="#9FC7FA" cx="2.5" cy="3" r="2" />
            <circle id="oval2" fill="#9FC7FA" cx="7.5" cy="2" r="2" />
          </g>
        </g>
      </svg>
            </label>

         </div>
      </div>
		</div>
		-->
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
				<button type="button" class="btn btn-danger" onclick="">취소</button>
			</div>
		</div>
		<div class="allReplies" style="margin-top: 10px; padding: 10px;">

		</div>
	</div>
	<div id="blank"></div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

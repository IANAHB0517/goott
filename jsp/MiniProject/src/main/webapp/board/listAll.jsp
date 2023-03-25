<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
	$(function(){
		getVpc();
		
	//	if(${param.page}.equals("")){ // pageNo 파라미터가 빈값이면 밑으로 보내기
		//	location.href="listAll.bo?pageNo=1";
	//	} //js 에서 파라미터 값으로 작업을 할 것이 아니라 밑에서 requestScope를 활용해서 하면 된다.
		
		
		$(".viewPostCnt").change(function(){
			let vpc = $(this).val();
			location.href="listAll.bo?pageNo=${param.pageNo}&viewPost=" + vpc;
			
		});
	});

	function goViewBoard(no){
		location.href='viewBoard.bo?no='+no;
	}
	
	function getVpc(){
		let vpc = Number('${param.viewPost}');
		if (vpc == ''){
			vpc = 3;
		}
		
		$(".viewPostCnt").val(vpc);
		
		console.log(vpc)
	}
	
</script>
<style type="text/css">
.board {
	margin-top: 15px;
	margin-bottom: 15px;
}

.writeBtn {
	float: right;
	maring-right: 10px;
}

.paging {
	clear: both;
}

.replyImg {
	width: 20px;
}
</style>
</head>
<body>
	
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<c:if test="${requestScope.boardList == null}">
		<!-- 포트폴리오에 꼭 넣을 것 -->
		<c:redirect url="listAll.bo"></c:redirect>
	</c:if>
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">
		<h4 style="margin-top: 15px;">게시판 글 목록 페이지</h4>
		<div>
			<select class="viewPostCnt"> <!-- form 태그가 없어서 name 값을 사용할 수 없음 -->
				<option value="3" selected>3개씩 보기</option>
				<option value="5">5개씩 보기</option>
				<option value="10">10개씩 보기</option>
				<option value="20">20개씩 보기</option>
			</select>
		</div>
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
						<th>ref</th>
						<th>step</th>
						<th>reforder</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${boardList }">
						<tr onclick="goViewBoard(${board.no });">
							<td>${board.no }</td>

							<td><c:if test="${board.step > 0 }">
									<c:forEach var="i" begin="1" end="${board.step }" step="1">
										<img src="${contextPath }/images/reply.png" class="replyImg">
									</c:forEach>
								</c:if>${board.title }</td>
							<td>${board.writer }</td>
							<td><fmt:formatDate value="${board.postDate }" /></td>
							<td>${board.readcount }</td>
							<td>${board.likecount }</td>
							<td>${board.ref }</td>
							<td>${board.step }</td>
							<td>${board.reforder }</td>
						</tr>

					</c:forEach>

				</tbody>
			</table>
			<div class="btns">
				<button type="button" class="btn btn-primary writeBtn"
					onclick="location.href='writeBoard.jsp';">글쓰기</button>
			</div>

			<div class="paging">
				<!-- ${requestScope.pagingInfo } -->
				${requestScope.pagingInfo }
				<ul class="pagination">
					<c:if test="${requestScope.pagingInfo.startNumOfCurrentPagingBlock > 1}">
						<li class="page-item"><a class="page-link" href="listAll.bo?pageNo=${param.pageNo - 1 }&viewPost=${param.viewPost}">Previous</a></li>
					</c:if>
					<c:forEach var="i" begin="${requestScope.pagingInfo.startNumOfCurrentPagingBlock }" end="${requestScope.pagingInfo.endNumOfCurrentPagingBlock }" step="1">
						<c:choose>
							<c:when test="${requestScope.pagingInfo.pageNo == i }">
								<li class="page-item active" ><a class="page-link" href="listAll.bo?pageNo=${i }&viewPost=${param.viewPost}">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="listAll.bo?pageNo=${i }&viewPost=${param.viewPost}">${i }</a></li>						
							</c:otherwise>							
						</c:choose>
					</c:forEach>
					<c:if test="${requestScope.pagingInfo.pageNo < requestScope.pagingInfo.totalPageCnt }">
						<li class="page-item"><a class="page-link" href="listAll.bo?pageNo=${param.pageNo + 1 }&viewPost=${param.viewPost}">Next</a></li>
					</c:if>	
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
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
	
	//function searchValid(){
	//	let isValid = false;
	//	// 검색어의 경우 sql injection 공격의 타겟이 된다.
	//	// 쿼리문으로 DB를 동작 하기 때문에
	//	// 강력하게 보호해야 한다.
	//	
	//	// 검색어가 입력 되지 않거나, sql injection 공격요소가 있다면 검색이 되지 않도록 처리
	//	let lenValid = false;
	//	let inValid = false;
	//	
	//	let sw = $("#searchWord").val();
	//	
	//	if(sw.length == 0){
	//		alert("검색어를 입력하세요");
	//	} else if (sw.length >= 1){
	//		lenValid = true;
	//	}
	//	
	//	let expText= /[%=><]/; // 데이터 베이스에서 조건 연산자에 해당하는 문자
	//	if(expText.test(sw) == true){
	//		alert("특수문자를 입력 할 수 없습니다.");
	//	} else {
	//		inValid = true;
	//	}
	//	
	//	
	//	if(lenValid && inValid){
	//		isValid = true;			
	//	}
	//	
	//	return isValid
	//}
	
	function searchValid(){
		let isValid = false;
		// 검색어의 경우 sql injection 공격의 타겟이 된다.
		// 쿼리문으로 DB를 동작 하기 때문에
		// 강력하게 보호해야 한다.
		
		// 검색어가 입력 되지 않거나, sql injection 공격요소가 있다면 검색이 되지 않도록 처리
		let lenValid = false;
		let inValid = false;
		
		let sw = $("#searchWord").val();
		
		if(sw.length == 0){
			alert("검색어를 입력하세요");
			return false;
		} 
		
		let expText= /[%=><]/; // 데이터 베이스에서 조건 연산자에 해당하는 문자
		if(expText.test(sw) == true){
			alert("특수문자를 입력 할 수 없습니다.");
			return false;
		} 
		
		const sql = new Array(
			"or", "select", "insert", "update", "delete", "create", "alter", "drop", "exec", "union", "fetch", "declare", "truncate"
				// exec 프로시저 키워드
				// fetch 데이터 인출
				// declare 
		);
		
		let regEx = "";	
		
		for (let i = 0; i < sql.length; i++){
			regEx = new RegExp(sql[i],"gi");
			// RegExp 정규 표현식 객체
			// gi = 쿼리문의 어디에라도 
			
			if (regEx.test(sw) == true){
				alert("특정 문자로 검색할 수 없습니다");
				return false;
			}
		}
		
		
		
		return true;
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
			
			<form class="searchBoard" action="listAll.bo">
				<select name="searchType">
					<option value="title">제목</option>
					<option value="writer">글쓴이</option>
					<option value="content">본문</option>
				</select>
				
				<input type="text" name="searchWord" id="searchWord"/>
				<button type="submit" onclick="return searchValid()">검색</button>
			</form>

			<div class="paging">
				<!-- ${requestScope.pagingInfo } -->
				${requestScope.pagingInfo }
				<ul class="pagination">
					<c:if test="${requestScope.pagingInfo.startNumOfCurrentPagingBlock > 1}">
						<li class="page-item"><a class="page-link" href="listAll.bo?pageNo=${param.pageNo - 1 }&viewPost=${param.viewPost}&searchType=${param.searchType}&searchWord=${param.searchWord}">Previous</a></li>
						<!-- 한번 검색을 해서 쿼리스트링에 검색 타입과 검색어가 붙어있기 때문에 param으로 붙일 수 있다 -->
					</c:if>
					<c:forEach var="i" begin="${requestScope.pagingInfo.startNumOfCurrentPagingBlock }" end="${requestScope.pagingInfo.endNumOfCurrentPagingBlock }" step="1">
						<c:choose>
							<c:when test="${requestScope.pagingInfo.pageNo == i }">
								<li class="page-item active" ><a class="page-link" href="listAll.bo?pageNo=${i }&viewPost=${param.viewPost}&searchType=${param.searchType}&searchWord=${param.searchWord}">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="listAll.bo?pageNo=${i }&viewPost=${param.viewPost}&searchType=${param.searchType}&searchWord=${param.searchWord}">${i }</a></li>						
							</c:otherwise>							
						</c:choose>
					</c:forEach>
					<c:if test="${requestScope.pagingInfo.pageNo < requestScope.pagingInfo.totalPageCnt }">
						<li class="page-item"><a class="page-link" href="listAll.bo?pageNo=${param.pageNo + 1 }&viewPost=${param.viewPost}&searchType=${param.searchType}&searchWord=${param.searchWord}">Next</a></li>
					</c:if>	
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
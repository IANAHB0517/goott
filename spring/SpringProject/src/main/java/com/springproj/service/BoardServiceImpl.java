package com.springproj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.springproj.domain.BoardImg;
import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.PagingInfo;
import com.springproj.domain.SearchCriteria;
import com.springproj.etc.UploadFileInfo;
import com.springproj.persistence.BoardDAO;

@Service // 현재 클래스가 서비스단임을 명시 // 비지니스 로직을 푸는 곳
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao; // BoardDAO 객체 주입

	@Override
	public Map<String, Object> listAll(int pageNo , int viewPostCnt, SearchCriteria sc) throws Exception {
		System.out.println("서비스단 : 게시판 목록 조회");
		
		PagingInfo pi = getPagingInfo(pageNo, viewPostCnt, sc);
		List<BoardVo> lst = null;
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// SearchWord에 초기 값이 없기 때문에 처음에는 getSearchWord() 가 null 이라고 나온다
		
		if (!sc.getSearchWord().equals("")) { // 검색어가 있을때
			lst = this.dao.selectAllBoardWithSearch(pi, sc);
		} else if (sc.getSearchWord().equals("") || sc.getSearchWord() == null) { // 검색어가 없을 때
			lst = this.dao.selectAllBoard(pi);
		}
		
		returnMap.put("boardList", lst);
		returnMap.put("pagingInfo", pi);
		
		return returnMap;
	}

	private PagingInfo getPagingInfo(int pageNo, int viewPostCnt, SearchCriteria sc) throws Exception {
		
		// 스프링 컨테이너가 관리하는 객체 가 아니기 때문에 new를 해줌
		PagingInfo pi = new PagingInfo();
		
		pi.setViewPostCntPerPage(viewPostCnt); // 1 페이지당 보여줄 글의 개수
		pi.setPageNo(pageNo); // 요청된 페이지 변호
		
		if (!sc.getSearchWord().equals("")) { // 검색어가 있을때
			pi.setTotalPostCnt(dao.getBoardCntWithSearch(sc)); // 검색어로 검색된 글의 개수
		} else if (sc.getSearchWord().equals("") || sc.getSearchWord() == null) { // 검색어가 없을 때
			pi.setTotalPostCnt(dao.getBoardCnt()); // 검색어가 없이 얻어온 글의 개수
		}
		
		pi.setTotalPostCnt(dao.getBoardCnt()); // 전체 글의 개수
		
		pi.setTotalPageCnt(pi.getTotalPostCnt(), pi.getViewPostCntPerPage()); // 전체 페이지 수
		
		pi.setStartRowIndex(pi.getPageNo()); // 현재 페이지 번호에서 보여줘야 할 row index;
		
		// ==========================페이징 처리끝====================================================
		
		// pagination 을 위한 코드
		
		pi.setPageBlockOfCurrentPage(pi.getPageNo()); // 현재 페이지가 속한 페이징 블럭
		
		// 현재 페이징 블럭 시작 번호
		pi.setStartNumOfCurrentPagingBlock(pi.getPageBlockOfCurrentPage());
		
		// 현재 페이징 블럭 끝 번호
		pi.setEndNumOfCurrentPagingBlock(pi.getStartNumOfCurrentPagingBlock());
		
		
		
		
		
		return pi;
	}

	@Override
	@Transactional
	public boolean saveBoard(BoardVo newBoard, List<UploadFileInfo> fileList) throws Exception {
		System.out.println("서비스단 : 게시글 저장");
		boolean result = false;

		// 글 insert
		newBoard.setContent(newBoard.getContent().replace("\n", "<br />"));
		int insertResult = dao.insertNewBoard(newBoard);

		// 위에서 insert 된 글의 번호 가져오기
		int boardNo = dao.selectLatest();

		// 파일이 있다면 위에서 가지고 온 글 번호로 파일 insert
		int saveTurns = 0;
		for (UploadFileInfo ufi : fileList) {
			// size() 가 0 이면 안돌아감

			dao.insertBoardFile(boardNo, ufi);
			System.out.println("파일 저장 완료" + saveTurns);
			saveTurns++;
		}

		// 글쓴 멤버에서 포인트 부여
		if (dao.addPointToMember(new MemberPointVo(0, newBoard.getWriter(), null, "게시판글쓰기", 0)) == 1) {
			result = true;
		}
		;

		return result;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED) // 커밋된 내용 만 읽는다.
	public Map<String, Object> viewByBoardNo(int no) throws Exception {
		System.out.println("서비스단 : 조회수 증가");
		// 1) no번 글의 조회수 증가
		dao.updateReadCount(no);

		// 글 수정을 위해서 글과 첨부파일을 읽어 오는 구문을 메서드로 분리

		// 2) no번 글 읽어옴
		Map<String, Object> returnMap = selectBoardByNO(no);

		return returnMap;
	}

	@Override
	public Map<String, Object> viewByBoardNoForMod(int no) throws Exception {
		System.out.println("서비스단 : 글 수정을 위한 조회");

		Map<String, Object> returnMap = selectBoardByNO(no);

		return returnMap;
	}

	public Map<String, Object> selectBoardByNO(int no) throws Exception {
		BoardVo board = dao.selectByBoardNo(no);

		// 3) 2번 이 실패하면 1번을 rollback

		// 4) 첨부 파일 읽어오기
		List<BoardImg> upFiles = dao.selectUploadFile(no);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("board", board);
		returnMap.put("upFiles", upFiles);
		return returnMap;
	}

	@Override
	public int delBoard(int no) throws Exception {
		System.out.println("서비스단 : 게시글 삭제");

		return dao.deleteBoardByNo(no);
	}

	@Override
	public BoardVo modiBoard(int no) throws Exception {
		System.out.println("서비스단 : 게시글 수정");

		return dao.modifyBoardByNo(no);
	}

	@Override
	@Transactional
	public boolean modifyBoard(BoardVo modiBoard, List<UploadFileInfo> fileList) throws Exception {
		modiBoard.setContent(modiBoard.getContent().replace("\n", "<br />"));
		

		// 1) 게시물 update ( 작성일, 제목, 본문)
		int updateBoardResult = dao.updateBoard(modiBoard);

		// 2) 업로드 된 파일 업데이트
		if (updateBoardResult == 1) {
			// -> ㄱ) no 번 글의 첨부파일을 모두 삭제
			// -> ㄴ) no 번 글에 모두 insert
			dao.deleteBoardImg(modiBoard.getNo());
			// 파일이 있다면 위에서 가지고 온 글 번호로 파일 insert
			int saveTurns = 0;
			
			for (UploadFileInfo ufi : fileList) {
				// size() 가 0 이면 안돌아감
				dao.insertBoardFile(modiBoard.getNo(), ufi);
				System.out.println("파일 저장 완료" + saveTurns);
				saveTurns++;
			}

		}
		return false;

	}

}

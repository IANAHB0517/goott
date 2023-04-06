package com.springproj.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.etc.UploadFileInfo;
import com.springproj.persistence.BoardDAO;

@Service // 현재 클래스가 서비스단임을 명시 // 비지니스 로직을 푸는 곳
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao; // BoardDAO 객체 주입
	
	
	@Override
	public List<BoardVo> listAll() throws Exception {
		System.out.println("서비스단 : 게시판 목록 조회");
		
		return this.dao.selectAllBoard();
	}


	@Override
	public boolean saveBoard(BoardVo newBoard, List<UploadFileInfo> fileList) throws Exception {
		System.out.println("서비스단 : 게시글 저장");
		// 글 insert
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
		dao.addPointToMember(new MemberPointVo(0, newBoard.getWriter(), null, "게시판글쓰기", 0));
		
		return false;
	}

}

package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.etc.UploadFileInfo;

public interface BoardDAO {
	// 게시판 전체 목록 조회
	List<BoardVo> selectAllBoard() throws Exception;
	
	// 신규 게시물 저장(일반 데이터)
	int insertNewBoard(BoardVo newBoard)throws Exception;
	
	// 게시물에 파일이 있다면 파일 insert
	int insertBoardFile(int boardNo, UploadFileInfo ufi) throws Exception;
	
	// 게시판에 가장 최근 insert된 글의 번호
	int selectLatest()throws Exception;
	
	// 게시물 작성한 회원에게 포인트 부여
	int addPointToMember(MemberPointVo mpv) throws Exception;
	
}

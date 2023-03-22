package com.miniproj.board.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.miniproj.vodto.BoardVo;
import com.miniproj.vodto.ReadCountProcess;

public interface BoardDAO {
	// 게시물 전체 리스트 얻어오는 메서드
	List<BoardVo> selectEntireBoard() throws NamingException, SQLException;

	// 다음 ref 값을 얻어옴
	int getNextRef() throws NamingException, SQLException;

	// 게시물 insert
	int insertBoard(BoardVo vo) throws NamingException, SQLException;

	// 게시물 상세 조회
	BoardVo selectBoardByNo(int no) throws NamingException, SQLException;

	// ? 번 ip 주소가 ? 번 읽은 적이 있는가 (하루 이상 1, 하루 이하 0, 기록 없음 -1)
	int withinOneDayOrNot(String ipAddr, int boardNo) throws NamingException, SQLException;

	// 조회수 증가
	int updateReadCount(int no) throws NamingException, SQLException;

	// ip 주소, 글번호 , 읽은 시간 insert
	int insertReadCount(String ipAddr, int boardNo) throws NamingException, SQLException;

	// 조회시간 업데이트
	int updateReadTime(String ipAddr, int boardNo) throws NamingException, SQLException;

	// 조회수 증가 트랜잭션 작업
	int transactionProcessReadCount(ReadCountProcess rcp) throws NamingException, SQLException;

	// 글 삭제하기
	int deleteBoard(int boardNo) throws NamingException, SQLException;

	// 글 수정하기
	int modiBoard(int boardNo) throws NamingException, SQLException;

}

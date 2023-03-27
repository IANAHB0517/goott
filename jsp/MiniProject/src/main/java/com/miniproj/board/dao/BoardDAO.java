package com.miniproj.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.miniproj.etc.PagingInfo;
import com.miniproj.vodto.BoardVo;
import com.miniproj.vodto.ReadCountProcess;
import com.miniproj.vodto.SearchCriteria;

public interface BoardDAO {
	// 게시물 전체 리스트 얻어오는 메서드
	List<BoardVo> selectEntireBoard() throws NamingException, SQLException;
	
	// 페이징처리 를 받는 메서드로 오버로딩
	List<BoardVo> selectEntireBoard(PagingInfo pi) throws NamingException, SQLException;
	
	// 페이징처리 + 검색어처리 를 받는 메서드로 오버로딩
	List<BoardVo> selectEntireBoard(PagingInfo pi, SearchCriteria sc) throws NamingException, SQLException;

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

	// 글 수정하기 //해야함
	int modiBoard(int boardNo) throws NamingException, SQLException;
	
	// 부모글이 원래글인지 답글인지 판단 (답글이 끼어 들기할 공간 만들기)\ - 트랜잭션 처리
	int updateReplyPost(BoardVo reply, Connection con) throws NamingException, SQLException;
	
	// 답글을 board 테이블에 등록 - 트랜잭션 처리
	int insertReplyPost(BoardVo reply, Connection con) throws NamingException, SQLException;
	
	// 답글 처리 트랜잭션
	int transactionProcessForReplyPost(BoardVo reply) throws NamingException, SQLException;

	// 인기글 3개 가져오기
	ArrayList<BoardVo> getTop3() throws NamingException, SQLException;

	// 전체 게시판의 글 개수 없어오기
	int getTotalPostCnt(String tableName) throws NamingException, SQLException;
	
	// 전체 게시판의 글 개수 없어오기
	int getTotalPostCnt(String tableName, SearchCriteria sc) throws NamingException, SQLException;

	
	
}

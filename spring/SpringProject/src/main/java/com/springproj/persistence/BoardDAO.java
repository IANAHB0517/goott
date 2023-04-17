package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.BoardImg;
import com.springproj.domain.BoardLikeDTO;
import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.PagingInfo;
import com.springproj.domain.SearchCriteria;
import com.springproj.etc.UploadFileInfo;

public interface BoardDAO {
	// DB 와 가깝기 때문에 쿼리문의 종류로 메서드 명을 짓는것이 일반적
	
	// 게시판 전체 목록 조회
	List<BoardVo> selectAllBoard(PagingInfo pi) throws Exception;
	
	// 신규 게시물 저장(일반 데이터)
	int insertNewBoard(BoardVo newBoard)throws Exception;
	
	// 게시물에 파일이 있다면 파일 insert
	int insertBoardFile(int boardNo, UploadFileInfo ufi) throws Exception;
	
	// 게시판에 가장 최근 insert된 글의 번호
	int selectLatest()throws Exception;
	
	// 게시물 작성한 회원에게 포인트 부여
	int addPointToMember(MemberPointVo mpv) throws Exception;
	
	// no 번 글 조회수 증가
	int updateReadCount(int no)throws Exception;
	
	// no 번 글 조회
	BoardVo selectByBoardNo(int no)throws Exception;

	// no 번 글의 첨부 파일 조회
	List<BoardImg> selectUploadFile(int no) throws Exception;
	
	// no 번 글 삭제
	int deleteBoardByNo(int no)throws Exception;

	// no 번 글 수정
	BoardVo modifyBoardByNo(int no)throws Exception;
	
	// no 번 게시물 수정
	int updateBoard(BoardVo modiBoard) throws Exception;
	
	// no 번 첨부 파일 삭제
	int deleteBoardImg(int no) throws Exception;

	// 전체 게시글 수 조회
	int getBoardCnt() throws Exception;

	// 검색어가 있을 때 검색된 게시글의 글 개수 가져오기
	int getBoardCntWithSearch(SearchCriteria sc) throws Exception;

	// 검색어가 있을 때 검색된 글을 페이징 하여 가져오기
	List<BoardVo> selectAllBoardWithSearch(PagingInfo pi, SearchCriteria sc) throws Exception;

	// 좋아요 정보 입력
	int insertLike(BoardLikeDTO dto) throws Exception;

	// 좋아요 정보 삭제
	int deleteLike(BoardLikeDTO dto) throws Exception;

	// like/dislike 처리시 likeCount 증감
	int addLikeCount(int boardNo, int result) throws Exception;

	// boardNo번 글의 likecount 가져오기
	int getLikeCountByBoardNo(int boardNo)throws Exception;

	// no 번 글을 좋아요한 유저 리스트 가져오기
	List<BoardLikeDTO> getLikeList(int no)throws Exception;

	
}

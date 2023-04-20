package com.springproj.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.BoardImg;
import com.springproj.domain.BoardLikeDTO;
import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.PagingInfo;
import com.springproj.domain.SearchCriteria;
import com.springproj.etc.UploadFileInfo;

@Repository // 현재 클래스가 DAO 단임을 명시
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session; // SqlSessionTemplate 객체 주입

	private static String ns = "com.springproj.mappers.boardMapper";

	@Override
	public List<BoardVo> selectAllBoard(PagingInfo pi) throws Exception {
		System.out.println("DAO단 : 게시판 목록 조회");

		return session.selectList(ns + ".getAllBoards", pi);
	}

	@Override
	public int insertNewBoard(BoardVo newBoard) throws Exception {
		System.out.println("DAO단 : 게시글 저장");

		return session.insert(ns + ".saveNewBoard", newBoard);
	}

	@Override
	public int insertBoardFile(int boardNo, UploadFileInfo ufi) throws Exception {
		System.out.println("DAO단 : 파일 업로드");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("boardNo", boardNo);
		param.put("mimeType", ufi.getMimeType());
		param.put("ext", ufi.getExt());
		param.put("fileNameWithExt", ufi.getFileNameWithExt());
		param.put("thumbImgName", ufi.getThumbImgName());
		param.put("base64Str", ufi.getBase64Str());

		return session.insert(ns + ".saveUpFile", param);
	}

	@Override
	public int selectLatest() throws Exception {
		System.out.println("DAO단 : 최근글 번호 조회");

		return session.selectOne(ns + ".latestBoardNo");
	}

	@Override
	public int addPointToMember(MemberPointVo mpv) throws Exception {
		System.out.println("DAO단 : 멤버 포인트 증가");

		return session.insert(ns + ".addPointToMember", mpv);
	}

	@Override
	public int updateReadCount(int no) throws Exception {
		System.out.println("DAO단 : " + no + " 번글 조회수 증가");

		return session.update(ns + ".updateReadCount", no);
	}

	@Override
	public BoardVo selectByBoardNo(int no) throws Exception {
		System.out.println("DAO단 : " + no + " 번글 조회");

		return session.selectOne(ns + ".selectByBoardNo", no);
	}

	@Override
	public List<BoardImg> selectUploadFile(int no) throws Exception {
		System.out.println("DAO단 : " + no + " 번글 첨부파일 조회");

		return session.selectList(ns + ".selectUploadFile", no);
	}

	@Override
	public int deleteBoardByNo(int no) throws Exception {
		System.out.println("DAO단 : 게시글 삭제");

		return session.delete(ns + ".deleteBoardByNo", no);
	}

	@Override
	public int updateBoard(BoardVo modiBoard) throws Exception {
		System.out.println("DAO단 : 게시글 수정");

		return session.update(ns + ".updateBoard", modiBoard);
	}

	@Override
	public int deleteBoardImg(int no) throws Exception {
		System.out.println("DAO단 : 게시글 첨부 파일 삭제");

		return session.delete(ns + ".deleteBoardImg", no);
	}

	@Override
	public BoardVo modifyBoardByNo(int no) throws Exception {
		System.out.println("DAO단 : 쓰지 않게 된 메서드");

		return null;
	}

	@Override
	public int getBoardCnt() throws Exception {

		return session.selectOne(ns + ".getTotalBoardCnt");
	}

	@Override
	public int getBoardCntWithSearch(SearchCriteria sc) {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("searchType", sc.getSearchType());
		param.put("searchWord", "%" + sc.getSearchWord() + "%");

		return session.selectOne(ns + ".getTotalBoardCntWithSearch", param);
	}

	@Override
	public List<BoardVo> selectAllBoardWithSearch(PagingInfo pi, SearchCriteria sc) {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("searchType", sc.getSearchType());
		param.put("searchWord", "%" + sc.getSearchWord() + "%");
		param.put("startRowIndex", pi.getStartRowIndex());
		param.put("viewPostCntPerPage", pi.getViewPostCntPerPage());

		return session.selectList(ns + ".getAllBoardWithSearch", param);
	}

	@Override
	public int insertLike(BoardLikeDTO dto) {

		return session.insert(ns + ".insertLike", dto);
	}

	@Override
	public int deleteLike(BoardLikeDTO dto) {

		return session.delete(ns + ".deleteLike", dto);
	}

	@Override
	public int addLikeCount(int boardNo, int result) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("boardNo", boardNo);
		param.put("acc", result);
		
		
		return session.update(ns + ".updateLikeCount", param);
		
	}

	@Override
	public int getLikeCountByBoardNo(int boardNo) throws Exception {
		
		return session.selectOne(ns + ".selectLikeCount", boardNo);
	}

	@Override
	public List<BoardLikeDTO> getLikeList(int no) throws Exception {
		
		return session.selectList(ns + ".selectLikeList", no);
	}

	@Override
	public List<BoardImg> selectAllBoardImg() {
		
		return session.selectList(ns + ".getAllAppendFiles");
	}
	
	

}

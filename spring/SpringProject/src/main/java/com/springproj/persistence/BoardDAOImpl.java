package com.springproj.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.etc.UploadFileInfo;

@Repository // 현재 클래스가 DAO 단임을 명시
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session; // SqlSessionTemplate 객체 주입
	
	private static String ns = "com.springproj.mappers.boardMapper";
	
	@Override
	public List<BoardVo> selectAllBoard() throws Exception {
		System.out.println("DAO단 : 게시판 목록 조회");
		
		return session.selectList(ns + ".getAllBoards");
	}

	@Override
	public int insertNewBoard(BoardVo newBoard) throws Exception {
		System.out.println("DAO단 : 게시글 저장");

		return session.insert(ns+ ".saveNewBoard", newBoard);
	}

	@Override
	public int insertBoardFile(int boardNo, UploadFileInfo ufi) throws Exception {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("boardNo", boardNo);
		param.put("mimeType", ufi.getMimeType());
		param.put("ext", ufi.getExt());
		param.put("fileNameWithExt", ufi.getFileNameWithExt());
		param.put("thumbImgName", ufi.getThumbImgName());
		param.put("base64Str", ufi.getBase64Str());
		
		return session.insert(ns+ ".saveUpFile", param);
	}

	@Override
	public int selectLatest() throws Exception {
		
		return session.selectOne(ns + ".latestBoardNo");
	}

	@Override
	public int addPointToMember(MemberPointVo mpv) throws Exception {
		
		return session.insert(ns + ".addPointToMember", mpv);
	}

	
	
}

package com.springproj.service;

import java.util.List;

import com.springproj.domain.BoardVo;
import com.springproj.etc.UploadFileInfo;

public interface BoardService {
	//게시판 목록 조회
	List<BoardVo> listAll() throws Exception;
	
	// 신규 게시물 저장 (파일 제외)
	boolean saveBoard(BoardVo newBoard, List<UploadFileInfo> fileList) throws Exception;

}

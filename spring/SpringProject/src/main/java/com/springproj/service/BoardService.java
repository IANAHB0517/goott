package com.springproj.service;

import java.util.List;

import com.springproj.domain.BoardVo;

public interface BoardService {
	//게시판 목록 조회
	List<BoardVo> listAll() throws Exception;

}

package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.BoardVo;

public interface BoardDAO {
	// 게시판 전체 목록 조회
	List<BoardVo> selectAllBoard() throws Exception;
}

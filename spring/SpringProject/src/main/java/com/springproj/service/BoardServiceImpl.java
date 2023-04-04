package com.springproj.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springproj.domain.BoardVo;
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

}

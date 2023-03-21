package com.miniproj.board.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniproj.board.controller.BoardFactory;

public class BoardViewService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int no = Integer.parseInt(req.getParameter("no"));
	
	// 1) 글을 읽어보기
	// 2) 1번이 성공했다면 .. 조회수 1 증가
	// 3) ip 체크해서 동일 ip면 24시간에 한번씩 조회수 1 증가
	
	
		return null;
	}

}

package com.miniproj.board.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniproj.board.controller.BoardFactory;
import com.miniproj.board.dao.BoardDAO;
import com.miniproj.board.dao.BoardDAOImpl;

public class BoardModiService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardFactory bf = BoardFactory.getInstance();
		
		int boardNo = Integer.parseInt(req.getParameter("no"));
		
		System.out.println(boardNo + " : 번 글 수정");
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		
		try {
			if(dao.modiBoard(boardNo) ==1) {
				// 잘못 생각했네 수정을 선택하면 먼저 데이터를 받아와서 수정 할 수 있도록 하고 수정하기를 선택하면 BoardVo를 날려야한다. 
			};
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}

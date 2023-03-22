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

public class BoardDeleteService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardFactory bf = BoardFactory.getInstance();
		
		System.out.println(req.getParameter("no") + ": 번 게시글 삭제하기");
		
		int boardNo = Integer.parseInt(req.getParameter("no"));
		 
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		
		
		try {
			if (dao.deleteBoard(boardNo) == 1) {
				bf.setRedirect(false);
				bf.setWhereisgo("listAll.bo");
			} ;
			
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		return bf;
	}

}

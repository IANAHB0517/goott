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
import com.miniproj.error.CommonException;
import com.miniproj.vodto.BoardVo;

public class BoardReplyService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardFactory bf = BoardFactory.getInstance();
		System.out.println("답글 작성하기 서비스단");
		
		String writer = req.getParameter("writer");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		int pNo = Integer.parseInt(req.getParameter("pNo"));
		int pRef = Integer.parseInt(req.getParameter("pRef"));
		int pStep = Integer.parseInt(req.getParameter("pStep"));
		int pRefOrder = Integer.parseInt(req.getParameter("pRefOrder"));
		
		content = content.replace("\n", "<br/>");
		
		
		BoardVo reply = new BoardVo(0, writer, title, null, content, null, 0, 0, pRef, pStep, pRefOrder);
		System.out.println(reply.toString());
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		try {
			if(dao.transactionProcessForReplyPost(reply) == 1) {
				bf.setRedirect(true);
				bf.setWhereisgo("listAll.bo");
			} else {
				bf.setRedirect(true);
				bf.setWhereisgo("viewBoard.bo?no"+pNo + "&status=fail");
			}
		} catch (NamingException | SQLException e) {
			if (e instanceof NamingException) {
				e.printStackTrace(); 
			
				CommonException ce = new CommonException(e.getMessage(), 99); 
				
				ce.setErrorMsg(e.getMessage()); // 에러의 종류
				ce.setStackTrace(e.getStackTrace()); // 에러 기록

				req.setAttribute("error", ce); // 에러 정보를 가진 CommonException 객체 바인딩

				bf.setRedirect(false);
				bf.setWhereisgo("../error.jsp");

			} else if (e instanceof SQLException) {
				bf.setRedirect(true);
				bf.setWhereisgo("viewBoard.bo?no"+pNo + "&status=fail");
			}
		}
		
		return bf;
	}

}

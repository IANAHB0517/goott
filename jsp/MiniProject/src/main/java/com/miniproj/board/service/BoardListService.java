package com.miniproj.board.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniproj.board.controller.BoardFactory;
import com.miniproj.board.dao.BoardDAO;
import com.miniproj.board.dao.BoardDAOImpl;
import com.miniproj.error.CommonException;
import com.miniproj.vodto.BoardVo;

public class BoardListService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("게시판 글 목록 출력");
		
		BoardFactory bf = BoardFactory.getInstance();
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		try {
			List<BoardVo> lst = dao.selectEntireBoard();
			
			req.setAttribute("boardList", lst);
			
			bf.setRedirect(false);
			bf.setWhereisgo("listAll.jsp");
			
		} catch (NamingException | SQLException e) {
			if (e instanceof NamingException) {
//				e.printStackTrace(); // 개발자가 잘못한 경우
				// NamingException 은 개발자 실수이기 때문에 개발자만 보도록 공통 에러페이지(error.jsp)를 만들었고
				// 에러 정보를 error.jsp로 바인딩하여 error.jsp 페이지에서 에러 정보를 출력하였다.
				// forward
				CommonException ce = new CommonException(e.getMessage(), 99); // 예외 정보를 바인딩
				// throw 와 throws 가 각각 다르다
				// throws는 예외를 넘겨주는 구문
				// throw 는 컴퓨터가 예외라고 생각하지 않는 구문에서 강제로 예외가 발생한 것으로 간주하는 구문
				// 여기서 throw를 사용하지 않은 이유는 이미 오류가 발생해서 코드가 실행중인 경우이기 때문이다. 
				
				ce.setErrorMsg(e.getMessage()); // 에러의 종류
				ce.setStackTrace(e.getStackTrace()); // 에러 기록

				req.setAttribute("error", ce); // 에러 정보를 가진 CommonException 객체 바인딩

				bf.setRedirect(false);
				bf.setWhereisgo("../error.jsp");

			} else if (e instanceof SQLException) {
				// SQL Exception 은 대부분 실제 유저의 입력 오류로 인한 예외
				bf.setRedirect(true);
				bf.setWhereisgo("../index.jsp");
				
			}
		}
		
		
		
		
		
		return bf;
	}

}

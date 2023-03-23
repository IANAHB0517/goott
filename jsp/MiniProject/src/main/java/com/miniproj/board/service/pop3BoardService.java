package com.miniproj.board.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.miniproj.board.controller.BoardFactory;
import com.miniproj.board.dao.BoardDAO;
import com.miniproj.board.dao.BoardDAOImpl;
import com.miniproj.vodto.BoardVo;

public class pop3BoardService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardFactory bf = BoardFactory.getInstance();
		
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		JSONObject json = new JSONObject();
		
		 try {
			ArrayList<BoardVo> result = new ArrayList<>();
			result = dao.getTop3();
			
			json.put("status", "success");
			
			for (BoardVo b : result) {
				
			}
			
			json.put("status", b); // 가지고온 리스트를 까서 json에 넣어야 하는데 어떻게 하더라?
			
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bf;
	}

}

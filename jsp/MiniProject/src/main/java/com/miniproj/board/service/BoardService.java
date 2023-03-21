package com.miniproj.board.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniproj.board.controller.BoardFactory;

public interface BoardService {
	// 명시하지 않아도 public abstract
	 BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}

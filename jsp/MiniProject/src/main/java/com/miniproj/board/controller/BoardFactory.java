package com.miniproj.board.controller;

import com.miniproj.board.service.BoardListService;
import com.miniproj.board.service.BoardService;
import com.miniproj.board.service.BoardViewService;
import com.miniproj.board.service.BoardWriteService;

public class BoardFactory {
	private static BoardFactory instance = null;
	private boolean isRedirect; // 리다이렉트 할 것인지
	private String whereisgo; // 어디로 갈 것인지

	public static BoardFactory getInstance() {
		if (instance == null) {
			instance = new BoardFactory();
		}

		return instance;
	}

	public BoardService getService(String command) {
		BoardService service = null;

		if (command.equals("/board/listAll.bo")) {
			service = new BoardListService();
		} else if (command.equals("/board/write.bo")) {
			service = new BoardWriteService();
		} else if (command.equals("/board/viewBoard.bo")) {
			service = new BoardViewService();
		}

		return service;

	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getWhereisgo() {
		return whereisgo;
	}

	public void setWhereisgo(String whereisgo) {
		this.whereisgo = whereisgo;
	}

	private BoardFactory() {
	}

	@Override
	public String toString() {
		return "BoardFactory [isRedirect=" + isRedirect + ", whereisgo=" + whereisgo + "]";
	}

}

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
import com.miniproj.etc.IPCheck;
import com.miniproj.vodto.BoardVo;
import com.miniproj.vodto.ReadCountProcess;

public class BoardViewService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardFactory bf = BoardFactory.getInstance();

		// 쿼리스트링에 no 라는 파라메터가 존재하지 않거나(null), no라는 파라메터의 값이 비어 있다("")
		// 잘못된 접근 방식 처리
		if (req.getParameter("no") == null || req.getParameter("no").equals("")) {
			bf.setRedirect(true);
			bf.setWhereisgo("listAll.bo");
			return bf;
		}

		// parseInt에서 null을 감지 했을 경우 리다이렉트 시켜도 된다.
		int boardNo = Integer.parseInt(req.getParameter("no"));
		System.out.println(boardNo + " 번 글 조회");
		BoardDAO dao = BoardDAOImpl.getInstance();

		// 1) ip 체크 후 동일한 ip
		String ipAddr = IPCheck.getIPAddr();

		ReadCountProcess rcp = new ReadCountProcess(0, ipAddr, boardNo, null);
		System.out.println(rcp.toString());

		try {
			if(dao.transactionProcessReadCount(rcp) == 1 ) { // 트랜잭션 처리
				BoardVo board = dao.selectBoardByNo(boardNo);
				
				req.setAttribute("board", board);
				
				bf.setRedirect(false);
				bf.setWhereisgo("viewBoard.jsp");
				
				System.out.println("viewBoard.jsp 요청");
			} 
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2) 글을 읽어보기

		// dao.selectBoardByNo(boardNo);

		// 2) 1번이 성공했다면 조회수 1 증가 ip 체크해서 동일 ip면 24시간에 한번씩 조회수 1 증가

		// System.out.println("요청을 보낸 ip(internet protocol) : " +
		// (req.getRemoteAddr()));
		// 위 방법은 proxy 주소를 반환 하기 때문에 사용하지 않는다

		return bf;
	}

}

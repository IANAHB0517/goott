package com.miniproj.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


import com.miniproj.board.dao.BoardDAO;
import com.miniproj.board.dao.BoardDAOImpl;
import com.miniproj.board.service.BoardService;
import com.miniproj.error.CommonException;
import com.miniproj.etc.PagingInfo;
import com.miniproj.member.controller.MemberFactory;
import com.miniproj.member.dao.MemberDAO;
import com.miniproj.member.dao.MemberDAOImpl;
import com.miniproj.vodto.BoardVo;
import com.miniproj.vodto.MemberPointVo;

public class PointListBoardService implements BoardService {

	@Override
	public MemberFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("포인트 목록 출력");

		MemberFactory mf = MemberFactory.getInstance();
		
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();

		String userId = req.getParameter("userId");

		// 페이지 번호
		int pageNo = -1;
		if (req.getParameter("pageNo") == null || req.getParameter("pageNo").equals("")) {
			pageNo = 1;
		} else {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
		}

		int viewPostCntPerPage = 10;

		System.out.println("userId : " + userId + "페이지 번호 : " + pageNo + "보여줄 글 개수");

		MemberDAO dao = MemberDAOImpl.getInstance();
		BoardDAO dao2 = BoardDAOImpl.getInstance();
		
		JSONObject json = new JSONObject();

		try {
			// 페이지 번호, 전체 글의 개수로 페이징 처리를 해서.
			PagingInfo pi = getPagingInfo(pageNo, viewPostCntPerPage, dao2, userId);
			
			// get 방식이기 때문에 반환 해주기 위한 json 객체
			
			// 페이징 처리한 쿼리문이 실행 되도록 dao 단 호출
			List<MemberPointVo> lst = dao.getMemberPoint(userId ,pi);
			System.out.println("lst : " + lst.toString());

			json.put("status", "success");

			
			
//			out.print(json.toJSONString());

		} catch (NamingException | SQLException e) {
			if (e instanceof NamingException) {
				// NamingException은 개발자 실수이기 때문에, 개발자만 보도록 공통 에러 페이지(error.jsp)를 만듦
				// 에러 정보를 error.jsp로 바인딩 하여, error.jsp 페이지에서 에러 정보를 출력하도록 함.
				// forward 방식으로 보냄.
				CommonException ce = new CommonException(e.getMessage(), 99);

				ce.setErrorMsg(e.getMessage());
				ce.setStackTrace(e.getStackTrace());

				req.setAttribute("error", ce); // 에러 정보를 가진 CommonException 객체 바인딩

				req.getRequestDispatcher("../error.jsp").forward(req, resp); // 페이지 이동

			} else {
				json.put("status", "fail");
			}
		}
		
		out.print(json.toJSONString());
		out.close();

		mf.setRedirect(false);
		
		return mf;
	}

	private PagingInfo getPagingInfo(int pageNo, int viewPostCntPerPage, BoardDAO dao2, String userId)
			throws NamingException, SQLException {
		PagingInfo pi = new PagingInfo();

		// 실질적인 페이징에 필요한 변수들 setting
		pi.setViewPostCntPerPage(viewPostCntPerPage);
		pi.setPageNo(pageNo);
		pi.setTotalPostCnt(dao2.getTotalPostCnt("memberpoint where userid = " + userId));
		pi.setTotalPageCnt(pi.getTotalPostCnt(), pi.getViewPostCntPerPage());
		pi.setStartRowIndex(pi.getPageNo());

		// 페이징 블럭 처리를 위해 필요한 변수들 setting

		// 현재 페이지가 속한 페이징 블럭
		pi.setPageBlockOfCurrentPage(pi.getPageNo());

		// 현재 페이징 블럭 시작 번호
		pi.setStartNumOfCurrentPagingBlock(pi.getPageBlockOfCurrentPage());

		// 현재 페이징 블럭 끝 번호
		pi.setEndNumOfCurrentPagingBlock(pi.getStartNumOfCurrentPagingBlock());

		System.out.println(pi.toString());

		return pi;
	}

}

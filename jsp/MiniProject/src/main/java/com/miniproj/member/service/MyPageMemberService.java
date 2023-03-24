package com.miniproj.member.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.miniproj.board.dao.BoardDAO;
import com.miniproj.board.dao.BoardDAOImpl;
import com.miniproj.etc.PagingInfo;
import com.miniproj.member.controller.MemberFactory;
import com.miniproj.member.dao.MemberDAO;
import com.miniproj.member.dao.MemberDAOImpl;
import com.miniproj.vodto.MemberDTO;
import com.miniproj.vodto.MemberPointVo;

public class MyPageMemberService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("마이페이지 서비스");
		// 파라메터에서 얻기
//		String userId = req.getParameter("userId");
		
		//세션에서 얻기
		HttpSession ses = req.getSession();
		 MemberDTO loginMember= (MemberDTO)ses.getAttribute("loginMember"); 
		 if (loginMember != null) {
			 String userId =loginMember.getUserId();
			 System.out.println("loginMember" + loginMember.toString());
			 
			 MemberDAO dao = MemberDAOImpl.getInstance();
			 BoardDAO bDao = BoardDAOImpl.getInstance();
			 
			 int pageNo = -1;
			 int viewPostCntPerPage = 5;
			 if (req.getParameter("pageNo") == null || req.getParameter("pageNo").equals("")) {
					pageNo = 1;
				} else {
					pageNo = Integer.parseInt(req.getParameter("pageNo"));
				}
			 
			 try {
				MemberDTO memberInfo = dao.getMemberInfo(userId); // 회원정보 가져오기
				PagingInfo pi = getPagingInfo(pageNo,viewPostCntPerPage, bDao, userId);
				
				List<MemberPointVo> mpv = dao.getMemberPoint(userId); // 포인트 내역 가져오기
				// 트랜잭션 처리를 하지 않는 이유 DML문이 아니기 때문에 commit 하거나 rollback 할 게 없기 때문에
				// 단순 select문에서는 트랜잭션 처리르 할 필요가 없다.
				
				
				// 회원 정보를 request에 바인딩
				req.setAttribute("memberInfo", memberInfo);
				req.setAttribute("memberpoint", mpv);
				req.setAttribute("pagingInfo", pi);
				// 페이지 이동
				req.getRequestDispatcher("/member/myPage.jsp").forward(req, resp);
			} catch (NamingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 } 
		
		return null;
	}

	private PagingInfo getPagingInfo(int pageNo, int viewPostCntPerPage, BoardDAO bDao, String userId) throws NamingException, SQLException {
		PagingInfo pi = new PagingInfo();
		pi.setViewPostCntPerPage(viewPostCntPerPage);
		pi.setPageNo(pageNo);
		pi.setTotalPostCnt(bDao.getTotalPostCnt("memberpoint where who ='" + userId +"'"));
		pi.setTotalPageCnt(pi.getTotalPostCnt(), pi.getViewPostCntPerPage());
		pi.setStartRowIndex(pageNo);
		
		return pi;
	}
}

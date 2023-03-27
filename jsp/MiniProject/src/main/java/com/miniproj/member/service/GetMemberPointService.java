package com.miniproj.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.miniproj.board.dao.BoardDAO;
import com.miniproj.board.dao.BoardDAOImpl;
import com.miniproj.etc.PagingInfo;
import com.miniproj.member.controller.MemberFactory;
import com.miniproj.member.dao.MemberDAO;
import com.miniproj.member.dao.MemberDAOImpl;
import com.miniproj.vodto.MemberPointVo;

public class GetMemberPointService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();

		String userId = req.getParameter("userId");
		int pageNo = 1;
		if (req.getParameter("pageNo") == null || req.getParameter("pageNo").equals("")) {
			pageNo = 1;
		} else {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
		}
		
		System.out.println("페이지 번호 : " + pageNo);
		MemberDAO mdao = MemberDAOImpl.getInstance();
//		BoardDAO bdao = BoardDAOImpl.getInstance();

		JSONObject json = new JSONObject();

		try {
			PagingInfo pi = getPaingInfo(pageNo, mdao, userId);

			List<MemberPointVo> mpv = mdao.getMemberPoint(userId, pi);
			// 포인트 내역 가져오기
			for (MemberPointVo pt : mpv) {
				System.out.println(pt.toString());
			}

			
			JSONArray memberpoints = new JSONArray();
			for (MemberPointVo pt : mpv) {
				JSONObject memberpoint = new JSONObject();
				memberpoint.put("who", pt.getWho());
				memberpoint.put("when", pt.getWhen().toString()); // 날짜 형식이 맞지 않아서 날짜형식을 문자형으로 바꿔서 출력
				memberpoint.put("why", pt.getWhy());
				memberpoint.put("howmuch", pt.getHowmuch() + "");

				memberpoints.add(memberpoint);
			}

			json.put("memberpoints", memberpoints);

			json.put("startNumOfCurrentPagingBlock", pi.getStartNumOfCurrentPagingBlock() + "");
			json.put("endNumOfCurrentPagingBlock", pi.getEndNumOfCurrentPagingBlock() + "");
			json.put("status", "success");

		} catch (NamingException | SQLException e) {
			json.put("status", "fail");
			json.put("errorMsg", e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.print(json.toJSONString());
		System.out.println(json.toJSONString());
		out.close();

		return null;
	}

	private PagingInfo getPaingInfo(int pageNo, MemberDAO mdao, String userId) throws NamingException, SQLException {
		PagingInfo pi = new PagingInfo();
		pi.setViewPostCntPerPage(5);
		pi.setTotalPostCnt(mdao.getTotalPointCnt(userId));
		pi.setTotalPageCnt(pi.getTotalPostCnt(), pi.getViewPostCntPerPage());
		pi.setStartRowIndex(pageNo); // 페이징 끝

		// 페이징 블럭
		pi.setPageBlockOfCurrentPage(pageNo);
		pi.setStartNumOfCurrentPagingBlock(pi.getPageBlockOfCurrentPage());
		pi.setEndNumOfCurrentPagingBlock(pi.getStartNumOfCurrentPagingBlock());

		return pi;
	}

}

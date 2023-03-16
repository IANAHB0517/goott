package com.miniproj.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.miniproj.controller.MemberFactory;
import com.miniproj.dao.MemberDAOImpl;
import com.miniproj.error.CommonException;

import netscape.javascript.JSObject;

public class DulicateUserIdService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		MemberFactory mf = MemberFactory.getInstance();

		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();

		String userId = req.getParameter("userId");
		System.out.println("서비스 단 : " + userId + "값이 중복 되는지 검사");

		JSONObject json = new JSONObject();

		try {
			int result = MemberDAOImpl.getInstance().selectByUserId(userId);
			System.out.println("=============" + result + " : DB에 중복된 유저수 ============= ");

			json.put("status", "success");

			if (result == 0) { // 중복 X
				json.put("duplicate", "no");
			} else { // 중복 O
				json.put("duplicate", "yes");

			}

		//	out.print(json.toJSONString());

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

}

package com.jspbasic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogOut.do")
public class SessionLogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//로그아웃 처리 -> session 객체에 남아있는 로그인 정보를 지운후 session 객체 갱신
		HttpSession ses = req.getSession();
		
		ses.removeAttribute("loginMember"); // 로그인 정보 삭제
		
		ses.invalidate(); // session 객체 무효화 -> 갱신
		
		resp.sendRedirect("mainTest.jsp"); // mainTest.jsp로 이동
	}

}

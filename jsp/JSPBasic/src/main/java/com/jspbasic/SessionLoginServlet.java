package com.jspbasic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionLogin.do")
public class SessionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 아이디는 abcd123
		// 비번 555566
		
		
		
		String userId = req.getParameter("userId") ;
		String userPwd = req.getParameter("userPwd") ;
		
		if (userId.equals("abcd123") && userPwd.equals("555566")) { // 로그인 성공
			// 세션 객체에 로그인 정보를 남겨야 함 -> 로그인 정보를 세션 객체에 바인딩
			HttpSession ses = req.getSession(); // request로 부터 세션 객체를 얻어옴
			
			System.out.println("세션 id : " + ses.getId()); 
			
			ses.setAttribute("loginMember", userId); // 로그인한 멤버라는 이름으로 id 값을 남김
			
			resp.sendRedirect("mainTest.jsp?status=loginSuccess"); // mainTest.jsp로 이동 따로 가지고 가야할 데이터가 없기 때문에 바인딩이 아닌 리다이렉트로 보냄
		}
		
	}

}

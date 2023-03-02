package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SendRedirect.do")
public class SendRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 아이디 : abcd123
		// 비밀번호 : a1b2c3!
		//
		// 아이디와 비밀번호가 일치하면 mainTest.jsp로 이동시키자
		String userId = request.getParameter("userId");
		String password = request.getParameter("userPwd");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(userId.equals("abcd123") && password.equals("a1b2c3!")) {

//			out.print("<script>");
//			out.print("alert('로그인성공!');");
//			out.print("</script>");
			
			// respinse.sendRedirect(String url) : url 페이지로 이동
			// 쿼리스트링을 달고 갈 수 있다.
			
			response.sendRedirect("mainTest.jsp?status=loginSuccess"); // 자바코드가 먼저 컴파일 되기 때문에 우선순위에 의해서 위의 코드가 무시됨
			
		}
	}

}

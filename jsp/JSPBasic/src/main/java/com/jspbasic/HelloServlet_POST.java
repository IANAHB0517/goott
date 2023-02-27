package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet_POST") // 매핑(mapping 주소)
									//(현재 서블릿이 실행되는 요청 이름)

public class HelloServlet_POST extends HttpServlet {

	/**
	 * 서버내에서 객체를 직렬화 하는 것. 
	 * 직렬화 하면 객체를 더 빠르게 찾을 수 있다.
	 */
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		System.out.println("GET 방식으로 요청됨");
//	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// request 객체 인코딩 설정 // 요청으로 들어오는 값도 한국어로 입력가능하도록 설정해주어야 깨지지 않는다
		req.setCharacterEncoding("utf-8"); 
		System.out.println("HelloServlet_POST");
		System.out.println("POST 방식으로 요청됨");
		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
		
		// 출력객체 인코딩
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print("<!DOCTYPE html>");
		writer.print("<html>");
		writer.print("<head>");
		writer.print("<title>");
		writer.print("POST 방식 연습");
		writer.print("</title>");
		writer.print("<style>");
		writer.print("<div { color : gray;}>");
		writer.print("</style>");
		writer.print("</head>");
		writer.print("<body>");
		writer.print("<div> 입력하신 아이디 : " + userId + ", 비밀번호 : " + userPwd + "</div>");
		writer.print("</body>");
		writer.print("</html>");
		
		writer.flush();
		writer.close();
		
	}
	
	

}

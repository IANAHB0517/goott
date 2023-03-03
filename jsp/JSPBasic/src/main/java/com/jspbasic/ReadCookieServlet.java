package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReadCook.rc")
public class ReadCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		Cookie[] cooks = request.getCookies();
		
		// 이름을 알고 있는 쿠키의 값만 출력
		PrintWriter out = response.getWriter();
		for (Cookie c : cooks) {
			if(c.getName().equals("myCook")) {
				out.print(c.getValue());
				out.print("<hr/>");
			}
		}
		
		out.print("<hr/>");
		// 모든 쿠키 다 출력
		for (Cookie c : cooks) {
			out.print(c.getName() + ":" + c.getValue());
			out.print("<hr/>");
		}
		
		
		// 이름이 myCook인 쿠키 삭제
		
		for (Cookie c : cooks) {
			if(c.getName().equals("myCook")) {
				c.setMaxAge(0);
				response.addCookie(c);
				out.print("<hr/>");
			}
			
		}
		
		out.print("<hr/>");
		// 모든 쿠키 다 출력
		for (Cookie c : cooks) {
			out.print(c.getName() + ":" + c.getValue());
			
			out.print("<hr/>");
		}
		
		
	}

}

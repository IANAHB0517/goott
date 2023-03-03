package com.jspbasic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/createCook.do")
public class CreateCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie c =  new Cookie("myCook" , "lsj");
		
		c.setMaxAge(60 * 60 * 24); // 하루 동안 저장되는 쿠키
		// expiry - 초단위 정수 값; 음수면 쿠키저장 안됨: 0이면 쿠키삭제
		
		resp.addCookie(c); // 만들어진 쿠키 객체를 response 객체에 담아 클라이언트로 전송
		
		Cookie sesID = new Cookie("session" , req.getSession().getId());
		sesID.setMaxAge(60 * 60 * 24 * 7);
		resp.addCookie(sesID);
	}

	

}

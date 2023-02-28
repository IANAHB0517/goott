package com.jspbasic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/HelloServlet")// 매핑주소 : 현재 서블릿이 실행될 때 요청될 이름
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public HelloServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello Servlet"); 
	
	}

}

package com.weblsj;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PracticeGet")
public class PracticeGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PracticeGet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int h = Integer.parseInt(request.getParameter("height"));
		int w = Integer.parseInt(request.getParameter("width"));
		
		float width = (h * w /2f);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter result = response.getWriter();
		
		result.print("<!DOCTYPE html>"
				+ "<html>"
				+ "<head> <meta charset='utf-8'><title>삼각형의 넓이는?</title> </head> <body>"
				+ "<div><span> 삼각형의 높이는 "+ h +"㎝ </span></div>"
				+ "<div><span> 삼각형의 밑변길이는 "+ w +"㎝</span></div>"
				+ "<div><span> 삼각형의 넓이는 "+ width +"㎠ </span></div>"
				+ "</body></html>");

		result.flush();
		result.close();
		
	}

}

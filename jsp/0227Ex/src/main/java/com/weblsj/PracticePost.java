package com.weblsj;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PracticePost")
public class PracticePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PracticePost() {
        super();
       
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		PrintWriter out =  response.getWriter();
		
		out.print("<!DOCTYPE html>"
				+ "<html>"
				+ "<head> <meta charset='utf-8'><title>아이디와 비밀번호는?</title> </head> <body>"
				+ "<div><span> 유저의 아이디는 "+ id +"</span></div>"
				+ "<div><span> 유저의 비밀번호는 "+ pwd +"</span></div>"
				+ "</body></html>");
		
		out.flush();
		out.close();
	}

}


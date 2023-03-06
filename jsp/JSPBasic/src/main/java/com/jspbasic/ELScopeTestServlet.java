package com.jspbasic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspbasic.vo.TestMemberVo;

@WebServlet("/ELScope.do")
public class ELScopeTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int num1 = 5, num2 = 3;
	int result = num1 - num2;
	
	req.setAttribute("num1", num1);
	req.setAttribute("num2", num2);
	
	TestMemberVo member = new TestMemberVo("abc", "1234", null, "010-1234-5678", "male", 20, null, null, null);
	
	req.setAttribute("member", member);
	
	
	HttpSession ses = req.getSession();
	ses.setAttribute("result", result);
	
	req.getRequestDispatcher("elScope.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

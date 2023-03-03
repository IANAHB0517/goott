package com.MusicPlatform.Service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogIn.pg")
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 아이디 asdf123
		// 비번 123123

		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");

		if (userId.equals("asdf123") && userPwd.equals("123123")) {

			HttpSession ses = req.getSession();
			
			ses.setAttribute("loginUser", userId);
			
			resp.sendRedirect("index.jsp?status=loginSuccess");
		}


	}

}

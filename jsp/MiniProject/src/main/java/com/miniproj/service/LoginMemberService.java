package com.miniproj.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.miniproj.controller.MemberFactory;
import com.miniproj.dao.MemberDAOImpl;
import com.miniproj.error.CommonException;
import com.miniproj.vodto.LoginDTO;
import com.miniproj.vodto.MemberDTO;

public class LoginMemberService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("회원 로그인 처리 하자~");

		MemberFactory mf = MemberFactory.getInstance();

		String userId = req.getParameter("userId");
		String pwd = req.getParameter("pwd");

		LoginDTO dto = new LoginDTO(userId, pwd);
		System.out.println(dto.toString());

		try {
			MemberDTO loginMember = MemberDAOImpl.getInstance().loginUser(dto);
			if (loginMember != null) { // 로그인 성공
				System.out.println("로그인 성공!");
				// 로그인한 유저으 ㅣ정보를 세션 객체에 바인딩
				HttpSession ses = req.getSession();
				ses.setAttribute("loginMember", loginMember);

				req.getRequestDispatcher("../index.jsp").forward(req, resp);

			} else {
				mf.setRedirect(true);
				mf.setWhereisgo("login.jsp?status=fail");
			}
		} catch (NamingException | SQLException e) {
			if (e instanceof NamingException) {
//				e.printStackTrace(); // 개발자가 잘못한 경우
				// NamingException 은 개발자 실수이기 때문에 개발자만 보도록 공통 에러페이지(error.jsp)를 만들었고
				// 에러 정보를 error.jsp로 바인딩하여 error.jsp 페이지에서 에러 정보를 출력하였다.
				// forward
				CommonException ce = new CommonException(e.getMessage(), 99); // 예외 정보를 바인딩
				// throw 와 throws 가 각각 다르다
				// throws는 예외를 넘겨주는 구문
				// throw 는 컴퓨터가 예외라고 생각하지 않는 구문에서 강제로 예외가 발생한 것으로 간주하는 구문
				// 여기서 throw를 사용하지 않은 이유는 이미 오류가 발생해서 코드가 실행중인 경우이기 때문이다.

				ce.setErrorMsg(e.getMessage()); // 에러의 종류
				ce.setStackTrace(e.getStackTrace()); // 에러 기록

				req.setAttribute("error", ce); // 에러 정보를 가진 CommonException 객체 바인딩

				req.getRequestDispatcher("../error.jsp").forward(req, resp); // 페이지 이동

			} else if (e instanceof SQLException) {
				// SQL Exception 은 대부분 실제 유저의 입력 오류로 인한 예외
				mf.setRedirect(true);
				mf.setWhereisgo("login.jsp?status=fail");

			}
		}
		return mf;
	}

}

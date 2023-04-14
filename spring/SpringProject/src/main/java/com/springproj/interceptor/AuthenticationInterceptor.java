package com.springproj.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.springproj.domain.MemberVo;
import com.springproj.etc.DestinationPathProc;
import com.springproj.service.MemberService;

// 제어를 빼앗아 로그인을 했는지 안했는지 검사하는 interceptor
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Inject
	private MemberService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("인터셉터 : 로그인 상태를 검사");

		// 로그인 이전에 있었던 경로를 남기자
		DestinationPathProc.returnPathProc(request);

		boolean result = false;

		HttpSession ses = request.getSession();

		if (ses.getAttribute("loginMember") == null) {
			// 로그인 하지 않았음

			// "ses" 쿠키가 있는지 없는지 검사하여 있다면
			// request.getCookies() 를 이용하여 쿠키 배열을 얻고 쿠키 배열에서 이름이 "ses"인 쿠키를 반환 해줌
			Cookie rememberCookie = WebUtils.getCookie(request, "ses"); // request 객체와 쿠키의 이름을 주면 찾아준다
			if (rememberCookie != null) { // 쿠키 가 있다면
//			2) 사용자가 로그인 하지 않은 상태지만, 쿠키가 있는 경우에 쿠키에 저장되어 있는 세션 ID를
//		   DB에 저장되어 있는 세션 ID 값과 비교하여 유효기간에 맞는 값인지 확인
				String sesIdCookie = rememberCookie.getValue(); // 쿠키의 값 (session ID)
				System.out.println(sesIdCookie + "의 쿠키가 있음");
				// 두 값을 DB로 보내 비교 하고 limit의 유효성을 검사한 후 userId를 반환 해서 session에 loginMember로 남겨준다.

				MemberVo validMember = service.sesValid(sesIdCookie);
				if (validMember != null) { // 유효한 쿠키
//					3) 2)번 조건에 해당하는 유저라면 세션에 로그인 정보를 기록(로그인 성공)
					ses.setAttribute("loginMember", validMember); // 로그인 처리
					// 이전 경로가 있다면 이전 경로로 이동

					String returnPath = "";
					if (ses.getAttribute("returnPath") != null) {
						returnPath = (String) ses.getAttribute("returnPath");
					}

					response.sendRedirect((!returnPath.equals("")) ? returnPath : "/");
					return false; // 더이상 코드가 진행 되지 않도록 함
				}

			} // 쿠키가 없거나 validMember가 없거나
//			페이지에 접속 했을때 자동 로그인 되어 있도록 하고 싶다면 / 루트에서 인터셉터를 걸어서 검사하고 로그인 시킨다

			response.sendRedirect("/login"); // login 페이지 mapping 호출

		} else {
			// 로그인 하였음
			String uri = request.getRequestURI();
			if (uri.indexOf("/modiBoard") != -1 || uri.indexOf("/remBoard") != -1) {
				System.out.println("수정 / 삭제 페이지 로그인 되어 있음");

				MemberVo loginMember = (MemberVo) ses.getAttribute("loginMember");
				String writer = request.getParameter("writer");
				System.out.println(writer);

				if (!loginMember.getUserId().equals(writer)) {
					// 작성자와 로그인 유저가 다를 때
					response.sendRedirect("viewBoard?no=" + request.getParameter("no") + "&status=notPermission");
					return result;
				}

				//

			}

			result = true; // 원래 하던 역할 수행 하도록
		}
		return result;
	}

}

package com.springproj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 제어를 빼앗아 로그인을 했는지 안했는지 검사하는 interceptor
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("인터셉터 : 로그인 상태를 검사");
		
		boolean result = false;
		
		HttpSession ses = request.getSession();
		
		
		if (ses.getAttribute("loginMember") == null) {
			// 로그인 하지 않았음
			
			// 로그인 이전에 있었던 경로를 남기자
			returnPathProc(request);
			
			response.sendRedirect("/login"); // login 페이지 mapping 호출
			
		} else {
			// 로그인 하였음
			result = true; // 원래 하던 역할 수행 하도록
		}
		return result;
	}

	private void returnPathProc(HttpServletRequest req) {
		String requestURI = req.getRequestURI();
		String queryString =  req.getQueryString(); // ?를 제외한 쿼리스트링 문자열
		
		if(queryString == null || queryString.equals("")) {
			queryString= "";
		} else {
			queryString ="?" + queryString;
		}
		
		if(req.getMethod().equals("GET")) { // GET 방식으로 요청 되었다면
			System.out.println("로그인 후 이동할 페이지 : " + requestURI + queryString) ;
			req.getSession().setAttribute("returnPath", requestURI + queryString);
		}
	}

	
}

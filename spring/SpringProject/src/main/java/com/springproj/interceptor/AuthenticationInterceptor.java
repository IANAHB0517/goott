package com.springproj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springproj.domain.MemberVo;
import com.springproj.etc.DestinationPathProc;

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
			DestinationPathProc.returnPathProc(request);
			
			response.sendRedirect("/login"); // login 페이지 mapping 호출
			
		} else {
			// 로그인 하였음
			String uri = request.getRequestURI();
			if (uri.indexOf("/modiBoard") != -1 || uri.indexOf("/remBoard") != -1) {
				System.out.println("수정 / 삭제 페이지 로그인 되어 있음");
				
				MemberVo loginMember= (MemberVo)ses.getAttribute("loginMember");
				String writer = request.getParameter("writer");
				System.out.println(writer);
				
				if(!loginMember.getUserId().equals(writer)) {
					// 작성자와 로그인 유저가 다를 때
					response.sendRedirect("viewBoard?no="+request.getParameter("no") +
							"&status=notPermission");
					return result;
				}
				
				//
				
			}
			
			result = true; // 원래 하던 역할 수행 하도록
		}
		return result;
	}

	

	
}

package com.springproj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springproj.domain.MemberVo;

// 제어를 빼앗아 실제 로그인을 처리하는 interceptor
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginInterceptor : 로그인 처리");

		HttpSession ses = request.getSession();
		ses.removeAttribute("loginMember");
		ses.removeAttribute("returnPath");
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("LoginInterceptor : 로그인 처리하려고 DB 다녀 왔음 ");
		// session 객체에 로그인 성공한 객체를 바인딩

		HttpSession ses = request.getSession();

		ModelMap mm = modelAndView.getModelMap(); // ModelMap() 이전 컨트롤러단 에서 바인딩한 Model 객체를 가지고 있는 객체
		MemberVo loginMember = (MemberVo) mm.get("loginMember");
		if (loginMember != null) {
			System.out.println("로그인 성공");
			ses.setAttribute("loginMember", loginMember); // 세션에 로그인 한 유저의 정보를 바인딩
//			response.sendRedirect("/"); // 로그인이 완료된 뒤에는 index.jsp 페이지로 이동
			String returnPath = "";
			if (ses.getAttribute("returnPath") != null) {
				returnPath = (String)ses.getAttribute("returnPath");
			}
			
			response.sendRedirect((!returnPath.equals("")) ? returnPath : "/" );
			// 3항 연산자 ? 앞의 구문을 검사하여 참이면 : 의 앞의 값을 수행 거짓이면 뒤의 것을 수행

		} 
	}

}

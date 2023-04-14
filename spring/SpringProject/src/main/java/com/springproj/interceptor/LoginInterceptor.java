package com.springproj.interceptor;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springproj.domain.MemberVo;
import com.springproj.domain.SessionDTO;
import com.springproj.etc.DestinationPathProc;
import com.springproj.service.MemberService;

// 제어를 빼앗아 실제 로그인을 처리하는 interceptor
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	private MemberService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("/login GET : LoginInterceptor : 로그인 처리 - preHandel ");
		
		HttpSession ses = request.getSession();
		//DestinationPathProc.returnPathProc(request); // 이전 경로 저장
//		ses.removeAttribute("loginMember");
//		ses.removeAttribute("returnPath");
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("/loging POST : LoginInterceptor : 로그인 처리하려고 DB 다녀 왔음 - postHandle");
		// session 객체에 로그인 성공한 객체를 바인딩

		HttpSession ses = request.getSession();

		ModelMap mm = modelAndView.getModelMap(); // ModelMap() 이전 컨트롤러단 에서 바인딩한 Model 객체를 가지고 있는 객체
		MemberVo loginMember = (MemberVo) mm.get("loginMember");
		if (loginMember != null) {
			System.out.println("로그인 성공");
			ses.setAttribute("loginMember", loginMember); // 세션에 로그인 한 유저의 정보를 바인딩
			
//			1) 사용자가 로그인 시에 자동로그인을 체크하여 로그인 했을 때 
//			   쿠키에 현재 세션 ID 값을 저장하고 , DB에도 아이디, 세션 ID 값과 만료일(+3)을 저장해야함
			
			// /login으로 부터 가로 채었기 때문에 해당 메서드가 가지고 있는 request 객체를 그대로 가지고 있다.
			System.out.println("remember : " + request.getParameter("remember")); 
			
			if (request.getParameter("remember") != null) { // 자동로그인이 체크가 되어있으면
				System.out.println("자동로그인 체크 , 쿠키저장");
				
				String sesId = ses.getId();
				Timestamp now = new Timestamp(System.currentTimeMillis()); // long 타입으로 현재시간을 ms 단위로 반환
				Timestamp seslimit = new Timestamp(System.currentTimeMillis() + (60 * 60 * 24 * 3 * 1000));
				
				System.out.println("현재시간 : " + now.toString() + " 만료일 : " + seslimit.toString());
				
				// servlet 객체의 Cookie 객체
				Cookie rememberCookie = new Cookie("ses" , ses.getId()); 
				rememberCookie.setPath("/");
				rememberCookie.setMaxAge(60 * 60 * 24 * 3); // 4일 동안 쿠키 저장
				
				response.addCookie(rememberCookie); // 실제 브라우저에 쿠키저장
				
				// DB에 session 정보 저장
				// 서비스를 거쳐서 다오단을 거쳐 DB에 접근해야함
				if(service.remember(new SessionDTO(sesId, seslimit, loginMember.getUserId()))) {; //Controller 가 아닌데 되나?
					System.out.println("쿠키 및 DB에 Session 값 저장 완료");
				}
				
			}
			
			String returnPath = "";
			if (ses.getAttribute("returnPath") != null) {
				returnPath = (String)ses.getAttribute("returnPath");
			}
			
			response.sendRedirect((!returnPath.equals("")) ? returnPath : "/" );
			// 3항 연산자 ? 앞의 구문을 검사하여 참이면 : 의 앞의 값을 수행 거짓이면 뒤의 것을 수행

		} 
	}

}

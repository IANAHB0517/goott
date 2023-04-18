package com.springproj.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;
import com.springproj.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private MemberService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	@RequestMapping("exam")
	public void examInterceptor() {
		System.out.println("examInterceptor() 동작");
	}
	
	@RequestMapping("login")
	public void login() {
		// 먼저 loginInterceptor의 preHandle() 호출
		
		// login.jsp 렌더링
		
	}
	
	// POST 방식이 동작하기 위해서는 GET 방식으로 호출 했을때 동작하는 메서드가 필요하다????? 아닌듯
	@RequestMapping(value="login", method = RequestMethod.POST)
	public void login(LoginDTO login, Model model, RedirectAttributes rab , HttpServletRequest req) throws Exception { 
		// 먼저 loginInterceptor의 preHandle() 호출
//		System.out.println(login.toString());
		
		MemberVo loginMember = service.login(login, req);
		
		
		if (loginMember != null) {
			System.out.println(loginMember.toString());
			model.addAttribute("loginMember", loginMember);
			
		} 
//		else {
//			rttr.addFlashAttribute("status", "fail"); // rttr redirect 할때만 사용 가능 이 메서드는 void 한 포워딩이나 마찬가지 이기때문에 작동하지 않는다
//		}
		
		// loginInterceptor의 posthandle() 호출
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession ses = req.getSession();
		
		System.out.println("로그아웃");
		
//		4) 유저가 자동 로그인 상태에서 로그아웃을 눌렀을 경우 쿠키 삭제
		Cookie sesCookie = WebUtils.getCookie(req, "ses");
		if(sesCookie != null ) {			
			sesCookie.setMaxAge(0); // 쿠키 삭제
			resp.addCookie(sesCookie);
		}
		
		ses.removeAttribute("loginMember");
		ses.removeAttribute("returnPath");
		ses.invalidate();
		
		return "redirect:/";
	}
}

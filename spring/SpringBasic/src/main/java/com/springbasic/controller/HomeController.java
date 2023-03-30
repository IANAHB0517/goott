package com.springbasic.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller // 현재 클래스가 컨트롤러 단 임을 명시
public class HomeController {
	// 로그를 남길 수 있도록 하는 객체
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * http://localhost:8081/controller/를 실행 했을때
	 *  서블릿 단으로 부터 요청된 객체를 매핑
	 *  "/ 가" GET방식으로 요청 되면 아래의 home 메서드를호출하게된다.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		// 모델 객체에 serverTime 이란이름으로 formattedDate 로 바인딩
		// Model 객체 (Cotroller 단에서 view 단으로 바인딩 된 정보를 넘겨주는 개체
		
		System.out.println("Hello world");
		
		return "home";
		// servlet 에게 "home" 반환
		
		// servlet-context에 설정되어있는 servlet 객체의 ViewResolver에게 return 되고,
		
		// 위의 viewReslover 객체에 의해 forwarding 되거나 이동될 view 페이지의 이름과 경로가 조립된다
		//  /"/WEB-INF/views + "home" + ".jsp
		// 그래서 /WEB-INF/views/home.jsp 이 완성된다.
	}
	
}

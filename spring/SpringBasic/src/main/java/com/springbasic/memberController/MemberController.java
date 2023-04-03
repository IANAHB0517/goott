package com.springbasic.memberController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	
	// "memerView", method = RequestMethod.GET에 대응하는 메서드
		// GET 방식으로 호출될 때는 method = RequestMethod.GET 생략 가능
		@RequestMapping("memberView")
		public String doMemberView() {
			System.out.println("doMemberView 호출됨");
			
			return "MemberView"; // /webapp/WEB-INF/view/doMemberView.jsp로 응답
			
		}

	@RequestMapping(value = "memberModi", method = RequestMethod.GET)
	public String doMemberModify() {
		System.out.println("MemberModify 호출됨!");
		
		return "MemberModify";
	}
	
	// 매핑되는 매핑주소를 String[] 타입으로 여러개를 지정할 수 있다.
	@RequestMapping(value = {"memberSave","memberInput"}, method = RequestMethod.GET)
	public String doMemberSave() {
		System.out.println("MemberSave 호출됨!");
		
		return "MemberSave";
	}
	
	@RequestMapping(value="login.do") // return 값이 없을 경우(void인 경우) 현재 경로 .jsp를 찾게 된다.
	public void doLogin() {
		// 여기가 안됨
		System.out.println("login 호출됨!");
		
	}
	// 리턴 타입이 void인 경우에는 매핑주소 .jsp를 현재 경로에서 찾게 된다
	@RequestMapping(value="logout") 
	public void doLogOut() {
		System.out.println("logout 호출됨!");
		// logout.jsp를 찾아 response
	}
	
	// @ModelAttribute: 해당 파라메터를 변수에 저장했다가, view까지 전달	
	@RequestMapping("hello")
	public String doHello(@ModelAttribute("name") String name) {
		System.out.println("name : " + name);
		return "hello";
		
	}
	// parameter 인 name의 값이 없어도 오류가 나지 않는다
	@RequestMapping("sayHello")
	public String doSayHello(@ModelAttribute("name") String name, @ModelAttribute("age") String age) {
		System.out.println("name : " + name);
		
		int intAge = -1;
		try {
			intAge = Integer.parseInt(age); // 이구문이 있을 경우 파라메터에 age가 없으면 오류가 난다			
		} catch (NumberFormatException e) {
			intAge = 0;
		}
		
		
		System.out.println("age : " + intAge);
		return "sayHello";
		// 쿼리스트링은 String이 기본 값이기 때문에 int로 초기화 하기보다 String으로 초기화 하고 값을 이용할때
		// parseInt 해주어야 한다.
	}
	
}

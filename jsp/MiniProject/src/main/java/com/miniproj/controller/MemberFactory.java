package com.miniproj.controller;

import com.miniproj.service.MemberService;

public class MemberFactory {
	private static MemberFactory instance = null;

	private MemberFactory() { }

	public static MemberFactory getInstance() {
		if (instance == null) {
			instance = new MemberFactory();
		}
		return instance;
	}
	
	
	/**
	 * 공통 서블릿에서 command를 받아 필요한 서비스단의 객체를 반환해주는 메서드
	 * @param command : 공통 서블릿 단에서 주는 요청한 서비스 객체
	 * 반환 값 타입 : 
	 */
	public MemberService getService(String command) {
		MemberService  service = null;
		
		if (command.equals("/member/login.mem")) {
			service = new LoginMemberService();
		} else if (command.equals("/member/register.mem")) {
			service = new RegisterMemberService();
		}
		
		return service;
	}
}

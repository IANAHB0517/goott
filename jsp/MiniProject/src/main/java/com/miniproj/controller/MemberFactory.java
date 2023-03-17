package com.miniproj.controller;

import com.miniproj.service.ConfirmCodeService;
import com.miniproj.service.DulicateUserIdService;
import com.miniproj.service.SendEmailService;
import com.miniproj.service.LoginMemberService;
import com.miniproj.service.LogoutMemberService;
import com.miniproj.service.MemberService;
import com.miniproj.service.RegisterMemberService;

public class MemberFactory {
	private static MemberFactory instance = null;
	private boolean isRedirect; // redirect 할것인지 말것인지
	private String whereisgo; // 어느 페이지로 이동할것인지
	
	public String getWhereisgo() {
		return whereisgo;
	}

	public void setWhereisgo(String whereisgo) {
		this.whereisgo = whereisgo;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

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
		} else if (command.equals("/member/duplicateUserId.mem")) {
			service = new DulicateUserIdService();
		} else if (command.equals("/member/logout.mem")) {
			service = new LogoutMemberService();
		} else if (command.equals("/member/sendMail.mem")) { // 인증 코드를 메일로 보내주는 서비스
			service = new SendEmailService();
		} else if (command.equals("/member/confirmCode.mem")) { // 인증 코드 확인 서비스
			service = new ConfirmCodeService();
		}
		return service;
	}
}

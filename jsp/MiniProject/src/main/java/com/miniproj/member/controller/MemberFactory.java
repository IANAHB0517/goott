package com.miniproj.member.controller;

import com.miniproj.member.service.ConfirmCodeService;
import com.miniproj.member.service.DulicateUserIdService;
import com.miniproj.member.service.GetMemberPointService;
import com.miniproj.member.service.LoginMemberService;
import com.miniproj.member.service.LogoutMemberService;
import com.miniproj.member.service.MemberService;
import com.miniproj.member.service.MyPageMemberService;
//import com.miniproj.member.service.PointListBoardService;
import com.miniproj.member.service.RegisterMemberService;
import com.miniproj.member.service.SendEmailService;

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
	 * 반환 값 타입 : MemberFactory
	 */
	public MemberService getService(String command) {
		MemberService  service = null;
		
		if (command.equals("/member/login.mem")) {
			service = new LoginMemberService();
		} else if (command.equals("/member/register.mem")) {
			service = new RegisterMemberService();
		} else if (command.equals("/member/duplicateUserId.mem")) {
			service = new DulicateUserIdService();
		} else if (command.equals("/logout.mem")) {
			service = new LogoutMemberService();
		} else if (command.equals("/member/sendMail.mem")) { // 인증 코드를 메일로 보내주는 서비스
			service = new SendEmailService();
		} else if (command.equals("/member/confirmCode.mem")) { // 인증 코드 확인 서비스
			service = new ConfirmCodeService();
		} else if (command.equals("/member/myPage.mem")) { // 마이 페이지
			service = new MyPageMemberService();
		}  else if (command.equals("/member/getMemPoint.mem")) { // 마이 페이지
			service = new GetMemberPointService();
		} 
//		else if (command.equals("/pointListAll.mem")) {
//			service = new PointListBoardService();
//		} 
		
		return service;
	}
}

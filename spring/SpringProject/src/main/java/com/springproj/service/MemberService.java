package com.springproj.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.MemberVo;
import com.springproj.domain.SessionDTO;


public interface MemberService {

	// 로그인 처리
	MemberVo login(LoginDTO login, HttpServletRequest req) throws Exception;
	
	// 멤버 포인트 조회
	List<MemberPointVo> getMemPoint(String userId)throws Exception;
	
	// 자동로그인 처리
	boolean remember(SessionDTO ses)throws Exception;

	// 쿠키에 저장된 세션이 유효한지 아닌지
	MemberVo sesValid(String sesIdCookie) throws Exception;
	
}

package com.springproj.service;

import java.util.List;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.MemberVo;


public interface MemberService {

	// 로그인 처리
	MemberVo login(LoginDTO login) throws Exception;
	
	// 멤버 포인트 조회
	List<MemberPointVo> getMemPoint(String userId)throws Exception;
}

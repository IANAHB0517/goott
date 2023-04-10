package com.springproj.service;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;


public interface MemberService {

	// 로그인 처리
	MemberVo login(LoginDTO login) throws Exception;
}

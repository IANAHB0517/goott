package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.MemberVo;
import com.springproj.domain.SessionDTO;

public interface MemberDAO {
	
	MemberVo selectMemberWithLoginDTO(LoginDTO login) throws Exception;
	
	// 멤버 포인트 조회
	List<MemberPointVo> selectMemberPoint(String userId) throws Exception;

	// 자동 로그인
	int updateRemember(SessionDTO ses)throws Exception;

	// 쿠키에 저장된 세션이 유효한지 안한지
	MemberVo selectRemember(String sesIdCookie) throws Exception;
}

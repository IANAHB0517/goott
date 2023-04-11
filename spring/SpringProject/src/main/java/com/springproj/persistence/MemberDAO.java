package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.MemberVo;

public interface MemberDAO {
	
	MemberVo selectMemberWithLoginDTO(LoginDTO login) throws Exception;
	
	// 멤버 포인트 조회
	List<MemberPointVo> selectMemberPoint(String userId) throws Exception;
}

package com.springproj.persistence;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;

public interface MemberDAO {
	MemberVo selectMemberWithLoginDTO(LoginDTO login) throws Exception;
}

package com.miniproj.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.miniproj.vodto.MemberDTO;

public interface MemberDAO {
	// 회원 가입
	public int insertMember(MemberDTO dto) throws NamingException,  SQLException;
}

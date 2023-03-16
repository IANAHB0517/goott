package com.miniproj.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.miniproj.vodto.LoginDTO;
import com.miniproj.vodto.MemberDTO;

public interface MemberDAO {
	// 회원 가입
	public int insertMember(MemberDTO dto) throws NamingException,  SQLException;
	
	// 아이디 중복
	int selectByUserId(String userId) throws NamingException,  SQLException;
	
	// 로그인
	MemberDTO loginUser(LoginDTO dto) throws NamingException,  SQLException;
}

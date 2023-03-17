package com.miniproj.dao;

import java.sql.Connection;
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
	
	// 포인트 부여
	int addPoint(String userId, String why, Connection con) throws NamingException,  SQLException;
}

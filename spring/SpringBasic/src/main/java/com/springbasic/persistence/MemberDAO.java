package com.springbasic.persistence;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.springbasic.domain.MemberVO;

public interface MemberDAO {
	
	String getdateTime();
	
	// 회원 가입
	public int insertMember(MemberVO dto) throws NamingException, SQLException;


}

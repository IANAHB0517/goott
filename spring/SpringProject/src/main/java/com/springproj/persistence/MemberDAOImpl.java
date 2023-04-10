package com.springproj.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession ses;
	
	private static String ns = "com.springproj.mappers.memberMapper";
	
	@Override
	public MemberVo selectMemberWithLoginDTO(LoginDTO login) throws Exception {
		
		return ses.selectOne(ns + ".login", login);
	}

}

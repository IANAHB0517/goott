package com.springproj.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.MemberVo;
import com.springproj.domain.SessionDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession ses;
	
	private static String ns = "com.springproj.mappers.memberMapper";
	
	@Override
	public MemberVo selectMemberWithLoginDTO(LoginDTO login) throws Exception {
		
		return ses.selectOne(ns + ".login", login);
	}

	@Override
	public List<MemberPointVo> selectMemberPoint(String userId) throws Exception {
		System.out.println("DAO 단 : 멤버 포인트 조회");
		
		return ses.selectList(ns + ".memberPoint", userId);
	}

	@Override
	public int updateRemember(SessionDTO ses) throws Exception {
		System.out.println("DAO 단 : 자동 로그인 기록");
		
		return this.ses.update(ns + ".updateRemember", ses);
	}

	@Override
	public MemberVo selectRemember(String sesIdCookie) throws Exception {
		System.out.println("DAO 단 : 자동 로그인 기록 검사");
		
		return this.ses.selectOne(ns + ".selectRemember", sesIdCookie);
	}

	
	
	
}

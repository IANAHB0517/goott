package com.springbasic.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springbasic.domain.MemberPointVO;
import com.springbasic.domain.MemberVO;

@Repository // 현재 클래스가 DAO 단임을 spring컨테이너에게 알린다
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession ses; // sqlSessionTemplate 객체 주입

	private static String ns = "com.springbasic.mapper.memberMapper";
	
	@Override
	public String getDateTime() {
		return this.ses.selectOne(ns +".getDateTime"); 
	}

	@Override
	public int insertMember(MemberVO dto) throws NamingException, SQLException {
		String sql =ns +".insertMemberWithFile";
		return this.ses.insert(sql, dto);
	}

	@Override
	public int updateMember(MemberVO dto) throws Exception {
		return this.ses.update(ns + ".updateMember", dto);
	}

	@Override
	public int deleteMember(String userId) throws Exception {
		return this.ses.delete(ns +".deleteMember", userId);
	}

	@Override
	public MemberVO selectMemberByUserId(String userId) throws Exception {
		return this.ses.selectOne(ns +".viewMemberByUserId", userId);
	}

	@Override
	public List<MemberVO> selectAllMembers() throws Exception {
		return this.ses.selectList(ns + ".viewAllMember");
	}

	@Override
	public MemberVO loginMember(String userId, String userPwd) throws Exception {
		// SqlSessionTemplate 객체의 insert, update, delete, select 문의 파라메터는
		// Object로 하나만 받을 수 있다.
		
		// 여러개의 파라메터 값을 Map으로 감싸서 넘겨 줄 수 있다.
		
		// DTO를 새로 만들고 싶지 않을 때 가능
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("userPwd", userPwd);		
		return ses.selectOne(ns + ".loginMember", param);
	}

	@Override
	public int selectByUserId(String userId) throws Exception {
		return ses.selectOne(ns + ".isDuplicate", userId);
	}

	@Override
	public int addPoint(String who, String why) throws Exception {
		Map<String, Object> point = new HashMap<String, Object>();
		point.put("who", who);
		point.put("why", why);
		
		return ses.selectOne(ns + ".addPoint", point);
	}

	@Override
	public int whenLatestLogin(String userId) throws Exception {
	
		return ses.selectOne(ns + ".whenLatestLogin", userId);
	}

	@Override
	public List<MemberPointVO> getMemberPoint(String userId) throws Exception {
		return ses.selectList(ns + ".memberPointList",userId);
	}
	
	
	
	

}

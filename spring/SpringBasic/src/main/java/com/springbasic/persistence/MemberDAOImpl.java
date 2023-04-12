package com.springbasic.persistence;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springbasic.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession ses;
	
	
	
	
	@Override
	public int insertMember(MemberVO dto) throws NamingException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public String getdateTime() {
		String sql = "com.springbasic.mapper.memberMapper.getDateTime";
		
		return this.ses.selectOne(sql);
	}

}

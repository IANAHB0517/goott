package com.springproj.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.MessageVo;

@Repository
public class MessageDAOImple implements MessageDAO {

	@Inject
	private SqlSession ses;
	
	private static String ns = "com.springproj.mappers.messageMapper";
	
	@Override
	public int sendMassage(MessageVo msg) throws Exception {
		
		return ses.insert(ns + ".sendMsg", msg);
	}

	@Override
	public int selectMsgCnt(String userId) throws Exception {
		
		
		return ses.selectOne( ns + ".getMsgCnt",userId);
		
	}

	
	
}
 
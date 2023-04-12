package com.springproj.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.Replies;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession ses; // SQL 세션 주입
	
	private static String ns = "com.springproj.mappers.replyMapper";
	
	@Override
	public int insertReply(Replies reply) throws Exception {
	
		return ses.insert(ns + ".insertReply", reply);
	}

	@Override
	public List<Replies> selectAllReplies(int boardNo) throws Exception {
		
		return ses.selectList(ns + ".selectAllReplies", boardNo);
	}

}

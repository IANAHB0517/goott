package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.Replies;

public interface ReplyDAO {

	//댓글 등록
	int insertReply(Replies reply) throws Exception;

	// boardNo번 글의 모든 댓글 가져오기
	List<Replies> selectAllReplies(int boardNo)throws Exception;
}

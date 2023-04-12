package com.springproj.service;

import java.util.List;

import com.springproj.domain.Replies;

public interface ReplyService {

	// 댓글등록
	boolean addReply(Replies reply) throws Exception;

	// 전체 댓글 조회
	List<Replies> getAllReplies(int boardNo) throws Exception;
}

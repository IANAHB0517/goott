package com.springproj.service;

import com.springproj.domain.MessageVo;

public interface MessageService {

	// 메세지 발송
	boolean send(MessageVo msg) throws Exception;

	// 읽지 않은 쪽지 개수 가져오기
	int getMsgCnt(String userId) throws Exception;

}

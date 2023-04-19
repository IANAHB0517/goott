package com.springproj.service;

import com.springproj.domain.MessageVo;

public interface MessageService {
	
	// 메세지 발송
	boolean send(MessageVo msg) throws Exception;
}

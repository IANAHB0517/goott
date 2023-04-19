package com.springproj.persistence;

import com.springproj.domain.MessageVo;

public interface MessageDAO {

	// 메세지 발송
	int sendMassage(MessageVo msg) throws Exception;
}

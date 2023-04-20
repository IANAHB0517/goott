package com.springproj.persistence;

import com.springproj.domain.MessageVo;

public interface MessageDAO {

	// 메세지 발송
	int sendMassage(MessageVo msg) throws Exception;

	// userId 가 읽지 않은 쫒지 개수 가져오기
	int selectMsgCnt(String userId)throws Exception;
}

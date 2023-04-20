package com.springproj.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;



import com.springproj.domain.MessageVo;
import com.springproj.persistence.MessageDAO;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Inject
	private MessageDAO dao;

	@Override
	public boolean send(MessageVo msg) throws Exception {
		System.out.println("service 단 : Message 전송" );
		
		int result = dao.sendMassage(msg);
		if (result == 1) {
			return true;
			
		}
		return false;
	}

	@Override
	public int getMsgCnt(String userId) throws Exception {
		
		return  dao.selectMsgCnt(userId);
	}

	
	
}

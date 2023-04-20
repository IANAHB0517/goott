package com.springproj.controller.message;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springproj.domain.MessageVo;
import com.springproj.domain.MemberVo;
import com.springproj.service.MemberService;
import com.springproj.service.MessageService;

@RestController
@RequestMapping("/message/*")
public class MessageController {
	
	@Inject
	private MessageService service;
	
	
	@RequestMapping(value="send", method = RequestMethod.POST)
	public ResponseEntity<String> sendMessage(@RequestBody MessageVo msg, HttpServletRequest req) {
		System.out.println(msg.toString() + "를 전송") ;
		
		
		String sender = ((MemberVo)(req.getSession().getAttribute("loginMember"))).getUserId();
		msg.setSender(sender);
		
		System.out.println(msg.toString() + "를 전송") ;
		
		ResponseEntity<String> result = null;
		
		try {
			
			if (service.send(msg)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
		return result;
	}
	
	@RequestMapping("updateMsgCnt/{userId}")
	public ResponseEntity<String> getMsgCnt(@PathVariable("userId") String userId) {
		System.out.println(userId);
		
		ResponseEntity<String> result = null;
		try {
			int cnt = service.getMsgCnt(userId);
			result = new 		ResponseEntity<String>(cnt+"", HttpStatus.OK);
		} catch (Exception e) {
			result = new 		ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

			e.printStackTrace();
		}
		
		return result;
	}
}

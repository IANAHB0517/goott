package com.springproj.controller.reply;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springproj.domain.Replies;
import com.springproj.service.ReplyService;

@RestController // 현재 클래스가 REST 방식으로 동작하는 컨트롤러 객체임을 명시
@RequestMapping("/reply") // reply URI에 대해 현재 객체가 mapping
public class ReplyController {

	@Inject
	private ReplyService service;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> saveReply(@RequestBody Replies reply) { // json 으로 받은 Replies 객체를 reply라는 이름으로 가지고와라
		// ResponseEntity객체 ResponseBody는 JSON으로 반환 받는다. Text를 보내기위한 객체
		System.out.println(reply.toString() + "댓글을 등록하자");

		ResponseEntity<String> result = null;

		if (reply.getReplier().equals("")) {
			result = new ResponseEntity<String>("notPermitted", HttpStatus.BAD_REQUEST);
		} else {
			// 댓글 등록 처리 후 긍정적 result
			try {
				service.addReply(reply);
			} catch (Exception e) {
				result = new ResponseEntity<String>("notSaved", HttpStatus.CONFLICT);
				e.printStackTrace();
			}

			result = new ResponseEntity<String>("success", HttpStatus.OK);
		}

		return result;
	}
	
	@RequestMapping(value="/all/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<List<Replies>> getAllReplies(@PathVariable("boardNo") int boardNo) {
		
		ResponseEntity<List<Replies>> result = null;
		
		System.out.println(boardNo + "번 글의 댓글을 조회 하자");
		
		try {
			List<Replies> lst = service.getAllReplies(boardNo);
			result = new ResponseEntity<List<Replies>>(lst, HttpStatus.OK);
		} catch (Exception e) {
//			result = new ResponseEntity<List<Replies>>(null, HttpStatus.CONFLICT); // 위 상황에서 List<Replies> 자리에 null 이 들어오기 때문에  아래와 같이 처리해준다
			result = new ResponseEntity<>( HttpStatus.CONFLICT);
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/{replyNo}", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyReply(@PathVariable("replyNo") int replyNo, @RequestBody Replies reply) {
		System.out.println("수정할 댓글 번호 : " + replyNo + ", 수정 내용 : " + reply.toString());
		
		
		ResponseEntity<String> result = null;
		
		try {
			if(service.modifyReply(reply)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
				
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>("fail", HttpStatus.CONFLICT);
		};
		
		return result;
	}
}

package com.springproj.controller.member;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproj.domain.MemberPointVo;
import com.springproj.domain.MemberVo;

import com.springproj.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Inject
	private MemberService service;
	
	
	
	@RequestMapping("myPage")
	public void myPage(@RequestParam("userId") String userId, Model model) throws Exception {
		System.out.println("컨트롤러 단 : "+ userId +" myPage 조회");
		
		MemberVo memberInfo = service.getUserInfo(userId);
		
		if (memberInfo != null) {
			model.addAttribute("memberInfo", memberInfo);
		}
		
	}
	
	@RequestMapping("getMemPoint")
	public void memPoint(Model model, HttpServletRequest req) throws Exception {
		System.out.println("컨트롤러 단 : myPage memberPoint 조회");
		HttpSession ses = req.getSession();
//		MemberVo loginMember =(MemberVo)req.getSession().getAttribute("loginMember");
//		System.out.println(loginMember.toString());
		String userId = ((MemberVo)req.getSession().getAttribute("loginMember")).getUserId();
		
		
		List<MemberPointVo> mpList = service.getMemPoint(userId);
		for (MemberPointVo mp : mpList ) {
			System.out.println(mp);
		}
		
		model.addAttribute("mpList", mpList);
		
	}
	
}

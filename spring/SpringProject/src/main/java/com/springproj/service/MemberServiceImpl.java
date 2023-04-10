package com.springproj.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.MemberVo;
import com.springproj.persistence.BoardDAO;
import com.springproj.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO dao; // MemberDAO 객체 주입
	
	@Inject
	private BoardDAO bdao;

	
	@Override
	@Transactional // Transaction 도 Interceptor 로 수행된다
	public MemberVo login(LoginDTO login) throws Exception {
		// 1) 로그인 처리
		MemberVo loginMember = dao.selectMemberWithLoginDTO(login);
		
		// 2) 로그인 성공이라면 -> 로그인한 유저에게 포인트 지급
//		System.out.println(loginMember.toString());
		if(loginMember != null) {
			bdao.addPointToMember(new MemberPointVo(0, loginMember.getUserId(), null, "로그인", 0));
		}
		
		return loginMember;
	}

}

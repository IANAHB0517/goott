package com.miniproj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniproj.service.MemberService;

public class LoginMemberService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 System.out.print("회원 로그인 처리 하자~");
		 return null;
	}

}

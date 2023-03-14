package com.miniproj.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniproj.controller.MemberFactory;

public interface MemberService {
// req 와 resp을 처리 하는 것이 공통 적으로 해야하는 일이다.
	
	public abstract MemberFactory execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}

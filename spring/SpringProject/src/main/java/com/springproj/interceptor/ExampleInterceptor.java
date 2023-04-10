package com.springproj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExampleInterceptor extends HandlerInterceptorAdapter{

	// 지정된 컨트롤러 단의 역할이 실행되기 이전에 수행될 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("prehandle()");
		return true;
	}
	// 지정된 컨트롤러 단의 역할이 실행된 이후 (view 단이 렌더링 되기 이전에) 수행될 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("postHandle()");
	}
	// 지정된 컨트롤러 단의 역할이 실행된 이후 (view 단이 렌더링 되고난 이후에) 수행될 메서드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion()");
		
	}

	
}

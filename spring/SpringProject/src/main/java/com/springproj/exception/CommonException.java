package com.springproj.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 현재 클래스에서 공통예외 처리 할 클래스임을 명시
public class CommonException {

//	@ExceptionHandler(SQLException.class) // 각 예외 상황을 따로따로 처리할 때
//	public String sqlExceptionProcessing(SQLException se) {
//		System.out.println("SQL Exception 발생!");
//
//		System.out.println(se.getMessage());
//		se.printStackTrace();
//
//		return "error"; // error.jsp 로 이동
//	}

	@ExceptionHandler(ArithmeticException.class)
	public String arithmeticExceptionHandling(ArithmeticException ae) {
		System.out.println("ArithmeticException 발생");

		System.out.println(ae.getMessage());
		return null;
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandling(Exception e, Model model) {
		System.out.println("Exception 발생");
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		model.addAttribute("stacktrace", e.getStackTrace());
		return "/error";
	}

}

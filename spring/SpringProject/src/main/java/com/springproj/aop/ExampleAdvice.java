package com.springproj.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Spring Container에 Bean으로 자동 등록 시키기 위한 어노테이션
@Aspect // AOP 기능을 하는 클래스에 선언을 추가 하는 어노테이션
public class ExampleAdvice {
	// 현재 클래스에서 로그를 남기기위한 로거 객체를 얻어옴
	private static final Logger logger = LoggerFactory.getLogger(ExampleAdvice.class);
	
	// Junit은 단위 테스트용
	@Before("execution(* com.springproj.service.BoardServiceImpl.saveBoard(..))") 
	//  com.springproj.service.BoardServiceImpl 클래스의 매개변수가 0 개 이상인 모든 메서드가
	//	실행되기 이전에 startLog()를 실행하라
	public void startLog(JoinPoint joinPoint) {
		this.logger.info("----------------------------------------------------------------------");
		this.logger.info("------------------------------start log-------------------------------");
		this.logger.info("----------------------------------------------------------------------");
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("------------------------------start log-------------------------------");
		System.out.println("----------------------------------------------------------------------");
		
		System.out.println(Arrays.toString(joinPoint.getArgs()));
						// 해당 메서드가 받는 모든 매개변수를 가져온다.
	}
	
	//com.springproj.service.ReplyServiceImpl 클래스의 addReply 메서드가 실행되기 이전과 이후에 개입
	@Around("execution(* com.springproj.service.ReplyServiceImpl.addReply(..))")
	public Object timeLog(ProceedingJoinPoint pJoinPoint) throws Throwable {
		// addReply(..) 가 호출되기 이전에 수행 할 것
		System.out.println("----------------------------------------------------------------------");
		System.out.println("------------------------------start log-------------------------------");
		System.out.println("----------------------------------------------------------------------");
		
		long startTime = System.currentTimeMillis();
		System.out.println("댓글 저장 시작 시간 : " + startTime);
		System.out.println("매개변수 : " + Arrays.toString(pJoinPoint.getArgs()));
		
		// addReply(..) 가 실행되도록 처리한다
		// proceed()의 반환 값은 addReply()의 반환값 타입 
		Object result = pJoinPoint.proceed();  // 어떤 메서드가 호출 될지 모르기 때문에 다형성에 의해서 Object로 받아서 반환하도록한다.
		
		// addReply(..) 가 호출된 이후에 수행할 것들을 기술
		long endTime = System.currentTimeMillis();
		System.out.println("댓글 저장 완료 시간 : " + endTime);
		System.out.println("댓글 저장 총 수행 시간 : " + (endTime - startTime));
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("--------------------------------end log-------------------------------");
		System.out.println("----------------------------------------------------------------------");
		
		return result; // 여기에서는 addReply()를 호출한 반환값 (boolean)을 service.addReply()를 호출한 컨트롤러 단에 반환한다.
		
	}
	
}

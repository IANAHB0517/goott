package weblsj;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {
	 public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력하세요 >>");
		int result = 0;
		try {
			int num = sc.nextInt(); // 정수를 입력하지 않으면 java.util.InputMismatchException 예외 발생
			result = 3 / num; // 0을 입력했다면 java.lang.ArithmeticException: / by zero 예외 발생			
			System.out.println("결과는 : " + result + "입니다");
			
			
			//result를 파일로 저장하는 코드 
		} catch (InputMismatchException e) {
//			e.printStackTrace(); // 예외가 발생한 곳을 추적 하기 위한 코드
			System.out.println("정수를 입력하세요");
		} catch(ArithmeticException e) {
//			e.printStackTrace();
			System.out.println("0보다 큰 숫자를 입력하세요"); // 사실 try 블럭을 메서드로 만들어서 재 호출하는 것이 좋은 처리 방법일듯 싶다
		} 
		
		  catch(Exception e) { // Exception 예외 처리 객체의 최상위 객체(다형성) -- 모든 종류의 예외 상황을 한번에 처리
			System.out.println("처리하지 않은 다른 예외 발생");
		} 
		
		  finally { // try 블럭에서 예외가 발생 하든 안 하든 마지막에 수행될 블럭
			System.out.println("프로그램 끝!");
		}
		
	
	}
}

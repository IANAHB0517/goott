package weblsj;

import java.util.Scanner;
import java.util.InputMismatchException;

public class CustomExceptionTest {

	public static void main(String[] args) {
		Integer num = null;
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.print("양수를 입력하세요 >>");
				num = sc.nextInt();
				if (num > 0) {
					break;
				} else {
					// 예외 객체를 만들어서 던짐 -> 사용자 정의 예외 발생
					throw new NotPositiveInteger("양수가 아닙니다!"); //throw는 예외 객체만 던질수있음 (예외를 발생시키는 구문)
				}
			} catch (InputMismatchException e) {
				System.out.println("하다 못해 숫자라도...");
			} catch (NotPositiveInteger e) {
				System.out.println (e.getMessage());
				System.out.println("입력한 숫자 " + num + "은 음수입니다");
				
			} 
		}
		System.out.println(num);
	}

}

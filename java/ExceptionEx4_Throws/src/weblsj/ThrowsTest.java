package weblsj;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ThrowsTest {

	public static void main(String[] args) {
		// throws 는 계속 넘길 수 있다. (메인 메서드가 throws로 넘겨버리면 OS가 받는데 처리를 못해서 에러를 뱉어버린다.)
		System.out.print("숫자를 입력하세요 >>> ");
		// int num = getPositiveNumber(); // static한 getPositiveNumber메서드를 호출
		int num = 0;
		
		try {
			num = getPositiveNumber();
		} catch (InputMismatchException e) {
			System.out.println("숫자가 아닙니다");
		}

		System.out.println(num);
		
		System.out.print("문자열을 입력하세요 >>> ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int num2 = 0;
		try {
			num2 = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.out.println("정수로 바꿀 수 없는 문자열 입니다.");
		}
		System.out.println("바뀐 숫자는 " + num2 + "입니다.");
		
		
		
	}

	private static int getPositiveNumber()
		// throws ; 아래의 int tmp = sc.nextInt();에서 발생할 수 도 있는 InputMismatchExceptiondmf
		// 이 메서드를 호출한 곳에서 예외처리를 하도록 미룸
		throws InputMismatchException {
		Scanner sc = new Scanner(System.in);
		int tmp = sc.nextInt();
		return tmp;
	}

}

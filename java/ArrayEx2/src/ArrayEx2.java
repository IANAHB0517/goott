import java.util.Scanner;

public class ArrayEx2 {

	public static void main(String[] args) {
		int[] coinUnit = {500, 100, 50, 10};
		
		// 키보드로부터 숫자나 문자를 입력받을 수 있는 객체 생성(sc)
		Scanner sc = new Scanner(System.in);
		System.out.print("거스름돈 >>> ");
		int money = sc.nextInt();
		System.out.println(money);
		
		for (int i = 0; i < coinUnit.length; i++) {
			System.out.println(coinUnit[i] + "원 짜리 동전 : " + (money / coinUnit[i]) + "개");
			money %= coinUnit[i];
		}
	}

}

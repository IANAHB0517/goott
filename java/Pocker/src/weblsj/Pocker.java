package weblsj;

import java.util.Scanner;

public class Pocker {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("딜러의 이름을 입력하세요");
		String dealerId = sc.nextLine();
		Dealer d1 = new Dealer(dealerId);
		
		System.out.println(d1.outputEntireDeck());
		
	}

}

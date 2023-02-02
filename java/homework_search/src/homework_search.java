import java.util.Arrays;
import java.util.Scanner;

public class homework_search {

	public static void main(String[] args) {
		int[] randNum = new int[100];
		for(int i = 0; i < 100; i++) {
			randNum[i] = ((int) Math.floor(Math.random()*100));
		}
		
		System.out.println(Arrays.toString(randNum));

		Scanner sc = new Scanner(System.in);
		System.out.print("찾을 숫자는?");
		int findNum = sc.nextInt();
		System.out.println("찾을 숫자는 " + findNum);
		
		boolean flag = false; 
		int i = 0;
		
		for(int n : randNum) {
			if(findNum == n) {
				flag = true;
				System.out.println(findNum + "을(를)" + i + "번째 에서 " + "찾았다");
			} 
			i++;
		};
		
		if(flag == false) {	
			System.out.println(findNum + "을(를) 못 찾았다");
		} 
		
		
		
	};
	

}

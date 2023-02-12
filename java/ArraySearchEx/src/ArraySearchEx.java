import java.util.Arrays;
import java.util.Scanner;


public class ArraySearchEx {

	public static void main(String[] args) {
		int[] ar = new int[20];
		
		for (int i = 0; i < ar.length; i++) {
			ar[i] = (int) (Math.random() * 100) + 1 ;
		}
		
		System.out.println(Arrays.toString(ar));
		
		Scanner sc = new Scanner(System.in); // (System.in) system : 컴퓨터 , in : input장치 ex)마우스,키보드 등의 입력장치 = 컴퓨터의 입력장치로 입력 받는다
		System.out.print("찾을 숫자 >>>");
		int findNum = sc.nextInt();//엔터 칠때까지 나오는 모슨 숫자를 입력받는다
		
		int i = 0;
		boolean isFind = false;
		for (int num : ar) {
			if(num == findNum) {
				isFind = true;
				System.out.println("찾는 값" + findNum + "은" + i + "번째에 있습니다");
			}
			i++;
		}
			
		
		if (!isFind) {
			System.out.println("찾는 값" + findNum + "은 없습니다");
		}
	}

}

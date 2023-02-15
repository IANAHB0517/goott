package weblsj;

import java.util.Scanner;
import java.util.InputMismatchException;

public class CustomExceptionTest_2 {
		static int num = 0;
		
		public static int inputNum() {
		Scanner sc = new Scanner(System.in);
		System.out.print("양수를 입력하세요 >>");
		num = sc.nextInt();
		return num;
		
		}
	 public static void main(String[] args) {
			try {
				inputNum();
				
			} catch (InputMismatchException e) {
				
					inputNum();
								
			}
			
			System.out.println(num);
			
		
		}
	 
	
	 
}

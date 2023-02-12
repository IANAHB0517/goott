package weblsj;


import java.util.Scanner;

// 다양하게 메서드를 만드는 방법과 호출하는 방법을 연습해보자
public class MethodCreationEx {
	public void outputNTimes(String a , int b) {
		
		for (int i = 0; i < b; i++) {
			System.out.println(a);
		};
		
	};
	
	
	
	//메서드 오버로딩
	public static int add(int a, int b) {
		return a + b;
	}
	
	
	public static void main(String[] args) {
		//숫자 두개를 더해서 결과 값을 반화하는 함수
		int result = MethodCreationEx.add(3, 5);
		// MethodCreationEx.add(); //add라는 함수는 static한 메서드, MethodCreationEx의 멤버, 매개변수는 없음
		System.out.println(result);
		// 호출하는 곳과 호출되는 클래스가 같다면 메서드 호출시 클래스명 생략 가능
		System.out.println(add(300,500)); //eclipse에서는 static한 메서드,상수,변수 는 이름이 이탤릭체로 표시됨
		
		MethodCreationEx mce = new MethodCreationEx();
		float fResult = mce.add(3.14f, 5.65f); // instance 메서드라서 이탤릭체로 표시가 되지 않음
		System.out.println(fResult);
		
		// 이름이 outputNTimes 이고, 매개변수를 String타입 변수 하나와 n을 받아 
		// 그 문자열을 화면에 n번 출력하는 메서드를 만드시고 호출하세요
		
		
		Scanner sc = new Scanner(System.in);
		System.out.print("문장을 입력하세요");
		String stringLine = sc.nextLine();
		System.out.println("입력한 문장은" + "'" + stringLine + "'");
		
		System.out.print("출력 횟수를 입력하세요");
		int outputTime = sc.nextInt();
		System.out.println("입력한 출력 횟수는" + "'" +outputTime + "'");
		
		
		mce.outputNTimes(stringLine, outputTime);
		
		System.out.println(add(23,45,67));
		
		
		
		
		
		
	}

	// 메서드는 어디 있던지 상관 없음 하지만 관례적으로 메인 함수를 마지막에 쓰는 것을 추천
	// 메서드 오버로딩
	public float add(float a, float b) {
		float result = 0f; // 메서드 내에서 지역변수를 만들 수 있음
		result = a + b;		
		return result;
	}
	
	// 메서드 오버로딩
	public static int add(int num1, int num2, int num3) {
		return  num1 + num2 + num3 ;
	}

	// 메서드 오버로딩
	public void add(int a , float b) {		
		float result = a + b;
	}
	
	// 메서드 오버로딩
	public void add(float a , int b) {		
		float result = a + b;
	}
	
	// 메서드 오버로딩
//	public void add(float a , int b) {		// 데이터 갯수가 같은 상태에서 데이터 타입만 다르기 때문에 안된다
//		float result = a + b;
//	}

}

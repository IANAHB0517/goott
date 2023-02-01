import java.util.Arrays;

public class ArrayEx1 {

	public static void main(String[] args) {
		int a;
		//System.out.println(a); //지역변수는 초기화를 하지 않으면 사용하지 못함
		int[] scores = new int[3]; // 객체는 자동 초기화 된다.[0,0,0]
		System.out.println(scores.hashCode()); // [I@1ee0005 객체를 구분하기 위한 아이디값 -> [ : 배열 I@1ee0005 : 주소
		
		String[] names= new String[5];
		System.out.println(names); // 초록공 C 클래스 회색공 l 로컬 변수 I는 인터페이스
								   // 문자열의 기본 값은 null

		boolean isOk[] =  new boolean[2];
		System.out.println(isOk);
		
		//배열의 요소에 값 할당
		scores[0] = 45;
		scores[1] = 23;
		scores[2] = 100;
		
		System.out.println(scores[2]);
		//scores[3] = 45; //런타임 에러 발생 (runtime exception) : 배열의 범위에서 벗어남
		
		// 기존의 scores 배열이 참조하던 배열에서 다른 배열로 참조됨
		// new연산자는 : 새롭게 메모리를 할당하고, 객체를 만들어라 (new를 만날 때마다 새롭게 참조됨)
		
		scores = new int[4];
//		scores = new char[3]; // 에러 scores변수는 위에서 int 배열로 사용하겠다고 선언
		System.out.println(scores.hashCode()); 
		System.out.println(scores[2]); // 배열도 초기화 되었다. 0
		
		// 배열의 각 요소에 값을 할당할때 아래와 같은 방법을 쓰면 편리하다.
		int[] scores2 = {100,20,30,55,23}; // 배열의 크기가 아니라 배열에 들어가는 값의 양을 넣어주어서 스스로 크기를 지정하고 값을 넣도록 한다.
		System.out.println(scores2.hashCode());
		
		String[] heros= {"아이언맨", "스파이더맨","헐크"};
		
		// 배열의 각 요소를 화면에 출력
		for (int i = 0; i < heros.length; i++) {
			System.out.println(heros[i]);
		}
		
		// 자바스크립트의 for (... of ...) 와 같은 문법
		for (String hero : heros) {
			System.out.println(hero);
		}
		// 배열의 각요소를 간단하게 출력, Array.toString(배열이름);
		System.out.println(Arrays.toString(scores2));
		
		// 2차원 배열의 선언과 생성
		int[][] arr = new int[3][4];
		
	}

}

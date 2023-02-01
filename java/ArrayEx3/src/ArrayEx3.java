import java.util.Arrays;

public class ArrayEx3 {

	public static void main(String[] args) {
		// ========================================================================
		char[] chArr = new char[] {
				'a', 'b', 'c'
		};
		
		System.out.println("변경 전 원본 배열 : " + Arrays.toString(chArr));
	
		//원본 배열을 복사(deep copy)
		char[] copyChArr = new char[chArr.length];
		int i = 0;
		for (char c : chArr) { // chArr 에서 꺼내서 c에 할당해줘서 돌린다
			copyChArr[i++] = c;
		}
		System.out.println("copyChArr : " + Arrays.toString(copyChArr));
		
		// 원본 배열의 값을 수정(복사본이 원본의 영향을 받지 않는다)
		chArr[1] = 'B';
		System.out.println("변경 후 원본 배열 : " + Arrays.toString(chArr));
		System.out.println("변경 후 copyChArr : " + Arrays.toString(copyChArr));
		// why? 원본배열의 주고 값과 사본 배열의 주소 값이 다르다 (= 다른 객체)
		System.out.println("원본 배열의 주소 값 : " + chArr.hashCode());
		System.out.println("사본 배열의 주소 값 : " + copyChArr.hashCode());
		System.out.println("-----------------------------------------------------------------------------");
		
		
		//========================================================================
		
		String[] heros = new String[] {
				"아이언맨", "헐크", "캡틴아메리카노"
		};
		System.out.println("변경 전 원본 배열 : " + Arrays.toString(heros));
		//얕은 복사 (shallow copy)
		
//		System.out.println();
		
		String[] copyedHeros = heros;
		System.out.println("변경 전 사본 배열 : " + Arrays.toString(copyedHeros));
		
		//원본 배열의 값을 수정
		heros[2] = "캡틴아메리카";
		System.out.println("변경 후 원본 배열 : " + Arrays.toString(heros));
		System.out.println("변경 후 copyedHeros : " + Arrays.toString(copyedHeros));
		System.out.println("원본 배열의 주소 값 : " + heros.hashCode());
		System.out.println("사본 배열의 주소 값 : " + copyedHeros.hashCode());
		System.out.println("-----------------------------------------------------------------------------");
		
		//why? 원본 배열의 주소값과 사번 배열의 주소값이 같다(= 같은 객체)
		
		// String 타입의 특성 // new로 하면 hash코드 값은 같게 나오는데 다른 주소라고 나온다 물어보자
		String str1 = "대한민국"; // 코딩을 쉽게 하기위한 단축법
//		String str1 = new String("대한민국");
		String str2 = "대한민국"; // 얘 일때는 같은 주소
//		String str2 = new String("대한민국"); // 얘 일때는 다른 주소
		
	
		if (str1 == str2) { // str1 주소와 str2 주소가 같냐?
			System.out.println("같은 주소");
		} else {
			System.out.println("다른 주소");
		}
		
		if (str1.equals(str2)) { 
			System.out.println("같은 값");
		} else {
			System.out.println("다른 값");
		}
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		// 주소값이 같게 나온다. 문자열을 많이 사용하기 때문에 초기에 값이 같은 경우에는 두개의 객체가 같은 주소를 참조하도록 한다.
		
		System.out.println("-----------------------------------------------------------------------------");
		
		
		str2 += "짱!"; // 문자열이 참조하는 값이 달라지면 주소도 달라진다.
		
		if (str1.hashCode() == str2.hashCode()) { // str1 주소와 str2 주소가 같은가?
			System.out.println("같은 주소");
		} else {
			System.out.println("다른 주소");
		}
		
		if (str1.equals(str2)) { // 문자열의 값(내용)을 비교할 때는 equals메서드를 이용  
			System.out.println("같은 값");
		} else {
			System.out.println("다른 값");
		}

		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode()); // 내용이 달라지기 때문에 주소가 달라진다.
		
		System.out.println("-----------------------------------------------------------------------------");
		
		String userid = "abcd1234";
		if (userid == "abcd1234") { // 변수와 문자열의 주소가 같다.
			System.out.println("같은 주소");
		} else {
			System.out.println("다른 주소");
		}
		
		if (userid.equals("abcd1234")) { // userid 가 참조하는 문자열의 값과 괄호안의 값이 같은가?
			System.out.println("같은 값");
		} else {
			System.out.println("다른 값");
		}
		
		
		
		
	}
	
//자바 bean : 자바의 객체	

}

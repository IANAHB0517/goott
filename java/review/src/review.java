
public class review {
	public static void main(String[] args) {
	
		char a = '우';
		char b = '리';
		char c = '나';
		char d = '라';
		char e = '대';
		char f = '한';
		char g = '민';
		char h = '국';
		
		// char 타입은 언제든지 정수형으로 변할 수 있다. (아스키코드로 취급한다.)
		// 문자열을 웬만하면 String타입으로 처리
		// short , char , byte 는 하드웨어적인 부분을 처리하기 위한 걸로 생각하면 편하다
		// 두번째 코드의 경우 앞의 4글자는 아스키코드로 연산이 되어 190576이 나온 반면
		// 뒤의 4글자는 공백 문자가 정확히 문자이기 때문에 + 연산을 할 수 없어서 문자열로 표시되었다. 
		System.out.println("" + a + b + c + d + " " + e + f + g + h);
		System.out.println(a + b + c + d + " " + e + f + g + h);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(a + b);
		
		int i = 42;
		System.out.println(i);
		
	}
}

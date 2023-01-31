
public class DataTypesAndVariables {
	public static void main(String[] args) { // "엔트리 포인트" 프로그램의 진입점 이라한다.
		// boolean : true 또는 false를 저장 1byte
		boolean bool = true;
		System.out.println(bool);
		
		// char : 문자형. 2byte. 한 문자를 저장 할 수 있다.
		char character = '\u0041';// \u0041: 유니코드 'A'
		System.out.print(character);
		
		// 정수형
		// byte : 정수형. 1byte
		byte b = 127;
		System.out.println(b);
		//b = b + 1; // b + 1의 값이 byte의 범위를 넘는다. 오버 플로우 에러
		System.out.println("Byte 타입의 최대값 : " + Byte.MAX_VALUE); //Byte 는 대문자로 시작하니까 "클래스"
		System.out.println("Byte 타입의 최소값 : " + Byte.MIN_VALUE);
		
		//short : 정수형 . 2byte
		short sInt = 128;
		System.out.println(sInt);
		System.out.println("short 타입의 최대값 : " + Short.MAX_VALUE); // Short : short 타입의 클래스
		System.out.println("short 타입의 최소값 : " + Short.MIN_VALUE);
		
		// int : 정수형. 4byte (정수형 타입의 기본 타입)
		int i = 35;
		System.out.println(i);
		System.out.println("integer 타입의 최대값 : " + Integer.MAX_VALUE); // Integer : integer 타입의 클래스
		System.out.println("integer 타입의 최소값 : " + Integer.MIN_VALUE);
		
		// long : 정수형. 8byte
		long l = 35l; // 소문자 l 또는 대문자 L(리터럴 상수)를 붙여 long 타입임을 명시.
		System.out.println(l);
		System.out.println("long 타입의 최대값 : " + Long.MAX_VALUE); // Long : long 타입의 클래스
		System.out.println("long 타입의 최소값 : " + Long.MIN_VALUE);
		
		// float : 실수형. 4byte
		float f= 3.14f; //  double에서 float로 변환 할 수 없다. 뒤에 f 또는 F 붙여서 float 임을 명시
		System.out.println(f);
		System.out.println("float 타입의 최대값 : " + Float.MAX_VALUE); // Float : float 타입의 클래스
		System.out.println("float 타입의 최소값 : " + Float.MIN_VALUE);
		
		// double : 실수형. 8byte
		double d = 3.14; // 리터럴 상수를 생략하거나 d 또는 D를 붙여 명시
		System.out.println(d);
		System.out.println("double 타입의 최대값 : " + Double.MAX_VALUE); // Double : double 타입의 클래스
		System.out.println("double 타입의 최소값 : " + Double.MIN_VALUE);
		
		// 문자열 : 기본 데이터 타입이 아닌 참조타입. string
		String str = "대한민국";
		System.out.println(str);
		System.out.println("str의 길이 : " + str.length());
		
		// null : 기본 데이터 타입이 아님.
		String str2 = null;
		System.out.println(str2);
	}
}


public class DataTypeCasting {
	public static void main(String[] args) {
		byte b = 123;
		System.out.println(b);
//		System.out.println("b의 데이터 타입" + b.getClass().getName());
		// 묵시적 형변환(casting) : 프로그래머가 형 변환 연산자를 쓰지 않더라도 자동으로 되는 형 변환.
		// 보통. 작은 타입에서 큰 타입으로 변할때 묵시적 형 변환이 일어난다.
		int i = b;
		System.out.println("i : " + i );
		
		// 명시적 형변환 : 프로그래머가 형 변환을 하라고 명시해 놓는것. (큰타입 -> 작은타입 변환)
		// 억지로 넣는다. 값이 변하는 것도 불사하는 프로그래머의 의지
		double pi = 3.141592d;
		// float fpi = pi; // 에러 큰 타입에서 작은 타입으로 데이터 형 변환을 묵시적으로 하지 못한다.
		float fpi = (float) pi; //캐스팅할 변수 앞에 형변환할 데이터 타입 ---> (형변환할 데이터 타입) 캐스팅할 변수 
		// 억지로 넣으면 전의 값이 float 보다 클 경우 underflow현상이 일어난다.
		System.out.println(fpi);
		
		//
		int i2 = 2147483647;
		short s = (short)i2;
		System.out.println(s);
		
		// -1 나오는데 underflow 에러에 의해 엉뚱한 값이 나오는 것이다.
		// (short가 표현할 수 있는 값의 최대치를 초과하면 비트 연산으로 인한 결과가 나온다)
		// 명시적 형변환시에 형 변환 되는 변수와 형변환 하려고 하는 변수가 같은 범위에 있는 것이 좋다.
		
		int i3 = 32767;
		short s2 = (short)i3;
		System.out.println(s2);
		
		//명시적 형변환 해도 underflow가 발생하지 않는다
		// 명시적 형변환 시에 현 변환 되는 변수와 형변환 하려고 하는 변수가 같은 범위에 있는 것이 좋다.
		
		
		// 문자는 내부적으로 아스키 코드 값이 있기 때문에 아래와 같은 코딩이 가능
		char c = '김';
		System.out.println(c); // a
		System.out.println((int)c); // 문자는 내부적으로 아스키 코드 값이 있고, 그 값의 정수 값으로 바뀜
		
		System.out.println((char)98); // 'b'
		
		/*
		boolean bool = true;
		System f2 = bool; // 사이즈만 보면 들어 갈 수 있지만 데이터의 타입이 다르기 때문에  형변이 불가하다. casting 연산자도 안됨
		 (문자와 정수는 아스키 코드 값에 의해 가능)
		 */
		
	}
}

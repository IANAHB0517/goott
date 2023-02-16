package weblsj;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RawTypeTest {

	public static void main(String[] args) {
		ArrayList ar = new ArrayList<>(); // raw 타입의 ArrayList 객체 생성
		
		//raw타입으로 생성된 ArrayList ar에는 모든 데이터타입이 다 저장될 수 있다.
		// ---> 데이터 타입의 안정성을 보장하지 못한다.
		ar.add(10);
		ar.add(3.14f);
		ar.add(3.1415926535d);
		ar.add("대한민국");
		ar.add(new Computer());
		
		// Generic 타입으로 사용할 것을 권고
		ArrayList<String> ar2 = new ArrayList<String>();
		ar2.add("스트링만");
		ar2.add("저장됨");
//		ar2.add(new Computer()); // 에러
		
		// Generic 타입은 참조 타입만 가능
//		ArrayList<int> ar3 = new ArrayList<>(); // 값타입을 줄 수 없다.
		ArrayList<Integer> ar3 = new ArrayList<>();
		// Integer 가 있음에도 값타입 int 같은 데이터 타입을 만든 이유
		// 많이 사용되기 때문에 빠르게 사용할 수 있도록
		
		
		// URL url = new URL("https://www.naver.com");
		// 입력 받는 값과 지정되어 있는 값 이라는 차이점 + 전 단계에서 예외처리가 되었을 수도 있다는 점????
		// 물어보기

		// 위의 형식 보다 아래처럼 인터페이스로 다형성을 구현하여 사용하는 것이 이점이 있다.
		List<String> ar4 = new ArrayList<>();
		
		ar4.spliterator();// ArrayList의 메서드를 쓰다가
//		List<String> ar4 = new Stack<>(); // 재선언이 아닌 코드를 작성중에 클래스를 바꾸고자 하면 윗줄에서 바꾸더라도
		// 상위 객체이기 때문에 하위 모든 메서드를 아우를 수 있다.
		
//		ar4.peek(); //선언부를 변경한 이후에 Stack의 메서드를 쓰는 식의 작업이 가능하다.
		
		// List를 상속 받아 구현한 다양한 클래스들이 있기 때문에 당장은 ArrayList로 선언하더라도 
		// 나중에 다형성을 통해 Stack이나 Queue로 활용하기 위해서 데이터 타입을 부모 클래스로 선언 한다.
		
		// 인터페이스를 이용하여 다형성을 구현하여 사용하는 것에 대한 이점
		// List 인터페이스의 하위 클래스로 변경해야 할 때 용이. (데이터 타입이 유연해짐)
		// 인터페이스의 특정 구현과 내 코드를 분리하기 위해서.
		
		
	}

}

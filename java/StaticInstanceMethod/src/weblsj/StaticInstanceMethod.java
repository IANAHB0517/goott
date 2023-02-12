package weblsj;

import java.util.Scanner;
import java.lang.Math; // java.lang은 기본 패키지 (import 생략 가능)

public class StaticInstanceMethod {

	// Static 과 non-Static 메서드의 메서드 호출 방법 
	public static void main(String[] args) {
		// 1) static 키워드가 있는 멤버의 사용 방법 : 클래스명.멤버명()으로 호출
		System.out.println(Math.abs(-3)); // Math 클래스는 public javaapi 참고, final로 되어 있다 = 수정불가
		// 초록색 공의 의미 : 멤버 , 작은 빨간색 S : Static , F : Final 최종본으로 수정이 되지 않는다 = 상수
		
		
		//2) non-static(instance) : static 키워드가 없는 멤버.
		// 그 멤버를 가지고 있는 클래스로부터 객체를 만들고, 객체명.멤버명()으로 호출
		
		Scanner sc = new Scanner(System.in); // Scaaner 클래스로부터 sc라는 객체를  생성
		int num1 = sc.nextInt();// 초록색 공에 s가 없음 static이 아님
		System.out.println(num1);
		
		// static은 한번 메모리에 올라가면 프로그램 종료까지 내려오지 않기 때문에 메모리 관리가 어렵다.
		// instance는 지역변수로써 자신이 포함된 메서드가 종료될 때 함께 종료되어 메모리를 반환한다.
		
		sc = null; // 객체에게 null 값을 할당하여 가비지 컬렉터에 의해 사라지도록 준비시킨다.
		//sc.close(); // sc의 메모리를 죽이는 법 null 값을 넣어준다.
		
		
		System.gc(); // 가비지 컬렉터를 호출
		
		

		
		
		System.out.println("num >>> ");
		int num2 = sc.nextInt(); // 여기서 에러가남 이미 sc가 메모리에서 사라졌기 때문에 -> 이런식으로 instance을 사용하면 메모리 관리하기가 용이하다
		System.out.println(num2);
	}

}

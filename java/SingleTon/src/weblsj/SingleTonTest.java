package weblsj;

import java.util.Calendar;

public class SingleTonTest {

	public static void main(String[] args) {
//		SingleTon s = new SingleTon() // 생성자가 private해서 접근 불가

		for (int i =0; i<10; i++) {
			SingleTon s = SingleTon.getInstance();
			System.out.println(s.hashCode());
		}
		
		// java.util.Calendar 클래스 또한 싱글톤으로 구현되어 있어서
		// 아래와 같이 객체를 얻어올 수 있다.
		Calendar c = Calendar.getInstance(); // 상속 받지 않은 외부 패키지에서 Calendar 객체를 얻어오기 위한 방법
		System.out.println(c.toString());
		
		
	}

}

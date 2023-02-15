package weblsj;

public class ThrowTest {

	public static void main(String[] args) {
		Student s = null;
		try {
			s = new Student("980001", "둘리", -120, 10, 10);
		} catch (IllegalArgumentException e) {
			System.out.println("국어점수를 잘못 입력 했습니다.");
			
		}

		System.out.println(s.hashCode());
	}

}

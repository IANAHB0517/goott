package weblsj;

public class ScoreV2 {

	public static void main(String[] args) {
		Student s1 = new Student("9900001", "홍길동", 98, 87, 88);
		Student s2 = new Student("9900002", "둘리", 88, 67, 98);
		
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		
		System.out.println(s1.getGrade());
		
		s1.setKor(120);
		System.out.println(s1.toString());
		s1.setKor(0);
		System.out.println(s1.toString());
		
		
		
		

	}

}

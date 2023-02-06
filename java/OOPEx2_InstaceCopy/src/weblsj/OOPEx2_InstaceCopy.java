package weblsj;

//깊은 복사 vs 얕은 복사
public class OOPEx2_InstaceCopy {

	public static void main(String[] args) {
		Student s1 = new Student("980011", "둘리", 67, 87, 45);
		System.out.println(s1.toString());
		
		//얕은 복사
		Student s2 = s1;
		s2.setKor(0);
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		
		// 깊은 복사(생성자를 통해)
		Student s3 = new Student("980015", "도우너", 100, 100, 0); // 복사 대상 생성
		Student deepCopyS3 = new Student(s3); // 생성자에게 s3 전달 생성자가 대상의 속성 값을 deepCopyS3에게 부여함
		System.out.println(s3.hashCode()); // 원본의 주소
		System.out.println(s3.toString()); // 원본 객체 출력
		System.out.println(deepCopyS3.hashCode()); // 사본의 주소
		System.out.println(deepCopyS3.toString()); // 사본 객체 출력
		System.out.println();
		
		deepCopyS3.setStuName("도우나");
		System.out.println();
		
		System.out.println(s3.hashCode()); // 원본의 주소
		System.out.println(s3.toString()); // 원본 객체 출력
		System.out.println(deepCopyS3.hashCode()); // 사본의 주소
		System.out.println(deepCopyS3.toString()); // 사본 객체 출력
		
		
	}

}

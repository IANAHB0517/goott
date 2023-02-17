package weblsj;


//public class Student implements Comparator<Student> {
public class Student implements Comparable<Student> { // 학생은 다른 학생과 비교 되어 질 수 있다.
	private String sNum;
	private String name;
	private int score;
	
	public Student(String sNum, String name, int score) {
		super();
		this.sNum = sNum;
		this.name = name;
		this.score = score;
	}

		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public int getScore() {
			return this.score;
		}
	
		public void setScore(int score) {
			this.score = score;
		}
	
		public String getsNum() {
			return sNum;
		}

	@Override
	public String toString() {
		return "Student [sNum=" + sNum + ", name=" + name + ", score=" + score + "]";
	}

	@Override
	public int hashCode() {
		
		return super.hashCode();
	}

//	@Override
//	public boolean equals(Object obj) {
//		boolean result = false;
//		if(obj instanceof Student) {
//			Student tmp = (Student) obj;
//			if(this.sNum.equals(tmp.sNum)) {
//				result = true;
//			}
//		}
//		
//		return result;
//	} 

	
	
	// java.lang.Comparable을 상속받아 구현할때는 아래의 메서드를 오버라이딩
	@Override
	public int compareTo(Student s) {
		int result = 0;
		if(this.score < s.score) {
			result = -1;			
		} else if (this.score > s.score) {
			result = +1;
		} 
		return result * -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	// java.util.Comparator를 상속 받아 구현할 때는 아래의 메서드를 오버라이딩
//	// 사용방법이 약간 다르다
//	@Override
//	public int compare(Student o1, Student o2) {
//		if (o1.score < o2.score) {
//			return -1;
//		} else if (o1.score == o2.score ) {
//			return 0;
//		} else {
//			return +1;
//		}
//	}

	
	
	
	
}

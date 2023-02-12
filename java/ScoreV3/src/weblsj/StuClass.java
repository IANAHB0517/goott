package weblsj;

public class StuClass {

		// 변수
		private int no; // 반 번호
		private String className;
//		private String lecturerName;
		private Student[] stuList; // 학생 목록(포함관계) 가지고 있게되는 항목을 배열로
		private int totalTot; // 반 학생들의 전체 총점 // 반 마다 다를 수 있기 때문에 instance 변수를 사용해야 한다.
		private float totalAvg; // 반 학생들의 전체 평균
		
		// 상수
		public static final int STUDENT_COUNT = 3; // 학생 수 (상수) // 상수는 static 해야한다. 언제 만들어지는 객체라도 참조할 수 있도록
		// 학생을 담는 배열의 방의 수
		
		//final class = 오버로딩 할 수 없는 클래스
		
		// 생성자
		public StuClass( int no, String className) {
			this.no = no;
			this.className = className;
			this.stuList = new Student[STUDENT_COUNT]; // 배열은 생성시 자동 초기화 // static한 상수를 불러와서 배열을 길이를 정해준다.
		}
		
		public StuClass( int no, String className, Student[] stuList) {
			this.no = no;
			this.className = className;
			this.stuList = stuList;
		}
		
		// 메서드
		// getter
		public int getNo() {
			return this.no;
		}
		
		public String getClassName() {
			return this.className;
		}
		
		public Student[] getStuList() {
			return this.stuList;
		}
		
		//setter
		public void setClassName(String className) {
			this.className = className;
		}
		
		
		public void addStudent(Student student, int no) { // 학생을 반에 배정
			this.stuList[no] = student;
		}
		
		
		public void outputEntireStudents() {
			ScoreV3 sc = new ScoreV3();
			for (int i = 0; i < sc.getCurSavedStudentCnt(); i++) {
				System.out.println(this.stuList[i].toString());
			}
		}
		
		// 학생들의 전체 총점
		public int calcTotalTot() {
			this.totalTot = 0;
			ScoreV3 sc = new ScoreV3();
			for (int i = 0; i <sc.getCurSavedStudentCnt(); i++) {
				this.totalTot += this.stuList[i].getTot();
			}
			return this.totalTot;
		}
		
		// 학생들의 전체 평균
		public float calcTotalAvg() {
			ScoreV3 sc = new ScoreV3();
			this.totalAvg = this.totalTot / 3f / (sc.getCurSavedStudentCnt());
			this.totalAvg = (Math.round(totalAvg * 100))/100;
			return this.totalAvg;
		}
		
		// 학번으로 학생의 정보 검색 
		public String findStudentByNo(String stuNo) {
			ScoreV3 sc = new ScoreV3();
			boolean isfind = false;
			Student curStu = null;
			
			int getCnt = sc.getCurSavedStudentCnt();
			
//			for (Student s : this.stuList) {
//				if (stuNo.equals(s.getStuNo())) {
//					isfind = true;
//					System.out.println("-----------검색된 학생 정보-----------");
//					System.out.println(s.toString());
//				}
//				if(!isfind) {
//					System.out.println("학생을 찾지 못했습니다.");
//				}
//			}
			
			for (int i = 0; i < getCnt; i++) {
				if (stuList[i].getStuNo().equals(stuNo)) {
					curStu = stuList[i];
					isfind = true;
				} 
			}
			if(!isfind) {
				System.out.println("학생을 찾지 못했습니다.");
			}
			return curStu.toString();
		}
		
		public String toString() {
			return "반 번호 : " + this.no + "," + "과정명 : " + this.className;
		}
}

package weblsj;

public class Student {
	// 변수
	private String stuNo;
	private String stuName;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private float avg;
	private char grade;
	
//	private static int totalTot; // 전체 총점
//	private static float totalAvg; // 전체 평균 좋지 못한 디자인 한 명의 학생이 전체의 평균과 총점을 알 수 없음
	
	// 생성자
	public Student(String stuNo, String stuName, int kor, int eng, int math) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		
		setTot();
		setAvg();
		setGrade();
		
//		totalTot += this.tot;
//		this.tot =  kor + eng + math;
//		this.avg =  this.tot / 3f;
//		
//		switch ((int)(Math.floor(this.avg)/10d)) {
//		case 10:
//		case 9:
//			this.grade = 'A';
//			break;
//		case 8:
//			this.grade = 'B';
//			break;
//		case 7:
//			this.grade = 'C';
//			break;
//		case 6:
//			this.grade = 'D';
//			break;
//		default:
//			this.grade = 'F';
//			break;
//		}
//		
	}
	
	
	
	// 메서드
	
//	public static int getTotalTot() {
//		return totalTot;
//	}
	
	public String getStuNo() {
		return this.stuNo;
	}
	
	public String getStuName() {
		return this.stuName;
	}
	
	public int getKor() {
		return this.kor;
	}
	
	public int getEng() {
		return this.eng;
	}
	
	public int getMath() {
		return this.math;
	}
	
	public int getTot() {
		return this.tot;
	}
	
	public float getAvg() {
		return this.avg;
	}
	
	public char getGrade() {
		return this.grade;
	}
	
	public String studentAll() {
		return "학생의 모든정보";
	}
	
//	public static int getTotalTot() {
//		return 	totalTot; // 같은 클래스내에 있어서 클래스 명 생략 가능
//	}
//	
//	public static float getTotalAvg() {
//		return 	totalAvg; 
//	}
	
	// public void setStuNo(String stuNo) { 
	// 학번은 Primary Key 이다 변경 불가한 값이어야 함으로 setter를 만들지 않아서 
	// Read Only 로 만들어주어야 한다.
	// }
	
 
	
	
	public void setStuName(String stuNamem) {
		this.stuName = stuNamem;
		
	}
	
	public void setKor(int kor) {
		if (kor >= 0 && kor <= 100)
		this.kor = kor;
		setTot();
		setAvg();
		setGrade();
	}
	
	public void setEng(int eng) {
		if (eng >= 0 && eng <= 100)
		this.eng = eng;
		setTot();
		setAvg();
		setGrade();
	}
	
	public void setMath(int math) {
		if (math >= 0 && math <= 100)
		this.math = math;
		setTot();
		setAvg();
		setGrade();
	}
	
	public void setAvg() {
		this.avg =  this.tot / 3f;		
	}
	public void setTot() {
		this.tot =  this.kor + this.eng + this.math;
	}
	public void setGrade() {
		switch ((int)(Math.floor(this.avg)/10d)) {
		case 10:
		case 9:
			this.grade = 'A';
			break;
		case 8:
			this.grade = 'B';
			break;
		case 7:
			this.grade = 'C';
			break;
		case 6:
			this.grade = 'D';
			break;
		default:
			this.grade = 'F';
			break;
		}
	}
	
	public String toString() {
		return "학번 : " + this.stuNo + ","
				+ "이름 : " + this.stuName + ","
				+ "국어 : " + this.kor + ","
				+ "영어 : " + this.eng + ","
				+ "수학 : " + this.math + ","
				+ "총점 : " + this.tot + ","
				+ "평균 : " + this.avg + ","
				+ "학점 : " + this.grade ;
	}
	
	
	
	
}

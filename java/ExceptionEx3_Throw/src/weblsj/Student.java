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
	
	// 생성자
	public Student(String stuNo, String stuName, int kor, int eng, int math) throws IllegalArgumentException  {
		// 생성자에서는 예외 처리가 불가능
		// 예외 처리는 생성자를 호출하기 전에 입력 받으면서 해주어야 한다.
		
		this.stuNo = stuNo;
		this.stuName = stuName;
		if (kor >= 0 && kor <= 100) {
			this.kor = kor;
		} else {
			// 원래 아래의 문장에서 예외가 발생했으므로 여기에서 예외 처리를 해야함
			throw new IllegalArgumentException("국어 점수가 잘못된 데이터 입니다");  // 예외가 발생하는 순간 객체가 생성 되다가 멈춘다.
			// 예외 처리를 다른 곳으로 미루는 이유 : 잘못된 데이터가 request 된 곳으로 예외를 response 해주어야 한다.
		}
		this.eng = eng;
		this.math = math;
		
		setTot();
		setAvg();
		setGrade();
	
		
	}
	
	
	
	// 메서드
	
	
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
	
	
	public void setStuName(String stuNamem) {
		this.stuName = stuNamem;
		
	}
	
	public void setKor(int kor) {
		if (kor >= 0 && kor <= 100) // 객체가 생성된 다음에 다시 바꿀때 검사함
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

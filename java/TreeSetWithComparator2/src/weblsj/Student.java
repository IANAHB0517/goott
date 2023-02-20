package weblsj;

import java.util.Objects;

public class Student {
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
	public int hashCode() {
		return this.sNum.hashCode(); // 같은 학번일때 같은 해쉬코드가 반환되도록
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		Student tmp = (Student)obj;
		if(obj instanceof Student) {
			
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "Student [sNum=" + sNum + ", name=" + name + ", score=" + score + "]";
	}


}

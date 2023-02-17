package weblsj;

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
	public String toString() {
		return "Student [sNum=" + sNum + ", name=" + name + ", score=" + score + "]";
	}


}

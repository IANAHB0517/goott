package weblsj;

public class Student {
	private String id;
	private String name;
	private int score;
	
	public Student(String id, String name, int score) {
		super();
		this.id = id;
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
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", score=" + score + "]";
	}
	
	
	
	
}

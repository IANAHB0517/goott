package weblsj;


import java.util.Set;
import java.util.HashSet;

public class StuClass {
	private int classno;
	private Set<Student> stuSet;
	
	public StuClass(int classno) {
		super();
		this.classno = classno;
		this.stuSet = new HashSet<>(); 
		// 정렬방법 다형성으로 구현하기 두 정렬 방법을 같은 객체로부터 상속 받도록 하기 + 
	}

		public Set<Student> getStuSet() {
			return stuSet;
		}
	
		public void setStuSet(Set<Student> stuSet) {
			this.stuSet = stuSet;
		}
	
		public int getClassno() {
			return classno;
		}
		
	public void addStudent(Student s) {
		this.stuSet.add(s);
	}
	
	public void outputEntireStudent() {
		for(Student student : this.stuSet) {
			System.out.println(student.toString());
		}
	}

	@Override
	public String toString() {
		return "StuClass [classno=" + classno + ", stuSet=" + stuSet + "]";
	}

	
	
	
	
	
}

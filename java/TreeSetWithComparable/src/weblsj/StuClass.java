package weblsj;

import java.util.Set;
import java.util.TreeSet;

public class StuClass {
	private int classno;
	private Set<Student> stuSet;
	
	public StuClass(int classno) {
		super();
		this.classno = classno;
		this.stuSet = new TreeSet<>();
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

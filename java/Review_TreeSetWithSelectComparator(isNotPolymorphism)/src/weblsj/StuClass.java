package weblsj;


import java.util.Set;
import java.util.TreeSet;

public class StuClass {
	private int classno;
	private Set<Student> sortType;
	
	public StuClass(int classno, int selectSortType) {
		super();
		this.classno = classno;
		if (selectSortType == 1) {
			this.sortType = new TreeSet<>(new AscendingByStudentScore()); 			
		}
		if (selectSortType == 2) {
			this.sortType = new TreeSet<>(new DescendingByStudentName()); 
		}
		// 정렬방법 다형성으로 구현하기 두 정렬 방법을 같은 객체로부터 상속 받도록 하기 + 
	}

		public Set<Student> getStuSet() {
			return sortType;
		}
	
		public void setStuSet(Set<Student> stuSet) {
			this.sortType = stuSet;
		}
	
		public int getClassno() {
			return classno;
		}
		
	public void addStudent(Student s) {
		this.sortType.add(s);
	}
	
	public void outputEntireStudent() {
		for(Student student : this.sortType) {
			System.out.println(student.toString());
		}
	}

	@Override
	public String toString() {
		return "StuClass [classno=" + classno + ", stuSet=" + sortType + "]";
	}

	
	
	
	
	
}

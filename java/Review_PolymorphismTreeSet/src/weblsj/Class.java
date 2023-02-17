package weblsj;

import java.util.Set;

public class Class {
	
	private int classNo;
	private Set<Student> stuClass;
	
	public Class(int classNo, Set<Student> orderBy) {
		super();
		this.classNo = classNo;
		
		if(orderBy instanceof DescendingByStudentName ) {
			this.stuClass = orderBy;
		}
		
		if(orderBy instanceof AscendingByStudentScore ) {
			this.stuClass = orderBy;
		}
		
		
		
	}

		public int getClassNo() {
			return classNo;
		}
	
		public void setClassNo(int classNo) {
			this.classNo = classNo;
		}
	
		public Set<Student> getStuClass() {
			return stuClass;
		}
	
		public void setStuClass(Set<Student> stuClass) {
			this.stuClass = stuClass;
		}

		
		public void add(Student s) {
			this.stuClass.add(s);
		}
		
	@Override
	public String toString() {
		return "Class [classNo=" + classNo + ", stuClass=" + stuClass + "]";
	}
	
	

}

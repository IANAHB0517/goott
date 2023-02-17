package weblsj;

import java.util.Comparator;

public class AscendingByStudentScore   implements Sortable, Comparator<Student>  {

	@Override
	public int compare(Student o1, Student o2) {
		int result = 0;
		if(o1.getScore() < o2.getScore()) {
			result = -1;
		} else if (o1.getScore() > o2.getScore()) {
			result = 1;
		}
		return result * -1;
	}

	@Override
	public String sortType() {
		return this.getName();
		
	}

	
	

}

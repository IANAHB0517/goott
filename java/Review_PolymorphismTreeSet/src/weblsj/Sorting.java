package weblsj;

import java.util.Set;
import java.util.TreeSet;

public class Sorting {
	public static Set<Student> SortControll(Sortable target) {
		 Set<Student> result = null;
		if(target.sortType().equals("AscendingByStudentScore")) {
			result = new TreeSet<>(new AscendingByStudentScore());
		}
		return result;
		
	}
}

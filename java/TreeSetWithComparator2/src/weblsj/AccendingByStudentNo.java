package weblsj;

public class AccendingByStudentNo implements Sortable {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getsNum().compareTo(o2.getsNum());
		
	}

}

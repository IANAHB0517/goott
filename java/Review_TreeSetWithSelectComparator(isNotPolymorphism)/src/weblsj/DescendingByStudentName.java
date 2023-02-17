package weblsj;

import java.util.Comparator;

public class DescendingByStudentName  implements Comparator<Student> ,JComparator {

	@Override
	public int compare(Student o1, Student o2) {
//		if (o1.getName() < o2.getName()) 문자를 산술 연산자로 비교할 수 없다 
		
		return o1.getName().compareTo(o2.getName())* -1;
//				String 클래스가 오버라이딩한 compareTo()를 호출 
	}

	@Override
	public void isThis() {
		// TODO Auto-generated method stub
		
	}

}

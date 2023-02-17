package weblsj;


import java.util.Set;
import java.util.TreeSet;



public class SetTreeTest {

	public static void main(String[] args)  {
		StuClass class1 = new StuClass(1);
		
		Student s1 = new Student("0001", "손오공", 200);
		Student s2 = new Student("0002", "배지터", 150);
		Student s3 = new Student("0003", "천진반", 250);
		Student s4 = new Student("0004", "손오반", 400);
		Student s5 = new Student("0018", "인조인간18호", 300);
		Student s6 = new Student("0018", "인조인간16호", 300); 
		
		
		class1.addStudent(s1);
		class1.addStudent(s2);
		class1.addStudent(s3);
		class1.addStudent(s4);
		class1.addStudent(s5);
		
		class1.outputEntireStudent();
		
		
		
		
		
		
	


	} // 메인 메서드
}

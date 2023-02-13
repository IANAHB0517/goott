package weblsj;

public class EmployeesTest {

	public static void main(String[] args) {
		Department 총무부  = new Department(10, "총무부");
		
		// 아래와 같이 하면 Department객체가 소멸되어도 Employees 객체가 살아남는다.(Aggregation 관계)
		Employee 채치수 = new Permanent("100011001", "채치수", 10, 100000);
		총무부.addEmployee(채치수);	
		
		// 아래와 같이 하면 Department 객체가 소멸될 때 Permanent 객체도 함께 소멸 되어 버린다.(Composition 관계)
		총무부.addEmployee(new PartTimer("100011011", "서태웅", 10, 5, 100000));

		
		Employee 강백호 = new PartTimer("100011010", "강백호", 10, 5, 10000);
		총무부.addEmployee(강백호);
		
		
		
		
		총무부.outputEntireEmployees();

		
	}
}

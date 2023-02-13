package weblsj;

public class Permanent extends Employee {

	Permanent(String empNo, String eName, int deptNo, int salary) {
		super(empNo, eName, deptNo, salary);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int paidSalary() {
		return super.salary;
	}

	@Override
	public String toString() {
		return "Permanent [toString()=" + super.toString() + "]";
	}

}

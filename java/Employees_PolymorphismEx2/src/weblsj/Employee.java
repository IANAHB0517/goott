package weblsj;

public abstract class Employee {
	private String empNo;
	private String eName;
	private int deptNo;
	protected int salary;
	
	Employee(String empNo, String eName, int deptNo, int salary) {
		super();
		this.empNo = empNo;
		this.eName = eName;
		this.deptNo = deptNo;
		this.salary = salary;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getEmpNo() {
		return empNo;
	}	
	
	
	// 정규직과 알바생의 급여지급방법이 다름
	public abstract int paidSalary();

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", eName=" + eName + ", deptNo=" + deptNo + ", salary=" + salary + "]";
	}
	
	
}


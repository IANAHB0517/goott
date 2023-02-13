package weblsj;

import java.util.ArrayList;

public class Department {
	private	int deptNo;
	private String deptName;
	private ArrayList<Employee> empList;	// Employee 타입만 들어갈 수 있는 가변 길이 배열(컬랙션)
	
	public Department(int deptNo, String deptName) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.empList = new ArrayList<Employee>(); // Employee는 생략가능 => 위에서 선언했기 때문에
	}
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getDeptNo() {
		return deptNo;
	}
	
	void addEmployee(Employee e) {
		this.empList.add(e);
	}
	
	
	public void outputEntireEmployees() {
		for (Employee e : this.empList) {
			System.out.println(e.toString());
		}
	}
}


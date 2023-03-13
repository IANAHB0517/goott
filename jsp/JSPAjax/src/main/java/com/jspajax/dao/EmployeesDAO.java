package com.jspajax.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.jspajax.vo.DepartmentVo;
import com.jspajax.vo.Employees;
import com.jspajax.vo.JobsVo;

public interface EmployeesDAO {
	
	// 모든 직원 정보를 얻어오는 메서드
	public abstract List<Employees> selecAllEmp(String searchName, String order) throws NamingException, SQLException;
	
	//모든 jobs 정보를 얻어오는 메서드
	
	List<JobsVo> selectAllJobs() throws NamingException, SQLException;
	
	//모든 dept 정보를 얻어오는 메서드
	List<DepartmentVo> SelectAllDept() throws NamingException, SQLException;
	
	// 사원을 저장하는 메서드
	String insertEmp(Employees empDto) throws NamingException, SQLException;
	
	// 사원을 삭제하는 메서드
	int deleteEmp(int empNo, Date quitDate) throws NamingException, SQLException;
	
	//사번으로 해당 사원의 정보를 얻어오는 메서드
	Employees selectEmpByEmpNo(int empNo) throws NamingException, SQLException;
	
	//사원 정보를 수정하는 메서드
	int updateEmployee(Employees dto) throws NamingException, SQLException;
}

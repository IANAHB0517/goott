package com.jspajax.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.jspajax.vo.DepartmentVo;
import com.jspajax.vo.Employees;
import com.jspajax.vo.JobsVo;

public class EmployeesDAOImpl implements EmployeesDAO {

	private static EmployeesDAOImpl instance;

	private EmployeesDAOImpl() {
	}

	public static EmployeesDAOImpl getInstance() {
		if (instance == null) {
			instance = new EmployeesDAOImpl();
		}
		return instance;
	}

	@Override
	public List<Employees> selecAllEmp() throws NamingException, SQLException {
		System.out.println(getClass().getName() + "DAO 단");

		List<Employees> lst = new ArrayList<>();

		Connection con = DBConnection.dbconnect();
		if (con != null) {
			String query = "SELECT e.* , d.department_name from employees e inner join departments d"
					+ " on e.department_id = d.department_id order by EMPLOYEE_ID desc";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new Employees(rs.getInt("EMPLOYEE_ID"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"),
						rs.getString("EMAIL"), rs.getString("PHONE_NUMBER"), rs.getDate("HIRE_DATE"),
						rs.getString("JOB_ID"), rs.getFloat("SALARY"), rs.getFloat("COMMISSION_PCT"),
						rs.getInt("MANAGER_ID"), rs.getInt("DEPARTMENT_ID"), rs.getString("DEPARTMENT_NAME")));
			}

			DBConnection.dbClose(rs, pstmt, con);
		}

		return lst;
	}

	@Override
	public List<JobsVo> selectAllJobs() throws NamingException, SQLException {
		// System.out.println(getClass().getName() + "DAO 단");

		List<JobsVo> lst = new ArrayList<>();

		Connection con = DBConnection.dbconnect();
		if (con != null) {
			String query = "select * from jobs";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new JobsVo(rs.getString("JOB_ID"), rs.getString("JOB_TITLE"), rs.getInt("MIN_SALARY"),
						rs.getInt("MAX_SALARY")

				));
			}

			DBConnection.dbClose(rs, pstmt, con);
		}

		return lst;
	}

	@Override
	public List<DepartmentVo> SelectAllDept() throws NamingException, SQLException {

		List<DepartmentVo> lst = new ArrayList<>();

		Connection con = DBConnection.dbconnect();
		if (con != null) {
			String query = "select * from DEPARTMENTS order by DEPARTMENT_ID";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new DepartmentVo(rs.getInt("DEPARTMENT_ID"), rs.getString("DEPARTMENT_NAME"),
						rs.getInt("MANAGER_ID"), rs.getInt("LOCATION_ID")));
			}

			DBConnection.dbClose(rs, pstmt, con);
		}

		return lst;
	}

	@Override
	public String insertEmp(Employees empDto) throws NamingException, SQLException {
		String result = null;

		Connection con = DBConnection.dbconnect();
		if (con != null) {
			String query = "{call PROC_SAVEEMP(?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement cstmt = con.prepareCall(query);

			cstmt.setString(1, empDto.getFIRST_NAME());
			cstmt.setString(2, empDto.getLAST_NAME());
			cstmt.setString(3, empDto.getEMAIL());
			cstmt.setString(4, empDto.getPHONE_NUMBER());
			cstmt.setDate(5, empDto.getHIRE_DATE());
			cstmt.setString(6, empDto.getJOB_ID());
			cstmt.setFloat(7, empDto.getSALARY());
			cstmt.setFloat(8, empDto.getCOMMISSION_PCT());
			cstmt.setInt(9, empDto.getMANAGER_ID());
			cstmt.setInt(10, empDto.getDEPARTMENT_ID());
			
			// 마지막 매개변수는 OUT 매개변수는 아래와 같이 등록한다.
			// java.sql.Types DB에서 쓰는 모든 타입을 정의해둠
			cstmt.registerOutParameter(11, java.sql.Types.VARCHAR);
			// 11번째 매개변수를 등록
			
			// 실행
			cstmt.executeUpdate(); //preparedstatement의 메서드 사용
			
			//out 매개변수에서 반환되는 값을 가져오기
			result = cstmt.getString(11);
			
			System.out.println(result);
			
			DBConnection.dbClose(cstmt, con);
		}
		
		return result;
	}
}

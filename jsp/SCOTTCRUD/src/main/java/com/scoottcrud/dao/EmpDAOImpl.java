package com.scoottcrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.scoottcrud.vo.Emp;

public class EmpDAOImpl implements EmpDAO {

	private static EmpDAOImpl instance;
	
	private EmpDAOImpl() {};
	
	public static EmpDAOImpl getinstance() {
		if (instance == null ) {
			instance = new EmpDAOImpl();
		}
		return instance;
	}
	
	@Override
	public List<Emp> getEmp() throws NamingException, SQLException {
		System.out.println("getEmp DAO 단 도착");
		
		Connection con = DBConnection.dbconnect();
		List<Emp> lst = new ArrayList<>();
		
		if(con != null) {
			String query = "select * from emp order by empno desc";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			
			if(rs.next()) {
				lst.add(new Emp(rs.getInt("EMPNO"), rs.getString("ENAME"), rs.getString("JOB"), rs.getInt("MGR"),
						rs.getDate("HIREDATE"), rs.getFloat("SAL"), rs.getFloat("COMM"), rs.getInt("DEPTNO")));
			}
			
			DBConnection.dbClose(rs, pstmt, con);
			
		}
		
		
		
		
		
		return lst;
	}

}

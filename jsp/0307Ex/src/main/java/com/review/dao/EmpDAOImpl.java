package com.review.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.review.vo.Employees;

public class EmpDAOImpl implements EmpDAO {

	private static EmpDAOImpl instance;
	
	private EmpDAOImpl() {
		
	}
	
	public static EmpDAOImpl getinstance() {
		if (instance == null) {
			instance = new EmpDAOImpl();
		}
		return instance;		
	}
	
	
	@Override
	public List<Employees> selecAllEmp() {
	
		List<Employees> lst = new ArrayList<>();
		
		Connection con = 
		
		
		
		
		return lst;
	}

}

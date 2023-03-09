package com.scoottcrud.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.scoottcrud.vo.Emp;

public interface EmpDAO {

	
	
	public abstract List<Emp> getEmp() throws NamingException, SQLException;
	
}

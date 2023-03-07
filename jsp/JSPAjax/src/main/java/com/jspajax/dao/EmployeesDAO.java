package com.jspajax.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.jspajax.vo.Employees;

public interface EmployeesDAO {

	public abstract List<Employees> selecAllEmp() throws NamingException, SQLException;
}

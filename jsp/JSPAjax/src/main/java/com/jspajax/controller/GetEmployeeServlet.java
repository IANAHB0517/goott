package com.jspajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jspajax.dao.EmployeesDAO;
import com.jspajax.dao.EmployeesDAOImpl;
import com.jspajax.etc.OutputJSONForError;
import com.jspajax.vo.Employees;


@WebServlet("/getEmp.do")
public class GetEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int empNo = Integer.parseInt(req.getParameter("empNo"));
		
		System.out.print(empNo);
		
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();
		
		EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		
		try {
			Employees e = dao.selectEmpByEmpNo(empNo);
			
			if (e != null) {
				JSONObject json = new JSONObject();
				json.put("status", "success");
				String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
				json.put("outputDate", outputDate);
				
				JSONObject employee = new JSONObject();

				employee.put("EMPLOYEE_ID", e.getEMPLOYEE_ID() + "");
				employee.put("FIRST_NAME", e.getFIRST_NAME());
				employee.put("LAST_NAME", e.getLAST_NAME());
				employee.put("EMAIL", e.getEMAIL());
				employee.put("PHONE_NUMBER", e.getPHONE_NUMBER());

				Date tempDate = e.getHIRE_DATE();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
				sdf.format(tempDate);

				employee.put("HIRE_DATE", sdf.format(tempDate));

				employee.put("JOB_ID", e.getJOB_ID());
				employee.put("SALARY", e.getSALARY() + "");
				employee.put("COMMISSION_PCT", e.getCOMMISSION_PCT() + "");
				employee.put("MANAGER_ID", e.getMANAGER_ID() + "");
				employee.put("DEPARTMENT_ID", e.getDEPARTMENT_ID() + "");
				employee.put("DEPARTMENT_NAME", e.getDEPARTMENT_NAME());
				
				json.put("employee", employee);
				
				out.print(json.toJSONString());

			}
		} catch (NamingException | SQLException e) {
			OutputJSONForError.outputJson(e);
		}
		
		out.close();
	}

}

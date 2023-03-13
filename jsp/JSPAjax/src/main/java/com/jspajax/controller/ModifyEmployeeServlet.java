package com.jspajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

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

@WebServlet("/ModiEmp.do")
public class ModifyEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		

		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();

		
		
		// request 객체를 처리
		// request로 부터 넘어온 데이터를 데이터 타입 변경하여 얻어옴 
		int empNo = Integer.parseInt(req.getParameter("Employee_id"));
		String firstName = req.getParameter("FIRST_NAME");
		String lastName = req.getParameter("LAST_NAME");
		String email = req.getParameter("EMAIL");
		String phoneNumber = req.getParameter("PHONE_NUMBER");
		String strHireDate = req.getParameter("HIRE_DATE"); // String Type이므로 java.sql.Date로 변환해야함.
		Date hireDate = Date.valueOf(strHireDate);
		System.out.println(Date.valueOf(strHireDate));

		String jobId = req.getParameter("JOB_ID");
		float salary = Float.parseFloat(req.getParameter("SALARY"));
		float commissionPct = Float.parseFloat(req.getParameter("COMMISSION_PCT"));
		int managerId = Integer.parseInt(req.getParameter("MANAGER_ID"));
		int departmentId = Integer.parseInt(req.getParameter("DEPARTMENT_ID"));
		// System.out.println("입력한 이름: "+ firstName + " " + lastName);

		Employees emp = new Employees(empNo, firstName, lastName, email, phoneNumber, hireDate, jobId, salary,
				commissionPct, managerId, departmentId, null);
		System.out.println(emp.toString());
		
		EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		
		try {
			if (dao.updateEmployee(emp) == 1) {

				JSONObject json = new JSONObject();
				json.put("status", "success");
				String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
				json.put("outputDate", outputDate);
				
				out.print(json.toJSONString());
			} else {
				
				JSONObject json = new JSONObject();
				json.put("status", "fail");
				String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
				json.put("outputDate", outputDate);
				
				out.print(json.toJSONString());
				
			}
			
		} catch (NamingException | SQLException e) {
			out.print(OutputJSONForError.outputJson(e));
		}
		out.close();
		
	}

}

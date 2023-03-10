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

@WebServlet("/saveEmp.do")
public class SaveEmployeeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();

		
		
		// request 객체를 처리
		// request로 부터 넘어온 데이터를 데이터 타입 변경하여 얻어옴 
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

		Employees emp = new Employees(0, firstName, lastName, email, phoneNumber, hireDate, jobId, salary,
				commissionPct, managerId, departmentId, null);
		System.out.println(emp.toString());

		// 저장 프로시져 (Stored Procedure)를 사용하지 않는다면
		// 1) 사번은 1씩 증가한 값을 넣어야 하기 때문에 다음 저장될 사원의 사번을 얻어온다.
		// 2) 1)번 과정에서 얻어온 사번과 함께 유저가 입력한 데이터를 insert
		// 위 두 과정으로 사원을 저장해야 하므로 dao단을 2번 다녀와야 한다.
		// ex) 자바 기본 때 배운 Friend 예제 참조

		// 하지만, 저장 프로시져를 사용하면 dao단을 1번만 호출하면 된다.
		// 왜? 비즈니스 로직이 저장 프로시져에 있으므로

		EmployeesDAO dao = EmployeesDAOImpl.getInstance();

		try {
			String result = dao.insertEmp(emp);
			if (result.equals("success")) { // 저장 성공

				JSONObject json = new JSONObject();
				json.put("status", "success");
				String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
				json.put("outputDate", outputDate);
				
				out.print(json.toJSONString());
				
			} else if(result.equals("error")) { // 저장 실패
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

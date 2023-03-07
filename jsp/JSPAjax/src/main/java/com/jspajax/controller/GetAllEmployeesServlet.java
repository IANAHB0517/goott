package com.jspajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jspajax.dao.EmployeesDAO;
import com.jspajax.dao.EmployeesDAOImpl;
import com.jspajax.vo.Employees;

@WebServlet("/getAllEmployees.do")
public class GetAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();

		EmployeesDAO dao = EmployeesDAOImpl.getInstance();

		try {
			List<Employees> lst = dao.selecAllEmp();

			// Gson 라이브러리를 이용한 json 변환하기
//			String outputJson = toJsonWithGson(lst);

			// json-simple 라이브러리를 이용해 json 변환하기
			String outputJson = toJsonWithJsonSimple(lst);

			out.print(outputJson);

			out.close();

		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		} // response 객체가 있기때문에 예외 처리를 할 수 있다.

	}

	private String toJsonWithJsonSimple(List<Employees> lst) { // 레스트 서버의 역할
		// simple-json 라이브러리를 이용한 json 변환
		// JSONObject : json 객체의미
		// JSONArray : 배열을 의미
		
		JSONObject json = new JSONObject();
		json.put("status", "success");
		String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString(); 
		json.put("outputDate", outputDate);
		json.put("count",lst.size() + "");
		
		JSONArray employees = new JSONArray();
		for (Employees e : lst) {
			JSONObject employee = new JSONObject();
			employee.put("EMPLOYEE_ID", e.getEMPLOYEE_ID() + "");
			employee.put("FIRST_NAME", e.getFIRST_NAME());
			employee.put("LAST_NAME", e.getLAST_NAME());
			employee.put("EMAIL", e.getEMAIL());
			employee.put("PHONE_NUMBER", e.getPHONE_NUMBER());
			
			
			Date tempDate =  e.getHIRE_DATE();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
			 sdf.format(tempDate);
			
			employee.put("HIRE_DATE",  sdf.format(tempDate));
			
			
			employee.put("JOB_ID", e.getJOB_ID());
			employee.put("SALARY", e.getSALARY() + "");
			employee.put("COMMISSION_PCT", e.getCOMMISSION_PCT() + "");
			employee.put("MANAGER_ID", e.getMANAGER_ID() + "");
			employee.put("DEPARTMENT_ID", e.getDEPARTMENT_ID() + "");
			employee.put("DEPARTMENT_NAME", e.getDEPARTMENT_NAME());
		
			employees.add(employee);
		}
		
		json.put("employees", employees);
		
//		json.put(json, outputDate)
		
		
		return json.toJSONString();
	}

	private String toJsonWithGson(List<Employees> lst) {
		// Gson 라이브러리를 이용한 json 변환
		Gson gson = new Gson();
		Type type = new TypeToken<List<Employees>>() {
		}.getType();
		String outputJson = gson.toJson(lst, type);

		return outputJson;

	}

}

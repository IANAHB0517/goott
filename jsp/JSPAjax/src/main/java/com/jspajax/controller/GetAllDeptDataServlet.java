package com.jspajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jspajax.dao.EmployeesDAO;
import com.jspajax.dao.EmployeesDAOImpl;
import com.jspajax.etc.OutputJSONForError;
import com.jspajax.vo.DepartmentVo;

@WebServlet("/getAllDept.do")
public class GetAllDeptDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public GetAllDeptDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(getClass().getName() + "deptData");
		

		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();

		EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		
		try {
			List<DepartmentVo> lst = dao.SelectAllDept();
			

			JSONObject json = new JSONObject();
			json.put("status", "success");
			String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
			json.put("outputDate", outputDate);
			json.put("count", lst.size() + "");

			JSONArray depts = new JSONArray();
			
			if (lst.size() > 0) {
				for (DepartmentVo d : lst) {
					
					JSONObject dept = new JSONObject();
					dept.put("DEPARTMENT_ID", d.getDEPARTMENT_ID() + "");
					dept.put("DEPARTMENT_NAME", d.getDEPARTMENT_NAME());
					dept.put("MANAGER_ID", d.getMANAGER_ID() + "");
					dept.put("LOCATION_ID", d.getLOCATION_ID() + "");
					
					depts.add(dept);
				}
				json.put("depts", depts);
			}
			
			out.print(json.toJSONString());
			
		} catch (NamingException | SQLException e) {
			out.println(OutputJSONForError.outputJson(e));
			
		}
		
		out.close();
	}

	
}

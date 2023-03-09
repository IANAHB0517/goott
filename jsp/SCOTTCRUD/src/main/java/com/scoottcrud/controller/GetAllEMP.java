package com.scoottcrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.scoottcrud.dao.EmpDAO;
import com.scoottcrud.dao.EmpDAOImpl;
import com.scoottcrud.vo.Emp;

@WebServlet("/getAllEmp.do")
public class GetAllEMP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();

		EmpDAO dao = EmpDAOImpl.getinstance();

		try {
			List<Emp> lst = dao.getEmp();

			String outputJson = toJsonWithJsonSimple(lst);

			out.print(outputJson);

		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.close();
	}

	private String toJsonWithJsonSimple(List<Emp> lst) {

		JSONObject json = new JSONObject();
		json.put("status", "success");
		String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
		json.put("outputDate", outputDate);
		json.put("cnt", lst.size() + "");

		JSONArray Emps = new JSONArray();

		if (lst.size() > 0) {

			for (Emp e : lst) {
				JSONObject emp = new JSONObject();
				emp.put("EMPNO", e.getEMPNO());
				emp.put("ENAME", e.getENAME());
				emp.put("JOB", e.getJOB());
				
				emp.put("MGR", e.getMGR() + "");

				Date tempDate = e.getHIREDATE();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");

				emp.put("HIREDATE", sdf.format(tempDate));
				
				
				emp.put("SAL", e.getSAL() + "");
				emp.put("COMM", e.getCOMM() + "");
				emp.put("DEPTNO", e.getDEPTNO() + "");
				
				Emps.add(emp);
				
			}

		}
		
		json.put("emps", Emps);

		
		
		return json.toJSONString();
	}

}
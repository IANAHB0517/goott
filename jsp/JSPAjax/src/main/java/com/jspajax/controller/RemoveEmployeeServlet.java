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

@WebServlet("/delEmp.do")
public class RemoveEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();

		int empNo = Integer.parseInt(req.getParameter("empNo")); // 파라미터는 String을 반환

		System.out.println(empNo);

		Date now = new Date(System.currentTimeMillis());

		EmployeesDAO dao = EmployeesDAOImpl.getInstance();

		JSONObject json = new JSONObject();
		String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
		json.put("outputDate", outputDate);
		try {
			if (dao.deleteEmp(empNo, now) == 1) { // 사원 삭제 처리 완료
				json.put("status", "success");

			} else { // 삭제 처리 실패
				json.put("status", "fail");

			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block

			out.print(OutputJSONForError.outputJson(e));
		}

		out.print(json.toJSONString());

		out.close();
	}

}

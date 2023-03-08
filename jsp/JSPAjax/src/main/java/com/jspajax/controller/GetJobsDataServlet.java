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
import com.jspajax.vo.JobsVo;

@WebServlet("/getJobsData.do")
public class GetJobsDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetJobsDataServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();
		EmployeesDAO dao = EmployeesDAOImpl.getInstance();

		try {
			List<JobsVo> lst = dao.selectAllJobs();

			JSONObject json = new JSONObject();
			json.put("status", "success");
			String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
			json.put("outputDate", outputDate);
			json.put("count", lst.size() + "");

			JSONArray jobs = new JSONArray();

			if (lst.size() > 0) {
				for (JobsVo j : lst) {
					JSONObject job = new JSONObject();
					job.put("JOB_ID", j.getJOB_ID());
					job.put("JOB_TITLE", j.getJOB_TITLE());
					job.put("MIN_SALARY", j.getMIN_SALARY() + "");
					job.put("MAX_SALARY", j.getMAX_SALARY() + "");

					jobs.add(job);
				}

				json.put("JOBS", jobs);
			}

			out.print(json.toJSONString());

		} catch (NamingException | SQLException e) {
			out.print(OutputJSONForError.outputJson(e));
		}
		out.close();
	}

}

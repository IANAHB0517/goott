package com.jspbasic;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspbasic.vo.TestMemberVo;

@WebServlet("/vInput.do")
public class VariableInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VariableInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); // 요청 데이터에 대한 인코딩 설정
		
		// checkbox에 동의 한 경우
		if (request.getParameter("agree").equals("Y")){
			
		// input type = text
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		// input type = radio
		String gender = request.getParameter("gender"); // 선택된 value 값이 자동으로 들어온다.
		// input type = checkbox
		String[] hobbies = request.getParameterValues("hobby");
		String hobby = "";
		for (String s : hobbies) {
			hobby += s + ", ";
		}
		
		// select - option 
		String job = request.getParameter("job");
		// textarea
		String memo = request.getParameter("memo");
		
		//input type=number
		int age = Integer.parseInt(request.getParameter("age"));
		
		System.out.println("userId : " + userId);
		System.out.println("pwd : " + pwd);
		System.out.println("email : " + email);
		System.out.println("mobile : " + mobile);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println(Arrays.toString(hobbies));
		System.out.println("job : " + job);
		System.out.println("memo : " + memo);

		
		// 바인딩
		TestMemberVo member = new TestMemberVo(userId, pwd, email, mobile, gender, age, hobby, job, memo);
		
		request.setAttribute("member", member);
		
		RequestDispatcher rd = request.getRequestDispatcher("outputTestMember.jsp");
		rd.forward(request, response);
		
		}
	}

}

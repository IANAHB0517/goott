package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ValidationInServlet")
public class ValidationInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ValidationInServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 국어, 영어, 수학, 총점, 평균을 유저가 확인 할 수 있는 웹 페이지로 출력
		response.setContentType("text/html; charset=utf-8"); 
		// 응답할 문서의 종류와 인코딩 방식을 설정
		
		PrintWriter out = response.getWriter(); // getWriter()는 Stream객체로서 한번 열리면 닫히기 전까지 새로 설정할 수 없다?
		// 출력할 수 있는 객체
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("HelloServlet_GET");
		System.out.println("Get방식으로 요청됨");
		
		// GET 방식으로 요청하면서 전달한 파라메터를 언더옴
		int kor = Integer.parseInt(request.getParameter("kor"));
		
		//아래의 코드는 국어점수의 유효성을 검사하는 부분이다.
		
		if (kor < 0 || kor > 100) {
			out.print("<script>");
			out.print("alert('국어점수가 잘못 입력 되었습니다!')");
			out.print("location.href='validationInServlet.jsp';");
			out.print("</script>");
		}
		
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		
		
		int total = kor + eng + math;
		float avg = total /3f;
		System.out.println(kor + "," + eng + "," + math + "=" + "total : " + total + "," + "avg : " + avg);
		
		
		
		
		out.print("<!DOCTYPE html>"
				+ "<html>" 
				+ "<head>"
				+ "<meta charset=\'UTF-8\'>"
				+ "<title>성적표</title>"
				 + "</head>"
				+ "<body>"
				 + "<div>국어 : " + kor + "</div>"
				 + "<div>영어 : " + eng + "</div>"
				 + "<div>수학 : " +  math+ "</div>"
				 + "<div>총점 : " +  total + "</div>"
				 + "<div>평균 : " +  avg + "</div>"
				 + "</body></html>"
				);
		
		out.flush();
		out.close();
	}

}

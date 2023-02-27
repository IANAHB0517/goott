package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet_GET")
public class HelloServlet_GET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
    public HelloServlet_GET() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 클라이언트에서 get 방식으로 요청하면 호출되는 메서드
	 * GET 방식으로 요청
	 *  - url 주소표시줄에 현재 페이지를 기술하고 엔터쳤을 경우
	 *  - a 태그가 클릭되어 현재 페이지가 로딩된 경우
	 *  - form태그에서 method="GET" 으로 요청된 경우
	 *  - js에서 location.href='현재페이지'로 이동된 경우
	 *  - js에서 ajax를 통해 GET 방식으로 데이터를 전송했을 경우
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("HelloServlet_GET");
		System.out.println("Get방식으로 요청됨");
		
		// GET 방식으로 요청하면서 전달한 파라메터를 언더옴
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		int total = kor + eng + math;
		float avg = total /3f;
		System.out.println(kor + "," + eng + "," + math + "=" + "total : " + total + "," + "avg : " + avg);
		
		// 국어, 영어, 수학, 총점, 평균을 유저가 확인 할 수 있는 웹 페이지로 출력
		response.setContentType("text/html; charset=utf-8"); 
		// 응답할 문서의 종류와 인코딩 방식을 설정
		
		PrintWriter out = response.getWriter(); // getWriter()는 Stream객체로서 한번 열리면 닫히기 전까지 새로 설정할 수 없다?
		// 출력할 수 있는 객체
		
		
		
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 클라이언트가 POST방식으로 요청했을 경우 실행되는 메서드
	 *  - form태그에서 method="POST" 으로 요청된 경우
	 *  - js에서 ajax를 통해 POST 방식으로 데이터를 전송했을 경우
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}

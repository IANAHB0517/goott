package com.springproj.etc;

import javax.servlet.http.HttpServletRequest;

public class DestinationPathProc {
	public static void returnPathProc(HttpServletRequest req) {
		String requestURI = req.getRequestURI();
		String queryString =  req.getQueryString(); // ?를 제외한 쿼리스트링 문자열
		
		if(queryString == null || queryString.equals("")) {
			queryString= "";
		} else {
			queryString ="?" + queryString;
		}
		
		if(req.getMethod().equals("GET")) { // GET 방식으로 요청 되었다면
			System.out.println("로그인 후 이동할 페이지 : " + requestURI + queryString) ;
			req.getSession().setAttribute("returnPath", requestURI + queryString);
		}
	}
}

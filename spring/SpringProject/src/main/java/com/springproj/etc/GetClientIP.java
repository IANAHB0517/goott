package com.springproj.etc;

import javax.servlet.http.HttpServletRequest;

public class GetClientIP {
	public static String getClientIP(HttpServletRequest request) {
		// req.getRemoteAddr(); // 방화벽이나 보안 장비가 있을 때는 ip 주소를 가져올 수 없다.
		
		String ip = request.getHeader("X-Forwarded-For");
	    System.out.println("> X-FORWARDED-FOR : " + ip);

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        System.out.println("> Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	        System.out.println(">  WL-Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        System.out.println("> HTTP_CLIENT_IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        System.out.println("> HTTP_X_FORWARDED_FOR : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	        System.out.println("> getRemoteAddr : "+ip);
	    }
	    System.out.println("> Result : IP Address : "+ip);

	    return ip;
	}
}

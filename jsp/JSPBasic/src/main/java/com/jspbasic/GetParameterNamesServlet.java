package com.jspbasic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getParamNames.do")
public class GetParameterNamesServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		req.setCharacterEncoding("utf-8");

//		Enumeration<String> params = req.getParameterNames();
//
//		Map<String, List<String>> map = new HashMap<>();
//		while (params.hasMoreElements()) { // 다음의 요소가 있을 동안
//			String paramName = (String) params.nextElement(); // 파라미터의 이름을 얻어옴 html에서 태그에 준 name값
//
//			String value = req.getParameter(paramName); // name 값으로 얻은 paramName
//
//			System.out.println(paramName + " : " + value);
//
//			List<String> values = new ArrayList<>();
//			values.add(value);
//			map.put(paramName, values);
//
//		}
//
//		String userId = "";
//		if (map.containsKey("userId")) {
//			List<String> value = map.get("userId");
//			userId = value.get(0);
//
//		}
//
//		System.out.println(userId); // jsp 파일을 볼 수 없을때는 이렇게 사용해야한다.
		
		// req.getParameterMap() - 위의 코드를 줄여주는 메서드 한번 사용해보자

		
		String userId = req.getParameter("userId");
		System.out.println(userId); // jsp 파일을 볼 수 있을 때 사용가능한 코드
		
	}

}

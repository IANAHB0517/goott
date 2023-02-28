package com.jspbasic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/hungry.h-well", "/hgr.bab"} ) // 매핑 주소를 여러개 등록하는 것도 가능
public class HungrySevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public HungrySevlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("배고파");
	}


}

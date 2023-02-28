package com.jspbasic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspbasic.vo.ProductVo;

@WebServlet("/inputProd.do")
public class ProductInOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductInOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		int qty = Integer.parseInt(request.getParameter("qty"));
		int price = Integer.parseInt(request.getParameter("price"));
		String color = request.getParameter("color");
		
		ProductVo prod = new ProductVo(name, qty, price, color);
		
		request.setAttribute("prod", prod);
		
		RequestDispatcher rd = request.getRequestDispatcher("productOutput.jsp");
		
		rd.forward(request, response);
		
		System.out.println("여기까지옴");
		System.out.println(name + "," + qty + "," + price + "," + color);
	}
}

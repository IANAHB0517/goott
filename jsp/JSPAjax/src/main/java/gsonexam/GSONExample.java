package gsonexam;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jspajax.vo.Employees;


@WebServlet("/GSONExample")
public class GSONExample extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public GSONExample() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Employees emp = new Employees(1000, "길동", "홍", "hkd@asd.com", "010-0000-1111", new Date(2023,3,7),
			"IT_PROG", 5000, 0.0f, 103, 90, "IT");
	
	Employees emp2 = new Employees(1001, "길동", "콩", "hkd@asd.com", "010-2222-1111", new Date(2023,2,22),
			"IT_PROG", 5000, 0.0f, 103, 90, "IT");
	
	
	System.out.println(emp.toString());
	
	Gson gson = new Gson();
	
	String json = gson.toJson(emp, Employees.class); //일반 객체를 json으로 변환
	
	System.out.println(json);
	
	List<Employees> lst = new ArrayList<>();
	lst.add(emp);
	lst.add(emp2);
	
	
	
//	 Type typeOfSrc = new TypeToken<Collection<Foo>>(){}.getType();
	 Type type = new TypeToken<List<Employees>>() {}.getType(); //TypeToken : 컬렉션에 들어있는 요소들을 Employees 단위로 분할하는 역할
	 
	String json2 = gson.toJson(lst,type); // 컬렉션 객체를 json으로 변환
	
	System.out.println(json2);
	
	}


}

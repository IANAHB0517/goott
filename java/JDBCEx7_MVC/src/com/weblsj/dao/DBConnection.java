package com.weblsj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 class DBConnection {
	private static String id = "hr"; 
	private static String pwd = "1234";
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

	static Connection getConnection() throws SQLException, ClassNotFoundException {
		// 디비와의 연결은 dao 단에서만 하기 때문에 접근 제어자를 default로 둔다
		// 예외 처리 또한 dao단에서 출력할 수 없기 때문에 미뤄준다.

		Connection con = null;
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버가 있는지
			con = DriverManager.getConnection(url, id, pwd); // 실제 Connection 객체를 얻어옴


		return con;

	}
	
	static void close(ResultSet rs, Statement stmt, Connection con) throws SQLException {
					 	            // 다형성 preparedConnectin, callable 머시기의 부모클래스로 닫아줌
		
		rs.close();
		stmt.close();
		con.close();
		
	}
	
	static void close(Statement stmt, Connection con) throws SQLException  { // 오버로딩
		
		stmt.close();
		con.close();
		
	}
	
}

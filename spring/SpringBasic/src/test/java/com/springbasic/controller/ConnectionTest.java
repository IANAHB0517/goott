package com.springbasic.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionTest {
	private static String driverClassName = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/lsj?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
	private static String username = "root";
	private static String password = "1234";

	@Test // 아래의 메서드가 JUnit4 라이브러리에 의해 동작 되는 테스트 메서드임을 알림
 	public void testConnection() throws ClassNotFoundException {
		Class.forName(driverClassName);
		
		try(Connection con = DriverManager.getConnection(url, username, password)) {
			System.out.println(con.toString()); // 예외가 나지 않는다면
		} catch (SQLException s) {
			s.printStackTrace(); // 예외가 난다면
		}
		
	}

}

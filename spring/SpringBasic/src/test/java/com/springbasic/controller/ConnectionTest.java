package com.springbasic.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionTest {
 private static String driverClassName="com.mysql.cj.jdbc.Driver";
 private static String url="jdbc:mysql://localhost:3306/lsj?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";

 private static String username = "root";
 private static String password = "1234";
 
 @Test
 public void testConncection() throws ClassNotFoundException {
	Class.forName(driverClassName);
	
	try(Connection con = DriverManager.getConnection(url, username, password)){
		System.out.println(con.toString());
	} catch (SQLException s) {
		s.printStackTrace();
	};
}
}

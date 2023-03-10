package com.scoottcrud.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	public static Connection dbconnect() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env"); // Directory service에 의해 context.xml 파일의
																				// 객체를 얻어온다.(JDNI)
		DataSource ds = (DataSource) envContext.lookup("jdbc/sctcrud"); // 아래 이름의 태그를 찾아 그 부분으로 부터 Connection 객체를 얻어온다.
		// 여기를 봐라 이 우매한 짐승아
		Connection conn = ds.getConnection(); // context.xml에서 만들어진 connection 객체를 얻어옴
		// etc.

		return conn;
	}
	
	public static void dbClose(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		rs.close();
		stmt.close();
		con.close();
	}
	
}
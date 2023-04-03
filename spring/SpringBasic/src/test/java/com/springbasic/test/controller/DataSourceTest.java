package com.springbasic.test.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// root-context.xml에 있는 Connection 객체가 잘 주입 되는지 테스트
// 현재 DataSourceTest와 함께 구동시킬 라이브러리 클래스 설정
// 현재 클래스를 실행시킬때 Spring이 함께 로딩 되도록 // (그래야 root-context.xml를 읽어올 수 있기 때문)
@RunWith(SpringJUnit4ClassRunner.class) // 
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
		)// root-context.xml 파일의 위치
public class DataSourceTest {
	
	@Inject // 객체주입 (root-context.xml 에 있는 빈 중에 데이터 타입이 DataSource인 객체 주입)
	// 같은 타입인 객체를 찾아서 넣어라 싱글톤 처럼 하나의 객체로 사용하기 때문에 없으면 만들어서 있으면 있는 객체로 사용한다
	private DataSource ds;
	
	@Test // 아래의 메서드가 테스트 대상임을 명시
	public void testConnection() throws ClassNotFoundException {
		
		try(Connection con = ds.getConnection()) {
			System.out.println(con.toString()); // 예외가 나지 않는다면
		} catch (SQLException s) {
			s.printStackTrace(); // 예외가 난다면
		}
		
	}
	
	
}
 
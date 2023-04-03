package com.springbasic.test.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
		)// root-context.xml 파일의 위치
public class SessionFactoryTest {

	@Inject
	private SqlSessionFactory ssf;
	
	@Test
	public void sqlSessionFactoryTest() {
		try(SqlSession session = ssf.openSession()) { // session은 통신 의미가 강해서 메서드 이름이 open이다
			System.out.println(session.toString()); // 예외가 나지 않는다면
		} catch (Exception s) {
			s.printStackTrace();
		}
	}
	
}

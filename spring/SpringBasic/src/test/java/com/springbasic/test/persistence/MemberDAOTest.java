package com.springbasic.test.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springbasic.domain.MemberPointVO;
import com.springbasic.domain.MemberVO;
import com.springbasic.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class) //
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" }) // root-context.xml 파일의
																									// 위치

public class MemberDAOTest {
	@Inject
	private MemberDAO dao;

//	@Test // Test 메서드는 한 클래스에 하나만 있어야 한다.
//	public void getDateTimeTest() {
//		System.out.println(this.dao.getDateTime());
//	}

//	@Test
//	public void insertMemberTest() throws Exception {
//		MemberVO member = new MemberVO("test2", "1234", "test@t.com", "010-0442-0123", "M", null, null, null, "log4j!", "Y");
//		if(this.dao.insertMember(member) == 1 ) {
//			System.out.println("회원 저장성공");
//		} else {
//			System.out.println("회원 저장실패");
//			
//		};
//	}
//	@Test
//	public void updateMemberTest() throws Exception {
//		MemberVO member = new MemberVO("test4", null, null, null,null, null, null, "uploadMember/noimg.png", null, null);
//		if(dao.updateMember(member) ==1) {
//			System.out.println("회원수정성공");
//		};
//	}

//	@Test
//	public void deleteMemberTest() throws Exception {
//		String userId = "test";
//		if (dao.deleteMember(userId) == 1) {
//			System.out.println("회원삭제 성공");
//		}
//	}
//	@Test
//	public void selectMemberByUserIdTest() throws Exception {
//		String userId = "test2";
//		System.out.println((dao.selectMemberByUserId(userId)).toString());
//		
//	}
//	@Test
//	public void selectAllMemberTest() throws Exception {
//		
//		for(MemberVO member : dao.selectAllMembers()) {
//			System.out.println(member.toString());
//		};
//	}

//	@Test
//	public void loginMemberTest() throws Exception {
//
//		if (dao.loginMember("tet2", "1234") != null) {
//			System.out.println("로그인 성공");
//		} else {
//			System.out.println("로그인 실패");
//		};
//	}

//	@Test
//	public void isDuplicateTest() throws Exception {
//		if (dao.selectByUserId("test3") == 1) {
//			System.out.println("회원 아이디 중복");
//		} else {
//			System.out.println("회원 아이디 사용가능");
//		};
//	}
//	@Test
//	public void addPointTest() throws Exception {
//		if (dao.addPoint("test2", "로그인") == 1) {
//			System.out.println("포인트 적립 성공");
//		} else {
//			System.out.println("포인트 적립 실패");
//		};
//	}
//	@Test
//	public void whenLatestLoginTest() throws Exception {
//		if(dao.whenLatestLogin("a1a1") <= 1) {
//			System.out.println("포인트 부여 불가능");
//		} else if (dao.whenLatestLogin("a1a1") > 1) {
//			System.out.println("포인트 부여 가능");
//			
//		}
//	}

	@Test
	public void memberPointList() throws Exception {
		for(MemberPointVO point : dao.getMemberPoint("ramen")) {
			System.out.println(point.toString());
		}
	}
}

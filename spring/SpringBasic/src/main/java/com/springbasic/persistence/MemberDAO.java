package com.springbasic.persistence;

import java.util.List;

import com.springbasic.domain.MemberPointVO;
import com.springbasic.domain.MemberVO;

public interface MemberDAO {
	// 회원 가입
	public int insertMember(MemberVO dto) throws Exception;

	// 아이디 중복
	int selectByUserId(String userId) throws Exception;

	// DB로 부터 현재 날짜와 현재 시간을 얻어오는
	String getDateTime();

	// 회원 수정
	int updateMember(MemberVO dto) throws Exception;

	// 회원 삭제
	int deleteMember(String userId) throws Exception;

	// 아이디로 회원조회
	MemberVO selectMemberByUserId(String userId) throws Exception;

	// 모든 회원 조회
	List<MemberVO> selectAllMembers() throws Exception;

	// 로그인 멤버
	MemberVO loginMember(String userId, String userPwd) throws Exception;

	// 포인트 부여
	int addPoint(String userId, String why) throws Exception;

	// 가장 최근에 로그인 한 기록이 오늘인지?
	int whenLatestLogin(String userId) throws Exception;
	
	// 포인트 내역 가져오기
	List<MemberPointVO> getMemberPoint(String userId) throws Exception;
}
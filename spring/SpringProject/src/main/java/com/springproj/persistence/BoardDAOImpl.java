package com.springproj.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.BoardVo;

@Repository // 현재 클래스가 DAO 단임을 명시
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session; // SqlSessionTemplate 객체 주입
	
	private static String ns = "com.springproj.mappers.boardMapper";
	
	@Override
	public List<BoardVo> selectAllBoard() throws Exception {
		System.out.println("DAO단 : 게시판 목록 조회");
		
		return session.selectList(ns + ".getAllBoards");
	}

}

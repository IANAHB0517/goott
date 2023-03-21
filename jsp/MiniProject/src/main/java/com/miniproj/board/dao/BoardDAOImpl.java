package com.miniproj.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.miniproj.member.dao.DBConnection;
import com.miniproj.member.dao.MemberDAOImpl;
import com.miniproj.vodto.BoardVo;

public class BoardDAOImpl implements BoardDAO {
	private static BoardDAOImpl instance = null;

	private BoardDAOImpl() {
	}

	public static BoardDAOImpl getInstance() {
		if (instance == null) {
			instance = new BoardDAOImpl();
		}

		return instance;
	}

	@Override
	public List<BoardVo> selectEntireBoard() throws NamingException, SQLException {
		// List<BoardVo> lst = null; // 리스트를 초기화 할때는 null 로 하지 말것 null에는 아무리 add를 해봐야
		// 들어가지 않는다
		// 처음부터 ArrayList를 만들어주자
		List<BoardVo> lst = new ArrayList<>();

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "select * from board order by no desc;";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);

			while (rs.next()) {
				lst.add(new BoardVo(rs.getInt("no"), rs.getString("writer"), rs.getString("title"),
						rs.getTimestamp("postDate"), rs.getString("content"), rs.getString("imgFile"),
						rs.getInt("readcount"), rs.getInt("likecount"), rs.getInt("ref"), rs.getInt("step"),
						rs.getInt("reforder")));
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return lst;
	}

	@Override
	public int getNextRef() throws NamingException, SQLException {
		int nextRef = 0;

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "select max(no) + 1 as nextRef from board";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);

			while (rs.next()) {
				nextRef = rs.getInt("nextRef");
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return nextRef;
	}

	@Override
	public int insertBoard(BoardVo board) throws NamingException, SQLException {
		// 글 등록 -> addPoint
		int result = 0;
		
		Connection con = DBConnection.dbconnect();
		
		con.setAutoCommit(false); // 트랜잭션 시작

		if (con != null) {
			String query = "insert into board(writer, title, content, imgFile, ref) "
					+ "values (?, ?, ?, ?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getImgFile());
			pstmt.setInt(5, board.getRef());
			
			// 게시물 등록
			int writeResult = pstmt.executeUpdate();
			// 글쓴이 포인트 부여
			int pointResult = MemberDAOImpl.getInstance().addPoint(board.getWriter(), "게시판작성", con);
			
			if (writeResult == 1 && pointResult == 1) {
				con.commit();
				result = 1;
			} else {
				con.rollback();
			}
			
			con.setAutoCommit(true);
			
			DBConnection.dbClose(pstmt, con);
			
			}
		
		return result;
	}

}

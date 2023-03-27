package com.miniproj.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.miniproj.etc.PagingInfo;
import com.miniproj.member.dao.DBConnection;
import com.miniproj.member.dao.MemberDAOImpl;
import com.miniproj.vodto.BoardVo;
import com.miniproj.vodto.ReadCountProcess;
import com.miniproj.vodto.SearchCriteria;

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
			String query = "select * from board order by ref desc, reforder asc";
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
	public List<BoardVo> selectEntireBoard(PagingInfo pi) throws NamingException, SQLException {
		// List<BoardVo> lst = null; // 리스트를 초기화 할때는 null 로 하지 말것 null에는 아무리 add를 해봐야
		// 들어가지 않는다
		// 처음부터 ArrayList를 만들어주자
		List<BoardVo> lst = new ArrayList<>();

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "select * from board order by ref desc, reforder asc limit ?, ?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pi.getStartRowIndex());
			pstmt.setInt(2, pi.getViewPostCntPerPage());

			ResultSet rs = pstmt.executeQuery();

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
	public List<BoardVo> selectEntireBoard(PagingInfo pi, SearchCriteria sc) throws NamingException, SQLException {
		List<BoardVo> lst = new ArrayList<>();

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "select * from board where ";
			System.out.println(query);
			query += sc.getSearchType();
			System.out.println(query);
			query += " like ? order by ref desc, reforder asc limit ?,?";
			System.out.println(query);

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + sc.getSearchWord() + "%"); // setString으로 변환시 작은따옴표를 포함하기때문에 이렇게 해주어야한다
			pstmt.setInt(2, pi.getStartRowIndex());
			pstmt.setInt(3, pi.getViewPostCntPerPage());
			System.out.println(pstmt);

			ResultSet rs = pstmt.executeQuery();

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
			String query = "insert into board(writer, title, content, imgFile, ref) " + "values (?, ?, ?, ?,?)";
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

	@Override
	public BoardVo selectBoardByNo(int no) throws NamingException, SQLException {
		BoardVo board = null;

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "select * from board where no =?";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, no);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardVo(rs.getInt("no"), rs.getString("writer"), rs.getString("title"),
						rs.getTimestamp("postDate"), rs.getString("content"), rs.getString("imgFile"),
						rs.getInt("readcount"), rs.getInt("likecount"), rs.getInt("ref"), rs.getInt("step"),
						rs.getInt("reforder"));
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return board;
	}

	/**
	 * ? 번 ip 주소가 ? 번 읽은 적이 있는가 return 값 : (하루 이상 1, 하루 이하 0, 기록 없음 -1)
	 */
	@Override
	public int withinOneDayOrNot(String ipAddr, int boardNo) throws NamingException, SQLException {
		int result = -1;

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "select ifnull(timestampdiff( hour,"
					+ "(select readTime from readcountprocess where ipAddr =? and boardNo = ?), now()) > 24 , -1) as diff";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(1, boardNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("diff");
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return result;
	}

	// 오버로딩 + 트랜잭션 처리용
	/**
	 * @param ipAddr
	 * @param boardNo
	 * @param con
	 * @return ? 번 ip 주소가 ? 번 읽은 적이 있는가 return 값 : (하루 이상 1, 하루 이하 0, 기록 없음 -1)
	 * @throws NamingException
	 * @throws SQLException
	 */
	public int withinOneDayOrNot(String ipAddr, int boardNo, Connection con) throws NamingException, SQLException {
		int result = -1;

		if (con != null) {
			String query = "select ifNull(timestampdiff( hour,"
					+ " (select readTime from readcountprocess where ipAddr=? and boardNo=?), now()) > 24 , -1) as diff";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("diff");
			}

		}

		return result;
	}

	@Override
	public int updateReadCount(int boardNo) throws NamingException, SQLException {
		int result = 0;

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "update board set readcount = readcount + 1 where no = ?";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();

			DBConnection.dbClose(pstmt, con);

		}

		return result;
	}

	public int updateReadCount(int boardNo, Connection con) throws SQLException {
		int result = 0;

//		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "update board set readcount = readcount + 1 where no = ?";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();

//			DBConnection.dbClose(pstmt, con);

		}

		return result;

	}

	/**
	 * ip 주소, 글번호 , 읽은 시간 insert
	 */
	@Override
	public int insertReadCount(String ipAddr, int boardNo) throws NamingException, SQLException {
		int result = 0;

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "insert into readcountprocess(ipAddr, boardNo) values (?,?)";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();

			DBConnection.dbClose(pstmt, con);

		}

		return result;
	}

	/**
	 * @param ipAddr
	 * @param boardNo
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int insertReadCount(String ipAddr, int boardNo, Connection con) throws SQLException {
		int result = 0;

//		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "insert into readcountprocess(ipAddr, boardNo) values (?,?)";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();

//			DBConnection.dbClose(pstmt, con);

		}

		return result;

	}

	@Override
	public int updateReadTime(String ipAddr, int boardNo) throws NamingException, SQLException {
		int result = 0;

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "update readcountprocess set readTime = now() where ipAddr = ? and boardNo =?";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();

			DBConnection.dbClose(pstmt, con);

		}

		return result;
	}

	public int updateReadTime(String ipAddr, int boardNo, Connection con) throws SQLException {
		int result = 0;

//		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "update readcountprocess set readTime = now() where ipAddr = ? and boardNo =?";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();

//			DBConnection.dbClose(pstmt, con);

		}

		return result;
	}

	@Override
	public int transactionProcessReadCount(ReadCountProcess rcp) throws NamingException, SQLException {
		int result = -1;

		Connection con = DBConnection.dbconnect();
		con.setAutoCommit(false);

		if (con != null) {
			int oneday = withinOneDayOrNot(rcp.getIpAddr(), rcp.getBoardNo(), con);
			if (oneday == -1) {
				int insertResult = insertReadCount(rcp.getIpAddr(), rcp.getBoardNo(), con);

				int updateResult = updateReadCount(rcp.getBoardNo(), con);

				if (insertResult == 1 && updateResult == 1) {
					con.commit();
				} else {
					con.rollback();
				}
			} else if (oneday == 1) {
				int updateResult1 = updateReadTime(rcp.getIpAddr(), rcp.getBoardNo(), con);
				int updateResult2 = updateReadCount(rcp.getBoardNo(), con);

				if (updateResult1 == 1 && updateResult2 == 1) {
					con.commit();
				} else {
					con.rollback();
				}
			} else {
				result = 1;
			}
		}

		con.setAutoCommit(true);
		DBConnection.dbClose(con);

		return result;
	}

	@Override
	public int deleteBoard(int boardNo) throws NamingException, SQLException {
		int result = 0;

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "delete from board where no = ?;";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();

			DBConnection.dbClose(pstmt, con);

		}

		return result;
	}

	@Override
	public int modiBoard(int boardNo) throws NamingException, SQLException {
		// 로직 부터 다시 시작하기
		return 0;
	}

	@Override
	public int updateReplyPost(BoardVo reply, Connection con) throws NamingException, SQLException {
		int result = 0;

		if (con != null) {
			String query = "update board set reforder = reforder + 1" + " where ref = ? and reforder > ?";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, reply.getRef());
			pstmt.setInt(2, reply.getReforder());

			result = pstmt.executeUpdate();

		}

		return result;
	}

	@Override
	public int insertReplyPost(BoardVo reply, Connection con) throws NamingException, SQLException {
		int result = -1;

		if (con != null) {
			String query = "insert into board (writer, title, content, ref, step, reforder)" + " values (?,?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, reply.getWriter());
			pstmt.setString(2, reply.getTitle());
			pstmt.setString(3, reply.getContent());

			pstmt.setInt(4, reply.getRef());
			pstmt.setInt(5, reply.getStep() + 1);
			pstmt.setInt(6, reply.getReforder() + 1);

			result = pstmt.executeUpdate();
		}

		return result;
	}

	@Override
	public int transactionProcessForReplyPost(BoardVo reply) throws NamingException, SQLException {
		int result = -1;
		Connection con = DBConnection.dbconnect();

		con.setAutoCommit(false);
		if (con != null) {
			int updateResult = updateReplyPost(reply, con);

			if (updateResult >= 0) {
				int insertResult = insertReplyPost(reply, con);

				if (insertResult == 1) {
					int PointResult = MemberDAOImpl.getInstance().addPoint(reply.getWriter(), "답글쓰기", con);

					if (PointResult == 1) {
						con.commit();
						result = 1;
					} else {
						con.rollback(); // 포인트가 제대로 안들어가면 insert한 답글이 롤백 되어야 한다.
					}
				}
			}
		}
		con.setAutoCommit(true);
		DBConnection.dbClose(con);

		return result;
	}

	@Override
	public ArrayList<BoardVo> getTop3() throws NamingException, SQLException {
		ArrayList<BoardVo> lst = new ArrayList<>();

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "select * from board order by readcount desc limit 3";
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
	public int getTotalPostCnt(String tableName) throws NamingException, SQLException {
		int result = -1;
		// ArrayList<BoardVo> lst = new ArrayList<>();

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "select count(*) as cnt from " + tableName;
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("cnt");
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return result;
	}

	@Override
	public int getTotalPostCnt(String tableName, SearchCriteria sc) throws NamingException, SQLException {
		int result = -1;
		// ArrayList<BoardVo> lst = new ArrayList<>();

		Connection con = DBConnection.dbconnect();

		if (con != null) {
			String query = "select count(*) as cnt from " + tableName + " where " + sc.getSearchType() + " like ?" ;
			System.out.println(query);

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, "%" + sc.getSearchWord() + "%"); // setString으로 변환시 작은따옴표를 포함하기때문에 이렇게 해주어야한다

			System.out.println(pstmt);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("cnt");
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return result;
	}
	
}

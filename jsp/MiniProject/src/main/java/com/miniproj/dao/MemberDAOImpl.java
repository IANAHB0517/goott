package com.miniproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.miniproj.vodto.LoginDTO;
import com.miniproj.vodto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {
	private static MemberDAOImpl instance = null;

	private MemberDAOImpl() {
	}

	public static MemberDAOImpl getInstance() {
		if (instance == null) {
			instance = new MemberDAOImpl();
		}
		return instance;
	}

	@Override
	public int insertMember(MemberDTO dto) throws NamingException, SQLException {
		int result =0;
		
		Connection con = DBConnection.dbconnect();
		if (con != null) {
			String sql = "";
			PreparedStatement pstmt = null;
			if (!dto.getUserImg().equals("")) { // 업로드 파일이 있는 경우
				sql = "insert into member " + "values(?,sha1(md5(?)),?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getUserId());
				pstmt.setString(2, dto.getUserPwd());
				pstmt.setString(3, dto.getUserEmail());
				pstmt.setString(4, dto.getUserMobile());
				pstmt.setString(5, dto.getUserGender());
				pstmt.setString(6, dto.getHobbies());
				pstmt.setString(7, dto.getJob());
				pstmt.setString(8, dto.getUserImg());
				pstmt.setString(9, dto.getMemo());

			} else { // 업로드 파일이 없는 경우
				sql = "insert into member(userId, userPwd, userEmail, userMobile,  userGender, "
						+ " hobbies, job, memo) " + "values(?,sha1(md5(?)),?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getUserId());
				pstmt.setString(2, dto.getUserPwd());
				pstmt.setString(3, dto.getUserEmail());
				pstmt.setString(4, dto.getUserMobile());
				pstmt.setString(5, dto.getUserGender());
				pstmt.setString(6, dto.getHobbies());
				pstmt.setString(7, dto.getJob());
				pstmt.setString(8, dto.getMemo());
			}
			
			con.setAutoCommit(false); // 테이블이 두개의 쿼리문중 하나라도 안될 경우 커밋이 되는 것을 방지 하기 위함
									  // 오토 커밋이 되지 않았기 때문에 트랜잭션 처리가 끝나지 않았기 때문에 다음 트랜잭션인
									  // 포인트 테이블에 인서트가 되지 않는다.
			
			
			result = pstmt.executeUpdate();
			
			// 회원가입 과 포인트 부여가 트랜잭션 처리가 되어야 한다
			
		
			if (result == 1) {
				int result2 = addPoint(dto.getUserId(), "회원가입", con);
				
				if (result2 == 1) {
					con.commit(); // 두가지 쿼리문이 모두 잘 수행될 경우 테이블을 저장
				} else {
					con.rollback(); // 둘중 한 쿼리문이라도 수행 되지 않을 경우 테이블 롤백
				}
			}
			
			con.setAutoCommit(true);
			DBConnection.dbClose(pstmt, con);
			
		}
		System.out.println(result + " pstmt 실행결과");
		
		return result;
	}

	@Override
	public int selectByUserId(String userId) throws NamingException, SQLException {
		int result = 0;

		Connection con = DBConnection.dbconnect();
		if (con != null) {
			String query = "select count(*) as userCnt from member where userId = ?";

			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) { // 어차피 0,1 인 인트를 반환하는데 왜 while을 사용하는 이유가 있을까?
				result = rs.getInt("userCnt");
			}
			;

			DBConnection.dbClose(rs, pstmt, con);

		}

		return result;
	}

	@Override
	public MemberDTO loginUser(LoginDTO dto) throws NamingException, SQLException {
		MemberDTO member = null;

		Connection con = DBConnection.dbconnect();
		if (con != null) {
			String query = "select * from member where userId = ? and userPwd = sha1(md5(?))";

			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getPwd());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) { // 어차피 0,1 인 인트를 반환하는데 왜 while을 사용하는 이유가 있을까?
				member = new MemberDTO(rs.getString("userId"), rs.getString("userPwd"), rs.getString("userEmail"),
						rs.getString("userMobile"), rs.getString("userGender"), rs.getString("hobbies"),
						rs.getString("job"), rs.getString("userImg"), rs.getString("memo"));
			}
			;

			DBConnection.dbClose(rs, pstmt, con);

		}

		return member;
	}

	@Override
	public int addPoint(String userId, String why, Connection con) throws NamingException, SQLException {
		// 종속적 모든 경우에서 Connection 객체가 있어야만 메서드가 실행된다.
		System.out.println("포인트 부여");
		int result = 0;
		//Connection con = DBConnection.dbconnect(); // member 테이블에 insert하는 객체가 이미 열려 있기 때문에 
		// 하나의 객체를 통해 모든 작업을 수행 해야 한다.
		if (con != null) {
			String query = "insert into memberpoint (who, why, howmuch) "
					+ "values (?, ?, (select howmuch from pointpolicy where why =?)) ";
			// addPoint() 내에서만 오류가 나도 member 테이블과 memberPoint 테이블 두 곳 모두에 데이터가 들어가지 않도록
			// 해야한다.

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, why);
			pstmt.setString(3, why);

			result = pstmt.executeUpdate();

			System.out.println("포인트 부여결과 : " + result);
			
			// DBConnection.dbClose(pstmt, con);

		}

		return result;
	}

}

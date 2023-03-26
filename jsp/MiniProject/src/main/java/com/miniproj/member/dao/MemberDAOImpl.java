package com.miniproj.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.miniproj.etc.PagingInfo;
import com.miniproj.vodto.LoginDTO;
import com.miniproj.vodto.MemberDTO;
import com.miniproj.vodto.MemberPointVo;
import com.mysql.cj.xdevapi.Result;

/**
 * @author goott
 *
 */
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

	/**
	 * 실제 DB에 입력 받은 값들로 회원을 삽입 그 때마다 새로운 회원에게 회원 가입 포인트 부여
	 */
	@Override
	public int insertMember(MemberDTO dto) throws NamingException, SQLException {
		int result = 0;

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

	/**
	 * userId로 회원 검색
	 */
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

	/**
	 * 아이디와 비밀번호로 로그인
	 */
	@Override
	public MemberDTO loginUser(LoginDTO dto, Connection con) throws NamingException, SQLException {
		MemberDTO member = null;

//		Connection con = DBConnection.dbconnect();
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
						rs.getString("job"), rs.getString("userImg"), rs.getString("memo"), rs.getString("isAdmin"));
			}
			;

//			DBConnection.dbClose(rs, pstmt, con);

		}

		return member;
	}

	/**
	 * 회원 가입, 로그인, 게시글 작성 등 이벤트 발생시 종속적으로 포인트를 부여하는 메서드
	 */
	@Override
	public int addPoint(String userId, String why, Connection con) throws NamingException, SQLException {
		// 종속적 모든 경우에서 Connection 객체가 있어야만 메서드가 실행된다.
		System.out.println("포인트 부여");
		int result = 0;
		// Connection con = DBConnection.dbconnect(); // member 테이블에 insert하는 객체가 이미 열려
		// 있기 때문에
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

	/**
	 * result = -1 (null : 처음 로그인) -> insert
	 * result = 0 (같은 날짜에 로그인한 기록이 있는 유저) -> update
	 * result > 1 (최근 로그인 한 날짜가 오늘이 아닌 유저) -> update
	 */
	@Override
	public int whenLatestLogin(String userId, Connection con) throws NamingException, SQLException {
		// 받아온 컬럼의 값이 null 일 경우에 while 문에 걸리지 않기때문에 초기 값을 설정해줌
		int result = -1;

		if (con != null) {
			String query = "select ifnull(a.diff, -1) as datediff from"
					+ "(select datediff(now(), (select latestlogindate from latestloginlog where who= ?)) as diff) a";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

//			if(rs.wasNull()) {
//				System.out.println("널이다");
//			}
			System.out.println("ResultSet : " + rs.toString());
			
			while (rs.next()) {
				int tmp = rs.getInt("datediff");
				if (tmp == 0) {
					result = 0;
				} else if (tmp > 0) {
					result = 1;
				}

			}
			
			System.out.println("whenLatestLogin : " + result);
		}

		return result;
	}

	@Override
	public int writtenLoginDate(int mode, String userId, Connection con) throws NamingException, SQLException {
		int result = 0;
		
		if (con != null) {
			String sql = "";
			PreparedStatement pstmt = null;
			if (mode == -1) {
				sql = "insert into latestloginlog(who) values (?)";
			} else {
				sql = "update latestloginlog set latestlogindate = now() where who=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
		}
		return result;
	}

	@Override
	public MemberDTO loginWithTransaction(LoginDTO dto) throws NamingException, SQLException {
		MemberDTO loginUser = null;
		
		Connection con = DBConnection.dbconnect();
		con.setAutoCommit(false); // 트랜잭션 시작
		
		if(con != null) {
			MemberDTO loginMember = loginUser(dto, con);
			int addPointresult = -1;
			if (loginMember != null) { // 로그인 성공
				System.out.println("트랜잭션 메서드의 로그인 한 유저 : " + loginMember.toString());
				//String userId = loginMember.getUserId();
				System.out.println("로그인 유저아이디 : " + loginMember.getUserId());
				
				int loginDate = whenLatestLogin(loginMember.getUserId(), con);
				System.out.println("로그인 상태 : " + loginDate);
				
				if (loginDate > 0 || loginDate == -1) {
					addPointresult = addPoint(loginMember.getUserId(), "로그인", con); // 포인트 부여
					System.out.println("포인트 부여 : " + addPointresult);
				}
				
				int writtenLoginDateResult = writtenLoginDate(loginDate, loginMember.getUserId(), con); // 
				System.out.println("로그인 기록 : " + writtenLoginDateResult);
				
				if (addPointresult == 1 && writtenLoginDateResult == 1) { // 포인트 지급및 로그인 기록 완료
					loginUser = loginMember;
					con.commit();
				} else if (addPointresult == -1 && writtenLoginDateResult == 1){
					loginUser = loginMember;
					con.commit();
					
				} else {
					con.rollback();
				}
			}
			
			// 트랜잭션 처리
			con.setAutoCommit(true);
			con.close();
			
		}
		
		return loginUser;
	}

	@Override
	public MemberDTO getMemberInfo(String userId) throws NamingException, SQLException {
		MemberDTO memberInfo = null;
		
		Connection con =DBConnection.dbconnect();
		
		if (con != null) {
			String query ="select * from member where userId = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberInfo = new MemberDTO(rs.getString("userId"), rs.getString("userPwd"), rs.getString("userEmail"),
						rs.getString("userMobile"), rs.getString("userGender"), rs.getString("hobbies"),
						rs.getString("job"), rs.getString("userImg"), rs.getString("memo"), rs.getString("isAdmin"));
			}
			DBConnection.dbClose(rs, pstmt, con);
		}
		return memberInfo;
	}

	@Override
	public List<MemberPointVo> getMemberPoint(String userId) throws NamingException, SQLException {
		List<MemberPointVo> lst =new ArrayList<>();
		MemberPointVo mpv = null;

		Connection con =DBConnection.dbconnect();
		
		if (con != null) {
			String query ="select * from memberpoint where who = ? order by no desc";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lst.add (new MemberPointVo(rs.getInt("no"),
											rs.getString("who"),
											rs.getTimestamp("when"),
											rs.getString("why"),
											rs.getInt("howmuch")));
			}
			DBConnection.dbClose(rs, pstmt, con);
		}
		
		return lst;
	}
	
	
	public List<MemberPointVo> getMemberPoint(String userId, PagingInfo pi) throws NamingException, SQLException {
		List<MemberPointVo> lst =new ArrayList<>();
		MemberPointVo mpv = null;

		Connection con =DBConnection.dbconnect();
		
		if (con != null) {
			String query ="select * from memberpoint where who = ?  order by no desc limit ?, ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, pi.getStartRowIndex());
			pstmt.setInt(3, pi.getViewPostCntPerPage());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lst.add (new MemberPointVo(rs.getInt("no"),
											rs.getString("who"),
											rs.getTimestamp("when"),
											rs.getString("why"),
											rs.getInt("howmuch")));
			}
			DBConnection.dbClose(rs, pstmt, con);
		}
		
		return lst;
	}

}

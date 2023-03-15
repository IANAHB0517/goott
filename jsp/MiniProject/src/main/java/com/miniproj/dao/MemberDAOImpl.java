package com.miniproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

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
			
			result = pstmt.executeUpdate();
			con.close();
			
		}
		System.out.println(result + "pstmt 실행결과");
		
		return result;
	}

}

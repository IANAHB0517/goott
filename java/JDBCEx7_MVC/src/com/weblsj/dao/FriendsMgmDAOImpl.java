package com.weblsj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weblsj.dto.FriendDTO;
import com.weblsj.vo.Friend;

public class FriendsMgmDAOImpl implements FriendsMgmDAO {

	private static FriendsMgmDAOImpl instance = null;

	private FriendsMgmDAOImpl() {
	}

	public static FriendsMgmDAOImpl getInstance() {
		if (instance == null) {
			instance = new FriendsMgmDAOImpl();
		}
		return instance;
	}

	// 여기까지가 싱글톤 특유의 패턴
	
	
	@Override
	public List<Friend> selectAllFriends() throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
//		System.out.println(con.toString()); // 연결이 잘 되었나 확인

		List<Friend> list = new ArrayList<>();

		if (con != null) {
			String query = "select * from friends";

			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Friend(rs.getInt("FRIENDNO"), rs.getString("FRIENDNAME"),
						rs.getString("MOBILE"), rs.getString("ADDR")));

			}

//			System.out.println(list.size()); // db를 커밋하지 않아서 안나왔었음

//			for (Friend f : list) {
//				System.out.println(f.toString());
//			} // 디버깅용으로 확인 함

			DBConnection.close(rs, pstmt, con);
		}

		return list;
	}

	@Override
	public int insertFriend(int friendNo, FriendDTO dto) throws Exception {
		int result = 0;

		Connection con = DBConnection.getConnection();

		String query = "insert into friends values(?,?,?,?)";

		PreparedStatement pstmt = null;

		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, friendNo);
		pstmt.setString(2, dto.getFriendName());
		pstmt.setString(3, dto.getMobile());
		pstmt.setString(4, dto.getAddr());

		result = pstmt.executeUpdate();
		
		DBConnection.close(pstmt, con);
		
		return result;
	}

	@Override
	public int getNextFriendNo() throws Exception {
		Connection con = DBConnection.getConnection();

		int result = 0;

		if (con != null) {
			String query = "select (max(friendNo) ) as friendNo  from friends";

			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("friendNo") + 1;
			}

			DBConnection.close(rs, pstmt, con);

		}
		return result;

	}

}

package com.weblsj.dao;

import java.util.List;

import com.weblsj.dto.FriendDTO;
import com.weblsj.vo.Friend;

public interface FriendsMgmDAO  {
	// 전체 친구 목록조회
	List<Friend> selectAllFriends() throws Exception;

	// 친구 저장
	int insertFriend(int friendNo , FriendDTO dto) throws Exception;
	
	// 친구번호 중 가장 높은 번호를 가져옴
	int getNextFriendNo() throws Exception;
	
}

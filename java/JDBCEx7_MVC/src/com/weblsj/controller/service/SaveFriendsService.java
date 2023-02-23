package com.weblsj.controller.service;

import com.weblsj.dao.FriendsMgmDAO;
import com.weblsj.dao.FriendsMgmDAOImpl;
import com.weblsj.dto.FriendDTO;
import com.weblsj.view.FriendDBInsert;

public class SaveFriendsService implements FriendManagementService {

	@Override
	public void toDo() throws Exception {
		System.out.println("친구 입력");
		
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();

		// 다음에 저장될 친구 번호를 가져와야함
		int friendNo = dao.getNextFriendNo();
		
		// 입력될 친구 데이터 가져옴
		FriendDTO friend = FriendDBInsert.getFriendData();
		
		// 친구저장
		if(dao.insertFriend(friendNo, friend) == 1) {
			System.out.println("친구 저장 완료");
		};
	}

}

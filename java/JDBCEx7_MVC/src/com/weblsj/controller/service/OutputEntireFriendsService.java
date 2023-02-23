package com.weblsj.controller.service;

import java.util.List;

import com.weblsj.dao.FriendsMgmDAO;
import com.weblsj.dao.FriendsMgmDAOImpl;
import com.weblsj.vo.Friend;

public class OutputEntireFriendsService implements FriendManagementService {

	@Override
	public void toDo() throws Exception {
		System.out.println("전체 친구 조회");

		FriendsMgmDAO fmd = FriendsMgmDAOImpl.getInstance();
		List<Friend> lst = fmd.selectAllFriends();
		
		for (Friend f : lst) {
			System.out.println(f.toString());
		} 
	}

}

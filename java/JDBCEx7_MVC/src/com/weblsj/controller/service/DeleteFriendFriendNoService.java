package com.weblsj.controller.service;

import java.util.List;

import com.weblsj.dao.FriendsMgmDAO;
import com.weblsj.dao.FriendsMgmDAOImpl;
import com.weblsj.view.FriendDBSearch;
import com.weblsj.vo.Friend;

public class DeleteFriendFriendNoService implements FriendManagementService {

	@Override
	public void toDo() throws Exception {

		// 먼저 친구들을 보여주기
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		List<Friend> list = dao.selectAllFriends();
		for (Friend f : list) {
			System.out.println(f.toString());
		}

		// 삭제할 친구 번호 선택

		int delNo = 0;
		int isValid = 0;
		do {
			delNo = FriendDBSearch.getDeletNo();
			isValid = dao.isValidFriend(delNo);
		} while (isValid == 0);

		int result = dao.deleteFriendByFriendNo(delNo);

		if (result == 1) {
			System.out.println("친구가 삭제 되었습니다.");
		}
		list = dao.selectAllFriends();
		for (Friend f : list) {
			System.out.println(f.toString());
		}

	}

}

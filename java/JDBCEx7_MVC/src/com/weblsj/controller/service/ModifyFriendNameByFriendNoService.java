package com.weblsj.controller.service;

import java.util.List;

import com.weblsj.dao.FriendsMgmDAO;
import com.weblsj.dao.FriendsMgmDAOImpl;
import com.weblsj.dto.ModifyFriendNameByNoDTO;
import com.weblsj.view.FriendDBInsert;
import com.weblsj.vo.Friend;

public class ModifyFriendNameByFriendNoService implements FriendManagementService {

	@Override
	public void toDo() throws Exception {
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		List<Friend> lst = dao.selectAllFriends();

		for (Friend f : lst) {
			System.out.println(f.toString());
		}
		
		ModifyFriendNameByNoDTO dto = null;
		int isValid = 0;

		do {
			dto = FriendDBInsert.getModifyNameData(); // 수정될 친구 데이터			
		} while(isValid == 0);

		int result = dao.updateFriendName(dto);

		if (result == 1) {
			System.out.println("친구 수정 완료");
		}
	}

}

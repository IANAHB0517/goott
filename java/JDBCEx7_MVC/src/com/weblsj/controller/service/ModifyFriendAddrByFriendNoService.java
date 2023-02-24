package com.weblsj.controller.service;

import java.util.List;

import com.weblsj.dao.FriendsMgmDAO;
import com.weblsj.dao.FriendsMgmDAOImpl;
import com.weblsj.dto.ModifyFriendAddrByFriendNoDTO;
import com.weblsj.view.FriendDBInsert;
import com.weblsj.vo.Friend;

public class ModifyFriendAddrByFriendNoService implements FriendManagementService {

	@Override
	public void toDo() throws Exception {
		System.out.println("친구 집주소 변경 서비스");

		// 먼저 친구들을 보여주기
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		List<Friend> list = dao.selectAllFriends();
		for (Friend f : list) {
			System.out.println(f.toString());
		}

		// 변경할 친구 객체 생성

		ModifyFriendAddrByFriendNoDTO dto = null;

		dto = FriendDBInsert.getModifyAddr();

		// dao 단에 dto 전달

		dao.updateFriendAddr(dto);

		list = dao.selectAllFriends();

		System.out.println("집 주소 변경 완료");

		for (Friend f : list) {
			System.out.println(f.toString());
		}

	}

}

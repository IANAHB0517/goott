package com.weblsj.controller.service;

import java.util.List;

import com.weblsj.dao.FriendsMgmDAO;
import com.weblsj.dao.FriendsMgmDAOImpl;
import com.weblsj.dto.ModifyFriendMobileByFriendNoDTO;
import com.weblsj.view.FriendDBInsert;
import com.weblsj.vo.Friend;

public class ModifyFriendMobileByFriendNoService implements FriendManagementService {

	@Override
	public void toDo() throws Exception {
		System.out.println("번호 변경 서비스");
		// 먼저 친구들을 보여주기
		// dao 객체 생성
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		List<Friend> list = dao.selectAllFriends();
		for (Friend f : list) {
			System.out.println(f.toString());
		}

		// view단 에서 전화번호를 바꾸고자 하는 친구 선택 및 전화번호 입력
		ModifyFriendMobileByFriendNoDTO dto = null;
		int checkMobDup = 0;
		do {
			dto = FriendDBInsert.getModifyMobile();

			// 중복검사
			checkMobDup = dao.isDuplicateMobile(dto.getMobile());
		} while (checkMobDup == 1);
		// dao 단에서 전화번호 변경 메서드 호출
		dao.updateFriendMobile(dto);

		list = dao.selectAllFriends();

		System.out.println("전화 번호 변경 완료");

		for (Friend f : list) {
			System.out.println(f.toString());
		}

	}

}

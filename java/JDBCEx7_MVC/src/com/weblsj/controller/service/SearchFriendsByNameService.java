package com.weblsj.controller.service;

import java.util.List;

import com.weblsj.dao.FriendsMgmDAO;
import com.weblsj.dao.FriendsMgmDAOImpl;
import com.weblsj.view.FriendDBSearch;
import com.weblsj.vo.Friend;

public class SearchFriendsByNameService implements FriendManagementService {

	@Override
	public void toDo() throws Exception {
		// 입력받은 이름을 dao단으로 보내는 곳
		String fname = null;

		fname = FriendDBSearch.getSearchName();

		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();

		List<Friend> lst = dao.searchFriendsByName(fname);

		for (Friend f : lst) {
			System.out.println(f.toString());

		}

	}

}

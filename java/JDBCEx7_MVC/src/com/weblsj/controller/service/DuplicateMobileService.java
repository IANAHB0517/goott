package com.weblsj.controller.service;

import com.weblsj.dao.FriendsMgmDAO;
import com.weblsj.dao.FriendsMgmDAOImpl;

public class DuplicateMobileService {
	private static DuplicateMobileService instance = null;

	private DuplicateMobileService() {
	}

	public static DuplicateMobileService getInstance() {
		if (instance == null) {
			instance = new DuplicateMobileService();
		}

		return instance;
	}

	/**
	 * @Method_Name : duplicateMobileService,
	 * @작성일 : 2023. 2. 24.,
	 * @작성자 : goott,
	 * @매개변수 : mobile,
	 * @반환값 : boolean
	 * @Description : 중복이 되면 (true), 중복이 안되면 (false) 반환
	 */
	public boolean duplicateMobileService(String mobile) throws Exception {
		boolean result = false;
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();

		if (dao.isDuplicateMobile(mobile) == 1) { // 중복이 있다.
			result = true;
		}

		return result;
	}

}

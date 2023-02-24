package com.weblsj.controller;

import com.weblsj.controller.service.DeleteFriendFriendNoService;
import com.weblsj.controller.service.FriendManagementService;
import com.weblsj.controller.service.ModifyFriendAddrByFriendNoService;
import com.weblsj.controller.service.ModifyFriendMobileByFriendNoService;
import com.weblsj.controller.service.ModifyFriendNameByFriendNoService;
import com.weblsj.controller.service.OutputEntireFriendsService;
import com.weblsj.controller.service.SaveFriendsService;
import com.weblsj.controller.service.SearchFriendsByNameService;

public class Action { // 컨트롤러의 객체는 싱글톤으로 만들어져야 한다.
	private static Action instance = null;
	// Action 타입의 private static 변수인 instance를 선언하고, 초기값으로 null을 할당
	
	// 프라이빗 생성자를 생략하면 public한 생성자가 있는 것으로 간주되어 객체가 여러개 만들어 진다.
	// ** 중요하다
	private Action() {
		
	}
	
	public static Action getInstance() {
		if (instance == null) {
			instance = new Action();
		}
		return instance;
	}
	
	public FriendManagementService getService(int menu) {
		
		FriendManagementService result = null;
		
		switch (menu) {
		case 1:
			result = new OutputEntireFriendsService(); 
			//OutputEntireFriendsService 객체의 기본 생성자 호출
			break;
		case 2:
			result = new SaveFriendsService();
			break;
		case 3:
			result = new SearchFriendsByNameService();
			break;
		case 4:
			result = new ModifyFriendNameByFriendNoService();
			break;
		case 5:
			result = new ModifyFriendMobileByFriendNoService();
			break;
		case 6:
			result = new ModifyFriendAddrByFriendNoService();
			break;
		case 7:
			result = new DeleteFriendFriendNoService();
			break;
			
		case 9:
			System.exit(0);
			
		}
		
		return result;
	}
	
}

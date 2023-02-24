package com.weblsj.view;

import java.util.Scanner;

import com.weblsj.controller.service.DuplicateMobileService;
import com.weblsj.controller.service.ModifyFriendMobileByFriendNoService;
import com.weblsj.dto.FriendDTO;
import com.weblsj.dto.ModifyFriendAddrByFriendNoDTO;
import com.weblsj.dto.ModifyFriendMobileByFriendNoDTO;
import com.weblsj.dto.ModifyFriendNameByNoDTO;

public class FriendDBInsert {

	public static FriendDTO getFriendData() {
		FriendDTO friend = null;

		Scanner sc = new Scanner(System.in);

		String name = "";
		do {
			System.out.print("친구 이름 >>> ");
			name = sc.nextLine();
		} while (name.equals(""));

		String mobile;
		boolean result = false;
		do {
			System.out.print("친구 전화번호 >>> ");
			mobile = sc.nextLine();
			try {
				result = DuplicateMobileService.getInstance().duplicateMobileService(mobile);
			} catch (Exception e) {
				System.out.println("전화번호 입력시 에러");
				e.printStackTrace();
			}
		} while (result);
		if (result) {
			mobile = "";
		}

		System.out.print("친구 주소 >>> ");
		String addr = sc.nextLine();

		friend = new FriendDTO(name, mobile, addr);

		return friend;
	}

	public static ModifyFriendNameByNoDTO getModifyNameData() {

		// 키보드로 부터 수정될 친구 번호 입력
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		
		System.out.println("이름을 수정할 친구 번호 >>> ");
		int fNo = scInt.nextInt();
		
		System.out.print("수정될 친구 이름 >>>> ");
		String fName = scString.nextLine();

		return new ModifyFriendNameByNoDTO(fNo, fName);
	}

	public static ModifyFriendMobileByFriendNoDTO getModifyMobile() {
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		
		System.out.println("전화번호를 수정할 친구 번호 >>> ");
		int fNo = scInt.nextInt();
		
		System.out.print("수정될 친구 전화번호 >>>> ");
		String fMob = scString.nextLine();

		return new ModifyFriendMobileByFriendNoDTO(fNo, fMob);
	}

	public static ModifyFriendAddrByFriendNoDTO getModifyAddr() {
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		
		System.out.println("주소를 수정할 친구 번호 >>> ");
		int fNo = scInt.nextInt();
		
		System.out.print("수정될 친구 주소 >>>> ");
		String fAddr = scString.nextLine();

		return new ModifyFriendAddrByFriendNoDTO(fNo, fAddr);
		
	}
}

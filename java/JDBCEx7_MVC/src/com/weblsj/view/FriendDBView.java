package com.weblsj.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.weblsj.controller.Action;
import com.weblsj.controller.service.FriendManagementService;

public class FriendDBView {

	private static void outputMenu() {
		System.out.println();		System.out.println();	System.out.println();
		System.out.println("***************************************************************");
		System.out.println("**************************친구 관리 V1*************************");
		System.out.println("***************************************************************");
		System.out.println("**** 1. 친구 조회(전체 조회)                               ****");
		System.out.println("**** 2. 친구 입력                                          ****");
		System.out.println("**** 3. 친구 조회 (이름으로 조회)                          ****");
		System.out.println("**** 4. 친구 수정(이름 수정)                               ****");
		System.out.println("**** 5. 친구 수정(전화번호 수정)                           ****");
		System.out.println("**** 6. 친구 수정(주소     수정)                           ****");
		System.out.println("**** 7. 친구 삭제                                          ****");
		System.out.println("**** 9. 종료                                               ****");
		System.out.println("***************************************************************");
		System.out.print("메뉴 번호 입력 >>> ");
	}

	public static void main(String[] args) {
		while (true) {
			outputMenu();
			Scanner sc = new Scanner(System.in);
			int menu = sc.nextInt();

//			for (int i = 0; i < 5; i++) {
//				System.out.println(Action.getInstance().hashCode());
//			} // 싱글톤이 잘 만들어 졌는지 검사
			
//			Action.getInstance().getService(menu);
			
			FriendManagementService fms = Action.getInstance().getService(menu);			
			if (fms != null) {
				
				try {
					fms.toDo();
				} catch (Exception e) {
					if( e instanceof ClassNotFoundException) {
						System.out.println("드라이버가 없습니다");
					}
					
					if( e instanceof SQLException) {
					System.out.println("SQL Exception");
					}
					
					e.printStackTrace();
				}
			}
			
			

		}

	}

}

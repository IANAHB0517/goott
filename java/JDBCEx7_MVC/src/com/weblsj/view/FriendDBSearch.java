package com.weblsj.view;

import java.util.Scanner;

public class FriendDBSearch {
//	친구 이름을 입력 받는 곳
	public static String getSearchName() {
		String fname = null;

		System.out.println("찾으려는 친구 이름을 입력하세요 >>>");
		Scanner sc = new Scanner(System.in);
		fname = sc.nextLine();

		return fname;
	}

	public static int getDeletNo() {
		int deleteNo = 0;

		System.out.println("삭제하려는 친구 번호를 입력하세요 >>>");
		Scanner sc = new Scanner(System.in);
		deleteNo = sc.nextInt();

		return deleteNo;

	}

}

package com.weblsj.dto;

public class ModifyFriendMobileByFriendNoDTO {
	private int friendNo;
	private String mobile;

	public ModifyFriendMobileByFriendNoDTO(int friendNo, String mobile) {
		super();
		this.friendNo = friendNo;
		this.mobile = mobile;
	}

	public int getFriendNo() {
		return friendNo;
	}

	public String getMobile() {
		return mobile;
	}

	@Override
	public String toString() {
		return "ModifyFriendMobileByFriendNoDTO [friendNo=" + friendNo + ", mobile=" + mobile + "]";
	}

}

package com.weblsj.dto;

public class ModifyFriendNameByNoDTO {
	private int friendNo;
	private String friendName;
	
	public ModifyFriendNameByNoDTO(int friendNo, String friendName) {
		super();
		this.friendNo = friendNo;
		this.friendName = friendName;
	}

	public int getFriendNo() {
		return friendNo;
	}

	public String getFriendName() {
		return friendName;
	}

	@Override
	public String toString() {
		return "ModifyFriendNameByNoDTO [friendNo=" + friendNo + ", friendName=" + friendName + "]";
	}
	
	
	
	
}

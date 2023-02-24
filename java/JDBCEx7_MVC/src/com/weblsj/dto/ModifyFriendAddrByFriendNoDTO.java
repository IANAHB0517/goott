package com.weblsj.dto;

public class ModifyFriendAddrByFriendNoDTO {
	private int friendNo;
	private String addr;

	public ModifyFriendAddrByFriendNoDTO(int friendNo, String addr) {
		super();
		this.friendNo = friendNo;
		this.addr = addr;
	}

	public int getFriendNo() {
		return friendNo;
	}

	public String getAddr() {
		return addr;
	}

	@Override
	public String toString() {
		return "ModifyFriendAddrByFriendNoDTO [friendNo=" + friendNo + ", addr=" + addr + "]";
	}

}

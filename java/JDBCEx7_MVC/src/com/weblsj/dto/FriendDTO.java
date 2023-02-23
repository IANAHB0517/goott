package com.weblsj.dto;

public class FriendDTO {

	private String FriendName;
	private String mobile;
	private String addr;

	public FriendDTO(String friendName, String mobile, String addr) {
		super();
		FriendName = friendName;
		this.mobile = mobile;
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "FriendDTO [FriendName=" + FriendName + ", mobile=" + mobile + ", addr=" + addr + "]";
	}

	public String getFriendName() {
		return FriendName;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddr() {
		return addr;
	}
	
	
	

}

package com.weblsj.vo;

public class Friend {
	private int freiendNo;
	private String friendName;
	private String mobile;
	private String addr;
	
	public Friend(int freiendNo, String friendName, String mobile, String addr) {
		super();
		this.freiendNo = freiendNo;
		this.friendName = friendName;
		this.mobile = mobile;
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Friend [freiendNo=" + freiendNo + ", friendName=" + friendName + ", mobile=" + mobile + ", addr=" + addr
				+ "]";
	}
	
	
	
}

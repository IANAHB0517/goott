package com.springbasic.domain;

public class MemberVO {
	private String userId;
	private String userPwd;
	private String userEmail;
	private String userMobile;
	private String userGender;
	private String hobbies;
	private String job;
	private String userImg;
	private String memo;
	private String isAdmin;

	public MemberVO(String userId, String userPwd, String userEmail, String userMobile, String userGender,
			String hobbies, String job, String userImg, String memo, String isAdmin) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
		this.userMobile = userMobile;
		this.userGender = userGender;
		this.hobbies = hobbies;
		this.job = job;
		this.userImg = userImg;
		this.memo = memo;
		this.isAdmin = isAdmin;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public String getUserGender() {
		return userGender;
	}

	public String getHobbies() {
		return hobbies;
	}

	public String getJob() {
		return job;
	}

	public String getUserImg() {
		return userImg;
	}

	public String getMemo() {
		return memo;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", userPwd=" + userPwd + ", userEmail=" + userEmail + ", userMobile="
				+ userMobile + ", userGender=" + userGender + ", hobbies=" + hobbies + ", job=" + job + ", userImg="
				+ userImg + ", memo=" + memo + ", isAdmin=" + isAdmin + "]";
	}

}
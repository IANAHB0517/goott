package com.springproj.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberVo {
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
}

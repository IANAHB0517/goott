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

public class LoginDTO {
	private String userId;
	private String userPwd;
	private boolean remember; // 자동 로그인 checkbox
}

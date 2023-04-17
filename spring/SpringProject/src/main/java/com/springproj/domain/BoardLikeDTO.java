package com.springproj.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardLikeDTO {
	private int likeNo;
	private String who;
	private int boardNo;
	private boolean isLike;
}

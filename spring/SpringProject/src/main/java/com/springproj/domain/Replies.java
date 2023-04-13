package com.springproj.domain;

import java.sql.Timestamp;

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
public class Replies {
	private int replyNo;
	private int boardNo;
	private String replier;
	private String replytext;
	private Timestamp postdate;
	private int ref;
}

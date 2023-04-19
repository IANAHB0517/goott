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
public class MessageVo {
	private int msgId;
	private String sender;
	private String receiver;
	private String messageText;
	private Timestamp sendTime;
	private String isRead;
	
}

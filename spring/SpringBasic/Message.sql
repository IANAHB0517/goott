SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS member;




/* Create Tables */

CREATE TABLE member
(
	userId varchar(8) NOT NULL,
	PRIMARY KEY (userId)
);


CREATE TABLE message
(
	msgid int NOT NULL AUTO_INCREMENT,
	sender varchar(8) NOT NULL,
	receiver varchar(8) NOT NULL,
	messageText varchar(1000),
	sendTime datetime DEFAULT now(),
	isRead char(1) DEFAULT '''N''',
	PRIMARY KEY (msgid)
);



/* Create Foreign Keys */

ALTER TABLE message
	ADD FOREIGN KEY (sender)
	REFERENCES member (userId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE message
	ADD FOREIGN KEY (receiver)
	REFERENCES member (userId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;




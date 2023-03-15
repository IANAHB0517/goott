use lsj;

drop table board;
drop table genre;
drop table member;
drop table rent;
drop table video;

-- 테이블 생성
CREATE TABLE `member` (
  `userId` varchar(8) NOT NULL,
  `userPwd` varchar(200) NOT NULL,
  `userEmail` varchar(50) NOT NULL,
  `userMobile` varchar(13) DEFAULT NULL,
  `userGender` varchar(1) NOT NULL,
  `hobbies` varchar(100) DEFAULT NULL,
  `job` varchar(20) DEFAULT NULL,
  `userImg` varchar(100) DEFAULT 'uploadMember/noimg.png',
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 패스워드 암호화
select md5('1234'); -- md5 알고리즘으로 암호화
select sha1('1234'); -- sha1 알고리즘으로 암호화

select sha1(md5('1234')); -- 섞어쓰기


-- 회원 가입
-- 회원 가입시 이미지가 없을 때는 default 값이 들어가야 하므로 sql문
insert into member(userId, userPwd, userEmail, userMobile,  userGender,
 hobbies, job,  userImg, memo   )
values(?,sha1(md5('?')),?,?,?,?,?,?);

insert into member(userId, userPwd, userEmail, userMobile,  userGender,
 hobbies, job,   memo   )
values('abcd123',sha1(md5('1234')),'abc@abc.com','010-1111-2222','M','','학생','');

-- 회원가입시 이미지가 있을때 sql문
insert into member
values(?,sha1(md5('?')),?,?,?,?,?,?,?);


select * from member;
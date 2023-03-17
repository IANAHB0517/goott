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

-- 회원 포인트 적립을 위한 테이블
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


-- 중복된 유저 아이디 검사 sql문
select * from member where userId = ?;
select * from member where userId = 'abcde123';

select count(*) as userCnt from member where userId = ?;

-- 로그인 처리
select * from member where userId = ? and userPwd = sha1(md5(?)) ;

select * from member where userId = 'abcd123' and userPwd = sha1(md5('1234')) ;


-- 포인트 정책 테이블
CREATE TABLE `pointpolicy` ( 
  `why` varchar(50) NOT NULL,
  `howmuch` int(11) DEFAULT NULL,
  PRIMARY KEY (`why`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 포인트 테이블
INSERT INTO `lsj`.`pointpolicy` (`why`, `howmuch`) VALUES ('회원가입', '100');
INSERT INTO `lsj`.`pointpolicy` (`why`, `howmuch`) VALUES ('로그인', '10');
INSERT INTO `lsj`.`pointpolicy` (`why`, `howmuch`) VALUES ('게시판작성', '5');
INSERT INTO `lsj`.`pointpolicy` (`why`, `howmuch`) VALUES ('답글쓰기', '1');

-- 테이블간 참조 관계 생성
ALTER TABLE `lsj`.`memberpoint` 
ADD INDEX `who_fk_idx` (`who` ASC) VISIBLE;
;
ALTER TABLE `lsj`.`memberpoint` 
ADD CONSTRAINT `who_fk`
  FOREIGN KEY (`who`)
  REFERENCES `lsj`.`member` (`userId`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
  alter table membepoint
  add constraint memberpoint_why_fk foreign key(why)
  references pointpolicy(why);
  
  alter table memberpoint
  add constraint memberpoint_howmuch_fk foreign key(howmuch) references pointpolicy(howmuch);

-- -----------------
ALTER TABLE `lsj`.`memberpoint` 
ADD CONSTRAINT `memberpoint_why_fk`
  FOREIGN KEY (`why`)
  REFERENCES `lsj`.`pointpolicy` (`why`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- -----------------member
ALTER TABLE `lsj`.`memberpoint` 
ADD CONSTRAINT `who_fk`
  FOREIGN KEY (`who`)
  REFERENCES `lsj`.`member` (`userId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SELECT * FROM lsj.member;
select * from memberpoint;
commit;

insert into memberpoint(who, why, howmuch)
values('a1a1', '회원가입', 100);

insert into memberpoint(who, why, howmuch)
values('abcde', '회원가입', 100);

insert into memberpoint(who, why, howmuch)
values('abcde123', '회원가입', 100);


-- ----------------- 

-- 회원가입시 포인트 부여
insert into memberpoint (who, why, howmuch)
values (?, '회원가입', (select howmuch from pointpolicy where why ='회원가입'));



-- 테이블 조회
use lsj;
select * from member;
select * from memberpoint;

use lsj;




-- 관리자 컬럼생성
ALTER TABLE `lsj`.`member` 
ADD COLUMN `isAdmin` VARCHAR(1) NULL DEFAULT 'N' AFTER `memo`;


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


-- 로그인 기록 테이블
CREATE TABLE `latestloginlog` (
  `who` varchar(8) NOT NULL,
  `latestlogindate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`who`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 멤버포인트 테이블 생성
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
  `isAdmin` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`userId`)
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
  
  alter table latestloginlog
  add constraint member_userId_fk foreign key(who) references member(userId);

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
-- 포인트 부여
-- 회원가입시 포인트 부여
insert into memberpoint (who, why, howmuch)
values (?, '회원가입', (select howmuch from pointpolicy where why ='회원가입'));

-- 최근 로그인 한 날짜가 바뀌었는가?
select datediff(now(), (select latestlogindate from latestloginlog where who='a1a1'));

-- 회원 가입후 처음 로그인 하면 -1
select ifnull(a.diff, -1) as datediff from (select datediff(now(), (select latestlogindate from latestloginlog where who= 'kimlay')) as diff) a;

-- 처음 로그인 x, 로그인 하고 날짜가 바뀌었으면 > 0
select datediff(now(), (select latestlogindate from latestloginlog where who='a1a1'))  as diff;

-- 처음 로그인 x, 로그인 하고 날짜가 바뀌지 않았으면 0

-- 처음 로그인 한 사람 로그인 기록 남기는 sql문
insert into latestloginlog(who) values (?);

-- 기존 로그인기록이 있는 사람 update 하는 sql문
update latestloginlog set latestlogindate = now() where who=?;



select TIMESTAMPDIFF(day, now(), (select latestlogindate from latestloginlog where who='kimlay')) as diff;

-- 로그인한 회원의 전체 정보 조회
select * from member where userId = ?;

-- 유저 아이디로 포인트 내역 가져오기
select * from memberpoint where who = ? order by no desc;

-- 회원 탈퇴하기 -- fk 제약땜에 삭제가 안됨			
delete from member where userId = '?';




-- ==========================================================================================================================================================================

-- 계층형 게시판 구현
-- 게시판 테이블 생성alter
CREATE TABLE `lsj`.`board` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `writer` VARCHAR(8) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `postDate` DATETIME NULL DEFAULT now(),
  `content` VARCHAR(500) NOT NULL,
  `imgFile` VARCHAR(50) NULL,
  `readcount` INT NOT NULL DEFAULT 0,
  `likecount` INT NOT NULL DEFAULT 0,
  `ref` INT NOT NULL DEFAULT 0, -- 부모글을 참조
  `step` INT NOT NULL DEFAULT 0, -- 답글의 깊이 -- 가로순
  `reforder` INT NOT NULL DEFAULT 0, -- 부모글과 답글을 보여주는 순서 -- 세로순
  PRIMARY KEY (`no`));
  
  -- member, board 테이블 관계 설정
  alter table board
  add constraint board_writer_fk
  foreign key (writer)
  references member(userId)
  on delete cascade;
  
  -- 게시글 삭제
  delete from board where no = ?;

-- 게시판에 글 등록(부모글) 하는 쿼리문
insert into board(writer, title, content, imgFile, ref)
values ('kimlay', '1번글', 'ㅎ2', '', (select max(no) + 1 from board));

commit;
-- ref 컬럼은 부모글을 참조하는 컬럼이다. 답글이 아닌 부모글을 쓸 때는 자기 자신의 글 번호(no)와 
-- 같은 값을 가지도록 해야한다.

select max(no) + 1 from board;

insert into board(writer, title, content, imgFile, ref)
values ('abcd1230', '1번 놓침', 'hello', '', 2);

--  이렇게 하면 되는데 왜 안할까...
insert into board(writer, title, content, imgFile, ref)
values ('abcd1230', '왜 이렇게 안하나..', 'hello', '', (select max(b.no) + 1 from board b));
-- 위의 경우 같은 테이블을 참조 하는 것을 속여서 각각 다른 테이블에서 가져오는 것 처럼 속이는 방법

-- 테이블을 운용하기 위한 정보를 모아놓은 테이블에서 값을 가지고 오는 법
insert into board(writer, title, content, imgFile, ref)
values ('abcd1230', '이렇게 하면되나', 'hello', '',
(select auto_increment from information_schema.tables where table_schema = 'lsj' and table_name = 'board'));

-- 하지만 위의 두 경우 모두 오류 발생의 위험이 있어서 먼저 no 값을 가져와서 ref에 넣어 주도록 한다.

-- 게시판 전체 글 목록을 보여주는 쿼리문
select * from board order by no desc;


-- ?번 글을 조회하는 쿼리문
select * from board where no =?;

-- ?번 글의 조회수를 증가하는 쿼리문
update board set readcount = readcount + 1 where no = ?;

-- ==========================================================================================================================================================================
-- 게시물 상세 조회

-- 조회수 처리를 위한 테이블 생성 및 fk 생성
CREATE TABLE `lsj`.`readcountprocess` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `ipAddr` VARCHAR(15) NOT NULL,
  `boardNo` INT NOT NULL,
  `readTime` DATETIME NOT NULL DEFAULT now(),
  PRIMARY KEY (`no`),
  INDEX `readcount_boardNo_fk_idx` (`boardNo` ASC) VISIBLE,
  CONSTRAINT `readcount_boardNo_fk`
    FOREIGN KEY (`boardNo`)
    REFERENCES `lsj`.`board` (`no`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- ? 번 ip 주소가 ? 번 읽은 적이 있는가? 
select * from readcountprocess where ipAddr =? && boardNo = ?;

select readTime from readcountprocess where ipAddr ='211.197.18.247' && boardNo = 1 ;

-- timediff t1 에서 t2를 뻄
select timediff(  now() , (select readTime from readcountprocess where ipAddr ='211.197.18.247' and boardNo = 1)) > 24;

-- timediff t2 에서 t1 을 뻄 단위를 지정해주어야함
select timestampdiff( hour, (select readTime from readcountprocess where ipAddr ='211.197.18.247' and boardNo = 1), now()) > 24;

-- ? 번 ip 주소가 ? 번 읽은 시간이 null 이면 -1 반환
select ifnull(timestampdiff( hour, (select readTime from readcountprocess where ipAddr ='211.197.18.247' and boardNo = 1), now()) > 24 , -1) as diff;

-- ? 번 ip 주소가 ? 번 읽은 시간이 null 이면 -1 반환 쿼리문
select ifnull(timestampdiff( hour, (select readTime from readcountprocess where ipAddr =? and boardNo = ?), now()) > 24 , -1) as diff;

--  ip 주소 글번호 입력
insert into readcountprocess(ipAddr, boardNo) values (?,?);

-- 글 번호 읽은 시간 업데이트
update readcountprocess set readTime = now() where ipAddr = ? and boardNo =? ;

-- ==========================================================================================================================================================================
-- 게시물 답글 달기

-- 부모글이 원래글인지 답글인지 판단 (답글이 끼어 들기할 공간 만들기)
update board set reforder = reforder + 1
where ref = ? and reforder > ? ;

-- 답글을 board 테이블에 등록
insert into board (writer, title, content, ref, step, reforder)
values (?,?,?,?,pStep+1,pRefOrder+1) ;

-- 모든 게시물을 출력
select * from board order by ref desc, reforder asc;

-- 답글 수정의 경우 (답글 작성자가 로그인 하여 수정 가능, 기존의 ref, setp, reforder 가 유지 됨)

-- 답글 삭제  (답글 작성자가 로그인 하여 삭제 가능,
-- 답글의 writer : admin
-- 답글의 title : 삭제된 글입니다
-- 답글의 내용 : x
-- viewBoard.bo 페이지로 링크를 안걸어도 된다.
-- 조회수, 좋아요 수, 초기화 되면서 대체 (ref, step, reforder 대체된 글이 유지)

--  ===============================================================================================================================================================
-- 인기글 조회 또는 최신글 몇개 가져오기
select * from board order by readcount desc limit 3;

-- 마이 페이지 내가 쓴글
select * from board where writer =?;

--  ===============================================================================================================================================================
-- 페이징 처리
-- select * from board order by ref desc, reforder asc limit 보여주기 시작할 row의 index, 보여줄 글의 수;
select * from board order by ref desc, reforder asc limit 1, 3;
select * from board order by no desc limit 6,3;

-- 1) 하나의 페이지에 몇개의 글(Row)을 보여 줄 것인가 결정 : 3
-- 2) 게시판 글의 총 갯수를 얻어온다 : 27
select count(*) as cnt from board ;
-- 3) 총 페이지의 수 : 게시판 수 / 하나의 페이지에 보여줄 글의 수
-- 27 / 3 = 9 , (나누어 떨어지지 않을 경우 + 1)

-- 4) X번 페이지에서 출력하게 될 글의 시작 번호
-- 전체 글의 갯수 (27) - (현재 페이지 번호-1) * 한페이지당 보여줄 글의 수
-- 1페이지 :  27 - 0 * 3 = 27
-- 1페이지 :  27 - 1 * 3 = 24

-- 1페이지 : 
select * from board order by ref desc, reforder asc limit 0, 3;
-- 2페이지 : 
select * from board order by ref desc, reforder asc limit 3, 3;
-- 3페이지 : 
select * from board order by ref desc, reforder asc limit 6, 3;
-- 4페이지 : 
select * from board order by ref desc, reforder asc limit 9, 3;

-- X페이지 : 
select * from board order by ref desc, reforder asc limit (?-1)?, ?;


select count(*) from board where step < 1;



-- 4) 보여주기 시작할 row index 번호 = (현재 페이지 번호-1) * 한페이지당 보여줄 글의 수
-- 1 페이지 0 

-- 페이징 블럭 만들기 (pagination 페이지 번호를 몇개씩 보여 줄 것인가)
-- 1) 1개의 블럭에 몇개 페이지를 둘 것인가(pageCntPerBlock) : 4
-- 전체 페이징 블럭 갯수 : 전체 페이지 수 / pageCntPerBlock
-- 나누어 떨어지지 않으면 + 1 = 9 / 4 = 2 -> (나누어 떨어지지 않으므로) 3

-- 2) 현재 페이지가 속한 페이징 블럭 : 현재 페이지번호 / pageCntPerBlock
-- 2 페이지 -> 2/4 (나누어 떨어지지 않으면 올림) = 1 번 블럭
-- 6 페이지 -> 6/4 = 2 번 블럭

-- 3) 현재 페이징 블럭의 시작 번호 : (현재 페이징 블럭 -1) * pageCntPerBlock + 1
-- 2번 블럭 : (2 - 1) * 4 + 1 = 5

-- 4) 현재 페이징 블럭의 끝 번호 : (현재 페이징 시작 번호 + pageCntPerBlock) - 1
-- 2번 블럭 : (5 + 4) - 1 = 8 

--  ===============================================================================================================================================================
-- 마이페이지 포인트 페이징
select count(*) as cnt from memberpoint where who = 'abcd1230';


--  ===============================================================================================================================================================
-- 테이블 조회
use lsj;
select * from member;
select * from memberpoint;
select * from latestloginlog;
SELECT * FROM lsj.pointpolicy;
select * from board order by ref desc, reforder asc;
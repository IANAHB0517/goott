
use lsj;
-- 이 이름의 스키마를 사용한다.

create table lsj.test(
userid varchar(10),
pwd varchar(20),
name varchar(10)
);
-- 원래는 테이블앞에 계정을 표기해주어야한다

create table test(
userid varchar(10),
pwd varchar(20),
name varchar(10)
);
--  use를 통해 어떤 스키마를 사용할지 설정한 뒤에는 스키마 명을 생략할 수 있다.

select * from lsj.test;

select * from test;

insert into test values('aaaa','1111','에이씨');

-- board 테이블 생성
CREATE TABLE `lsj`.`board` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `writer` VARCHAR(10) NOT NULL,
  `content` VARCHAR(200) NOT NULL,
  `writtenDate` DATETIME NULL DEFAULT now(),
  PRIMARY KEY (`no`))
COMMENT = '게시판글 저장 테이블';

select * from board;
insert into board(title, writer, content) values('1빠', 'dooly', '1등');
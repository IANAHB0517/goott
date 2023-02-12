use gottclass6;

----------------------------------------------------------------------------------------------------------------
-- 테이블 생성

CREATE TABLE `rent` (
  `NUM` int(11) NOT NULL AUTO_INCREMENT,
  `RENT_REC` datetime NOT NULL,
  `USER_NUM` int(4) NOT NULL,
  `VIDEO_CODE` char(10) NOT NULL,
  `GENRE_CODE` varchar(13) NOT NULL,
  `RENTDATE` date NOT NULL,
  `ISRETURN` char(1) NOT NULL DEFAULT 'N',
  `ADD_LATE_FEE` mediumint(9) DEFAULT NULL,
  `CHECK_LATE` char(1) DEFAULT 'N',
  `RETURN_DATE` date DEFAULT NULL,
  
  PRIMARY KEY (`NUM`,`RENT_REC`),
  
  KEY `rent_USER_NUM_FK` (`USER_NUM`),
  KEY `rent_VIDEO_CODE_FK` (`VIDEO_CODE`),
  KEY `rent_GENRE_CODE_FK` (`GENRE_CODE`),
  
  CONSTRAINT `rent_GENRE_CODE_FK` FOREIGN KEY (`GENRE_CODE`) REFERENCES `genre` (`GENRE_CODE`),
  CONSTRAINT `rent_USER_NUM_FK` FOREIGN KEY (`USER_NUM`) REFERENCES `member` (`USER_NUM`),
  CONSTRAINT `rent_VIDEO_CODE_FK` FOREIGN KEY (`VIDEO_CODE`) REFERENCES `video` (`VIDEO_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='대여기록 테이블';


----------------------------------------------------------------------------------------------------------------

-- 테이블 별조회
SELECT * FROM rent;
SELECT * FROM genre;
SELECT * FROM member;
SELECT * FROM video;


----------------------------------------------------------------------------------------------------------------

select date_format(now(), '%y-%m-%d-%h:%i:%s') -2;
select datetime();

select now()-'20 00:00:00';


-






----------------------------------------------------------------------------------------------------------------

insert into member values('8' ,'이상진', '010-4578-9560', '2001-03-22', 'male', 100, 'Y','서울시 구로구 구로동');

insert into member values('8' ,'이상진', '010-4578-9560', '2001-03-22', 'male', 100, 'Y','서울시 구로구 구로동');

update ;

----------------------------------------------------------------------------------------------------------------


ALTER TABLE rent AUTO_INCREMENT=0;

----------------------------------------------------------------------------------------------------------------

commit ;


-- 컬럼 변경
ALTER TABLE rent CHANGE RETURN_DATE RETURN_DUE_DATE DATE;
-- 컬럼추가
ALTER TABLE rent ADD  RETURN_DATE DATE NULL;



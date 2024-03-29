

use gottclass6;


-- 대여기록 CRUD
select * from rent;

insert into rent( USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE)
values ( '30', 'ER20223334' , 'Erotic',now()  , date_add(now(), INTERVAL +3 day ));

insert into rent( USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE)
values ( '25', 'CO20195555' , 'Comed' , now()  , date_add(now(), INTERVAL +3 day ));

insert into rent( USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE)
values ( '13', 'HO19940101' , 'Horror' ,  date_add(now(), INTERVAL -230 day )  , date_add(now(), INTERVAL -227 day ));



-- NUM을 독립적인 파라이머리 키로 사용 하면서 부터 NUM 컬럼에 값을 넣어주면 중복 될 경우 데이터가 입력 되지 않는다.
insert into rent(NUM, USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE)
values (17, now(), '25', 'CO20195555' , 'Comed' , now()  , date_add(now(), INTERVAL +3 day ));

select * from rent where USER_NUM = 30;

update rent
set RETURN_DATE= null,RETURN_DUE_DATE= (select date_add(RENTDATE, INTERVAL +3 day ) where RENT_REC = '2023-02-06 17:37:19' )
where RENT_REC = '2023-02-06 17:37:19';

update rent
set RETURN_DATE = '2023-02-06' , ISRETURN = 'Y'
where RENT_REC = '2023-02-03 18:59:08';

delete from rent where RENT_REC = '2023-02-06 17:37:19';
-- delete from rent where RENT_REC = ;


-- 회원 CRUD
select * from member ;
insert member (USER_NAME, PHONE_NUM, BIRTHDAY) values ('비둘기', '010-9999-9999', '1999-09-09');
select * from member where USER_NAME like '김%';
update member set ADDR = '서울시 파전이 많은곳' where USER_NAME = '비둘기' and USER_NUM = 50;
update member set deleted_at = sysdate() where USER_NUM = 50;




-- 비디오 CRUD
select * from video;
insert into video (VIDEO_CODE, GENRE_CODE, VIDEO_TITLE, MOVIE_RATED, RELEASE_DATE, DIRECTOR) values ('HO19940101', 'Horror', '어서와요 훈련소에', '19', 1994-01-01 ,  '2소대장');
select * from video where VIDEO_CODE = 'HO19940101';
update video set RELEASE_DATE = '1994-01-01' where VIDEO_CODE = 'HO19940101';
update video set PRODUCTION ='국방문화진흥원',  DIRECTOR = '3소대장' where VIDEO_CODE = 'HO19940101';
delete from video where VIDEO_CODE = 'HO19940101' ;


insert into video (VIDEO_CODE, GENRE_CODE, VIDEO_TITLE, MOVIE_RATED, RELEASE_DATE, DIRECTOR) values ('CH20010001', 'Chow Sing-Chi', '소림축구', '7', '2001-01-01' ,  '주성치');


-- 장르 CRUD
select * from genre;
insert into genre values('Chow Sing-Chi', 2000, 200, '주성치', 3);
select * from genre where GENRE_CODE = 'Chow Sing-Chi';
update genre set RENTAL_FEE = 1500, LATE_FEE = 100 , LEND_TIME = 10 where GENRE_CODE = 'Chow Sing-Chi';



-- PL/SQL

insert member (USER_NAME, PHONE_NUM, BIRTHDAY) values ('원동건', '010-1090-0405', '1994-01-05');
UPDATE member set GENDER = 'male' where USER_NUM =54;

set @video = 'AC20010001';
set @video_genre = (select GENRE_CODE from video where VIDEO_CODE = @video);
set @rent_date = date_add(now(), interval -2180 day);
set @return_due_date = (date_add(@rent_date, interval 3 day));

insert into rent( USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE ,ISRETURN , RETURN_DUE_DATE, RETURN_DATE)
values ('54', @video, @video_genre, @rent_date,'Y' ,  @return_due_date, @return_due_date);

insert into rent( USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE ,ISRETURN , RETURN_DUE_DATE )
values ('54', @video, @video_genre, @rent_date,'N' ,  @return_due_date );

update rent set ISRETURN = 'Y' where VIDEO_CODE = @video;

update rent set GENRE_CODE = 'Action' where VIDEO_CODE = @video;

update rent set VIDEO_CODE = 'AC20010001' where VIDEO_CODE = @video;

update video set VIDEO_CODE = 'AC20010001' where VIDEO_CODE = @video;

delete from rent where USER_NUM = 54;

update video set GENRE_CODE = 'Action' where VIDEO_CODE = 'AC20010001';

update video set TOTAL_VIEW = 0 where VIDEO_CODE = 'AC20010001';

delete from genre where GENRE_CODE = 'Chow Sing-Chi';

update rent set CHECK_LATE = 'Y' where NUM = 118;

update rent set CHECK_LATE = 'Y' where VIDEO_CODE = 'HO19940101';

update rent set RETURN_DATE = '2022-06-21' where NUM = 34;

update rent set RETURN_DATE = '2022-06-26' where NUM = 27;







rollback;

COMMIT;


-- 인기목록 조회
select VIDEO_TITLE , TOTAL_VIEW from video order by TOTAL_VIEW desc limit 5;


-- 매출확인 

-- 연체관리
-- 회원이 연체한 비디오 코드와 연체 일수
select distinct user_num ,video_code ,  DATEDIFF (now(), RENTDATE ) as 연체일수 from rent where ISRETURN = 'N' and RETURN_DATE is null;

-- 연체 중인 회원의 정보
select * from member where user_num in (select user_num from rent where ISRETURN = 'N' and RETURN_DATE is null);
select * from member where user_num in (select distinct user_num from rent where ISRETURN = 'N' and RETURN_DATE is null);

-- select m.user_name, m.phone_num, video_title , r.연체일수 from
--  (select distinct user_num ,video_code ,  DATEDIFF (now(), RENTDATE ) as 연체일수 from rent where ISRETURN = 'N' and RETURN_DATE is null) r,
--  (select * from member where user_num in (select distinct user_num from rent where ISRETURN = 'N' and RETURN_DATE is null)) m,
--  video v
--  where m.user_num = r.user_num
--  and r.video_code = v.video_code;
 

-- (select v.VIDEO_TITLE from video v ,rent r where r.VIDEO_CODE = v.VIDEO_CODE and VIDEO_CODE =  )

-- 회원나이별 선호장르



-- 회원별 추천 장르
select  * 
from 
	(select m.user_name as 회원명, r.genre_code 장르 , count(*) count
	 from member m , rent r where m.USER_NUM = r.USER_NUM group by 회원명) target 
where target.count > @avg
order by count desc
;



select avg(target.c) from( select count(*) as c from (select m.user_name as 회원명, r.genre_code 장르 
	 from member m , rent r where m.USER_NUM = r.USER_NUM) t) target;


-- 회원 별 가장 많이본 영화
select m.user_name user , v.video_title , count(r.video_code) count from member m , rent r , video v 
where m.user_num = r.user_num and
r.video_code = v.video_code
group by user
order by count desc;

select r.video_code, m.user_name from rent r , member m where r.USER_NUM = m.USER_NUM;
select * from member where user_name ='박내일' ;


-- select video_code , user_num  ,  count(*) count from rent group by USER_NUM order by count desc;
-- 장르별 인기비디오 top5
(select video_code, genre_code , count(*) count from rent where GENRE_CODE = 'Drama' group by video_code order by count desc limit 5 )
union
(select video_code, genre_code , count(*) count from rent where GENRE_CODE = 'Animation' group by video_code order by count desc  limit 5 )
union
(select video_code, genre_code , count(*) count from rent where GENRE_CODE = 'Action' group by video_code order by count desc  limit 5 )
;
 
--  select video_code ,genre_code, count(*) as count from rent group by video_code order by count desc;
 
 -- select genre_code, count(*) as count from rent group by genre_code order by count desc;
 
 -- 장르별 인기순위 
 
 
 (select genre_code, count(*) as count from rent group by genre_code order by count desc) ;



-- 신간(대여료 +1000), 연체료 +200, 대여일2, 시간기준 출시일로 부터 1달
select newvideo.video_title , g.RENTAL_FEE + 1000, (g.LEND_TIME -1) , g.LATE_FEE+200, newvideo.RELEASE_DATE from genre g inner join (select * from video where datediff(now(), RELEASE_DATE ) < 30) as newvideo 
on g.genre_code = newvideo.genre_code;

select datediff( now(), RELEASE_DATE) from video;

-- 영화 인기도(대여횟수)가 딱 가운데 순위인 영화의 감독 작품중에 가장 잘 나가는 영화

select user_num, count(*) from rent group by user_num;

select round(avg(t.count)) avg from (select video_code, count(video_code) count from rent group by video_code) t;

set @avgCount = (select round(avg(t.count)) avg from (select video_code, count(video_code) count from rent group by video_code) t);
select  @avgCount from dual;

-- 대여 횟수가 평균인 비디오
select VIDEO_CODE, count(video_code) Count from rent
		group by video_code
		having Count = @avgCount;
        
-- 해당 비디오의 감독
select v.video_code , v.director from (
		select VIDEO_CODE, count(video_code) Count from rent
		group by video_code
		having Count = @avgCount
) as VideoTarget , video v
where VideoTarget.video_code = v.video_code;


-- 해당 감독들의 영화
select v.VIDEO_CODE, v.video_title, v.director , v.total_view
from  video as v inner join
				(select v.video_code , v.director from (
					select VIDEO_CODE, count(video_code) Count from rent
					group by video_code
					having Count = @avgCount
				) as VideoTarget , video v
				where VideoTarget.video_code = v.video_code) as getD
using (director)
order by total_view desc
;

-- 한 장르를 많이 빌린 사람들에 대해서, 전화번호 조회
select * , count(video_code) count from rent group by video_code order by count desc;


-- 연체율이 가장 높은 장르에서 가장 인기 없는 감독의 영화중에 최신작

-- 특정 회원이 가장 많이 본 영화 장르 중에 인기도가 가장 높은 영화가 뭔가요


-- 특정 회원이 가장 많이 본 영화 장르 중에 인기도가 가장 높은 영화가 뭔가요
select v.video_title , t.GENRE_CODE, t.count from video v join (select r.GENRE_CODE, r.video_code , count(r.video_code) count
from video v , rent r,
	(select f1.user_num, f1.genre_code, f1.video_code, f1.count from
		(select * ,count(user_num) count 
		from rent group by user_num 
		order by  user_num desc) as f1
	where user_num = 10 
	order by f1.count desc limit 1) as target1
where target1.genre_code = v.genre_code and  v.video_code = r.video_code
group by video_code limit 1) as t
on v.video_code = t.video_code;


-- 특정 회원이 가장 좋아하는 영화의 장르 코드 , 비디오, 시청횟수
select f1.user_num, f1.genre_code, f1.video_code, f1.count from
	(select * ,count(user_num) count 
    from rent group by user_num 
    order by  user_num desc) as f1
where user_num = 12 
order by f1.count desc limit 1;

-- 20대 여자들이 선호하는(대여 횟수가 높은) 영화 top1의 감독을 알려주세요



-- 장르별로 특정 요일에 할인
select genre_name, RENTAL_FEE -500 as 대여로 from genre where genre_code = 'Comed';

-- 제목이가장 긴 영화 와 가장 짧은 영화가 무엇인가요
select VIDEO_CODE,  VIDEO_TITLE, floor(length(VIDEO_TITLE)/3) as 글자수  from video order by 글자수 desc limit 1;
select VIDEO_CODE,  VIDEO_TITLE,  floor(length(VIDEO_TITLE)/3) as 글자수  from video order by 글자수 limit 1;




-- 장르별 매출액이 많은 상위 3개의 장르에 대해서, 최근 한달 간 대여가 없는 고객의 전화번호 조회
select  num , m.USER_NAME, m.PHONE_NUM , r.rentdate, datediff(rentdate, now()) as '가장 최근 대여일' from member m  inner join rent r 
on r.user_num = m.user_num
where datediff(rentdate, now()) < -30
group by user_name
;

select datediff(now() ,rentdate ) from rent;


-- 2번 회원 : 제가 볼 수 있는 등급이며 대여료가 2500원인 비디오는 무엇이 있나요?

select * 
from video v natural join genre g 
where v.MOVIE_RATED <= (SELECT FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE( (select BIRTHDAY from member where user_num = 2),'-','') AS UNSIGNED)) / 10000 ) as engAge)
and g.RENTAL_FEE = 2500;

  (SELECT FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE( (select BIRTHDAY from member where user_num = 2),'-','') AS UNSIGNED)) / 10000 ) as engAge) ;  

-- 23번 회원 : 저희 가족(핸드폰번호가 같음)이 그동안 대여한 비디오명과 대여한 날짜 전부 알려주세요.
select * from rent r, (select user_num from member
where PHONE_NUM = (select PHONE_NUM from member where user_num = 23)) f
where r.user_num = f.user_num
order by r.rentdate ;




-- 가장 많이 연체되는 비디오 top10

select video_code,datediff(return_date ,return_due_date ) as 연체일 from rent where isreturn = 'N' and datediff(return_due_date , now()) < 0;

select video_code, COALESCE(datediff(return_date ,return_due_date ),datediff(COALESCE(return_date, now()), return_due_date) ) as 연체일  from rent  
	where datediff(return_date ,return_due_date ) > 0 or ( isreturn = 'N' and datediff(return_due_date ,now()) < 0 )
	order by 연체일 desc;


select v.video_title , LateVideo.연체일
from video v inner join 
	(select video_code, COALESCE(datediff(return_date ,return_due_date ),datediff(COALESCE(return_date, now()), return_due_date) ) as 연체일  from rent  
	where datediff(return_date ,return_due_date ) > 0 or ( isreturn = 'N' and datediff(return_due_date ,now()) < 0 )
    group by video_code
	order by 연체일 desc) as LateVideo 
on v.video_code = LateVideo.video_code
order by 연체일 desc limit 10
;

-- 올해 가장 매출액이 높은 월과 낮은 월 조회


-- 회원 나이대별 저장


set @tw = (select * from member where birthday between '2004-01-01' and '1994-01-01');




select v.video_title , v.director  ,twff.count from video v inner join (select video_code, count(video_code) as count
from rent r inner join
	(
	-- 10대
	(select *, '10대' as 연령대 from member where FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(birthday,'-','') AS UNSIGNED)) / 10000 ) between 0 and 19)
	union
	-- 20대
	(select * , '20대' as 연령대 from member where FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(birthday,'-','') AS UNSIGNED)) / 10000 ) between 20 and 29)
	union
	-- 30대
	(select * , '30대' as 연령대 from member where FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(birthday,'-','') AS UNSIGNED)) / 10000 ) between 30 and 39)
	union
	-- 40대
	(select * , '40대' as 연령대 from member where FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(birthday,'-','') AS UNSIGNED)) / 10000 ) between 40 and 49)
	union
	-- 50대
	(select * , '50대' as 연령대 from member where FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(birthday,'-','') AS UNSIGNED)) / 10000 ) between 50 and 59)
	union
	-- 60대
	(select * , '60대' as 연령대 from member where FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(birthday,'-','') AS UNSIGNED)) / 10000 ) between 60 and 69)
	union
	-- 70대
	(select * , '70대' as 연령대 from member where FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(birthday,'-','') AS UNSIGNED)) / 10000 ) between 70 and 79)
	union
	-- 고령대
	(select * , '고령대' as 연령대 from member where FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(birthday,'-','') AS UNSIGNED)) / 10000 ) between 80 and 150)
	) as ageGroup
on r.user_num = ageGroup.user_num
where ageGroup.연령대 = '20대' and ageGroup.gender = 'female'
group by video_code
order by count desc limit 1) as twff
on v.video_code = twff.video_code
;  

-- ======================
-- 테이블 생성

CREATE TABLE `age_group_rent` (
  `age_group` varchar(6) CHARACTER SET utf8mb4 DEFAULT NULL,
  `rgenre` varchar(13) NOT NULL,
  `cnt` bigint(21) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `genre` (
  `GENRE_CODE` varchar(13) NOT NULL,
  `RENTAL_FEE` int(5) NOT NULL,
  `LATE_FEE` int(5) NOT NULL,
  `GENRE_NAME` varchar(8) NOT NULL,
  `LEND_TIME` int(1) NOT NULL,
  PRIMARY KEY (`GENRE_CODE`),
  UNIQUE KEY `genre_GENRE_NAME_uq` (`GENRE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `member` (
  `USER_NUM` int(4) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(50) NOT NULL,
  `PHONE_NUM` varchar(13) NOT NULL,
  `BIRTHDAY` date NOT NULL,
  `GENDER` char(6) NOT NULL,
  `ADDR` varchar(100) DEFAULT NULL,
  `deleted_at` date DEFAULT NULL,
  PRIMARY KEY (`USER_NUM`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

CREATE TABLE `Persons` (
  `ID` int(11) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `removeUser` (
  `removeId` varchar(10) NOT NULL,
  `removeName` varchar(45) NOT NULL,
  `curDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`removeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rent` (
  `NUM` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NUM` int(4) NOT NULL,
  `VIDEO_CODE` char(10) NOT NULL,
  `GENRE_CODE` varchar(13) NOT NULL,
  `RENTDATE` date NOT NULL,
  `ISRETURN` char(1) NOT NULL DEFAULT 'N',
  `ADD_LATE_FEE` mediumint(9) DEFAULT NULL,
  `CHECK_LATE` char(1) DEFAULT 'N',
  `RETURN_DUE_DATE` date DEFAULT NULL,
  `RETURN_DATE` date DEFAULT NULL,
  PRIMARY KEY (`NUM`),
  KEY `rent_USER_NUM_FK` (`USER_NUM`),
  KEY `rent_VIDEO_CODE_FK` (`VIDEO_CODE`),
  KEY `rent_GENRE_CODE_FK` (`GENRE_CODE`),
  CONSTRAINT `rent_GENRE_CODE_FK` FOREIGN KEY (`GENRE_CODE`) REFERENCES `genre` (`GENRE_CODE`),
  CONSTRAINT `rent_USER_NUM_FK` FOREIGN KEY (`USER_NUM`) REFERENCES `member` (`USER_NUM`),
  CONSTRAINT `rent_VIDEO_CODE_FK` FOREIGN KEY (`VIDEO_CODE`) REFERENCES `video` (`VIDEO_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8 COMMENT='대여기록 테이블';

CREATE TABLE `Sell` (
  `idSell` int(11) NOT NULL AUTO_INCREMENT,
  `whatProd` varchar(45) NOT NULL,
  `qty` int(11) DEFAULT '0',
  PRIMARY KEY (`idSell`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `Stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prodName` varchar(45) NOT NULL,
  `qty` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `userAddress` (
  `id` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `video` (
  `VIDEO_CODE` char(10) NOT NULL,
  `GENRE_CODE` varchar(13) NOT NULL,
  `VIDEO_TITLE` varchar(100) NOT NULL,
  `MOVIE_RATED` int(2) NOT NULL,
  `RELEASE_DATE` date NOT NULL,
  `PRODUCTION` varchar(100) DEFAULT NULL,
  `DIRECTOR` varchar(30) NOT NULL,
  `TOTAL_VIEW` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`VIDEO_CODE`),
  KEY `VIDEO_GENRE_CODE_FK` (`GENRE_CODE`),
  CONSTRAINT `VIDEO_GENRE_CODE_FK` FOREIGN KEY (`GENRE_CODE`) REFERENCES `genre` (`GENRE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
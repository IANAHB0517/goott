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



COMMIT;

-- 블랙리스트 조회
select * from member where CHK_BLACK = 'Y';

-- 인기목록 조회
select VIDEO_CODE from rent group by VIDEO_CODE;
-- select * from (count(VIDEO_CODE)) order by 

-- where Lank <= 10;

-- 매출확인 

-- 연체관리

-- 회원나이별 선호장르

-- 회원별 추천 장르

-- 장르별 인기비디오 top5

-- 신간(대여료 +1000), 연체료 +200, 대여일2, 시간기준 출시일로 부터 1달

-- 영화 인기도(대여횟수)가 딱 가운데 순위인 영화의 감독 작품중에 가장 잘 나가는 영화

-- 한 장르를 많이 빌린 사람들에 대해서, 전화번호 조회

-- 연체율이 가장 높은 장르에서 가장 인기 없는 감독의 영화중에 최신작

-- 특정 회원이 가장 많이 본 영화 장르 중에 인기도가 가장 높은 영화가 뭔가요

-- 20대 여자들이 선호하는(대여 횟수가 높은) 영화 top1의 감독을 알려주세요

-- 장르별로 특정 요일에 할인

-- 제목이가장 긴 영화 와 가장 짧은 영화가 무엇인가요

-- 장르별 매출액이 많은 상위 3개의 장르에 대해서, 최근 한달 간 대여가 없는 고객의 전화번호 조회

-- 2번 회원 : 제가 볼 수 있는 등급이며 대여료가 2500원인 비디오는 무엇이 있나요?

-- 23번 회원 : 저희 가족(핸드폰번호가 같음)이 그동안 대여한 비디오명과 대여한 날짜 전부 알려주세요.

-- 가장 많이 연체되는 비디오 top10

-- 올해 가장 매출액이 높은 월과 낮은 월 조회



-- ddl
-- alter table rent drop PRIMARY KEY RENT_REC;


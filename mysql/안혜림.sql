-- 기본 CRUD
-- 회원 등록
insert into member (USER_NAME, PHONE_NUM, BIRTHDAY, GENDER, OVERDUE, CHK_BLACK, ADDR)
values ("김찬양", "010-7785-5216", "2001-05-27", "male", "5", "N", "서울시 구로구 구로동");

-- 회원 정보 수정
update member set USER_NAME = '박찬양', PHONE_NUM = '010-4885-5216'
where USER_NAME = '김찬양';

-- 회원 정보 조회
SELECT * FROM gottclass6.member;

-- 회원 삭제
update member set deleted_at = "2023-02-06"
where USER_NAME = '박찬양';

-- 비디오 등록
insert into video (VIDEO_CODE, GENRE_CODE, VIDEO_TITLE, MOVIE_RATED, RELEASE_DATE, PRODUCTION, DIRECTOR)
values ('RO20050082', 'Romance', '이터널 선샤인', '15', '2005-11-10', '코리아픽처스', '미셸 공드리');

-- 비디오 정보 수정 (업데이트)
update video set PRODUCTION = '(주)노바픽쳐스'
where VIDEO_CODE = 'RO20050082';

-- 비디오 조회
SELECT * FROM gottclass6.video;

-- 비디오 삭제
delete from video
where VIDEO_CODE = 'RO20050082';

-- 장르 등록
insert into genre values ('Test123', '2000', '200', '테스트', '3');

-- 장르 조회
select * from gottclass6.genre;

-- 장르 정보 수정
update genre set GENRE_NAME = '테스트'
where GENRE_CODE = 'Test123';

-- 장르 정보 삭제
delete from genre
where GENRE_CODE = 'Test123';

-- 대여기록 등록 
insert into rent (USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE, ISRETURN, ADD_LATE_FEE, CHECK_LATE, RETURN_DUE_DATE, RETURN_DATE)
values (32, 'RO20050082', 'Romance', '2023-02-07', 'N', null, 'N', '2023-02-10', null);

-- 대여기록 수정 (대여기록 등록 안했음, NUM 수정 필요)
update rent set RENTDATE = '2023-02-06', RETURN_DUE_DATE = '2023-02-09', RETURN_DATE = '2023-02-07'
where NUM = 43;

-- 대여기록 조회
select * from gottclass6.rent;

-- 대여기록 삭제 (대여기록 등록 안했음, NUM 수정 필요)
delete from rent
where NUM = 40;


-- 기본 상황
-- 인기 목록 조회
-- 뷰를 이용해 만들어보고, 오라클의 top 함수 대신에 limit 함수 사용
create or replace view view_popular_List
as
select VIDEO_CODE, VIDEO_TITLE, GENRE_CODE, TOTAL_VIEW
from video
order by TOTAL_VIEW desc;

select p.* from view_popular_List p limit 0,5;

drop view view_popular_List;


-- 총 매출 확인 (전체 매출 확인)
select sum(g.RENTAL_FEE) as total_rent, sum(r.ADD_LATE_FEE) as LATE_FEE
from genre g inner join rent r
on g.GENRE_CODE = r.GENRE_CODE;


-- 한달간 매출 확인
select sum(g.RENTAL_FEE) as total_rent, sum(r.ADD_LATE_FEE) as LATE_FEE
from genre g inner join rent r
on g.GENRE_CODE = r.GENRE_CODE
where r.RENTDATE >= subdate(date(now()), INTERVAL 1 month);


-- 연체 관리 / 현재 연체 되고 있는 비디오
select v.VIDEO_CODE, v.VIDEO_TITLE, m.USER_NUM, m.USER_NAME, m.PHONE_NUM, r.RETURN_DUE_DATE, r.ISRETURN
from gottclass6.rent r, gottclass6.member m, gottclass6.video v
where r.VIDEO_CODE = v.VIDEO_CODE
and r.USER_NUM = m.USER_NUM
and ISRETURN = 'N';


-- 신간 목록 검색 및 요금 처리
select v.VIDEO_CODE, v.VIDEO_TITLE, v.RELEASE_DATE, g.RENTAL_FEE+1000 RENTAL_FEE, g.LATE_FEE+200 LATE_FEE, g.LEND_TIME-1 LEND_TIME
from gottclass6.video v inner join gottclass6.genre g
on g.GENRE_CODE = v.GENRE_CODE
where v.RELEASE_DATE >= subdate(date(now()), INTERVAL 30 Day);


-- 장르별 인기 비디오 TOP3
-- 예시는 뮤지컬 
select VIDEO_CODE, VIDEO_TITLE, GENRE_CODE, TOTAL_VIEW
from video
where GENRE_CODE = 'Musical'
order by TOTAL_VIEW desc
limit 0,3;


-- 회원별 선호 장르
select r.USER_NUM as 회원번호, m.USER_NAME as 회원이름, r.GENRE_CODE as 선호장르, count(r.GENRE_CODE) as 총대여횟수
from rent r inner join member m
on r.USER_NUM = m.USER_NUM
where r.USER_NUM = '15'
group by GENRE_CODE
order by 총대여횟수 desc
limit 0,3;


-- 회원 나이별 선호 장르
select substr(BIRTHDAY, 1, 4) as 회원연령, r.GENRE_CODE as 선호장르, count(r.GENRE_CODE) as 총대여횟수
from rent r inner join member m
on r.USER_NUM = m.USER_NUM
where substr(BIRTHDAY, 1, 4) = 1996
group by GENRE_CODE
order by 총대여횟수 desc;


-- 한 장르를 많이 빌린 사람들에 대해서, 전화번호 조회
select m.USER_NUM, m.USER_NAME, m.PHONE_NUM, r.GENRE_CODE, count(USER_NAME) as 빌린횟수
from rent r inner join member m
on m.USER_NUM = r.USER_NUM
where GENRE_CODE = 'Horror'
group by USER_NAME
order by 빌린횟수 desc
limit 0,5;

-- Animation / Action / Fantasy / Horror / 검증 완


-- 23번 회원 : 저희 가족(핸드폰번호가 같음)이 그동안 대여한 비디오명과 대여한 날짜 전부 알려주세요.
select m.USER_NUM, m.PHONE_NUM, v.VIDEO_CODE, v.VIDEO_TITLE, r.RENTDATE
from member m, video v, rent r
where v.VIDEO_CODE = r.VIDEO_CODE
and m.USER_NUM = r.USER_NUM
and PHONE_NUM = (select PHONE_NUM from member where USER_NUM = '23');


-- 제목이 가장 긴 영화 와 가장 짧은 영화가 무엇인가요
-- 긴 영화
select VIDEO_TITLE, length(VIDEO_TITLE) as 길이
from video
order by 길이 desc
limit 1;

-- 짧은 영화
select VIDEO_TITLE, length(VIDEO_TITLE) as 길이
from video
order by 길이 asc
limit 1;








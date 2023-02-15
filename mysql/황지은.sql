------------------------------------------------------------------------------------------------------------------------
-- 회원 CRUD
-- 회원가입 (Create)
insert into member(user_name, phone_num, birthday, gender, addr) values("김김김", "010-0000-0000", "1996-06-14", "female", "서울시 구로구 구로동");
-- 회원조회 (Read)
select * from member;
-- 여자 회원만 조회 (Read)
select * from member where gender = "female";
-- 회원정보수정 (Update)
update member set overdue=100 where user_num = 50;
-- 회원 삭제 (Delete)
update member set deleted_at = "2023-02-06" where user_num = 42;

-- 비디오 CRUD
-- 비디오 추가 (Create)
insert into video values("AN19951230", "Animation", "Toy Story 1", 0, "0000-00-00", null, "존 라세터");
-- 비디오 조회 (Read)
select * from video;
-- Animation 영화만 조회 (Read)
select * from video where GENRE_CODE = "Animation";
-- 비디오 정보 수정 (Update)
update video set release_date = "1995-12-30" where video_code = "AN19951230";
-- 비디오 삭제 (Delete)
delete from video where video_code = "AN19951230";

-- 장르 CRUD
-- 장르 추가 (Create)
insert into genre values("Sports", 2500, 300, "스포츠", 3);
-- 장르 조회 (Read)
select * from genre;
-- 장르 정보 수정 (Update)
update genre set rental_fee = 2000, late_fee = 200 where genre_code = "Sports";
-- 장르 삭제 (Delete)
delete from genre where GENRE_CODE = "Sports";

-- 대여기록 CRUD
-- 대여기록 추가 (Create)
insert into rent(user_num, video_code, genre_code, rentdate, isreturn, add_late_fee, check_late, return_due_date, return_date)
values(23, "WA20121117", "War", "2023-12-12","Y", null, "N", "2022-12-15", "2022-12-14");
insert into rent(user_num, video_code, genre_code, rentdate, isreturn, add_late_fee, check_late, return_due_date, return_date)
values(6, "CO20195555", "Comed", "2022-11-17","Y", 600, "Y", "2022-11-20", "2022-11-22");
insert into rent(user_num, video_code, genre_code, rentdate, isreturn, add_late_fee, check_late, return_due_date, return_date)
values(23, "DR20221007", "Drama", "2023-12-12","Y", null, "N", "2022-12-15", "2022-12-15");
insert into rent(user_num, video_code, genre_code, rentdate, isreturn, add_late_fee, check_late, return_due_date, return_date)
values(23, "DR20222229", "Drama", "2023-02-07","N", null, "N", "2023-02-10", null);
insert into rent(user_num, video_code, genre_code, rentdate, isreturn, add_late_fee, check_late, return_due_date, return_date)
values(6, "DR20222229", "Drama", "2023-01-10","Y", 1200, "Y", "2023-01-13", "2023-01-19");
insert into rent(user_num, video_code, genre_code, rentdate, isreturn, add_late_fee, check_late, return_due_date, return_date)
values(13, "AN20203335", "Animation", "2023-01-17","Y", null, "N", "2023-01-20", "2023-01-19");
-- 대여 기록 조회 (Read)
select * from rent;
-- 연체비가 있는 대여 기록만 조회 (Read)
select * from rent where ADD_LATE_FEE is not null;
-- 대여 기록 수정 (Update)
update rent set return_date = "2022-12-13" where num = 25;
update rent set rentdate = "2022-12-12" where num = 41;

-- ====================================================================================================================
-- 인기목록 조회
select * from video order by total_view desc limit 5;

-- ====================================================================================================================
-- 매출확인
select rentdate 날짜, sum(g.RENTAL_FEE) + ifnull(sum(ADD_LATE_FEE),0) 매출 
from rent r 
inner join genre g on r.GENRE_CODE = g.GENRE_CODE where RENTDATE = "2022-12-07";

-- ====================================================================================================================
-- 연체관리
select user_num, video_code, rentdate, return_due_date, ISRETURN 
from rent 
where return_due_date < now() and ISRETURN = "N";

-- ====================================================================================================================
-- 회원 나이별 선호 장르
select 회원나이, 장르, max(횟수) 횟수
from (select substr(m.birthday, 1, 4) 회원나이, r.GENRE_CODE 장르, count(r.GENRE_CODE) 횟수
from rent r, member m
where m.USER_NUM = r.USER_NUM
group by 회원나이, r.genre_code
order by 횟수 desc) t group by 회원나이;

-- ====================================================================================================================
-- 회원별 추천 장르
select user_num, USER_NAME, GENRE_CODE, max(횟수)
from (select m.USER_NUM, m.USER_NAME, r.VIDEO_CODE, r.GENRE_CODE, count(r.GENRE_CODE) 횟수 from rent r, member m
where m.user_num = r.user_num group by m.user_num, r.genre_code order by count(r.GENRE_CODE) desc) t group by user_num;

-- ====================================================================================================================
-- 장르별 인기비디오
select VIDEO_CODE, VIDEO_TITLE, GENRE_CODE, TOTAL_VIEW
from (select VIDEO_CODE, VIDEO_TITLE, GENRE_CODE, TOTAL_VIEW from video order by genre_code) t group by genre_code;

-- ====================================================================================================================
-- 신간(대여료 +1000) 연체료 +200, 대여일 2, 시간기준 출시일로부터 1달
select v.genre_code, v.video_title, v.release_date, g.rental_fee + 1000 대여료, g.late_fee+200 연체료, g.lend_time-1 대여기간, '신간' 비고
from video v inner join genre g on v.GENRE_CODE = g.GENRE_CODE
where v.release_date >= subdate(date(now()), interval 30 day);

-- ====================================================================================================================
-- 연체율이 가장 높은 장르에서 가장 인기 없는 감독의 영화중에 최신작
set @topgenre = (select genre_code from (select video_code, genre_code, RETURN_DUE_DATE, RETURN_DATE, ifnull(return_date - RETURN_DUE_DATE, datediff(date(now()), return_due_date)) 연체일수 
from rent where (return_due_date < now() and ISRETURN = "N") or ADD_LATE_FEE is not null order by 연체일수 desc limit 1) t ); -- 연체율이 가장 높은 장르

set @saddirector = (select director from video  where genre_code = @topgenre order by total_view limit 1); -- 연체율이 가장 높은 장르에서 가장 인기 없는 감독 

select * from video where director = @saddirector order by RELEASE_DATE desc limit 1; -- 인기없는 감독 영화중에 최신작

-- ====================================================================================================================
-- 20대 여자들이 선호하는(대여 횟수가 높은) 영화 top1의 감독을 알려주세요
select r.video_code, r.GENRE_CODE, m.GENDER, v.total_view, v.DIRECTOR from rent r, member m, video v 
where GENDER = "female" and r.VIDEO_CODE = v.VIDEO_CODE
group by r.VIDEO_CODE order by v.TOTAL_VIEW desc limit 1;

-- ====================================================================================================================
-- 제목이가장 긴 영화 와 가장 짧은 영화가 무엇인가요
set @longTitle = (select video_code from video order by length(video_title) desc limit 1); -- 제목이 가장 긴 영화
set @shortTitle = (select video_code from video order by length(video_title) asc limit 1); -- 제목이 가장 짧은 영화
select video_code, genre_code, video_title from video where video_code = @longTitle or video_code = @shortTitle;

-- ====================================================================================================================
-- 2번 회원 : 제가 볼 수 있는 등급이며 대여료가 2500원인 비디오는 무엇이 있나요?
set @num2age =(select floor((cast(replace(current_date, '-', '')as unsigned) - cast(replace(birthday, '-','')as unsigned))/10000) from member where user_num =2); -- 2번 회원 나이

select v.*, g.rental_Fee from video v inner join genre g on v.genre_code = g.genre_code where g.rental_Fee = 2500 and v.movie_rated <= @num2age; 

-- ====================================================================================================================
-- 23번 회원 : 저희 가족(핸드폰번호가 같음)이 그동안 대여한 비디오명과 대여한 날짜 전부 알려주세요.
set @member = 23; -- 회원 번호
set @pNum = (select phone_num from member where user_num = 23); -- 핸드폰 번호
set @family = (select user_num from member where phone_num = @pNum and user_num != @member); -- 가족 회원 번호

select r.USER_NUM, v.video_title, r.rentdate from video v inner join rent r on v.VIDEO_CODE=r.VIDEO_CODE 
where r.USER_NUM = @member or r.USER_NUM = @family;

-- ====================================================================================================================
-- 가장 많이 연체되는 비디오 top10
-- 1. (연체 횟수가 가장 많은 비디오)
select video_code, ISRETURN, ADD_LATE_FEE, count(VIDEO_CODE) 연체횟수 from rent 
where ISRETURN = "N" or ADD_LATE_FEE is not null
group by video_code
order by 연체횟수 desc limit 10;

-- 2. (연체 일수 누적이 가장 많은 비디오)
select video_code, ISRETURN, ADD_LATE_FEE, RETURN_DATE, RETURN_DUE_DATE, RENTDATE, sum(ifnull(RETURN_DATE-RENTDATE, datediff(date(now()), return_due_date))) 연체일수
from rent 
where ISRETURN = "N" or ADD_LATE_FEE is not null
group by video_code
order by 연체일수 desc limit 10;


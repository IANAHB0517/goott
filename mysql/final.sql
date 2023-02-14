-- 20대 여성이 선호하는 영화 top 1의 감독
select v.video_title , v.director  ,twff.count from video v inner join (select video_code, count(video_code) as count
from rent r inner join
	-- 20대 
	(select * , '20대' as 연령대 from member where FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(birthday,'-','') AS UNSIGNED)) / 10000 ) between 20 and 29)
	 as ageGroup
on r.user_num = ageGroup.user_num
where ageGroup.연령대 = '20대' and ageGroup.gender = 'female'
group by video_code
order by count desc limit 1) as twff
on v.video_code = twff.video_code;  


-- 가장 많이 연체된 비디오 top 10
select v.video_title , LateVideo.연체일
from video v inner join 
	(select video_code, COALESCE(datediff(return_date ,return_due_date ),datediff(COALESCE(return_date, now()), return_due_date) ) as 연체일  from rent  
	where datediff(return_date ,return_due_date ) > 0 or ( isreturn = 'N' and datediff(return_due_date ,now()) < 0 )
    group by video_code
	order by 연체일 desc) as LateVideo 
on v.video_code = LateVideo.video_code
order by 연체일 desc limit 10;


-- 23번 회원 : 저희 가족(핸드폰번호가 같음)이 그동안 대여한 비디오명과 대여한 날짜 전부 알려주세요.
select * from rent r, (select user_num from member
where PHONE_NUM = (select PHONE_NUM from member where user_num = 23)) f
where r.user_num = f.user_num
order by r.rentdate ;

-- 2번 회원 : 제가 볼 수 있는 등급이며 대여료가 2500원인 비디오는 무엇이 있나요?
select * 
from video v natural join genre g 
where v.MOVIE_RATED <= (SELECT FLOOR( (CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE( (select BIRTHDAY from member where user_num = 2),'-','') AS UNSIGNED)) / 10000 ) as engAge)
and g.RENTAL_FEE = 2500;


-- 장르별 매출액이 많은 상위 3개의 장르에 대해서, 최근 한달 간 대여가 없는 고객의 전화번호 조회
select  num , m.USER_NAME, m.PHONE_NUM , r.rentdate, datediff(rentdate, now()) as '가장 최근 대여일' from member m  inner join rent r 
on r.user_num = m.user_num
where datediff(rentdate, now()) < -30
group by user_name
order by num;


-- 제목이가장 긴 영화 와 가장 짧은 영화가 무엇인가요
(select VIDEO_CODE,  VIDEO_TITLE, floor(length(VIDEO_TITLE)/3) as 글자수  from video order by 글자수 desc limit 1)
union
(select VIDEO_CODE,  VIDEO_TITLE,  floor(length(VIDEO_TITLE)/3) as 글자수  from video order by 글자수 limit 1);


-- 장르별로 특정 요일에 할인
select genre_name, RENTAL_FEE -500 as 대여로 from genre where genre_code = 'Comed';


-- 특정 회원이 가장 좋아하는 영화의 장르 코드 , 비디오, 시청횟수
select m.USER_NAME, v.video_title,g.GENRE_NAME , t.count 
	from video v join(select f1.user_num, f1.genre_code, f1.video_code, f1.count from
		(select * ,count(user_num) count 
		from rent group by user_num 
		order by  user_num desc) as f1
		where user_num = 12 
		order by f1.count desc limit 1) t
		on v.video_code = t.video_code
        join genre g
        on g.genre_code = v.genre_code
        join member m
        on m.user_num = t.user_num;
        
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


-- 신간(대여료 +1000), 연체료 +200, 대여일2, 시간기준 출시일로 부터 1달
select newvideo.video_title , g.RENTAL_FEE + 1000, (g.LEND_TIME -1) , g.LATE_FEE+200, newvideo.RELEASE_DATE from genre g inner join (select * from video where datediff(now(), RELEASE_DATE ) < 30) as newvideo 
on g.genre_code = newvideo.genre_code;


-- select video_code , user_num  ,  count(*) count from rent group by USER_NUM order by count desc;
-- 장르별 인기비디오 top5
(select video_code, genre_code , count(*) count from rent where GENRE_CODE = 'Drama' group by video_code order by count desc limit 5 )
union
(select video_code, genre_code , count(*) count from rent where GENRE_CODE = 'Animation' group by video_code order by count desc  limit 5 )
union
(select video_code, genre_code , count(*) count from rent where GENRE_CODE = 'Action' group by video_code order by count desc  limit 5 );


-- 회원 별 가장 많이본 영화
select m.user_name user , v.video_title , count(r.video_code) count from member m , rent r , video v 
where m.user_num = r.user_num and
r.video_code = v.video_code
group by user
order by count desc;


-- 회원별 추천 장르
select  * 
from 
	(select m.user_name as 회원명, r.genre_code 장르 , count(*) count
	 from member m , rent r where m.USER_NUM = r.USER_NUM group by 회원명) target 
where target.count > @avgCount
order by count desc;


-- 연체 중인 회원의 정보
select * from member where user_num in (select user_num from rent where ISRETURN = 'N' and RETURN_DATE is null);


-- 회원이 연체한 비디오 코드와 연체 일수
select distinct user_num ,video_code ,  DATEDIFF (now(), RENTDATE ) as 연체일수 from rent where ISRETURN = 'N' and RETURN_DATE is null and  DATEDIFF (now(), RENTDATE ) > 0;


-- 인기목록 조회
select VIDEO_TITLE , TOTAL_VIEW from video order by TOTAL_VIEW desc limit 5;


-- (영화 인기도(대여횟수)가 딱 가운데 순위인 영화의 감독 작품중에 가장 잘 나가는 영화)
-- 영화 인기도(대여횟수)가 딱 가운데 순위인 영화의 감독의 영화
set @avgCount = (select round(avg(t.count)) avg from (select video_code, count(video_code) count from rent group by video_code) t);

select  @avgCount from dual;

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
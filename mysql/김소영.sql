-- 2차 미니 프로젝트
-- ==========================================================================================================
--  member table CRUD
-- ==========================================================================================================
-- gottclass6 스키마 적용
use gottclass6;

-- 회원가입
set @uname = '너의에스큐엘';
set @phone = '010-1326-9542';
set @bday = '2001-08-14';
set @gend = 'female';
set @addr = '서울시 구로구 구로동';

-- 기존 가입/탈퇴/신규 여부 확인
-- -- 이름, 전화번호가 둘다 일치하면 가입자 
-- -- 탈퇴한 날짜 데이터가 있으면 탈퇴자
-- -- 그 밖의 경우: 가입가능
select case 
	when (select user_name from member where user_name = @uname) = @uname and (select phone_num from member where user_name= @uname) = @phone 
    and  ( select deleted_at is null from member where user_name = @uname) then '가입자입니다' 
	when ( select deleted_at  from member where user_name = @uname and phone_num = @phone) is not null  then '탈퇴자입니다' 
    else '가입 가능합니다' end '회원여부체크'  ;

 select deleted_at is null from member where user_name = '김김김';

-- 쿼리 결과가 맞는지 확인해보기.
set @uname = '김희망';  
set @phone = '010-1278-9845';
select case 
	when  ( select deleted_at  is  null  from member where user_name = @uname and phone_num = @phone)    then '가입자입니다' 
	when ( select deleted_at  from member where user_name = @uname and phone_num = @phone) is not null  then '탈퇴자입니다' 
    else '가입 가능합니다' end '회원여부체크'  ;
-- 결과 : '가입자입니다' 

set @uname = '비둘기';  -- 비둘기는 탈퇴회원 (탈퇴일: 2023-02-07)
set @phone = '010-9999-9999';
select case 
	when  ( select deleted_at  is  null  from member where user_name = @uname and phone_num = @phone)    then '가입자입니다' 
	when ( select deleted_at  from member where user_name = @uname and phone_num = @phone) is not null  then '탈퇴자입니다' 
    else '가입 가능합니다' end '회원여부체크'  ;
-- 결과: '탈퇴자입니다' 

set @uname = '김삼성';  
set @phone = '010-1111-1111';
select case 
	when  ( select deleted_at  is  null  from member where user_name = @uname and phone_num = @phone)    then '가입자입니다' 
	when ( select deleted_at  from member where user_name = @uname and phone_num = @phone) is not null  then '탈퇴자입니다' 
    else '가입 가능합니다' end '회원여부체크'  ;
-- 결과 :     '가입 가능합니다' 

-- 회원가입하기
insert into member (USER_NAME, PHONE_NUM, BIRTHDAY, GENDER, ADDR) values (@uname, @phone , @bday, @gend, @addr);

-- 가입 완료 체크하기
select * from member where user_name = @uname and  phone_num = @phone;

-- 가입 정보 수정하기
-- - 핸드폰 번호가 바뀜 
set @uname = '너의에스큐엘';
set @phone = '010-1326-9542';
update member set phone_num = @phone where user_name = @uname;


-- 가입 탈퇴하기 
-- delete from member where user_name = @usern;
update member set deleted_at = current_date() 
where user_name = @uname and phone_num = @phone ;


 -- 컬럼 삭제하기로 결정함. (블랙리스트, 누적 연체일수)
-- alter table member drop chk_black;
-- alter table member drop overdue;

-- ==========================================================================================================
--  rent table CRUD
-- ==========================================================================================================
-- 유모레 회원이 뉴욕어쩌구 영화를 대여하려고 함. 제목을 정확히 모른다. 
set @searchname = concat('%','헤어질', '%');  -- mysql 에서는 || 가 OR , concat()함수로 문자열 합침.
set @uname = '유모레';
set @age = (SELECT FLOOR((CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE((select birthday from member where user_name = @uname),'-','') AS UNSIGNED)) / 10000 ));

-- 1) 비디오이름으로 정보 확인 (정확한 비디오 이름을 제대로 모를 때, 찾아 볼 수 있도록 하기 위함)
set @vname = (select video_title from video where video_title like @searchname );
-- select video_title from video where video_title like  @searchname ;
select @vname;


-- 2)  대여가능 여부 확인

-- 만나이와 등급 비교
set @rate = (select movie_rated from video where video_title = @vname);
set @returnYN = (select isreturn from rent where video_code =  (select video_code from video where video_title = @vname));
select @uname, @vname,  @age '고객 만나이' , @returnYN ,  (select count(*) from rent where video_code =  (select video_code from video where video_title = @vname)) , 
case when 
@age > (select movie_rated from video where video_title = @vname) and ( @returnYN ='Y' or (select count(*) from rent where video_code =  (select video_code from video where video_title = @vname)) = 0 )
then '대여가능' else '대여불가' 
end as '대여가능여부';

select @uname, @vname,  @age '고객 만나이' , @returnYN ,  
case when 
@age > (select movie_rated from video where video_title = @vname) and ( @returnYN ='Y' or (select count(*) from rent where video_code =  (select video_code from video where video_title = @vname)) = 0 )
then '대여가능' else '대여불가' end as '대여가능여부';

-- 2) 대여기간 적용하여 반납일 계산하기 
-- (오늘보다 이전 날짜 date1 에 대여한다고 가정)
set @date1 = '2023-02-02' ; -- 대여날짜(rentdate)
set @period = (select lend_time from genre where genre_code = (select genre_code from video where video_title = @vname));  -- 대여기간
set @duedate = date_add(@date1, interval  @period day) ; -- 반납예정일 (return_due_date)
-- set @returndate = date_add(@date1, interval 3 day);  -- 3일 후 반납했다고 가정 
set @returndate = '2023-02-05';

-- 3) 대여기록 남기기
insert into rent ( user_num, video_code, genre_code, rentdate, return_due_date, return_date)  values(
(select user_num from member where user_name=@uname), 
(select video_code from video where video_title=@vname), 
(select genre_code from video where video_title=@vname), 
@date1, @duedate  , @returndate
); 

-- 대여 취소하기
delete from rent where video_code = (select video_code from video where video_title=@vname);

-- ------대여 결과 출력 (고객에게 공지) -------
select r.user_num, m.user_name, v.video_title, v.genre_code, r.rentdate, r.isreturn, r.return_due_date  
from rent  r inner join member m on r.user_num = m.user_num inner join video v on r.video_code = v.video_code
where  r.rentdate = @date1 and m.user_name = @uname ; 


-- 오늘 반납 예정 비디오를 검색 (inner join 이용)
select v.video_title, r.return_date from video v join rent r on v.video_code = r.video_code  where r.return_date = current_date();
select v.video_title, r.return_date from video v join rent r on v.video_code = r.video_code  where r.return_date = date_add(current_date(),  interval 2 day);

-- 한번도 대여되지 않은 비디오 목록 확인 (outer join 이용)
select * from video v left outer join rent r on v.video_code = r.video_code 
where r.rentdate is null;

-- 고객이 vname 비디오가 언제 반납 예정인지 궁금해할 때..
select v.video_title, r.RETURN_DUE_DATE, r.isreturn as '반납여부', r.USER_NUM, m.user_name 
from rent r join member m on r.user_num = m.user_num join video v  on v.video_code = r.video_code  
where v.video_title = @vname;

-- ==========================================================================================================
-- 매출
select concat(format(sum(g.rental_fee + r.add_late_fee), 0), '원') '총매출' 
from rent r inner join genre g on r.genre_code = g.genre_code;


select concat(format(sum(g.rental_fee + r.add_late_fee), 0), '원') '2023년 매출' 
from rent r inner join genre g on r.genre_code = g.genre_code 
where year(r.rentdate) = '2023';

select year(r.rentdate) ryear,  concat(format(sum(g.rental_fee + r.add_late_fee), 0), '원') '연간 매출' 
from rent r inner join genre g on r.genre_code = g.genre_code 
group by ryear ;

-- 선택 연도의 분기별 매출
select year(r.rentdate) ryear, quarter(r.rentdate) rquarter,  concat(format(sum(g.rental_fee + r.add_late_fee), 0), '원')  '선택 연도의 분기별 매출' 
from rent r inner join genre g on r.genre_code = g.genre_code 
where year(r.rentdate) = '2022'
group by rquarter ;

-- 총 분기별 매출
select year(r.rentdate), quarter(r.rentdate), concat(format(sum(g.rental_fee + r.add_late_fee), 0), '원') '총 분기별 매출' 
from rent r inner join genre g on r.genre_code = g.genre_code 
group by year(r.rentdate), quarter(r.rentdate)
order by year(r.rentdate), quarter(r.rentdate) ;

-- ****** rent테이블의 num=59 의 rentdate='2023-12-12'로, 잘못되어있음.
select min(rentdate) , max(rentdate) from rent ;

-- 월별매출 
select year(r.rentdate) ryear, month(r.rentdate) rmonth,  coalesce(concat(format(sum(g.rental_fee + r.add_late_fee), 0), '원'), 0)  '월별 매출' 
from rent r inner join genre g on r.genre_code = g.genre_code 
group by  ryear, rmonth ;

-- 주간매출
select year(r.rentdate) ryear , week(r.rentdate) rweek,  ifnull( concat(format(sum(g.rental_fee + r.add_late_fee), 0), '원'), 0) '주간 매출' 
from rent r inner join genre g on r.genre_code = g.genre_code where r.rentdate between '2022-01-01' and '2022-12-31' 
group by  ryear, rweek ;

select date(r.rentdate) rdate,  ifnull( concat(format(sum(g.rental_fee + r.add_late_fee), 0), '원'), 0) '일일매출' 
from rent r inner join genre g on r.genre_code = g.genre_code 
where date(r.rentdate) = '2022-06-13';

 -- 기간 일일매출
select date(r.rentdate) rdate,  ifnull( concat(format(g.rental_fee + r.add_late_fee, 0), '원'), 0) '일일매출' 
from rent r inner join genre g on r.genre_code = g.genre_code 
where date(r.rentdate) between '2022-06-01'  and '2023-01-29' 
group by rdate 
order by date(r.rentdate);
 

-- ======================================================================================================================================================================================================================================================
-- 회원나이별 선호장르
-- 10대  == 애니메이션 (4)
select m.user_name, m.birthday, r.genre_code
from rent r join member m on r.user_num = m.user_num 
where (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  between 10 and 19  ;

select count(r.genre_code), r.genre_code
from rent r join member m on r.user_num = m.user_num 
where (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  between 10 and 19  group by r.genre_code order by  count(r.genre_code)  desc;

-- 20대  == 액션 (11)
select count(r.genre_code), r.genre_code
from rent r join member m on r.user_num = m.user_num 
where (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  between 20 and 29  group by r.genre_code order by  count(r.genre_code)  desc;

-- 30대 == 호러, 판타지, 드라마
select count(r.genre_code), r.genre_code
from rent r join member m on r.user_num = m.user_num 
where (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  between 30 and 39  group by r.genre_code order by  count(r.genre_code)  desc;

-- 40대 == 대여를 한 기록이 없음 (as of 2023-02-09)
select m.user_name, m.birthday, r.genre_code
from rent r join member m on r.user_num = m.user_num 
where (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  between  40 and 49;

-- 40대 사람들은 대여를 한 기록이 없음 (as of 2023-02-09)
select m.user_num, m.user_name  from rent r inner join member m on r.user_num = m.user_num where (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  between 40 and 49 ;
select  m.user_num, m.user_name , r.num , TIMESTAMPDIFF(year, m.birthday,  current_date) from member m left outer join rent r on m.user_num = r.user_num;

select * from rent;
select user_num, genre_code, video_code from rent where user_num = (select user_num from member where user_name = '박내일');


select count(r.genre_code), r.genre_code
from rent r join member m on r.user_num = m.user_num 
where (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  between 40 and 49  group by r.genre_code order by  count(r.genre_code)  desc;

-- 50대 == 1건 판타지
select count(r.genre_code), r.genre_code
from rent r join member m on r.user_num = m.user_num 
where (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  between 50 and 59  group by r.genre_code order by  count(r.genre_code)  desc;

-- 60대  (대여기록 없음 as 2023-02-09)
set @age = 60 ;
select count(r.genre_code), r.genre_code
from rent r join member m on r.user_num = m.user_num 
where (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  between @age and @age+9  group by r.genre_code order by  count(r.genre_code)  desc;

-- ==========================================================================================================
-- 회원연령대별 선호장르 
--  "age_group_rent" 테이블( 회원1명의_연령대, 장르, 장르별대여횟수합) 을 생성해서 회원연령대별선호장르를 알아보자.
-- ==========================================================================================================
create table age_group_rent  (
		select  case
		when age between 10 and 19 then '10대'
		when age between 20 and 29 then '20대'
		when age between 30 and 39 then '30대'
		when age between 40 and 49 then '40대'
		when age between 50 and 59 then '50대'
		else '60대 이상'
		end as age_group, rgenre, count(*) as cnt
			from ( select  m.user_name as uname, r.genre_code as rgenre, (SELECT FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 ) )  as age 
			from rent r join member m on r.user_num = m.user_num 
			order by age )   a  
			group by age_group, rgenre
);


-- 연령대별 장르별 최대 대여수
select age_group, max(cnt) as favorite
				from age_group_rent
				group by age_group;
                
-- 연령대별 가장 선호하는 장르의 장르명을 가져오기 위한 쿼리 (위의 두 테이블 조인)
select f.age_group, f.rgenre , f.cnt
   from ( 	select age_group, max(cnt) as max_rent
				from age_group_rent  group by age_group
	) as x join age_group_rent as f on f.age_group = x.age_group and f.cnt  = x.max_rent; 
    
-- ==========================================================================================================
-- 연체관리
-- 연체료 계산하기 (물론 rent테이블의 add_late_fee가 있지만 계산해 보자)
 
select r.num, r.video_code , v. release_date,  r.genre_code, r.add_late_fee, 
datediff(r.return_date , r.return_due_date ) * g.late_fee ' 계산 연체료', r.add_late_fee - (datediff(r.return_date , r.return_due_date ) * g.late_fee) '연체료 차이(=r.add_late_fee - 계산연체료)'
from rent r join genre g  on r.genre_code = g.genre_code join video v  on v.video_code = r.video_code
where return_due_date < return_date  ;
-- 결과: 신간 비디오 ( RO20209654 ) 에 기본 연체료가 500원으로 되어있어서 연체료계산값 차이 발생


-- 현재 연체중인 회원 리스트 
-- delete_at is null => 탈퇴회원 적용하지 않았을 때의 리스트
select m.* , r.rentdate, r.isreturn, r.return_due_date, return_date, datediff(curdate() , r.return_due_date ) '연체일수', datediff(curdate() , r.return_due_date ) * g.late_fee '오늘까지의 연체료'
from rent r join member m on r.user_num = m.user_num join video v  on v.video_code = r.video_code join genre g on g.genre_code = r.genre_code 
where return_due_date < curdate() and return_date is null  ;

-- 탈퇴회원을 제외하면 현재 연체중인 회원이 없음.
select m.* , r.rentdate, r.isreturn, r.return_due_date, return_date, datediff(curdate() , r.return_due_date ) '연체일수', datediff(curdate() , r.return_due_date ) * g.late_fee '오늘까지의 연체료'
from rent r join member m on r.user_num = m.user_num join video v  on v.video_code = r.video_code join genre g on g.genre_code = r.genre_code 
where return_due_date < curdate() and return_date is null and deleted_at is null;

-- ==========================================================================================================
-- 장르별 인기 비디오 top 2
select gname, vname as ' 장르별 인기 비디오' 
 from (
			 select  gname, vname, genre, cnt, genre_cnt_rank
			from (
					 SELECT a.*,
							(CASE @cgenre WHEN a.genre THEN @rownum := @rownum + 1 ELSE @rownum := 1 END) genre_cnt_rank,
							(@cgenre := a.genre) cgenre
					  FROM 
							( 
							select r.genre_code as genre,  g.genre_name as gname, v.video_title as vname, count(r.genre_code) as cnt
							from rent r join genre g  on r.genre_code = g.genre_code join video v  on v.video_code = r.video_code
							group by r.genre_code , v.video_title
							) a, 
							(SELECT @cgenre :='',@rownum := 0 FROM DUAL) b
					  ORDER BY a.genre, a.cnt DESC 
			) c
 ) d 
where  genre_cnt_rank = 1 or genre_cnt_rank = 2;

-- 영화 인기도(대여횟수)가 딱 가운데 순위인 영화의 감독 작품중에 가장 잘 나가는 영화
-- 비디오별 대여횟수를 뷰로 생성
 create view sk_cnt_video as
 ( select v.video_title as vname, g.genre_name as gname,  count(r.video_code)  cnt_video
from rent r join genre g  on r.genre_code = g.genre_code join video v  on v.video_code = r.video_code
group by v.video_title
 );
 
 select * from sk_cnt_video;
 
 -- 두가지 방법으로 랭킹 정하기
-- 1) 동점자는 같은 랭킹
SELECT cnt_video, vname,
		( select count(*)+1 
		from sk_cnt_video  where cnt_video > b.cnt_video ) as vrank 
FROM  
	sk_cnt_video as b
ORDER BY 
	 vrank asc;
     
 
 -- 2) 내림차순 정렬한 대로 랭킹 (동점자 미처리)
SELECT cnt_video, vname,
	(@rank := @rank + 1) AS vrank
FROM 
	sk_cnt_video AS a , 
    (SELECT @rank := 0 ) AS b
ORDER BY 
	a.cnt_video DESC;
	

-- 2번째 랭킹 이용해서 가운데 순위 영화 제목찾기
set @mid_vname = (SELECT vname 
FROM (
SELECT cnt_video, vname,
	(@rank := @rank + 1) AS vrank
FROM 
	sk_cnt_video AS a , 
    (SELECT @rank := 0 ) AS b
ORDER BY 
	a.cnt_video DESC
) AS popular
WHERE vrank = ROUND(0.5 * ( select count(*) from sk_cnt_video) ) );

select count(*) from sk_cnt_video;

-- 딱가운데 순위 영화의 감독 찾기
select @mid_vname, director 
from video
where video_title = @mid_vname ;

-- 영화감독이름 변수 저장
set @director_name = (select director 
from video
where video_title = @mid_vname);

-- 위 감독의 영화찾기
select video_title
from video
where director =  @director_name;


select v.video_title, r.video_code
from video v inner join rent r on v.video_code = r.video_code inner join sk_cnt_video cnt on v.video_title = cnt.vname
where v.director =  @director_name
order by cnt.cnt_video limit 1;
-- 결과 반지의 제왕 : 두개의 탑

-- ==========================================================================================================
-- 한 장르를 많이 빌린 사람들에 대해서, 전화번호 조회
select r.genre_code, r.user_num, count(r.user_num) 
from rent r inner join video v on r.video_code= v.video_code
group by  r.genre_code, r.user_num;
-- 결과:  원동건씨가 Action 장르에서 가장 많이 대여했다. 
-- 원동건씨 전화번호 찾기
select m.phone_num, m.user_name, r.genre_code, r.user_num, count(r.user_num)
from rent r inner join video v on r.video_code= v.video_code inner join member m on m.user_num = r.user_num
group by  r.genre_code, r.user_num
order by count(r.user_num) desc limit 1;

-- 연체율이 가장 높은 장르에서 가장 인기 없는 감독의 영화중에 최신작 
-- 1) 연체율이 가장 높은 장르 r.check_late
select genre_code, count(check_late)
from rent 
group by (genre_code)
order by count(check_late) desc limit 1;  -- Action 장르

-- 2) Action 장르중에 가장 대여수가 낮은 영화의 감독
select v.director, v.genre_code, count(v.director)
from rent r inner join video v on r.video_code = v.video_code
where v.genre_code = (select genre_code
from rent 
group by (genre_code)
order by count(check_late) desc limit 1   )
group by v.director
order by count(v.director) asc limit 1 
;  -- 이정범, 랜들 에멧, 에드워드 드레이크 감독이 action 장르에서 대여횟수 각 1번

-- 3) 이정범 감독의 신작 영화
select video_title, genre_code, director, release_date
from video
where director = ( select v.director
from rent r inner join video v on r.video_code = v.video_code
where v.genre_code = (select genre_code
from rent 
group by (genre_code)
order by count(check_late) desc limit 1   )
group by v.director
order by count(v.director) asc limit 1   ) and release_date between '2023-01-15' and '2023-02-14'
 ;  -- 영화 '새비지 맨' 영화밖에 없다 . 



-- ==========================================================================================================
-- 특정 회원이 가장 많이 본 영화 장르 중에 인기도가 가장 높은 영화가 뭔가요
-- 1) 특정회원 user_num = 17 번이라 하자
select r.genre_code, count(r.genre_code) 
from rent r inner join member m on r.user_num = m.user_num
where m.user_num = 17
group by r.genre_code 
order by count(r.genre_code) desc limit 1; 
-- 결과는 Fantasy 장르 (2번 대여)

-- 2) genre_code = 'Fantasy' 장르 중에서 인기도가 높은 영화
select r.video_code, v.video_title, count(r.video_code)
from rent r inner join video v on r.video_code = v.video_code
where r.genre_code = (  select r.genre_code
from rent r inner join member m on r.user_num = m.user_num
where m.user_num = 17
group by r.genre_code 
order by count(r.genre_code) desc limit 1  )
group by r.video_code
order by count(r.video_code)  desc limit 1 ; 
-- 결과 : 반지의 제왕: 반지원정대 (14번 대여됨)


-- ==========================================================================================================
-- 20대 여자들이 선호하는(대여 횟수가 높은) 영화 top1의 감독을 알려주세요
-- 1) 20대 여자들이 선호하는 영화 top 1

select r.user_num, r.video_code, v.video_title 
from rent r inner join member m on r.USER_NUM = m.USER_NUM inner join video v on v.video_code  = r.video_code
where (FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 )   between 20 and 29) and m.gender = 'female'
;


select r.video_code, v.video_title , count(r.user_num), v.director
from rent r inner join member m on r.USER_NUM = m.USER_NUM inner join video v on v.video_code  = r.video_code
where (FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(m.birthday , '-' , '') AS UNSIGNED) ) / 10000 )   between 20 and 29) and m.gender = 'female'
group by v.video_code
order by count(r.user_num) desc limit 1 ;  -- 결과 video_title = '누구도 기억하지 않는 하루' 감독 = '이수지'


-- ==========================================================================================================
-- 제목이가장 긴 영화 와 가장 짧은 영화가 무엇인가요
-- 1) 제목이 가장 긴 영화
select video_title, char_length(video_title) as title_length
from video
order by title_length desc limit 1;
-- 또는 다중행 서브쿼리 이용
select video_title, char_length(video_title) as title_length
from video 
where char_length(video_title) >= all(select char_length(video_title) from video );

-- 2) 제목이 가장 짧은 영화
select video_title, char_length(video_title) as title_length
from video
order by title_length limit 1;

select video_title, char_length(video_title) as title_length
from video 
where char_length(video_title) <= all(select char_length(video_title) from video );

-- ==========================================================================================================
-- r.add_late_fee의 데이터가 정확하지 않을 수 있어서, 다른 방식으로 매출을 계산.
-- select r.genre_code,  coalesce(concat(format(sum(g.rental_fee + r.add_late_fee), 0), '원'), 0)  as '매출' from rent r inner join genre g on r.genre_code = g.genre_code;


-- 신간을 고려한 매출 계산
-- 연체료: (datediff(r.return_date , r.return_due_date ) * g.late_fee) 적용
-- 매출 = 기본대여료(g.rental_fee)  + 연체료
-- 신간 아닌 비디오 매출
select  r.genre_code,  r.rentdate, r.return_due_Date, r.return_Date, (g.rental_fee + (datediff(r.return_date , r.return_due_date ) * g.late_fee) )as income_per_video
from rent r inner join genre g on r.genre_code = g.genre_code inner join video v on v.video_code = r.video_code
where r.rentdate < r.return_date and r.rentdate < r.return_due_date and v.release_date < date_add(current_date(), interval -30 day)
;

-- 신간 비디오 매출
select   r.video_code, r.genre_code,  r.rentdate, r.return_due_Date, r.return_Date, (g.rental_fee+ 1000 + (datediff(r.return_date , r.return_due_date ) * (g.late_fee+200)) )as income_per_new
from rent r inner join genre g on r.genre_code = g.genre_code inner join video v on v.video_code = r.video_code
where r.rentdate < r.return_date and r.rentdate < r.return_due_date and v.release_date > date_add(current_date(), interval -30 day)
;

-- 총 매출
set @total_sales = (select sum(g.rental_fee + (datediff(r.return_date , r.return_due_date ) * g.late_fee) ) 
from rent r inner join genre g on r.genre_code = g.genre_code inner join video v on v.video_code = r.video_code
where r.rentdate < r.return_date and r.rentdate < r.return_due_date and v.release_date < date_add(current_date(), interval -30 day)) ;

set @total_sales_new_video = (select  sum(g.rental_fee+ 1000 + (datediff(r.return_date , r.return_due_date ) * (g.late_fee+200)) )
from rent r inner join genre g on r.genre_code = g.genre_code inner join video v on v.video_code = r.video_code
where r.rentdate < r.return_date and r.rentdate < r.return_due_date and v.release_date > date_add(current_date(), interval -30 day));

select @total_sales;
select @total_sales_new_video;

select format( (@total_sales + @total_sales_new_video),0) as '총매출';

-- 기존 비디오 매출목록
create  view sk_sales  as
(select  r.video_code, r.genre_code,  r.rentdate, r.return_due_Date, r.return_Date, (g.rental_fee + (datediff(r.return_date , r.return_due_date ) * g.late_fee) ) as income_per_video
from rent r inner join genre g on r.genre_code = g.genre_code inner join video v on v.video_code = r.video_code
where r.rentdate < r.return_date and r.rentdate < r.return_due_date and v.release_date < date_add(current_date(), interval -30 day)
) ;

 -- 신간 비디오 매출목록
create view sk_sales_new as 
(
select   r.video_code, r.genre_code,  r.rentdate, r.return_due_Date, r.return_Date, (g.rental_fee+ 1000 + (datediff(r.return_date , r.return_due_date ) * (g.late_fee+200)) )as income_per_new
from rent r inner join genre g on r.genre_code = g.genre_code inner join video v on v.video_code = r.video_code
where r.rentdate < r.return_date and r.rentdate < r.return_due_date and v.release_date > date_add(current_date(), interval -30 day)
);

-- select * from sk_sales; -- 기존 비디오 매출목록
-- select * from sk_sales_new;  -- 신간 비디오 매출목록


--  UNION이용  :  A와 B에 존재하는 row를 모두 출력하되, 공통으로 존재하는 row는 중복을 제거하고 하나만 표시 (합집합의 개념)
-- 매출이 많은  top3 장르중에서 인기 영화 top5


select total.genre_code
		from (
		select * from sk_sales 
		union
		select * from sk_sales_new ) total
		group by total.genre_code
		order by sum(total.income_per_video)   desc limit 3;

select v.video_title, r.genre_code, count(v.video_title)
from video v join rent r on v.video_code = r.video_code
where r.genre_code in ('Romance', 'Fantasy', 'Action')
group by r.genre_code, v.video_title
order by count(v.video_title) desc limit 5;
-- 반지의 제왕 : 반지원정대, 소림축구, 어바웃타임, 캐리비안의 해적: 블랙펄의 저주, 이터널 선샤인 영화를 다음의 고객전화번호로 홍보한다. 

-- 최근 한달간 대여가 없는 고객의 전화번호 조회
select m.user_name, m.phone_num, r.rentdate, r.return_date
from member m inner join rent r on m.user_num = r.user_num
where r.rentdate  < date_add(current_date(), interval -30 day) ;

-- ==========================================================================================================
-- 2번 회원 : 제가 볼 수 있는 등급이며 대여료가 2500원인 비디오는 무엇이 있나요?

select * from member where user_num = 2;
select FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(birthday , '-' , '') AS UNSIGNED) ) / 10000 )  from member  where user_num = 2;
-- 만 나이 11살
select v.video_title, g.rental_fee, v.movie_rated
from genre g inner join video  v on g.genre_code = v.genre_code 
where (g.rental_fee = 2500) and 
(  v.movie_rated < (select FLOOR(   ( CAST(REPLACE(CURRENT_DATE , '-' , '' )  AS UNSIGNED) - CAST(REPLACE(birthday , '-' , '') AS UNSIGNED) ) / 10000 )  from member  where user_num = 2)  );

-- ==========================================================================================================
-- 23번 회원 : 저희 가족(핸드폰번호가 같음)이 그동안 대여한 비디오명과 대여한 날짜 전부 알려주세요.
select  v.video_title, r.rentdate
from video v inner join rent r on v.video_code = r.video_code inner join member m on m.user_num = r.user_num
where  phone_num = (select phone_num from member where user_num =23);

-- ==========================================================================================================
-- 가장 많이 연체되는 비디오 top10
select v.video_title, count(r.check_late)
from video v inner join rent r on v.video_code = r.video_code
where r.check_late = 'Y'
group by v.video_title
order by count(r.check_late) desc limit 1;
-- 결과:  어서와요 훈련소에 (2회 연체)

/*
select v.video_title, r.check_late
from video v inner join rent r on v.video_code = r.video_code
where r.check_late = 'Y' 
group by v.video_title;
*/

-- ==========================================================================================================
-- 올해 가장 매출액이 높은 월과 낮은 월 조회 (연초이므로 2022년에 대해서 적용해보자)
select * from sk_sales ;

-- 2022년 월별 매출액
select year(return_date) ryear, month(return_date) rmonth, sum(income_per_video)  as 'total_income'
from (
select * from sk_sales 
union
select * from sk_sales_new ) total
where return_date between '2022-01-01' and '2022-12-31'
group by ryear, rmonth
order by total_income desc;

select year(return_date) ryear, month(return_date) rmonth, sum(income_per_video)  as 'total_income'
from (
select * from sk_sales 
union
select * from sk_sales_new ) total
where return_date between '2022-01-01' and '2022-12-31'
group by ryear, rmonth
order by total_income desc limit 1;
-- 결과:  가장 매출액이 높은 월은 2022년 8월

select year(return_date) ryear, month(return_date) rmonth, sum(income_per_video)  as 'total_income'
from (
select * from sk_sales 
union
select * from sk_sales_new ) total
where return_date between '2022-01-01' and '2022-12-31'
group by ryear, rmonth
order by total_income asc limit 1;
-- 결과:  가장 매출액이 낮은 월은 2022년 9월


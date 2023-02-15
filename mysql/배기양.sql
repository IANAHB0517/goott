-- 한 장르를 많이 빌린 사람들에 대해서, 전화번호 조회
select user_name,phone_num from member m,
(select user_num from rent group by user_num,GENRE_CODE having count(GENRE_CODE) > 1) t
			-- 하나의 장르를 2개 이상 빌린 사람들  
where m.user_num = t.USER_NUM;


-- 연체율이 가장 높은 장르에서 가장 인기 없는 감독의 영화 중에 최신작
SELECT *
FROM   video
WHERE  director = (-- 최신작 
                  SELECT director -- 가장 인기 없는 감독
                   FROM   video
                   WHERE  genre_code = (
                                       SELECT genre_code -- 연체율이 가장 높은 장르
                                        FROM   rent
                                        WHERE  add_late_fee IS NOT NULL
                                        GROUP  BY genre_code
                                        ORDER  BY Sum(add_late_fee) DESC
                                        LIMIT  1)
                   GROUP  BY director
                   ORDER  BY Sum(total_view)
                   LIMIT  1)
ORDER  BY release_date DESC
LIMIT  1;

-- 특정 회원이 가장 많이 본 영화 장르 중에 인기도가 가장 높은 영화가 뭔가요
SELECT 
    t1.video_code,
    v.VIDEO_TITLE,
    v.MOVIE_RATED,
    v.RELEASE_DATE,
    v.DIRECTOR
FROM
    (SELECT -- 그 장르에서 가장 대여 횟수가 높은 영화 
        *, COUNT(video_code) AS vcount
    FROM
        rent
    GROUP BY VIDEO_CODE
    HAVING GENRE_CODE = (SELECT  -- 12번 회원이 가장 많이 본 장르
            GENRE_CODE
        FROM
            rent
        WHERE
            user_num = 12
        GROUP BY GENRE_CODE
        ORDER BY COUNT(video_code) DESC
        LIMIT 1)
    ORDER BY vcount DESC
    LIMIT 1) t1,
    video v
WHERE
    t1.video_code = v.VIDEO_CODE;

-- 20대 여자들이 선호하는(대여 횟수가 높은) 영화 top1의 감독을 알려주세요

SELECT 
    director, rentCou, vname, video_title
FROM
    (SELECT -- 20대 여자들이 선호하는 영화
        birth, MAX(cou) AS rentCou, video AS vname
    FROM
        (SELECT -- 연령별 여자들이 선호하는 영화 
        @age AS birth, COUNT(VIDEO_CODE) AS cou, VIDEO_CODE AS video
    FROM
        member m, rent r
    WHERE
        m.user_num = r.user_num
            AND m.GENDER = 'female'
    GROUP BY @age:=YEAR(NOW()) - LEFT(birthday, 4) + 1 , VIDEO_CODE
    ORDER BY cou DESC) AS t1
    WHERE
        birth LIKE '2_' -- 20대
    GROUP BY birth
    ORDER BY MAX(cou) DESC
    LIMIT 1) t2,
    video v
WHERE
    t2.vname = v.video_code;
    
-- 장르 별로 특정 요일에 할인
SELECT 
    genre_code AS '할인 장르',
    rental_fee AS '할인 전 금액',
    rental_fee * 0.95 AS '할인 된 금액'
FROM
    genre
WHERE
    genre_code = (SELECT 
            CASE DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 DAY), '%w')
                    WHEN '0' THEN 'Action' -- 월
                    WHEN '1' THEN 'Comed' -- 화
                    WHEN '2' THEN 'Drama' -- 수
                    WHEN '3' THEN 'Horror' -- 목
                    WHEN '4' THEN 'Romance' -- 금
                    WHEN '5' THEN 'SF' -- 토
                    WHEN '6' THEN 'Thriller' -- 일
                END
        FROM DUAL);
        
-- 제목이 가장 긴 영화 와 가장 짧은 영화가 무엇인가요
SELECT 
    VIDEO_CODE, GENRE_CODE, VIDEO_TITLE
FROM
    (SELECT 
        @max_title:=MAX(CHAR_LENGTH(video_title)), 
            @min_title:=MIN(CHAR_LENGTH(video_title))
    FROM
        video v) t,
    video v
WHERE
    CHAR_LENGTH(v.video_title) = @max_title 
        OR CHAR_LENGTH(v.video_title) = @min_title;
        
-- 장르 별 매출액이 많은 상위 3개의 장르에 대해서, 최근 한 달 간 대여가 없는 고객의 전화번호 조회
SELECT DISTINCT
    m.*
FROM
    (SELECT  -- substring_index() 함수를 split 처럼 사용
        SUBSTRING_INDEX(result, ',', 1) AS top1,
            SUBSTRING_INDEX(SUBSTRING_INDEX(result, ',', - 2), ',', 1) AS top2,
            SUBSTRING_INDEX(result, ',', - 1) AS top3
    FROM
        (SELECT -- 해당 장르들을 concat() 함수로 묶음
        GROUP_CONCAT(genre) AS result
    FROM
        (SELECT -- 장르별 매출 top3
        g.genre_code AS genre,
            (COUNT(r.genre_code) * RENTAL_FEE + IFNULL(r.ADD_LATE_FEE, 0)) AS tot_fee
    FROM
        rent r, genre g
    WHERE
        r.genre_code = g.genre_code
    GROUP BY r.GENRE_CODE
    ORDER BY tot_fee DESC
    LIMIT 3) t) t2) t3,
    member m
        LEFT OUTER JOIN
    rent r ON m.USER_NUM = r.USER_NUM
WHERE -- member 테이블에 있고, rent 테이블에 없는 데이터 조회
    r.USER_NUM IS NULL
        OR (DATE(NOW()) - rentdate > 31
        AND (genre_code = top1 OR GENRE_CODE = top2
        OR GENRE_CODE = top3));
        
-- 2번 회원 : 제가 볼 수 있는 등급이며 대여료가 2500원인 비디오는 무엇이 있나요?
SELECT 
    v.*,
    g.rental_fee,
    @age:=FLOOR((CAST(REPLACE(CURRENT_DATE, '-', '') AS UNSIGNED) - CAST(REPLACE(
						(SELECT 
                            birthday
                        FROM
                            member
                        WHERE -- 2번 회원의 만 나이를 age 변수에 대입
                            user_num = 2),
                    '-',
                    '')
                AS UNSIGNED)) / 10000) AS "회원 나이"
FROM
    video v,
    genre g
WHERE
    @age > movie_rated AND rental_fee = 2500
        AND v.GENRE_CODE = g.GENRE_CODE;
        
-- 23번 회원 : 저희 가족(핸드폰 번호가 같음)이 그동안 대여한 비디오 명과 대여한 날짜 전부 알려주세요.
SELECT 
    *
FROM
    rent r
        INNER JOIN
    member m ON r.USER_NUM = m.USER_NUM
WHERE
    m.phone_num = (SELECT -- 23번 회원의 핸드폰 번호
            PHONE_NUM
        FROM
            member
        WHERE
            USER_NUM = 23);
            
-- 가장 많이 연체 되는 비디오 top10
SELECT 
    v.VIDEO_TITLE,
    v.genre_code,
    v.MOVIE_RATED,
    v.PRODUCTION,
    v.DIRECTOR,
    sum(ADD_LATE_FEE) as "연체료 합계"
FROM
    rent r
        INNER JOIN
    video v ON r.VIDEO_CODE = v.VIDEO_CODE
WHERE
    ADD_LATE_FEE IS NOT NULL -- 연체료가 있다
GROUP BY r.video_code
ORDER BY sum(ADD_LATE_FEE) DESC
LIMIT 10;

-- 올해 가장 매출액이 높은 월과 낮은 월 조회
SELECT Max(sales) AS "매출이 가장 높은 달",
       Min(sales) AS "매출이 가장 낮은 달"
FROM   (SELECT Concat(Sum(g.rental_fee)
                      + Ifnull(Sum(add_late_fee), 0), '원  || ',
                      Substr(rentdate, 1, 7))AS
               sales
        FROM   genre g,
               rent r
        WHERE  r.genre_code = g.genre_code
               AND Substr(rentdate, 1, 4) = Substr(Date(Now()), 1, 4)
																									-- 빌린 날짜가 올해인
        GROUP  BY Substr(rentdate, 1, 7)) t; -- 월별로 그룹화
        
-- 회원CRUD
-- update 
update member set chk_black = 'Y' where user_num = 3;

-- select
select * from member where gender = 'male';

-- delete
delete from member where user_num = 51;

-- insert
insert into member values(
0,'배기양','010-9124-1927','2007-09-01','male',0,'N','서울시 구로구 구로동'
);

-- 비디오 CRUD
-- update 
update video SET RELEASE_DATE = '2012-05-18'  WHERE VIDEO_TITLE = '5월 이후';

-- select
select * from video where RELEASE_DATE > '2020-01-01' and GENRE_CODE = 'Drama';
-- 장르가 Drama 이고 출시일이 2020년 1월 1일 이후인 비디오 출력 

-- delete
delete from video where video_code = 'DR20221007';

-- insert
insert into video values(
'DR19971219','Drama','타이타닉',15,'1997-12-19','파라마운트 픽쳐스','제임스카메론');

-- 장르 CRUD
-- update 
update genre set rental_fee = 3000 where genre_code = 'Comed';

-- select
select * from genre where rental_fee = 2500;

-- delete
delete from genre where GENRE_CODE = 'SF';

-- insert
insert into genre values('Noir',3000,400,'누아르',3);

-- 대여기록 CRUD
-- 대여 기록 insert

-- 1)
insert into rent values(0,7,'DO20222220','Documentary',
date(now()),'N',null,'N',date_add(date(now()), interval + 3
 day),null);
-- 2) -- video 테이블에 trigger 추가 후 에러발생
set @videocode = 'CH20010001';
insert into rent values(0,15,@videocode,
(select genre_code from video where VIDEO_CODE = @videocode),
date(now()),'N',null,'N',date_add(date(now()), interval + 
(select lend_time from genre 
where genre_code=
(select genre_code from video where VIDEO_CODE = @videocode))
 day),null);

-- update
update rent set isreturn = 'Y', return_date = date(now()) where num = 147;

-- select 
select * from rent where add_late_fee is not null;

-- 인기목록 조회 // 대여 횟수가 많은 수 top 5
SELECT r.video_code AS "비디오 코드",
       v.video_title AS "비디오 제목",
       v.genre_code,
       Count(r.video_code) AS "대여 횟수"
FROM   rent r,
       video v
WHERE  r.video_code = v.video_code
GROUP BY r.video_code
ORDER BY Count(r.video_code) DESC
LIMIT 5;

-- 매출확인
-- sum 함수가 null 을 반환하면 전체 매출이 null이 되므로 ifnull 함수 사용
select r.video_code as "비디오 코드", v.VIDEO_TITLE as "비디오 제목",v.GENRE_CODE,count(r.VIDEO_CODE) as "대여 횟수"
from rent r ,video v 
where r.video_code = v.video_code 
group by r.VIDEO_CODE order by count(r.VIDEO_CODE) desc limit 5;

-- 2022년 매출
select sum(g.rental_fee) + ifnull(sum(r.ADD_LATE_FEE),0) as "2022년 매출 총액" 
 from genre g , rent r 
 where r.GENRE_CODE = g.GENRE_CODE and
 substr(rentdate,1,4) = '2022';

-- 이번 달 매출
select sum(g.rental_fee) + ifnull(sum(ADD_LATE_FEE),0) as "이번 달 매출"
 from genre g , rent r 
 where r.GENRE_CODE = g.GENRE_CODE and
substr(rentdate,1,7) = substr(date(now()),1,7);

-- 올해 매출
select sum(g.rental_fee) + ifnull(sum(ADD_LATE_FEE),0) as "올해 매출"
 from genre g , rent r 
 where r.GENRE_CODE = g.GENRE_CODE and
substr(rentdate,1,4) = substr(date(now()),1,4);

-- 연체관리
select m.*,r.ISRETURN,r.VIDEO_CODE 
from rent r, member m 
where ISRETURN = 'N' and r.user_num = m.USER_NUM;

-- 회원나이별 선호장르
-- 만 나이
SELECT birth    AS "회원 연령",
       Max(cou) AS "대여 횟수",
       genre    AS "선호 장르"
FROM   (SELECT @age              AS birth,
               Count(genre_code) AS cou,
               genre_code        AS genre
        FROM   member m,
               rent r
        WHERE  m.user_num = r.user_num
        GROUP  BY @age := Floor(( Cast(REPLACE(CURRENT_DATE, '-', '') AS
                                  UNSIGNED) -
                                  Cast(REPLACE(birthday, '-',
                                  '') AS
                                  UNSIGNED) )/ 10000), genre_code
        ORDER  BY cou DESC) AS t1
GROUP  BY birth;

-- 한국 나이
SELECT birth    AS "회원 연령", -- 회원 나이별 그룹화
       Max(cou) AS "대여 횟수",
       genre    AS "선호 장르"
FROM   (SELECT @age              AS birth, -- 회원 나이별 선호 장르
               Count(genre_code) AS cou,
               genre_code        AS genre
        FROM   member m,
               rent r
        WHERE  m.user_num = r.user_num
        GROUP  BY @age := Year(Now()) - LEFT(birthday, 4) + 1,
                  genre_code
        ORDER  BY cou DESC) AS t1
GROUP  BY birth;


-- 회원별 추천 장르
SELECT unum           AS "유저 번호",
       uname          AS "유저 이름",
       Max(count_num) AS "대여 횟수", -- 회원의 대여 횟수가 가장 높은 장르 추천
       genre          AS "장르 추천"
FROM   (SELECT r.user_num        AS unum,  -- 회원들이 빌린 장르, 대여 횟수
               m.user_name       AS uname,
               Count(genre_code) AS count_num,
               genre_code        AS genre
        FROM   member m,
               rent r
        WHERE  m.user_num = r.user_num
        GROUP  BY unum,
                  genre_code
        ORDER  BY count_num DESC) AS t1
GROUP  BY unum;


-- 장르별 인기비디오 top5
SELECT *
FROM   (SELECT vcode                 AS "비디오 코드",
               vcount                AS "대여 횟수",
               CASE
                 WHEN @groupby = genre THEN @rownum := Concat(@rownum + 1, 
								-- 장르별로 rownum 추가
                                                       ' 등')
                 ELSE @rownum := Concat(1, ' 등')
               end                   AS top5,
               ( @groupby := genre ) AS "장르"
        FROM   (SELECT a.genre_code AS genre,  -- 비디오별 대여 횟수 desc로 정렬
                       a.video_code AS vcode,
                       vcount
                FROM   (SELECT genre_code,  
                               video_code,
                               Count(video_code) AS vcount
                        FROM   rent
                        GROUP  BY genre_code,
                                  video_code
                        ORDER  BY genre_code,
                                  vcount DESC) a) b,
               (SELECT @rownum := 0,
                       @groupby := '') r) c
WHERE  top5 < 6; -- rownum 5 이하만 출력


-- 신간(대여료 +1000), 연체료 +200, 대여일2, 시간기준 출시일로 부터 30일
SELECT video_code,
       Concat(genre_name, ' (NEW) '),
       video_title,
       movie_rated,
       release_date,
       production,
       director,
       rental_fee + 1000        AS 대여료,
       late_fee + 200           AS 연체료,
       REPLACE(lend_time, 3, 2) AS 대여일
FROM   video v,
       genre g
WHERE  Date(Now()) - release_date < 31 -- 출시일로부터 30일 이내
       AND v.genre_code = g.genre_code;
---------------------------------------------------------------------------------------
-- chapter. DDL문
---------------------------------------------------------------------------------------
--- (1) create table로 테이블의 구조 정의
--- create table 테이블명 (
--- 컬럼명1 데이터 타입 표현식,
--- 컬럼명2 데이터 타입 표현식,
--- .........
--- 컬럼명3 데이터 타입 표현식,)

--- oracle 데이터 베이스의 데이터 타입
--- notion 정리 참조 (https://www.notion.so/Chapter-DDL-6067ca61c8ab4a54843eab452dc3a9db)

-- emp01 이라는 이름으로 사번, 사원명, 급여와 입사일을 저장할 수 있는 테이블 생성
create table emp01(
empno number(4),
ename varchar2(10),
sal number(6),
hiredate date);

--member01 이라는 이름으로 회원아이디(문자형 12자리), 비밀번호 (문자16자리), 이름(문자10), 전화번호(), 생년월일, 성별(M,F)을 저장할 수 있는 테이블 생성
create table member01(
userid varchar2(12),
password varchar2(12),
name varchar2(10),
tel varchar2(13),
birthday date,
gender char(1)
);

---------------------------------------------------------------------------------------
-- DML

-- insert into문으로 데이터 저장해보자
-- insert into 테이블명[(컬럼명, ....)] values(값1,값2,....);
insert into member01 values('go5252' ,'qwe123','고길동','010-5252-1234', '1972-01-01', 'M'  );
insert into member01 values('doner', '2222', '도우너', '010-5252-0611', '1988-01-01', 'F');
insert into member01 values('michol', '555555555', '마이콜', '010-5252-5151', '1990-01-01', 'M');
insert into member01 values('michol', '555555555', '마이콜', '010-5252-5151', '1990-01-01', 'M'); -- 에러 데이터의 갯수가 부족해서 에러

-- 원하는 컬럼에만 데이터 넣기 (컬럼명을 지정해주면 데이터의 갯수를 기술한 컬럼의 갯수 만큼만 넣어주면 된다.)
insert into member01(userid, password, name) values('abcd123','1234','아무개');

select * from member01;

---------------------------------------------------------------------------------------
-- (2) alter table 구문으로 테이블 구조 수정하기
--- 1) add column : 새로운 컬럼 추가
alter table emp01 
add ( job varchar2(10));
-- member01 테이블에 hobby 라는 컬럼을 문자열 50자 저장할 수 있도록 추가 하세요
alter table member01 add
(hobby varchar(50));
--- 2) modify column : 기존 컬럼 수정
-- 해당 컬럼에 데이터가 없는 경우 : 데이터 타입, 사이즈 모두 변경 가능하다.
-- 해당 컬럼에 데이터가 있는 경우 : 데이터 타입 변경 불가, 사이즈는 업 만 가능
alter table emp01
modify (ename varchar2(6));

alter table emp01
modify (job char(14));

alter table member01
modify (gender varchar2(1));

alter table member01
modify(birthday varchar2(15)); -- 컬럼이 비어 있어야 데이터 타입 변경이 가능 (불가함)

alter table member01
modify (userid varchar2(10)); -- 컬럼에 있는 데이터의 최대값보다 더 줄일 수는 없다
alter table member01
modify (userid varchar2(40)); -- 늘리는건 가능

--- 3) drop column : 컬럼 삭제
alter table member01
drop column birthday;


-- (3) drop table로 테이블 삭제하기
drop table emp01;
drop table member01;

-- (4) truncate table로 테이블의 데이터 삭제하기
truncate table member01;
select * from member01;

drop table member01;

-- (5) rename to로 테이블 이름 변경하기
rename member01 to members;

select * from members;

drop table members;




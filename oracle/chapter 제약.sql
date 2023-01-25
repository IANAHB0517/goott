-- 제약 조건을 컬럼 단위로 기술하는 방법
---------------------------------------------------------------------------------------------------------------
-- 1)not null 제약 조건
---------------------------------------------------------------------------------------------------------------
create table emp01(
empno number(4),
ename varchar2(10));

insert into emp01 values(null,'둘리');
select * from emp01;

drop table emp01;


create table emp01(
empno number(4) not null, -- 제약 조건을 제시
ename varchar2(10) not null
);
-- not null 제약 조건 위반 시 나오는 오류 메세지
-- 명령의 8 행에서 시작하는 중 오류 발생 -
-- insert into emp01 values(null,'둘리')
-- 오류 보고 -
-- ORA-01400: cannot insert NULL into ("HR"."EMP01"."EMPNO")
---------------------------------------------------------------------------------------------------------------
-- 2)unique 제약 조건
---------------------------------------------------------------------------------------------------------------
create table emp01(
empno number(4) not null, -- 제약 조건을 제시
ename varchar2(10) unique
);

-- unique 제약 조건에 위배하면 아래와 같은 에러가 발생한다.
-- ORA-00001: unique constraint (HR.SYS_C007016) violated
insert into emp01 values(1000, '둘리');
insert into emp01 values(1000, '홀리');

-- unique 제약 조건은 null을 허용한다
-- null 값은 검사하지 않아서 여러개의 null 값이 들어 갈 수 있다.
insert into emp01 values(1000, null); 


drop table emp01;


---------------------------------------------------------------------------------------------------------------
-- 3)primary key 제약조건 : not null + unique
---------------------------------------------------------------------------------------------------------------
create table emp01(
empno number(4) primary key, -- 제약 조건을 제시
ename varchar2(10) not null
);
-- ORA-01400: cannot insert NULL into ("HR"."EMP01"."EMPNO")
insert into emp01 values(null, '둘리'); -- null을 저장 할 수 없다.
insert into emp01 values(1000, '둘리');
-- ORA-00001: unique constraint (HR.SYS_C007020) violated
insert into emp01 values(1000, '훌리'); -- 중독된 값을 허용하지 않는다.

insert into emp01 values(1001, '폴리'); -- 중독된 값을 허용하지 않는다.
insert into emp01 values(1002, '폴리'); -- 중독된 값을 허용하지 않는다.

select * from emp01;

alter table emp01
add (sal number(6));

-- primary key : null이 아니고, unique한 값이기 떄문에, 이 컬럼을 통해 검색, 수정, 삭제를 하는 것이 좋다.
-- 수정과 삭제 작업을 할 때는 where문의 조건식을 primary key 컬럼을 통해 만드는 것이 좋고
-- 데이터 검색 작업을 할 때에도 primary kry를 통해서 검색하는 것이 indexing(정렬) 되어 있어 성능이 향상 된다.
-- 1000 번 사원의 급여를 5000으로 수정
update emp01 set sal = 5000;
update emp01 set sal = 5000 where empno = 1000;


rollback; -- 특정 지점으로 되돌림
commit; -- 저장시킴

-- 이름이 폴리인 행 지우기
delete from emp01 where ename = '폴리'; -- 별로인 방법

-- 사번이 1001인 행 지우기
delete from emp01 where empno = 1001; -- 좋은 방법



drop table emp01;


---------------------------------------------------------------------------------------------------------------
-- 4) foreign key 제약 조건 : 참조되는 테이블의 컬럼에 해당하는 값이 존재해야 함
---------------------------------------------------------------------------------------------------------------
-- foreign key 제약 조건을 위배하면
-- 부서번호가 부서 테이블에 존재하지 않는 값을 넣으려 할 때
-- ORA-02291: integrity constraint (HR.EMP_DEPT_FK) violated - parent key not found (부모키(부서테이블의 부서번호)가 없다)
-- integrity constraint  (통합제약 조건)

insert into employees values(
207,
'길동',
'홍',
'kildong',
null,
sysdate,
'IT_PROG',
50000,
null,
115,
300

);

insert into employees values(
207,
'길동',
'홍',
'kildong',
null,
sysdate,
'IT_PROG',
50000,
null,
115,
60

);


-- 사원이 있는 부서를 삭제 하려 할 때
-- ORA-02292: integrity constraint (HR.EMP_DEPT_FK) violated - child record found (자식 기록 발견됨 -- 소속부서가 60번인 사원들이 존재)
delete from departments where department_id = 60;
select * from employees where department_id = 60;

update employees set department_id = 210 where department_id = 60;

rollback;

select * from employees where department_id = 40;
delete from departments where department_id = 40;
update employees set department_id = 250 where department_id = 40;

--부모 테이블 dept01 먼저 생성
create table dept01
(
deptno number(2) primary key,
dname varchar2(10) not null
);

drop table emp01;

--자식 테이블 emp01 생성
create table emp01
(
empno number(4) primary key,
ename varchar2(10) not null,
deptno number(2) REFERENCES dept01(deptno)
);

insert into dept01 values(10, '총무부');
insert into dept01 values(20, '개발부');

insert into emp01 values(1000, '둘리' , 10);

select * from emp01;
select * from dept01;

delete from dept01 where deptno = 10; -- 에러(부서 번호가 10번인 사원이 존재함)
update emp01 set deptno = 20 where empno = 1000; -- 10 부서 사원을 20번으로 이동
delete from dept01 where deptno = 10; -- 10번 부서 삭제 가능

drop table emp01;
drop table dept01; -- 테이블을 삭제할 때도 자신을 참조하는 테이블이 있기 때문에  삭제가 되지 않는다.
                   -- 먼저 emp테이블(자식)을 삭제 해줘야한다.
                   
---------------------------------------------------------------------------------------------------------------
-- check 제약 조건 : 입력되는 값을 체크하여 설정된 값 이외의 값이 들어오지 않도록한다
---------------------------------------------------------------------------------------------------------------

create table emp01(
empno number(4) primary key,
ename varchar2(10) not null,
gender char(1) check (gender in('M', 'F'))
);

insert into emp01 values(1000, '홍길동', 'm');
insert into emp01 values(1000, '홍길동', 'M');
insert into emp01 values(1001, '홍길순', 'F');
insert into emp01 values(1002, '홍길길', 'z');

drop table emp01;

create table member(
userid varchar2(10) primary key,
pass varchar2(20) not null,
age number(3) check (age between 20 and 150)
);

-- ORA-02290: check constraint (HR.SYS_C007036) violated -- rbclrdp dnlqoehla
insert into member values('abcd', '1234', 10);

insert into member values('abcd', '1234', 20);

-- ORA-02290: check constraint (HR.SYS_C007036) violated -- rbclrdp dnlqoehla
insert into member values('ddd', '1234', 160);


drop table member;


---------------------------------------------------------------------------------------------------------------
-- default 제약조건 : 값이 주저지지 않으면 기본 값으로 들어가도록 한다.
---------------------------------------------------------------------------------------------------------------

create table dept01(
deptno number(3) primary key,
dname varchar2(10),
loc varchar2(16) default '서울'
);

insert into dept01(deptno, dname) values(10, '개발부'); --loc 값을 명시하지 않으면 default 값이 들어감
insert into dept01 values(20, '총무부', null);

select * from dept01;

drop table dept01;
drop table emp01;
---------------------------------------------------------------------------------------------------------------
-- 제약 조건 이름 기술하기 : 제약 조건 이름만 보더라도 어떤 테이블의 어떤 컬럼에 어떤 제약조건이 걸려있는지 쉽게 파악이 될도록 하자  필수는 아님

-- 제약 조건 이름의 예
-- [테이블명]_[컬럼명]_[제약조건명]

--제약 조건 이름을 명시하는 방법
-- 컬러명 데이터타입 constaiant 제약조건 이름 제약조건 타입
 
 create table dept01(
 deptno number(2) constraint dept01_deptno_pk primary key,
 dname varchar2(10) constraint dept01_dname_nn not null,
 loc varchar2(20) default 'seoul'
 );
 
 create table emp01(
 empno number(4) constraint emp01_empno_pk primary key,
 ename varchar2(10) constraint emp01_ename_nn not null,
 email varchar2(20) constraint emp01_email_uq unique,
 gender char(5) constraint emp01_gender_ck check(gender in ('male' , 'female')),
 deptno number(2) constraint emp01_deptno_fk references dept01(deptno)
 );
 
 insert into dept01 values(10, '총무부', '부산' );
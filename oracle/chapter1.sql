-- select 문으로 특정 데이터 추출하자
-- 데이터베이스는 대소문자를 구분하지 않는다 
-- 1) select 문의 기본 문법
-- select * || [조회할 컬럼명1, 조회할 컬러명2, 조회할 컬러명3, ....]
-- from 테이블 명;

-- countries 테이블의 모든 컬럼 조회
select * from countries;

-- 부서 테이블의 모든 컬럼 조회
select * from departments;

-- 사원 테이블의 모든 컬럼 조회
select * from employees;

-- 특정테이블 조회
-- 사원 테이블에서 사원 이름만 조회하자
select first_name from employees;

-- 부서 테이블에서 부서명만 조회하자.
select department_name from departments;

-- 국가 테이블에서 도로명 주소만 조회하자. (불가)
select ;

-- 지역 테이블에서 도로명 주소만 조회하자.
select street_address from locations;

-- 사원테이블에서 사원명과 급여를 조회하자.
select first_name, salary from employees;

--  사워테이블에서 사번,이름,입사일을 조회하자.
select employee_id, first_name, hire_date from employees;

-- 부서테이블 에서 부서명, 부서번호를 조회하자
select department_name, department_id from departments;

select job_id, salary from employees;

-- 2) 컬럼명에 별칭을 지을 수 있다.
--- (1) 컬럼명에 별칭을 지으려면 컬럼을 기술한뒤 컬럼명 뒤에 as 라는 키워드를 쓴다.
select first_name as name from employees;

select employee_id as 사번, first_name as 이름 from employees;

select hire_date as 입사일 from employees;

select last_name as 성 ,first_name as 이름, hire_date as 입사일, salary as 급여 from employees;

select end_date as 퇴사일 from job_history;

--- (2) AS라는 키워드를 생략해도 된다.

select first_name 이름 from employees;

--- (3) 별칭에 공백이나 특수문자를 포함하는 경우에는 별칭을 "별칭"로 묶는다
select first_name "이 름" from employees;

-- 3) distint 키워드는 중복된 데이터를 한번씩만 출력하게 한다.

select job_id from employees;
select distinct job_id from employees; -- job_id의 중복된 행을 한번씩만 출력
select distinct job_id, first_name from employees; -- 사원이 104명 나옴 잘못된 데이터 도출 이런식으로 사용하면 안된다.

-- 4) where절을 사용하여 행 검색 할 때 조건을 부여할 수 있다.
-- 하나의 문장 안에 여러 종류의 절이 있을 수 있다.
select *  -- select 절
from employees -- from 절
where employee_id >= 100; -- where 절

select *  -- select 절
from employees -- from 절
where employee_id >= 200; -- where 절

--- (1) where 절에 조건식을 사용할 수 있다.
--- (2) 조건식에 사용되는 연산자 (>,<,>=,<=,!=(not equal)등)가 있다.
-- 사원테이블에서 이름이 Adam인 사원의 사번, 이름, 입사일을 조회하자.
-- sql에서 문자열이나 날짜를 표현할 때는 '(작은따옴표)'를 이용한다.
select employee_id, first_name, hire_date from employees where first_name = 'Adam'; -- 문자열 일때 대소문자를 구분함
select employee_id, first_name, hire_date from employees where first_name = 'adam'; -- 대소문자의 차이로 검색이 안됨

-- 사원 테이블에서 급여가 5000 이상인 사원들의 사번 이름 급여를 조회하자
select employee_id, first_name, salary from employees where salary >= 5000;

-- 지역 테이블에서 지역 번호가 1800번 이하인 지역의 모든 컬럼을 조회하자.
select * from locations where location_id <= 1800;

-- 사원테이블에서 입사일이 2002년 이전에 입사한 사원들의 사번, 이름, 급여, 입사일을 출력하자
select employee_id, first_name,salary,hire_date from employees where hire_date < '02/01/01';
-- 시스템에서 날짜를 timestamp로 처리해서 비교가 가능하다.

--- (3) 조건연산자를 연결할때 논리연사자(and, or, not)를 사용할 수 있다.
--- 사번이 130번 보다 작거나 급여가 5000 보다 큰 사원들의 사번, 급여를 출력하자
select employee_id, salary from employees where employee_id < 130 or  salary > 5000; -- 둘중에 하나도 참이면 참

--- 급여가 5000이상이고ㅓ 부서번호가 30번 보다 작은 사원들의 사번, 급여, 부서번호를 출력하자.
select employee_id, salary, department_id from employees where salary >= 5000 and department_id <30; -- 둘다 참이어야 참

-- 부서번호가 100번이 아닌 모든 사원들의 모든 컬럼을 조회하자

select *
from employees
where department_id != 100;

select *
from employees
where department_id <> 100;

select *
from employees
where department_id ^= 100; -- not eqaul, !=, <>, ^=

--- (4) between A and B 연산자 : A 이상이고, B이하

--- 급여가 5000이상이고 급여가 7000이하인 사원들의 이름과 급여를 출력해보자

select first_name, salary 
from employees
where salary >= 5000 and salary <=7000;

select first_name, salary 
from employees
where salary between 5000 and 7000;

-- 입사년도가 2003~2005 인 사원들의 모ㅓ든 정보를 조회하자

select *
from employees
where hire_date between '03/01/01' and '05/12/31';

--- (5) In(A, B,C,...) : A또는 B또는 C...
--- 부서번호가 10번 또는 부서번호가 50번 또는 부서번호가 100번인 사원들의 모든 정보를 출력하자
select *
from employees
where department_id = 10 or department_id = 50 or department_id = 100;

select *
from employees
where department_id in (10, 50, 100);

--- (6) 패턴을 이용하여 검색하는 like 연산자
---- 1) 컬럼명 like 패턴
---- 2) 패턴은 아래의 2가지를 이용할 수 있다.
---- '%' : 문자가 없거나 하나이상의 문자가 어떤 값이 오든 상관 없다.
---- '_' : 하나의 문자가 어떤 값이 오든 상관 없다.

--- 이름이 s로 시작하는 모든 사원들의 정보를 출력하자
select *
from employees where first_name like 'S%';

select *
from employees where first_name like 'S_';

이름이 n으로 끝나는 모든 사원들의 정보
select *
from employees where first_name like '%n';

-- 직무가 AN으로 끝나는 모든 사원들의 정보를 출력하자
select *
from employees where job_id like '%AN';

--- 이름의 끝에서 2번째 글자가 a인 사원들의 모든 정보를 출력
select *
from employees where first_name like '%a_';

select *
from employees where first_name like '%a__';


--- 이름의 3번째 글자가 r인 사원들의 모든 정보 출력
select *
from employees where first_name like '__r%';

--- 직무에 _가 포함된 사원들의 모든 정보를 출력
select *
from employees
where job_id like '%' || '_' || '%';
--where job_id like '%_%'; -- 이거 잘 모르겠음 물어보자
where job_id like '%' || '_' || '%'; -- 조합 순서에 따라서 첫째 자리가 아닌 상태로 _를 포함하는 모든 문자열


--- (7) null을 위한 연산자
--- 커미션을 받는 모든 사원들의 모든 정보를 출력
select * from employees where commissinon_pct != null; -- 이렇게는 되지 않는다. null을 위한 연산자를사용하지 않아서
--- is null; null과 같다, is not null null과 같지 않다.
select * from employees where commission_pct is not null;

--- (8) 정렬을 하기 위해서는 order by절을 사용한다.
--- order by 컬럼명 [정렬기준];
--- 정렬기준은 오름차순 (ASC), 내림차순(DESC)를 사용하되, 오름차순일 경우 ASC키워드 생략 가능하다.
--- order by 절은 모든 절의 제일 마지막에 기술한다.
--- 사원들의 급여 내림차순으로 모든 사원들의 정보를 출력하자.

select * from employees order by salary desc;

--- 정렬은, 로 구분하여 여러개를 사용할 수 있다.
--- 부서번호가 50번인 사원들의 모든 정보를 축력하되, 급여 오름차순으로 정렬하고, 급여가 같을경우 이름 내림차순으로 정렬하자

select *
from employees
where department_id = 50
order by salary, first_name desc;
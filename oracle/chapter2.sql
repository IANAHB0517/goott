--dual 테이블
--1) lower() : 소문자로 변환
select 'DataBase', lower('DataBase') from dual;
-- 이름이 LEX인 사원의 모든정보를 출력
select * from employees where lower(first_name) = 'lex';

--2) upper() : 대문자로 변환
select 'DataBase', upper('DataBase') from dual;
-- 이름이 LEX인 사원의 모든정보를 출력
select * from employees where upper(first_name) = 'lex';

-- 직급이 'it_prog'인 사원의 모든 정보를 출력
select * from employees where lower(job_id) = 'it_prog';
select * from employees where job_id = upper('it_prog');

--3) 첫 글자만 대문자로 나머지는 소문자로 변화하는 initcap 함수
select 'database', initcap('database') from dual;

-- 이름이 LEX인 사원의 모든정보를 출력
select * from employees where first_name = initcap('LEX'); 


--4) 문자를 연결하는 concat 함수
select concat('data', 'base') from dual;
select concat('data', 'base', 'oracle') from dual; --세개 부터는 안됨

select 'data' || 'base' || 'oracle' from dual;
-- || 연산자 자바스크립트의 + 와 같다

-- 모든 사원의 이름과 성을 합하여 이름,성 으로 출력하시고, 컬럼명을 fullName으로 하세요
select first_name || ',' || last_name as fullName from employees;

--5) 문자의 길이를 구하는 length 함수
select length('database') from dual;
select length ('데이터베이스') from dual;


-- 이름이 6글자 이하인 사원들의 이름을 소문자로 출력하는 쿼리문
select lower(first_name) from employees where length(first_name) <= 6;

-- 문자열의 일부를 추출하는 substr 함수
--- substr(대상, 시작위치, 추출할 갯수)
select substr('database', 1,3) from dual; -- dat
select substr('database', -4,3) from dual; --bas -번째는 문자열의 끝에서부터

-- 입사연도가 2005년인 사원들의 모든 정보 출력
select * from employees where substr(hire_date,1,2) = '05'; -- 내부적으로 날짜 데이터가 텍스트로 변환 된다.

--이름의 마지막 글자가 el로 끝나는 사원들의 모든 정보 출력
select * from employees where substr(first_name, -2,2) = 'el'; -- 마지막 변수는 자릿수가 아니라 갯수 이기 때문에 음수로 작성할 수 없다

select * from employees where first_name like '%el';

-- 특정 문자의 위치를 구하는 instr()
--7) instr(대상 문자열, 찾을 문자열) 자바스크립트 indexof 와 같음
select instr('database' , 'b') from dual; --5 
select instr('database' , 'a') from dual; --2
select instr('database' , 'a',3) from dual; --4 ('database' 에서 a를 찾는데 3번째 위치부터 찾기 시작)
-- 이름의 3번째 자리가 i인 사원들의 모든 정보를 출력하세요

select * from employees where instr(first_name,'i') = 3; --instr

select * from employees where substr(first_name, 3, 1 )='i';-- substr

select * from employees where first_name like '__i%';   -- like


--8) trim: 특정 문자를 잘라주는 함수
select trim ('a' from 'aaaaadatabaseaaaaaaa') from dual; -- database 만 남는다
select trim (' ' from '         dataBas         e           !       ')from dual; -- 문자열 사이에 있지 않은 특정문자만 제거
------------------------------------------------------------------------------------------------------------------
--- 숫자 데이터 처리 함수
-- 1) 절대값을 구하는 abs()
select abs(-15) from dual;

--2) 소수점 아래를 버리는 floor()
select floor(3.141592) from dual;

--3) 특정 자리수에서 반올림 하는 round()
-- round(대상, 표시할 자리수)
select round(3.141592,2)from dual; --3.14
select round(3.141592,3)from dual; --3.142
select round(314.1592,-2)from dual;  --300 십의 자리에서 반올림하여 백의 자리부터 나타내라 (표시할 자릿수가 음수가 되면 수숫점의 정부부분으로 반올림됨)

--4) 특정 자리수에서 잘라내는 trunc()
select trunc(3.141592, 2) from dual; --3.14
select trunc(3.141592, 4) from dual; --3.1415
select trunc(314.1592,-2)from dual;  --300 십의 자리에서 내림하여 백의 자리부터 나타내라 (표시할 자릿수가 음수가 되면 수숫점의 정부부분으로 반올림됨)

--5) 나머지 값을 반환하는 mod()-- 자바스크립트의 % 연산자
select mod(34,2) from dual; --0
select mod(34,3) from dual; --1

-- 사원들의 연봉을 구하려고 한다. 연봉 =(기본급 * 12) + (기본급 * 12 * 커미션) 을 구해, 소수점 이라 2자리까지만 출력되도록 하세요.
select employee_id, first_name, round((salary * 12) + (salary * 12 * commission_pct),2) as "annual salary"
from employees where commission_pct is not null;

------------------------------------------------------------------------------------------------------------------------

-- 날짜 관련 함수 : 데이터 타입이 DATE인 데이터를 대상으로 하는 함수
-- 날짜 타입도 연산이 가능하다.
------------------------------------------------------------------------------------------------------------------------
-- 1) 현재 날짜를 반환하는 sysdate
select sysdate from dual;
select sysdate +1 내일 from dual; -- date 타입도 연산이 된다.
-- 사원들이 입사일로부터 현재까지 입사한지 몇일 지났는지 구해보자
select first_name, floor(sysdate - hire_date) || '일 지났다' 근무일수 from employees;

-- 2) 두 날짜 사이 간격(개월 수)을 계산하는 months_between 함수
select first_name, hire_date, floor(months_between(sysdate, hire_date)) from employees;

-- 3) 개월 수를 더하는 add_months 함수
select first_name, hire_date, add_months(hire_date,3) from employees;


-- 4) 해당 요일에 가장 가까운 날짜를 반환하는 next_day 함수
select sysdate, next_day(sysdate, '금요일') from dual;

-- 5) 해당 달의 마지막 날짜를 반환하는 last_day 함수
select sysdate, last_day(sysdate) from dual;

-- 6) 특정 기준으로 반올림하는 round 함수
select sysdate, round(sysdate, 'month') from dual; -- 23/02/01

-- 7) 특정 기준으로 버리는 trunc 함수
select sysdate, trunc(sysdate, 'month') from dual; -- 23/01/01
select sysdate - 100,  trunc(sysdate -100, 'month') from dual; -- 22/01/01 (해당 날짜가 속한 달의 초기값 (22년 10월 1일을 반환))
select sysdate - 40,  trunc(sysdate -40, 'year') from dual; -- 22/01/01 (해당 날짜가 속한 해의 초기값 (22년 1월 1일을 반환))
------------------------------------------------------------------------------------------------------------------------

--- 변환 함수 : 데이터 타입을 변환 시키고자 할 때 사용
-- (문자 왼쪽 정렬 숫자 오른쪽 정렬)
------------------------------------------------------------------------------------------------------------------------
-- 1) to_char() : 문자형으로 변환
-- to_char(날짜데이터, '출력형식') : 날짜를 문자열로 변환
select sysdate, to_char(sysdate, 'yyyy-mm-dd') from dual;
select sysdate, to_char(sysdate, 'yyyy-mm-dd dy')from dual;
select sysdate, to_char(sysdate, 'yyyy-mm-dd am HH:mi:ss')from dual; -- hh24= 24시간제로 표기, am,pm = 오전인지 오후인지

select to_char(123456, '9999999' ) from dual; -- 9는 자릿수를 나타내며 자리수가 맞지 않아도 채우지 않는다.
select to_char(123456, '0000000' ) from dual; -- 0은 자릿수를 나타내며 자리수가 맞지 않으면 0으로 채운다.
select to_char(to_number(null), '0000000' ) from dual;

select first_name, salary, to_char(salary, 'L999,999') from employees; --L 지역 화폐단위로 표시, 세자리마다 쉼표

-- 2) to_date(): 날짜형으로 변환
select first_name, hire_date from employees
where hire_date= '06/01/03';

select first_name, hire_date from employees
where hire_date= to_date(20060103,'yyyymmdd');


-- 3) to_number() : 숫자형으로 변환
select '10000' + '20000' from dual;
select to_number('10,000','99,999') + to_number('20,000','99,999') from dual;
select to_char(10000 + 20000, '999,000') from dual;


------------------------------------------------------------------------------------------------------------------------

-- 기타 함수
-- (중요) 1) nvl() : 첫번째 인자로 받은 값이 null이면 두번째 인자 값으로 변경


-- 사원들의 연봉을 구하려고 한다. 연봉 =(기본급 * 12) + (기본급 * 12 * 커미션) 을 구해, 소수점 이라 2자리까지만 출력되도록 하세요.
select employee_id, first_name, to_char(round((salary * 12) + (salary * 12 * nvl(commission_pct,0)),2), 'L999,999') as "annual salary"
from employees;

select employee_id, first_name, to_char(round((salary*12) + (salary * 12 * nvl(commission_pct,0)),2), 'L999,999') as "annual salary"
from employees;
-- 2) decode 함수 : 프로그래밍 언어의 switch ~ case 문 같은 역할
select first_name,department_id, decode(department_id, 90, 'Executive' ,60, 'IT', 100, 'Finance') from employees;

-- 3) case 함수 : if else문과 비슷한 역할

select first_name,department_id, case when department_id=90 then 'Executive' when department_id=60 then 'IT' when department_id=100 then 'Finance' end from employees;


------------------------------------------------------------------------------------------------------------------------

-- 그룹 함수
-- 1) sum () : 합계 구하는 함수

select SUM(salary) FROM employees;
select first_name, SUM(salary) FROM employees; -- (에러) sum()은 단일행 함수이기 때문에 사용 할 수 없다.

-- 2) avg() : 평균 구하는 함수
select to_char(round(avg(salary),2),'L9,999.99') as "직원 평균 급여"  from employees;

-- 3) max() n min() : 초ㅓㅣ대값,최소값 구하는 함수
select max(salary), min(salary) from employees;

-- 4) COUNT() : 행의 갯수를 세어주는 함수
select count(commission_pct) as "커미션 직원의 수" from employees;
select count(*) as 전체직원수 from employees;

-- 5) stddev() : 표준편차 함수  -- 분산에 루트를 씌운것 원래 단위로 돌아온다.
select stddev(salary) from employees;

-- 6) variance() : 분산
select variance(salary) from employees;

-- group by 절 : 그룹화를 시킬때 사용하는 절
-- group by 컬럼

-- 소속 부서별 급여 총액 과 급여 평군 구해보자
select department_id, sum(salary), trunc(avg(salary),2) from employees
group by department_id ;

-- 직무별 급여 총액과 급여 평군
select job_id, sum(salary) as "salary sum", trunc(avg(salary),2) as "salary avg" from employees
group by job_id order by "salary avg" desc;

-- having 절 : 그룹화를 시킨 컬럼에 조건을 부여 할 때 쓰는 절
-- 부서별 평균 급여가 5000 이상인 부서번호와 부서별 평균 급여를 출력하세요
select department_id, avg(salary) from employees
--where avg(salary) >= 5000 -- (에러) 그룹화를 시킨 컬럼은 where절로 조건을 걸 수 없다.
GROUP by department_id
having avg(salary) >= 5000;

-- 직급별 급여 최대값과 급여 최솟값을 구하되, 최대 급여가 7000 이상인 부서만 출력하세요
select job_id ,max(salary), min(salary) from employees
group by job_id
having max(salary) >= 7000;

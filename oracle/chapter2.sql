--dual 테이블
-- lower() : 소문자로 변환
select 'DataBase', lower('DataBase') from dual;
-- 이름이 LEX인 사원의 모든정보를 출력
select * from employees where lower(first_name) = 'lex';

-- upper() : 대문자로 변환
select 'DataBase', upper('DataBase') from dual;
-- 이름이 LEX인 사원의 모든정보를 출력
select * from employees where upper(first_name) = 'lex';

-- 직급이 'it_prog'인 사원의 모든 정보를 출력
select * from employees where lower(job_id) = 'it_prog';
select * from employees where job_id = upper('it_prog');

-- 첫 글자만 대문자로 나머지는 소문자로 변화하는 initcap 함수
select 'database', initcap('database') from dual;

-- 이름이 LEX인 사원의 모든정보를 출력
select * from employees where first_name = initcap('LEX'); 


-- 문자를 연결하는 concat 함수
select concat('data', 'base') from dual;
select concat('data', 'base', 'oracle') from dual; --세개 부터는 안됨

select 'data' || 'base' || 'oracle' from dual;
-- || 연산자 자바스크립트의 + 와 같다

-- 모든 사원의 이름과 성을 합하여 이름,성 으로 출력하시고, 컬럼명을 fullName으로 하세요
select first_name || ',' || last_name as fullName from employees;

-- 문자의 길이를 구하는 length 함수
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
-- instr(대상 문자열, 찾을 문자열) 자바스크립트 indexof 와 같음
select instr('database' , 'b') from dual; --5 
select instr('database' , 'a') from dual; --2
select instr('database' , 'a',3) from dual; --4 ('database' 에서 a를 찾는데 3번째 위치부터 찾기 시작)
-- 이름의 3번째 자리가 i인 사원들의 모든 정보를 출력하세요

select * from employees where instr(first_name,'i') = 3; --instr

select * from employees where substr(first_name, 3, 1 )='i';-- substr

select * from employees where first_name like '__i%';   -- like





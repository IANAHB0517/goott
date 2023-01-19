-- 복습
-- 사원의 이름과 직업 그리고 근무 지역을 출력하자
select e.ename, e.job , d.loc
from emp e , dept d
where e.deptno = d.deptno;

select to_char( 123 , '000000') from dual; -- 000123;
select to_char( 123 , '999999') from dual; -- 123;
select to_char( 123 , '909090') from dual; -- 00123;
select to_char( 123 , '090909') from dual; -- 000123;
select to_char( 123 , '999990') from dual; -- 123;
select to_char( 123 , '000009') from dual; -- 000123;
select to_char( 123 , '000999') from dual; -- 000123;
select to_char( 123 , '999000') from dual; -- 123;
select to_char( 123 , '090999') from dual; -- 000123;
select to_char( 123 , '090099') from dual; -- 000123;
select to_char( 123 , '090009') from dual; -- 000123;
-- 어떤 조건에서 어떻게 나오는지 모르겠다.


select to_char(10000 + 20000, '999,000') from dual;
-- 숫자를 연산하고 원하는 형태로 출력하기에 좋을 듯하다.


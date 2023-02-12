-------------------------------------------------------------------------------------------------------
-- 조인(join)
-- 두개 이상의 테이블에서 결과를 얻기 위한 SQL문장
-------------------------------------------------------------------------------------------------------
-- 사번이 100인 사원의 정보 (사번, 이름, 부서번호)와 그가 소속한 부서의 부서명까지 출력해보자
select employee_id, first_name, employees.department_id, department_name
from employees, departments 
where employee_id = 100; -- cross join 두 테이블의 데이터를 의미 없이 연산한 경우

select employee_id, first_name, employees.department_id, department_name
from employees, departments 
where employees.department_id = departments.department_id -- 조인 조건
and employee_id = 100;

-- (중요) 조인 조건은 조인문에 사용되는 테이블의 총 객수 -1 개가 되어야 한다.
-- 테이블 별칭을 from절에 사용하여, 해당하는 조인문이나 셀렉트 문에 사용할 수 있다.
-- CEO가 설날을 맞이하여 모든 직원에게 선물을 택배로 보내려한다.
-- 모든 직원들이 택배를 부사히 받을 수 있도록 사무실의 주소, 사원 정보를 출력하세요.

select  e.job_id ,e.first_name || ',' || e.last_name,
d.department_name,
l.street_address,l.state_province,l.postal_code, l.city, 
c.country_name,
r.region_name
from employees e, departments d, locations l, countries c, regions r
where e.department_id = d.department_id
and d.location_id = l.location_id
and l.country_id = c.country_id
and c.region_id = r.region_id;


-------------------------------------------------------------------------------------------------------
-- 1) Cross Join : 의미없는 조인. 단순하게 두개 이상의 테이블을 곱연산 결과를 냄. 
--                 조인 조건에 해당하는 where절이 존재하지 않기 때문에 테이블같의 관계를 파악하여 조인 하지 않음.
select * from employees, departments; -- 2889개의 행 = 107 * 27
select count(*) from employees; --107
select count(*) from departments; --27
-------------------------------------------------------------------------------------------------------

-- 조인문장이 지켜야 할 조건
-- *) primary key  와 foreign key 컬럼을 통해 서로 다른 테이블을 연결한다.mandatory는 아님
--   (mandatory)__필수적인(맨다토리) -- 필수는 아닌데 꼭 지켜야함 ㅋㅋ
-- *-1) where 절을 사용하여 조인 조건을 기술한 (조인 조건의 갯수 = 연결하는 테이블 수 -1)
-- *-2) 명확성을 위해 컬럼명 앞에 테이블 별칭을 기술한다.

-- 2-1) Equi Join (= equal join) : 가장 많이 사용되는 조인. -- 동등 조인
-- 조인 대상이 되는 테이블에서 공통적으로 존재하는 컬럼을 연결(=로 연결)하여 결과를 생성하는 조인.
--(반드시 그런것은 아님. 컬럼명은 다를 수 있다. 하지만 컬럼의 데이터 타입과 크기는 같아야 함)

select e.first_name, d.department_name
from employees e , departments d
where e.department_id = d.department_id;

-- 2-2) Non-Equi Join : 조인할 테이블 사이의 컬럼값이 직접적으로 일치 하지 않을 때 사용하는 조인. -- 비동등 조인
-- '=' 연산자를 제외한 연산자를 사용한다.
-- scott 계정 이용
-- 사원의 호봉이 얼마인지. 출력해보자
select  e.empno, e.ename, e.sal, s.grade
from emp e , salgrade s
where e.sal >= s.losal and e.sal <= s.hisal;
-- btween and 이용
select  e.empno, e.ename, e.sal, s.grade
from emp e , salgrade s
where e.sal between s.losal and s.hisal;

-- 3) outer join : 행이 조인 조건에 만족하지 않을 경우 그 행은 조인 결과에 나타나지 않는다.
-- 그러나 가끔 조인 조건에 만족하지 않는 행들도 나타내기 위해 outer join을 사용해야 할 때가 있다.
select e.first_name, d.department_name
from employees e , departments d
where e.department_id = d.department_id;
-- 위의 결과에서 178번 사원이 부서번호가 null이라 누락이 되어 있다.
--outer join을 사용하여 누락된 정보까지 표현해 보자
-- 누락된 정보를 표현하라는 의미에서 찾고자하는 컬럼의 반대편 컬럼명 뒤에 (+) 기호를 붙여준다.
select e.first_name, d.department_name
from employees e , departments d
where e.department_id(+) = d.department_id; -- 부서는 존재 하지만 사원이 배치되지 않은 부서가 나옴

-- 아무 부서에도 배치되지 않은 178번 사원의 정보까지 출력된다.
select e.first_name, d.department_name
from employees e , departments d
where e.department_id = d.department_id(+); -- 킴벌리의 부서 이름은 NULL


select e.first_name, d.department_name
from employees e , departments d
where e.department_id(+) = d.department_id(+); -- 아웃터 조인은 한쪽에만 사용할 수 있어서 에러가 나온다 이걸 극복하기위해 나온것이 안씨(america join. 미국 표준협회에서 제정했다고 함) 조인

-- 4) Self Join : 하나의 테이블 내에서 조인을 하여 원하는 데이터를 얻는 조인
-- from절에 동일한 테이블 이름을 주고 조인조건을 주어야 하는데, 
-- 이 때 서로 다른 테이블 인것처럼 하기 위해 반드시 테이블 별칭을 준다.
select e1.first_name || '의 직속상사는' || e2.first_name || '입니다' as 직속상사
from employees e1, employees e2
where e1.manager_id = e2.employee_id; --포린 키와 프라이머리 키의 컬럼명은 달라질 수 있으나 데이터의 값과 데이터의 형식(ex.Number)은 같아야 한다.
-------------------------------------------------------------------------------------------------------
--ANSI Join : ANSI (미국 표준 연구소)에서 제정한 다른 DBMS와의 호환성을 취해 만든것
-------------------------------------------------------------------------------------------------------
-- 1) ANSI Cross Join
select count(*)
from employees cross join departments;

-- 2) ANSI INNER JOIN : EQUI JOIN과 동일
-- join 조건을 where 절이 아니라 on 절에 기술한다
-- where 절에 일반 조건을 기술 할 수 있다.
select e.first_name, d.department_name
from employees e inner join departments d
on e.department_id = d.department_id
where first_name like '%n';

-- using 절을 이용하여 조인 조건 지정하기.
-- 조인되는 프라이머리키와 포린 키의 컬럼명이 같을 때에만 사용 가능
-- using 절에서는 테이블 별칭 사용 불가
select e.first_name, d.department_name
from employees e inner join departments d
using (department_id); 

-- natural join
-- 동일한 컬럼명을 기술할 필요 없이 동일한 컬럼을 찾아서 inner join을 진행
-- 조인 되는 컬럼명은 반드시 동일 해야한다.
select e.first_name, d.department_name
from employees e natural join departments d; 



-- 3) ANSI Outer Join : outer join
-- left outer join , right outer join, full outer join

select e.first_name, d.department_name
from employees e , departments d
where e.department_id = d.department_id(+);

-- 부서에 배치되지 않은 정보까지 출력 (employees 테이블은 from 절을 기준으로 왼쪽에 있으므로 left outer join )
select e.first_name, d.department_name
from employees e left outer join departments d
on e.department_id = d.department_id;

-- 사원이 배치 되지 않은 부서정보까지 출력 
select e.first_name, d.department_name
from employees e right outer join departments d
on e.department_id = d.department_id;

-- 양쪽에 누락된 정보까지 모두 보고 싶다.
select e.first_name, d.department_name
from employees e full outer join departments d
using (department_id); 


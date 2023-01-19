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



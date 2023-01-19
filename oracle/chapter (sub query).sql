----------------------------------------------------------------------------------------------------------------
-- 서브 쿼리
-- 하나의 문장 안에 두개의 쿼리문이 있는 문장
----------------------------------------------------------------------------------------------------------------
--- 서브 쿼리의 종류 : 단일행 서브쿼리, 다중행 서브쿼리
--- 1)단일행 서브 쿼리(single row subquery) : 내부 select 문장으로 부터 하나의 행 만을 반환 받으며,
-- 연산자로 =, >, <, >=, <=, != 를 사용할 수 있다.
----------------------------------------------------------------------------------------------------------------

-- 100번 사원이 소속 되어 있는 부서의 부서명을 알아보자
-- 1) 100번 사원이 소속 되어있는 부서의 부서번호를 알아 내야 한다.
select department_id from employees
where employee_id = 100;

-- 2) 1번에서 알아낸 부서번호(90번)의 부서 이름을 알아 낸다.
SELECT department_name from departments
where department_id = 90;

-- 3) 1번과 2번 문장을 하나의 서브쿼리로 합치면
SELECT department_name from departments
where department_id = (select department_id from employees
where employee_id = 100);
---- 괄호 안에 있는 것을 서브 쿼리 괄호 밖에 있는 것을 메인 쿼리 라고 한다.

-- 사원들이 소속 되어있는 부서의 부서명을 알아보자. 사번, 이름, 소속부서명
select employee_id, first_name, department_name
from departments
where department_id = (select department_id from employees);
-- 에러. 서브 쿼리는 메인 쿼리문의 테이블에있는 컬럼 정보 밖에는 얻지 못한다.
-- 서로 다른 테이블에서의 데이터를 얻기 위해서는 join문을 사용해야 한다.



-- 사원들이 소속 되어있는 부서의 부서명을 알아보자. 사번, 이름, 소속부서명
-- join 문
select employee_id, first_name, department_name
from employees, departments
where employees.department_id = departments.department_id;

-- 서브쿼리 문제
-- excutive 부서의 국가코드, 주, 시, 도로명 주소를 출력해보자
select country_id, city, state_province, street_address
from locations
where location_id = (select location_id from departments where department_name = 'Executive');


-- 문제2)

-- 'diana'와 같은 부서에 다니는 동료들의 모든 정보를 출력하세요

select * from employees where department_id = (select department_id from employees where first_name = initcap('diana'));

-- 문) 사원들의 평균 급여보다 더 많은 급여를 받는 사원의 사번, 이름, 급여를 출력하세요.
select employee_id, first_name, salary
from employees
where salary > (select avg(salary) from employees);

----------------------------------------------------------------------------------------------------------------
-- 2) 다중 행 서브쿼리(multiple row subquery) : 서브 쿼리에서 반환되는 행의 갯수가 2개 이상일 때 사용하는 서브쿼리
-- 연산자로는 아래의 연산자를 사용한다.
-- in  : 메인 쿼리의 비교 조건이 서브 쿼리의 결과중에서 하나 라도 일치하면 참 (의미상 =과 가장 가까움)
-- any : 메인 쿼리의 비교 조건이 서브 쿼리의 결과중에서 하나 이상 일치하면 참
-- all : 메인 쿼리의 비교 조건이 서브 쿼리의 결과중에서 모든 값이 일치하면 참

--- in 연산자를 사용할 경우
-- 문) 급여를 5000 이상 받는 사원이 소속된 부서와 동일한 부서에서 근무하는 사원들의 정보 출력
-- 1) 급여를 5000 이상 받는 사원이 소속된 부서
select * from employees where department_id in
(select distinct department_id from employees where salary >= 12000);


--- all 연산자를 사용할 경우
--문) 30번 부서에 소속된 사원중에서 급여를 가장 많이 받는 사원보다 더 많이 받는 사원의 이름, 급여를 출력해보자.
select first_name , salary from employees where salary > all (select salary from employees where department_id =30);


select first_name , salary from employees where salary > all (select salary from employees where department_id = 30);
-- 그룹함수는 테이블의 양에 따라 속도가 기하급수적으로 느려지기 때문에 잘 쓰지 않고 다중행 서브쿼리로 처리 한다.

--- any 연산자를 사용할 경우
--문) 30번 부서에 소속된 사원중에서 급여를 가장 적게 받는 사원보다 더 많이 받는 사원의 이름, 급여를 출력해보자.

select first_name , salary from employees where salary > any (select salary from employees where department_id = 30);


-- 서브쿼리를 이용하여 테이블 복사하기
-- 데이터만 복사할 뿐 데이터의 조건등을 복사 할 수는 없다.
create table copyEmp
as select * from employees;


create table copyEmp2
as select employee_id, first_name, salary from employees where department_id = 30;

create table copyEmp3
as select * from employees
where 1 = 0;
-- 조건식에 맞는 내용이 없도록 해서 테이블의 컬럼(구조)만 복사한다.


-- 테이블 삭제
drop table copyemp;
drop table copyemp2;
drop table copyemp3;




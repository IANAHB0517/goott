------------------------------------------------------------------------------------------
--뷰
------------------------------------------------------------------------------------------
SELECT * from emp_details_view;

SELECT * from emp_details_view where department_id = 90 order by salary asc;


SELECT
  e.employee_id,
  e.job_id,
  e.manager_id,
  e.department_id,
  d.location_id,
  l.country_id,
  e.first_name,
  e.last_name,
  e.salary,
  e.commission_pct,
  d.department_name,
  j.job_title,
  l.city,
  l.state_province,
  c.country_name,
  r.region_name
FROM
  employees e,
  departments d,
  jobs j,
  locations l,
  countries c,
  regions r
WHERE e.department_id = d.department_id
  AND d.location_id = l.location_id
  AND l.country_id = c.country_id
  AND c.region_id = r.region_id
  AND j.job_id = e.job_id;
  
  ------------------------------------------------------------------------------------------
  -- 뷰의 생성
  
  -- 부서번호 30번 부서에 소속된 모든 직원의 정보를 담고 있는 view 생성
  create or replace view view_emp30
  as 
  select * from employees where department_id = 30;
  
  select * from view_emp30;
  
  insert into view_emp30 values(300, '길동', '고', 'gildong', '515.555.5555', sysdate, 'PU_CLERK' , 3000, null, 100, 30);
  
  delete from view_emp30 where employee_id = 300;
  
  ------------------------------------------------------------------------------------------
  -- with check option 으로 뷰 생성하기
  
  create or REPLACE view view_emp90
  as
  select employee_id, first_name, last_name, email ,job_id , hire_date, department_id
  from employees
  where department_id = 90
  with check option;
  
    select * from view_emp90;
    
    insert into view_emp90 values(500, '길동', '고', 'gildong', 'AD_VP', sysdate, 90);
    update view_emp90 set salary = 9000 where employee_id = 500; --에러) 뷰에 해당 컬럼이 없다. with check option
    
    update view_emp90 set hire_date = '05/12/31' where employee_id = 500;
    
    -- with read only 를 사용하여 뷰 생성하기
    
    create or REPLACE view view_emp90
  as
  select employee_id, first_name, last_name, email ,job_id , hire_date, department_id
  from employees
  where department_id = 90
  with read only;
  
  select * from view_emp90;
  
  --ORA-42399: cannot perform a DML operation on a read-only view
  delete from view_emp90 where employee_id = 500;
  
  select * from employees;
  delete from employees where employee_id = 500;
  
  ------------------------------------------------------------------------------------------
  -- force 옵션으로 view 생성
  drop table board;
  drop table member;
  drop table emp01;
  drop table dept01;
  
  create or replace force view view_member
  as select *  from member;
  
  create table member(
  userid varchar2(10)
  );
  
  select * from view_member;
  
  ------------------------------------------------------------------------------------------
  
  -- 만약 뷰에 컬럼 별칭을 붙여 생성한다면, 해당 뷰에 대해서 컬럼 별칭으로 조건절을 만들어야 한다.
  create or replace view view_emp(사번, 이름, 급여, 부서번호)
  as
  select employee_id, first_name , salary, department_id
  from employees;
  
  select * from view_emp where 부서번호 = 60;
  
  ------------------------------------------------------------------------------------------
  
  --그룹 함수를 이요하여 뷰를 생성할때에는 반드시 그룹함수를 지정한 컬럼은 컬럼 별칭을 주어야 한다.
  create or replace view view_sal
  as
  select department_id, sum(salary) 급여총액, avg(salary) 급여평균
  from employees
  group by department_id;
  
  select * from view_sal;
  
  --ORA-01732: data manipulation operation not legal on this view
  -- 그룹함수를 이용하여 만든 뷰는 DML(insert/ update/ delete) 사용불가
  delete from view_sal where department_id = 30;
  
  ------------------------------------------------------------------------------------------
  -- 조인을 통해 여러개의 테이블 결과를 뷰로 생성한 경우
  -- DML은 하나의 테이블을 대상으로 하기 때문에 여러 테이블을 조인한 이 뷰에서는 사용할 수 없다.
  -- ** 있음 삭제와 업데이트 가능 insert만 안된다 (뷰를 통해서는)
  create or replace view view_emp_dept
  as
  select e.employee_id, e.first_name, e.department_id, d.department_name
  from employees e inner join departments d
  on e.department_id = d.department_id;
  
  select * from view_emp_dept;
  
  update view_emp_dept set first_name = '제니퍼' where employee_id = 200;
  
  delete from view_emp_dept where employee_id = 200;
  
  ------------------------------------------------------------------------------------------
  --뷰 삭제
  drop view view_emp;
  drop view view_emp_dept;
  drop view view_emp30;
  drop view view_emp90;
  drop view view_member;
  drop view view_sal;
  
  ------------------------------------------------------------------------------------------
  -- 급여가 높은 순으로 5명만 출력해보자.
  
  -- 1) 급여 내림차순으로 정렬하여 출력
  select employee_id, first_name, salary
  from employees
  order by salary desc;
  
  -- 2) 1번의 쿼리문에 rownum컬럼 (insert 문에 의해 입력된 순서에 따라 1씩 증가되는 내부의 값 )활용하기
  select rownum, e.* from employees e;
  
  select rownum, employee_id, first_name, salary
  from employees 
  order by salary desc;
  
  -- 3) 2번 쿼리문으로 뷰 생성하기(rownum 뺴고)
  create or replace view view_sal_top5
  as
  select employee_id, first_name, salary
  from employees 
  order by salary desc;
  
  -- 4) 3번의 뷰에 rownum을 붙여 출력하고 5명만 출력할 수 있다. (뷰에 담긴 순서 = rownum)
  select rownum, e.* from view_sal_top5 e;
  
  select * from view_sal_top5 where rownum < 6 ;
  
  -- 위의 문제를 서브쿼리로도 풀 수 있다.
  
  
 select rownum, sallank.* from (select employee_id, first_name, salary
  from employees 
  order by salary desc) sallank where rownum < 6;
  
  --ex) 입사일이 늦은 7명
  
  select late_hire.* from (select * from employees order by hire_date desc) late_hire where rownum <8;
  
  create or replace view view_hire_late
  as
  select * from employees
  order by hire_date desc;
  
  select rownum, h.*
  from view_hire_late h
  where rownum <8;
  
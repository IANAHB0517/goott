-- 뷰 연습문제

-- 1. 사번, 이름, 부서명, 부서의 위치를 출력하는 뷰(view_loc)를 작성하세요
-- insufficient privileges 권한이 없음
create or replace view view_loc
as
select  e.empno, e.ename, d.dname, d.loc from emp e , dept d;

select * from view_loc;

-- 2. 30번 부서의 소속 사원의 이름, 입사일, 부서명을 출력하는 뷰 (view_dept30)을 작성하세요
create or replace view view_dept30
as
select e.ename, e.hiredate, d.dname from emp e, dept d where e.deptno =30;

select * from view_dept30;

-- 3. 부서별 최대 급여 정보를 가지는 뷰 (view_dept_maxsal)을 작성하세요
create or REPLACE view view_dept_maxsal
as
select deptno, max(sal) 최대급여 from emp group by deptno  ;

select * from view_dept_maxsal;

-- 4. 급여를 많이 받는 top3를 출력하는 뷰 (view_sal_top3)를 작성하세요

create or replace view view_sal
as
select * from emp
order by sal desc;


create or replace view view_sal_top3
as
select * from view_sal where rownum < 4;


select * from view_sal_top3;






select rownum, e.* from
(select rownum,  ename, sal
from emp
order by sal desc) e
where rownum <= 3;

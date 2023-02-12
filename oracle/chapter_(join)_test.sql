-- chapter. Join 연습문제


-- 1. 급여가 3000에서 5000 사이인 직원의 이름과 소속 부서명을 출력
select e.ename, d.dname
from emp e inner join dept d
using (deptno)
where e.sal between 3000 and 5000;


-- 2. 직급이 manager인 사원의 이름, 부서명을 출력하세요
select e.ename, d.dname
from emp e inner join dept d
using (deptno)
where e.job= upper('manager');


-- 3. accounting 부서 소속 사원의 이름과 입사일 출력하세요
select e.ename , e.hiredate
from emp e inner join dept d
using (deptno)
where d.dname = upper('accounting');


select e.ename, e.hiredate
from emp e inner join dept d
on  e.deptno = d.deptno
where dname= upper('accounting');

-- 4. 커미션을 받는 사원의 이름과 그가 속한 부서명을 출력
select e.ename, d.dname
from emp e inner join dept d
on e.deptno = d.deptno
where e.comm is not null;


--- 5. 뉴욕에서 근무하는 사원의 이름과 급여를 출력
select e.ename, e.sal
from emp e inner join dept d 
on e.deptno = d.deptno
where d.loc = upper('new york');


--- 6. 급여가 1200 이하인 사원 이름과 급여와 그가 근무하는 근무지를 출력하세요
select e.ename, e.sal ,d.loc
from emp e inner join dept d 
on e.deptno = d.deptno
where e.sal <= 1200;


-- 7. smith와 동일한 근무지에서 근무하는 사원의 이름을 출력하세요
select coworker.ename as 동료이름
from emp worker inner join emp coworker
on worker.deptno = coworker.deptno
where worker.ename = upper('smith')
and coworker.ename <> upper('smith');


-- 8. 매니저가 king인 사원들의 이름과 직급을 출력하세요
select e1.ename , e1.job
from emp e1 inner join emp e2
on e1.mgr = e2.empno
where e2.ename = upper('king'); 

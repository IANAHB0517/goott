-- 서브쿼리 연습문제

-- 1. smith와 동일한 직급을 가진 사원의 이름과 직급 출력
select empno, ename, job from emp where job =
(select job from emp where ename = upper('smith'))
and ename <> upper('smith') ;

-- 2. 부서별로 가장 급여를 많이 받는 사원의 정보를 출력
select * from emp
where sal in (select max(sal) from emp group by deptno );

-- 3. 직급이 salesman인 사원이 받는 급여 중 최소 급여 보다 많이 받는 사원의 이름과 급여를 출력하되,
-- 부서번호가 20번인 사원은 제외하여 출력
select ename,sal from emp
where sal > any (select sal from emp
where job = upper('salesman') )and
deptno !=20;

-- 4. 직급이 salesman인 사원이 받는 급여 중 최대 급여 보다 많이 받는 사원의 이름과 급여를 출력하되,
-- 부서번호가 30번인 사원은 제외하여 출력

select ename,sal from emp
where sal >
all (select sal from emp where job = upper('salesman')) and deptno !=30  ;

select ename, sal from emp
where sal >
all(select sal from emp where job = upper('salesman')) and deptno != 30;

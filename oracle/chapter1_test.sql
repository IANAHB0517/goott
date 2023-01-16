-- chapter 1. 연습문제
-- 1. 중복 되는 컬럼을 한번 씩만 출력하는 키워드는 (distinct)이다.

-- 1-1. 사원 테이블의 직무가 한번씩만 출력되도록 sql을 작성하세요.
select distinct job from emp;

-- 2. 사원 테이블의 모든 정보를 출력하는 sql
select * from emp;

-- 3. 사원의 이름, 급여, 입사일 만을 출력하는 sql
select ename, sal, hiredate from emp;
-- 4. 사원들이 어떤 부서에 소속 되어 있는지 소속 부서를 출력하되 중복되지 않고 한번씩만 출력하는 쿼리문
select distinct deptno from emp;
-- 5. emp 테이블 중 부서 번호가 10번인 사원의 모든 정보 출력
select * from emp where deptno=10;
-- 6. 사원 테이블 중 급여가 2000미만인 사원의 정보 중에서 사번, 이름, 급여를 출력하세요.
select empno, ename, sal from emp where sal < 2000;
-- 7. 이름이 'MILLER'인 사람의 사번, 이름, 직급을 출력하세요
 select empno, ename, job from emp where ename = 'MILLER'; 
-- 8. 커미션이 300 또는 500또는 1400인 사원의 사번, 이름, 커미션을 출력하세요
--- or 연산자
select empno, ename, comm from emp where comm = 300 or comm = 500 or comm = 1400;
--- in 연산자
select empno, ename, comm from emp where comm in (300, 500, 1400);
-- 9. 급여가 1500과 2500 사이인 사원의 사번, 이름, 급여를 출력하세요
---and 이용
select empno, ename, sal from emp where sal >= 1500 and sal <= 2500;
--- between and 이용
select empno, ename, sal from emp where sal  between 1500 and 2500;
-- 10. 이름에 A를 포합하지 않는 사원의 사번, 이름을 출력하세요.
select empno, ename from emp where ename not like '%A%';
-- 11. 자신의 직속 상관이 없는 사원의 이름, 직급, 직속 상관의 사번을 출력
select ename, job, mgr from emp where mgr is null;
-- 12. 사번, 이름, 급여를 급여가 높은 순으로 출력하세요
select empno, ename, sal from emp order by sal desc;
-- 13. 입사일이 가장 최근 순으로 사번, 이름, 입사일을 출력하세요.
select empno, ename, hiredate from emp order by hiredate desc;

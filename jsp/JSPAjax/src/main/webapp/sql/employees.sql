-- 모든 직원 정보를 출력하는 쿼리문
SELECT * FROM employees;


-- 모든 직원 정보 + 직원이 근무하는 부서명까지 출력하는 쿼리문

SELECT e.* , d.department_name from employees e inner join departments d
on e.department_id = d.department_id;
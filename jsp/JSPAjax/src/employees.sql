-- ?번 사원의 이름을 ? 로 수정할 때
update employees set first_name=? where employee_id=?;

-- ?번 사원의 성을 ? 로 수정할 때
update employees set Last_name=? where employee_id=?;

-- ?번 사원의 이름과 성을 ? 로 수정할 때
update employees set first_name=?,  Last_name=? where employee_id=?;

-- ?번 사원의 이메일과 전화번호를 ? 와 ? 로 수정할 때
update employees set email=?,  phone_number=? where employee_id=?;

-- update 문을 수행할 때, 유저가 어떤 값을 수정할지 모른다.
-- 그래서 온갖 경우의 쿼리문을 다 만들 수가 없다.
-- > 해법 : 1) 먼저 수정할 사원 정보를 데이터 입력 UI에 바인딩 시켜 준다,
select * from employees where employee_id=?;
-- 이미 가지고온 데이터를 다시 가지고 오는 이유 -> DB는 여러사람이 동시에 사용하기 때문에 내가 수정하려고 데이터를 받아 놓은 사이 누군가가 수정해 놓았을 수도 있기 때문에
-- 수정하려고 할때 DB에서 새로 데이터를 불러와야 한다.
-- > 모든 컬럼의 값을 update 시켜준다. (update 쿼리문이 하나만 나오게 되어 코딩이 용이하게 된다.)

update employees set first_name=?, last_name=?, email=?, phone_number=?, hire_date=?, job_id=?, salary=?, commission_pct=?, manager_id=?, department_id=?
where employee_id=?;
-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 사원 입력

insert into employees values(212, '수정', '용', 'dragonc', '010-0000-0003', '2023-03-13', 'AD_VP', 17000, 0.01, 100, 90, null);

commit;
-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 삭제 작업
-- 1) 실제 delete 문을 사용해야하는 경우
-- 삭제 하기 전에 삭제될 사원을 다른 테이블(ex oldEmp)에 옮긴 뒤에 delete 수행

create table oldEmp
as
select * from employees where 1=0; -- 껍데기만 복사함



-- 2) 실제 delte 문을 사용하지 않는 겨우
-- 해당 테이블에 삭제 여부 컬럼 (isDelete)을 만든 후 삭제 되는 데이터에 대해 Y로 업데이트 수행
-- -> 데이터 검색시 안 보이도록 처리

alter table employees
add quit_date date;

update employees set QUIT_DATE=? where employee_id=?;
-- 퇴사 안한 사원
SELECT e.* , d.department_name from employees e inner join departments d
on e.department_id = d.department_id where quit_date is null order by EMPLOYEE_ID desc;

-- 퇴사 한 사원
SELECT e.* , d.department_name from employees e inner join departments d
on e.department_id = d.department_id where quit_date is not null order by EMPLOYEE_ID desc;

select * from employees where quit_date is null;


-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 사원 수정

SELECT e.* , d.department_name from employees e inner join departments d
on e.department_id = d.department_id where quit_date is null and employee_id = ?;

SELECT e.* , d.department_name from employees e inner join departments d
on e.department_id = d.department_id where quit_date is null and employee_id = 207;
-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 정렬 방법
SELECT e.* , d.department_name from employees e inner join departments d on e.department_id = d.department_id where quit_date is null order by EMPLOYEE_ID desc


-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 특정 이름으로 검색

SELECT e.* , d.department_name from employees e inner join departments d
on e.department_id = d.department_id where quit_date is null 
and first_name like '%ev%' or last_name  like '%ev%'
order by EMPLOYEE_ID desc;


-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------

create or replace PROCEDURE PROC_SAVEEMP
(
    -- in매개변수 (프로시져로 데이터를 보낼 변수)
    -- out매개변수 (프로시져에서 밖으로 반환될 변수) 등을 선언하는 부분
    
    FIRSTNAME in employees.FIRST_NAME%TYPE,
    LASTNAME in employees.LAST_NAME%TYPE,
    EMAIL in employees.EMAIL%TYPE,
    PHONENUMBER in employees.PHONE_NUMBER%TYPE,
    HIREDATE in employees.HIRE_DATE%TYPE,
    JOBID in employees.JOB_ID%TYPE,
    SALARY in employees.SALARY%TYPE,
    COMMISSIONPCT in employees.COMMISSION_PCT%TYPE,
    MANAGERID in employees.MANAGER_ID%TYPE,
    DEPARTMENTID in employees.DEPARTMENT_ID%TYPE,
    result out varchar2 
    
)
AS 

    tmpEmpID employees.EMPLOYEE_ID%TYPE;

BEGIN
    select max(EMPLOYEE_ID) + 1 into tmpEmpID from employees;

    insert into employees values(tmpEmpID, FIRSTNAME, LASTNAME, EMAIL, PHONENUMBER, HIREDATE, JOBID, SALARY, COMMISSIONPCT, MANAGERID, DEPARTMENTID);
    
    result := 'success';
    commit;
    
EXCEPTION -- BEGIN 부분에 있는 DML문장을 실행할 때 예외가 나면 처리될 문장들
    when others THEN 
    result := 'error';
    rollback;
    
END ;
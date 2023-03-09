

CREATE OR REPLACE PROCEDURE PROC_SAVEEMP
(
    ENAME in emp.ENAME%type,
    JOB in emp.JOB%type,
    MGR in emp.MGR%type,
    HIREDATE in emp.HIREDATE%type,
    SAL in emp.SAL%type,
    COMM in emp.COMM%type,
    DEPTNO in emp.DEPTNO%type,
    result out varchar2
    
)
AS 
-- 이 프로세스 내에서 사용할 변수 선언 구역
    tmpno emp.empno%type;

BEGIN
-- 이 프로세스 내에서 하고자 하는 내용 작성
    select max(tmpno) +1 into tmpno from emp;
    
    insert into emp values(tmpno, ENAME, JOB,
                            MGR, HIREDATE, SAL, COMM, DEPTNO );

EXCEPTION
-- BEGIN 부분에 있는 DML문장을 실행할 때 예외가 나면 처리될 문장들
when others THEN 
    result := 'error';
    rollback;

END;
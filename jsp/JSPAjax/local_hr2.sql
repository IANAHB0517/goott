create table friends
(
friendNo number(4) primary key,
friendName varchar2(6) not null,
mobile varchar2(13) unique,
addr varchar2(50));


set SERVEROUTPUT on;

SELECT e.* , d.department_name from employees e inner join departments d on e.department_id = d.department_id where quit_date is null order by EMPLOYEE_ID desc;


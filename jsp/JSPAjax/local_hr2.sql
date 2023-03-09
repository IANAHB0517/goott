create table friends
(
friendNo number(4) primary key,
friendName varchar2(6) not null,
mobile varchar2(13) unique,
addr varchar2(50));


set SERVEROUTPUT on;
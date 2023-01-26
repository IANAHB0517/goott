create table member (
id varchar2(20) ,
password varchar2(40),
regno varchar2(13) CONSTRAINT member_regno_nn not null,
mobile varchar2(13),
address varchar2(100),

CONSTRAINT member_id_pk primary key(id),
CONSTRAINT member_reg_uq unique(regno),
CONSTRAINT member_mobile_uq unique(mobile)
);

create table books(
code number(4) ,
title varchar2(50) constraint books_title_nn not null,
count number(6),
price number(6),
publish varchar2(50),

constraint books_code_pk primary key(code)
);

alter table books -- code 데이터 타입 바꾸기 number로
modify code number(4);

create table bookorder(
no varchar2(10) constraint bookorder_no_pk primary key,
id varchar2(20) constraint bookorder_id_fk references member(id),
code number(4) constraint bookorder_code_fk references books(code),
count number(6) ,
orderDate Date 



-- constraint bookorder_id_fk foreign key(id) references member(id) on delete set null
-- constraint bookorder_id_fk foreign key(code) references books(code) on delete set null
);

alter table bookorder -- code 데이터 타입 바꾸기 number로
modify code number(4);

drop table bookorder;
drop table books;
drop table member;

insert into member values('reader', 're123', '0001', '010-1234-0987', null);
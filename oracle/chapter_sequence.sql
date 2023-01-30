--------------------------------------------------------------------------------
create sequence seq_test
start with 1
increment by 1;

create table board(
no number(6) primary key,
title varchar2(100),
writer varchar2(20)
);

insert into board values(seq_test.nextval,'test1','둘리');

select * from board;

select seq_test.currval from dual;

create sequence seq_test2
start with 1
increment by 1
maxvalue 5
cycle 
cache 2;

create table product(
serialNo varchar(10) primary key,
prodName varchar2(14)
);

select to_char(sysdate) from dual;

insert into product values(to_char(sysdate, 'yymmdd')||'_'||seq_test2.nextval,'모니터');
insert into product values(to_char(sysdate +1, 'yymmdd')||'_'||seq_test2.nextval,'모니터');
insert into product values(to_char(sysdate +2, 'yymmdd')||'_'||seq_test2.nextval,'모니터');

-- ORA-00001: unique constraint (HR.SYS_C007103) violated
-- cycle 이 반복되어서 값이 고유하지 않아졌기 때문에 입력을 거절당했지만 시퀀스는 참조되었기 때문에 값이 증가한다.
-- select currval 도 마찬가지 현재 값을 참조 했기 때문에 증가된다.

select * from product;

drop sequence seq_test2;
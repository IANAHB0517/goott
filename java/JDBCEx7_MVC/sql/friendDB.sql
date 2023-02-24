
-- 테이블 생성
create table friends
(friendNo number(4) primary key,
friendName varchar2(6) not null,
mobile VARCHAR2(13) unique,
addr VARCHAR2(50));

select * from friends;

-- 친구를 저장하는 쿼리문
insert into friends values(select max(friendNo) as friendNo from friends), '둘리', '010-4885-4888', '쌍문동');
insert into friends(firendName, mobile, addr) values(?,?,?);

select (max(friendNo) as nextNo  from friends;


-- 친구 이름 사이즈 변경
alter table friends
modify friendname varchar2(15);

commit;
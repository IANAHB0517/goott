create table test(
test varchar2(200),
name varchar2(15)
);

insert into test values('테스트 중입니다', 'lsj');
insert into test values('테스트 중입니까?', 'lsj');

commit;




update test set test ='코린이네?' where name='에릭한';



insert into test values('졸려요', 'sleepy', 10, 100);
insert into test values('졸려요ddd', 'sleepy', 10, 100);

rollback;

update test set name='잠쟁이' where num = 10 and numnumnum = 100;
select * from test;

savepoint after_update;

delete from test where num = 10;




commit;

insert into member values('abcd');

savepoint after_insert;

insert into member values('efgh');

select * from member;

update member set userid='bbbb' where userid= 'abcd';

rollback to after_insert;

rollback;




-----------------------------------------------------------------------------------
-- 다음의 쿼리문이 순서대로 실행 되었다면, db에 영구 반영되는 문장은?
-- 1. insert 문 수행
-- 2. savepoint a;
-- 3. delete 문 수행
-- 4. savepoint b;
-- 5. update 문 수행
-- 6. rollback to a;
-- 7. insert 문 수행
-- 8. savepoint c;
-- 9. delete 문 수행
-- 10. commit;

-- 1, 7, 9 커밋됨;


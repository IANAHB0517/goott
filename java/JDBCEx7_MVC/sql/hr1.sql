select count(*) as cnt from friends where mobile='010-1111-2222';

select * from friends order by friendno;

update friends set friendname = ? where friendno = ?;

delete from friends where friendno = 5 ;

update friends set addr = '오목상가' where friendno = 6;

select count(*) as count from friends where friendno = 6;

commit;
rollback;
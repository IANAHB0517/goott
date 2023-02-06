-- 대여기록 CRUD
select * from rent;

insert into rent( USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE)
values (now(), '30', 'ER20223334' , 'Erotic',now()  , date_add(now(), INTERVAL +3 day ));

insert into rent( USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE)
values (now(), '25', 'CO20195555' , 'Comed' , now()  , date_add(now(), INTERVAL +3 day ));


-- NUM을 독립적인 파라이머리 키로 사용 하면서 부터 NUM 컬럼에 값을 넣어주면 중복 될 경우 데이터가 입력 되지 않는다.
insert into rent(NUM, USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE)
values (17, now(), '25', 'CO20195555' , 'Comed' , now()  , date_add(now(), INTERVAL +3 day ));

select * from rent where USER_NUM = 30;

update rent
set RETURN_DATE= null,RETURN_DUE_DATE= (select date_add(RENTDATE, INTERVAL +3 day ) where RENT_REC = '2023-02-06 17:37:19' )
where RENT_REC = '2023-02-06 17:37:19';

update rent
set RETURN_DATE = '2023-02-06' , ISRETURN = 'Y'
where RENT_REC = '2023-02-03 18:59:08';

delete from rent where RENT_REC = '2023-02-06 17:37:19';
-- delete from rent where RENT_REC = ;


-- 회원 CRUD

insert into member(USER_NAME, PHONE_NUM, BIRTHDAY, );


COMMIT;

-- insert member (USER_NAME, PHONE_NUM, BIRTHDAY, GENDER, );


-- ddl
alter table rent drop PRIMARY KEY RENT_REC;
-- 대여기록 인서트

-- insert into gottclass6.rent values(date_format(now(), '%y-%m-%d-%h:%i:%s'),GT001,"AC20100214",Action,date_format(now(), '%y-%m-%d'),N,0,N,);

insert into gottclass6.rent(RENT_REC, USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DATE) values (date_add(now(), INTERVAL -300 day ), '3', 'MU20226666' , 'Musical',date_add(now(), INTERVAL -300 day )  , date_add(now(), INTERVAL -297 day ));
insert into gottclass6.rent(RENT_REC, USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DATE) values (now(), '2', 'DR20230101' , 'Drama',now()  , date_add(now(), INTERVAL +3 day ));
insert into gottclass6.rent(RENT_REC, USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DATE) values (now(), '4', 'OT20221007' , 'Other',now()  , date_add(now(), INTERVAL +3 day ));
insert into gottclass6.rent(RENT_REC, USER_NUM, VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DATE) values (now(), '8', 'FA20011231' , 'Fantasy',now()  , date_add(now(), INTERVAL +3 day ));

commit;


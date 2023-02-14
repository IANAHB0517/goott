SELECT * FROM gottclass6.rent;

SELECT * FROM gottclass6.rent where video_code = 'HO19940101';



insert into rent(user_num , VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE ,RETURN_DATE)
values ( 12 ,'FA20011231' , 'Fantasy' ,  NOW()  , date_add(now(), INTERVAL +3 day ), date_add(now(), INTERVAL +3 day ));


insert into rent(user_num , VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE ,RETURN_DATE)
values ( 5 ,'FA20011231' , 'Fantasy' , date_add(now(), INTERVAL -2 day ), date_add(now(), INTERVAL +1 day ), date_add(now(), INTERVAL +1 day ));

insert into rent(user_num , VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE ,RETURN_DATE)
values ( 12 ,'FA20032222' , 'Fantasy' , date_add(now(), INTERVAL -2 day ), date_add(now(), INTERVAL +1 day ), date_add(now(), INTERVAL +1 day ));


delete from rent where num = 35;
update rent set user_num = 14  where num = 137;


insert into rent(user_num , VIDEO_CODE, GENRE_CODE, RENTDATE ,CHECK_LATE , RETURN_DUE_DATE)
values ( 42 ,'MU20167904' , 'Musical' , date_add(now(), INTERVAL -1500 day ), 'Y' , date_add(now(), INTERVAL -1497 day ));

insert into rent(user_num , VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE)
values ( 24 ,'DR20191110' , 'Drama' , now(), date_add(now(), INTERVAL +3 day ));

delete from rent where num = 188;
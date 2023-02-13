SELECT * FROM gottclass6.rent;



insert into rent(user_num , VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE ,RETURN_DATE)
values ( 12 ,'FA20011231' , 'Fantasy' ,  NOW()  , date_add(now(), INTERVAL +3 day ), date_add(now(), INTERVAL +3 day ));


insert into rent(user_num , VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE ,RETURN_DATE)
values ( 5 ,'FA20011231' , 'Fantasy' , date_add(now(), INTERVAL -2 day ), date_add(now(), INTERVAL +1 day ), date_add(now(), INTERVAL +1 day ));

insert into rent(user_num , VIDEO_CODE, GENRE_CODE, RENTDATE , RETURN_DUE_DATE ,RETURN_DATE)
values ( 12 ,'FA20032222' , 'Fantasy' , date_add(now(), INTERVAL -2 day ), date_add(now(), INTERVAL +1 day ), date_add(now(), INTERVAL +1 day ));


delete from rent where num = 35;
update rent set user_num = 14  where num = 137
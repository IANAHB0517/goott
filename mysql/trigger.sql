use gottclass6;

insert into useAddress value('abc123', '둘리');

insert into useAddress value('ddd111', '도우너');

commit;

delimiter $$
create trigger  trg_removeMemberAuto
after delete
on userAddress
for each row
begin
	insert into removeUser(removeId, removeName) values (old.id, old.name);
end $$
delimiter ;


delete from userAddress where id = 'abc123';


insert into Stock(prodName, qty) value ('콜라', 100);
insert into Stock(prodName, qty) value ('새우깡', 34);

delimiter $$
create trigger trg_autoSell
after insert
on Sell
for each row
begin 
	update Stock set qty = qty - new.qty where prodName = new.whatProd;
end $$
delimiter ;

insert into Sell(whatProd, qty) values ('콜라' , 3);

select * from Sell;




-- trigger

-- Delimiter $$
-- create trigger trg_change_black
-- after update
-- on member
-- for each row
-- begin
-- 	update member set chk_black = "Y"
--     where overdue >= 100;
-- end $$    
-- Delimiter ;


Delimiter $$
create trigger trg_total_view
after insert
on rent
for each row
begin
	update video set total_view = total_view+1
    where video_code =new.video_code;
end $$    
Delimiter ;

-- Delimiter $$
-- create trigger trg_update_return_due_date
-- after insert
-- on rent
-- for each row
-- begin
-- 	update video set return_due_date = new.rentdate + 3 where video
-- end

Delimiter $$
-- 김소영씨 코드
set @date1 = '2023-01-09' ; -- 대여날짜(rentdate)
set @period = (select lend_time from genre where genre_code = (select genre_code from video where video_title = @vname));  -- 대여기간
set @duedate = date_add(@date1, interval  @period day) ; -- 반납예정일 (return_due_date)
set @returndate = date_add(@date1, interval 3 day);  -- 실제 반납일 

$$    
Delimiter ;

-- set @date = '2023-01-09' -- 변수 사용 2023-01-09 이라는 날짜를 @date에 넣어줌;

set @video = 'CH20010001';
set @video_genre = (select GENRE_CODE from video where VIDEO_CODE = @video);
set @rent_date = (now());
set @return_date = (date_add(@rent_date, interval 3 day));




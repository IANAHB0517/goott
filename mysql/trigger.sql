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



set @date = '2023-01-09' -- 변수 사용 2023-01-09 이라는 날짜를 @date에 넣어줌


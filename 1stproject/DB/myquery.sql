-- 내가 필요한 쿼리문

-- Notice
select * from Notice;

insert into Notice (noticeNo, noticeTitle, noticeContent ) values("N001", "교환정책", "교환 정책을 준수 하겠습니다");
insert into Notice (noticeNo, noticeTitle, noticeContent ) values("N002", "배송정책", "배송 정책을 준수 하겠습니다");
insert into Notice (noticeNo, noticeTitle, noticeContent ) values("N003", "환불정책", "환불 정책을 준수 하겠습니다");

-- QnA
select * from QnA;
-- qId 는 일자 + 아이디 + QA + 숫자값 로 처리하기
insert into QnA(qId, qTitle, qCategory, qContent,  memberId) values ('230424ericHan12QA002', "상품문의", "상품", "뱅 추가 상품은 없나요",  "ericHan12" );
insert into QnA(qId, qTitle, qCategory, qContent,  memberId) values ('230424doniQA001', "배송이 왜케 느려요", "배송", "어제 구매했는데 왜 안와요",  "doni" );
insert into QnA(qId, qTitle, qCategory, qContent,  memberId) values ('230424sky123QA003', "환불해줘요", "환불", "아그냥 해줘요",  "sky123" );

-- Answer
select * from Answer;
-- aId 는 일자 + 아이디 + QAN + 숫자값 로 처리하기
insert into Answer(aId, aContent, qId) values ('230425QAN001', '뱅에 는 추가상품이 없습니다', '230424ericHan12QA002');
insert into Answer(aId, aContent, qId) values ('230424doniQAN002', '모든 배송은 주문후 3일 이내에 완료됩니다', '230424doniQA001');
insert into Answer(aId, aContent, qId) values ('230424sky123QAN003', '모든 배송은 주문후 3일 이내에 완료됩니다', '230424sky123QA003');

commit;

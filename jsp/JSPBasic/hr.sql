exec PROC_insertFriend('enffl', '010-3333-2222', 'wlqwnth' );



set SERVEROUTPUT on;




create or replace PROCEDURE PROC_insertFriend
(
-- in매개변수 (프로시져로 데이터를 보낼 변수)
-- out매개변수 (프로시져에서 밖으로 반환될 변수) 등을 선언하는 부분
 pFriendName in friends.FRIENDNAME%type,
 pMobile in friends.MOBILE%type,
 pAddr in friends.ADDR%type
)


AS 
    -- in/out 매개변수는 아니지만, 임시로 사용할 변수들을 선언하는 부분
    tmp_friendNo friends.FRIENDNO%type;
    
BEGIN
    -- 실직적으로 저장프로시져가 호출 될 때 실행 되는 문장들
    select max(FRIENDNO) + 1 into tmp_friendNo from friends;
    
    
  insert into friends values(tmp_friendNo, pFriendName, pMobile, pAddr);
  DBMS_OUTPUT.put_line('친구저장완료');
END;
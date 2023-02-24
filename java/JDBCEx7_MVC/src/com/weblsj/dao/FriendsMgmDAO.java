package com.weblsj.dao;

import java.util.List;

import com.weblsj.dto.FriendDTO;
import com.weblsj.dto.ModifyFriendAddrByFriendNoDTO;
import com.weblsj.dto.ModifyFriendMobileByFriendNoDTO;
import com.weblsj.dto.ModifyFriendNameByNoDTO;
import com.weblsj.vo.Friend;

public interface FriendsMgmDAO {
	// 전체 친구 목록조회
	List<Friend> selectAllFriends() throws Exception;

	// 친구 저장
	int insertFriend(int friendNo, FriendDTO dto) throws Exception;

	// 친구번호 중 가장 높은 번호를 가져옴
	int getNextFriendNo() throws Exception;

	// 전화번호를 받아 테이블에 받은 전화번호가 중복되는지?
	int isDuplicateMobile(String mobile) throws Exception;

	// 친구 조회 (이름으로 조회)
	List<Friend> searchFriendsByName(String name) throws Exception;

	// 친구 이름 수정
	// 전체 친구 목록을 보여주고, 수정할 친구 번호를 입력 받고, 이름 입력받아 수정
	int updateFriendName(ModifyFriendNameByNoDTO dto) throws Exception;


	// 친구 수정 (전화 번호)
	int updateFriendMobile(ModifyFriendMobileByFriendNoDTO dto) throws Exception;

	// 친구 주소 수정
	int updateFriendAddr(ModifyFriendAddrByFriendNoDTO dao) throws Exception;

	// 친구 삭제
	int deleteFriendByFriendNo(int delNo) throws Exception;
	
	// 친구 존재 여부 확인
	int isValidFriend(int friendNo) throws Exception;
}

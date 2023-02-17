package weblsj;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
			//generic은 객체로만
		// Map에 데이터 저장
		map.put(1, "사과");
		map.put(2, "배");
		map.put(3, "포도");
		map.put(4, "오랜지");
		map.put(5, "딸기");
		map.put(6, "바나나");
		map.put(1, "치킨"); // 키가 중복되면 해당 키의 value가 overwrite 됨
		
		// 데이터 탐색
		if (map.containsKey(7)) {
		// 7이라는 키가 있으면 7번 키를 가진 값을 출력
			System.out.println(map.get(7));
			
		}
		System.out.println(map.get(1));
		
		//Map에 있는 모든 키를 검색하자
		System.out.println(map.keySet());
		Set<Integer> keys = map.keySet();
		for (Integer i : keys) {
			System.out.print(i + "\t");
		}
		
		//Map에 있는 모든 값을 검색
		Collection<String> vList = map.values();
		for (String v : vList) {
			System.out.print(v + "\t");
		}
			
	}

}

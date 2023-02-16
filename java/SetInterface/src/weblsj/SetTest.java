package weblsj;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		
		set.add(100);
		set.add(010); // 0으로 시작해서 8진수로 인식한다.
		set.add(1);
		set.add(10);
		set.add(40);
		set.add(1); // // 중복이 제거됨
		
		for (Integer i : set) {
			System.out.println(i);
		}
		
		System.out.println("=============================");
		
		Set<Integer> set2 = new TreeSet<>();
		
		set2.add(100);
		set2.add(1);
		set2.add(10);
		set2.add(40);
		set2.add(1); // // 중복이 제거되고 정렬됨
		


		for (Integer i : set) {
			System.out.println(i);
		}
		
	}

}

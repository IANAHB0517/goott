package weblsj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTest {
	
	public static void main(String[] args)  {
		List<String> list = new ArrayList<>();

		list.add("마이콜"); list.add("둘ㄹ"); list.add("또치"); list.add("도우너");
		
		System.out.println("=====정렬전======");
		
		for (String s : list) {
			System.out.println(s);
			
		}
		
//		// 정열을 하기 위해 정렬기준의 객체인 Comparator 구현
		Comparator<String> comp = new Comparator<String>() {
//
//			@Override
//			public int compare(String o1, String o2) {
//				if(o1 < o2) {
//				}
//				return 0;
//			}
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
				 
			}
			
//			//Compares its two arguments for order. Returns a negative integer, zero, or a positive integer
//			//as the first argument is less than, equal to, or greater than the second.
//			//첫 번째 매개변수가 두번째 매개변수 보다 작으면 음수 , 같으면 0 , 크면 양수를 반환하라
//			
//		};
		
		
		
	};
		list.sort(comp);
		System.out.println("=====정렬후======");
		System.out.println();
		for (String s : list) {
			System.out.println(s);
			
		}

}
	
}

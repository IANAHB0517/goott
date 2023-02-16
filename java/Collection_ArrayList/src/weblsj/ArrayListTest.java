package weblsj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ArrayListTest {
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
	
		// 데이터 추가
		list.add(10);
		
		list.add(30);
		list.add(1, 20);
		
		//
		int a = list.get(2);
		
		System.out.println(a);
		
		Collection<Integer> c = new Stack<>();
		c.add(100);
		c.add(200);
		
		list.addAll(list);
		list.addAll(c);
		
		System.out.println("=================================");
		System.out.println("ArrayList 출력결과");
		//리스트에 있는 모든 자료 출력
		for(Integer i : list) {
			System.out.print(i + "\t");
		}
		
//		System.out.println(list.indexOf(100) + "번째 있음");
//		
//		System.out.println(list.contains(300));
		System.out.println();
		System.out.println("=================================");
		System.out.println("Stack 출력결과");
		
		Stack tmp = (Stack)c;
		
			System.out.print((Integer)tmp.pop() + "\t");
			System.out.println((Integer)tmp.pop());
		
		
		System.out.println("=================================");
		
		System.out.println("Iterator 출력결과");
		// 리스트를 반복
		Iterator<Integer> iter = list.iterator(); // iter가 list의 첫번째 요소를 가리킴
		// 컬랙션의 종류의 상관없이 0번째 요소부터 출력하고 싶을 때 사용
		// Iterator를 사용하는 이유 : 컬렉션의 종류에 상관없이 데이터를 탐색하는 방법을 유지하고 싶을 때 사용
		
		for (Iterator iterator = list.iterator(); // 초기값 iterator
			iterator.hasNext(); // 조건식 iterator의 다음 값이 있는가?
				) // 증감식 생략  iterator가 정수형이아니기때문에
		{
		 System.out.print(iterator.next() + "\t");
			
		}
		System.out.println();
		System.out.println("=================================");
		
		
		
		LinkedList<Integer> l = new LinkedList<>();
		for (int i =0; i < 100000; i++) {
			l.add(i);
		}
		
		
		
		long startTime = System.currentTimeMillis();
		
//		for (int i =0; i < 100000; i++) {
//			System.out.print(l.get(i) + "\t");
//		} // 소요시간 182681
		
//		for (Iterator iterator = l.iterator(); iterator.hasNext();) { // 속도가 약 1000 배정도 나는듯 
//			System.out.print((Integer) iterator.next() + "\t"); 
//		} // 소요시간 161
		// hashing 함수를 사용 // 각 데이터의 위치를 저장해놓고 저장되어 있는 위치를 참조해서 값을 가져옴  
		
		for(Integer i : l) {
			System.out.print(i + "\t");
		} // 소요시간 154
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("\n소요시간 : " + (endTime - startTime));
		
	}
	
}

package weblsj;

import java.util.Iterator;
import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>(); // 자바에서의 컬렉션은 가변길이 배열
		
		stack.push("이상진");
		stack.push("한정우");
		stack.push("배기양");
		
//		while(!stack.isEmpty()) {
//			System.out.println(stack.pop()); // remove
//			System.out.println(stack.peek()); // 잠깐 꺼내 보기 때문에 무한 반복을 돌게 된다.
//			
//		}
		
		System.out.println(stack.peek());
		System.out.println(stack.peek());
		System.out.println(stack.peek());
		
		System.out.println(stack.size());
		
		for (Iterator iterator = stack.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
			// 자료구조에 상관없이 0번째 부터 마지막까지 조회 
		}

	}

}

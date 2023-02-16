package weblsj;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueTest {
	public static void main(String[] args) {
		
		Queue<String> queue = new ArrayDeque<>();
		
		queue.add("배기양");
		queue.add("한정우");
		queue.add("이상진");
		
		System.out.println(queue.poll()); // 데이터가 나오면 배열에서 삭제됨		
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
}

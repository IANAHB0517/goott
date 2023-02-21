package weblsj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadTest {

	static final int THREADCNT = 1;
	
	public static void main(String[] args) {
		
		
//		List<Integer> list = new ArrayList<>();
// 동기화를 제공하는 리스트를 만들고
		List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
		
		
		
		ExecutorService es = Executors.newFixedThreadPool(THREADCNT);
		
		for (int i = 0; i < THREADCNT; i++) {
			es.execute(new Runnable() {  // 2개의 쓰레드를 만듦
				
				@Override
				public void run() {
					while(true) {
						// 해당 리스트에 대해 작업하는 코드들을 synchronized
							synchronized (list) {
								list.clear();
								list.add(100);
								list.remove(0);
							}
	
					}
				}
			});
		}
	}

}
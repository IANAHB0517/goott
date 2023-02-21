package weblsj;

public class ThreadTest {

	public static void main(String[] args) {
		// 스레드 객체 생성법
		// 1) Thread 클래스를 상속받아 구현한 클래스
		ThreadEx t1 = new ThreadEx();

		// 2) Runnable 인터페이스를 구현한 클래스
		Runnable r = new ThreadRunnablee();
		Thread t2 = new Thread(r);
		
		
		t1.setPriority(Thread.MAX_PRIORITY); //  t1 스레드의 우선 순위를 가장 높게 조정
		// 그래도 sleep 이 들어가면서 가장 늦게 완료된다.
		
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY); // main 함수의 스레드를 가장 낮게 조정
		// 전보다는 main 스레드의 완료 시기가 늦어지는 걸 볼 수 있다.
		
		
		// 스레드를 실행 시키는 방법
		t1.start(); // 스레드가 시작되며 스레드가 실행 가능한 상태로 놓이게 된다.
		t2.start(); //
		
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + ", i :" + i);
			}
		
	}

}

class ThreadEx extends Thread { // 부모가 Thread이므로 ThreadEx도 스레드 객체가 된다.

	// 스레드가 생성되어 실행될 때 자동으로 호출되는 일종의 콜백 함수
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(getName() + ", i :" + i);
			
			try {
				Thread.sleep(500); // thread의 상태를 변화시키는 메서드는 100% 보장되지는 않는다. 그래서 예외처리가 필요하다
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.run();
	}
	// 상속받아도 에러가 없는 경우 
	// 상속 된 클래스가 구현되지 않은 추상메서드를 가지고 있지 않은 경우
	
	
}

class ThreadRunnablee implements Runnable { // 부모가 Runnable이므로 ThreadRunnablee도 Runnable 객체
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + ", i :" + i);
			
						
		}
		
	}
}
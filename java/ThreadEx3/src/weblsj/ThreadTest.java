package weblsj;

public class ThreadTest {

	public static void main(String[] args) {
//		Family 아빠 = new Family("아빠"); // 이러면 가족이 세개가 생김
//		Family 엄마 = new Family("엄마");
//		Family 나 = new Family("나");
		Family fam = new Family(" ");
//		
//		
//		Thread fThread = new Thread(아빠);
//		Thread mThread = new Thread(엄마);
//		Thread nThread = new Thread(나);
//		
//		mThread.start();
//		nThread.start();
//		fThread.start();

		Thread fThread = new Thread(fam);
		Thread mThread = new Thread(fam);
		Thread nThread = new Thread(fam);

		fThread.start();
		mThread.start();
		nThread.start();

	}
}
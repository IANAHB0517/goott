package weblsj;

import javax.swing.JOptionPane;

public class ThreadTest {

	public static void main(String[] args) {
		
		
		Thread t1 = new Thread(new ThreadRunnable());
		
		t1.start();
		
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요");
		System.out.println("입력 한 값은 : " + input);
	
	}

}


class ThreadRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println(i);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	} 
	
}
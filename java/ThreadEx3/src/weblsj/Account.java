package weblsj;

public class Account {
	private int balance = 1000000;

	public int getBalance() {
		return balance;
	}
	

//	public synchronized boolean withdraw(int money) { // synchronized 동기화 시키는 키워드
	public boolean withdraw(int money) { 
		boolean result = false;
		
		synchronized (this) { // 이객체가 메서드의 이부분에 접근할 때 동기화 하는 방법 
			if (this.balance >= money) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				this.balance -= money;
				result = true;
			}
		}
		return result;
	}
}

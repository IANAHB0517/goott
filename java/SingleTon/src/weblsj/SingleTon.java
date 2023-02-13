package weblsj;

public class SingleTon {
	
	private static SingleTon instance;
	
	private SingleTon() { } // private한 생성자를 만들어야 하므로 기본 생성자를 생략 하면 안된다.
	
	public static SingleTon getInstance() {
		if (instance == null) { // 객체가 없다면
			instance = new SingleTon(); // 새로운 객체를 만들어서
		}
		
		return instance;
	}
	
}

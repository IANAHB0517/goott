package weblsj;

public class StaticVSInstance {
	static int sInt; // static 멤버 변수 // 프로그램이 종료될 때 까지 남아있는다.
	int iInt; // instance 멤버 변수 // 객체가 생성되어야 메모리가 부여됨
	

	{
		// instance 멤버들을 초기화 할 수 있는 블럭
		 iInt = 5;
	}
	
	static {
		// static 멤버들을 초기화 할 수 있는 블럭
		sInt = 100;
	}
	
	
	public void acc() {
		StaticVSInstance.sInt++;
		this.iInt++;
		
		System.out.println("no : " + this.hashCode() + ", sInt : " + StaticVSInstance.sInt + ", iInt : " + this.iInt);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			StaticVSInstance svi = new StaticVSInstance();
			svi.acc();
			
		}
	}

}

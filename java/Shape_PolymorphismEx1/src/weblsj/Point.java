package weblsj;


//public class Point extends Object{ 이 형태가 생략된 모습
// 멤버 { 변수 상수 생성자 메서드(getter,setter,toString) } 을 모두 갖춘 상태 bean
public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	Point(Point p){ // 객체를 만들면서 멤버변수를 초기화하는 것이 생성자의 가장 주된 역할
		// p 라는 객체를 깊은 복사
		this.x = p.getX();
		this.y = p.getY();
	}

	
	// 메서드
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	@Override // anotation Object의 메서드를 오버라이딩 함
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	
	
	
}

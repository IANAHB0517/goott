package weblsj;


//public class Point extends Object{ 이 형태가 생략된 모습
// 멤버 { 변수 상수 생성자 메서드(getter,setter,toString) } 을 모두 갖춘 상태 bean
public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
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
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
	
	
}

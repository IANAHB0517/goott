package weblsj;

public class Triangle extends Shape {
	
	private int height, base; //제너레이터가 가끔씩 구분을 못해서 getter setter 를 만들때 두번 써야하는 경우가 생긴다.

	public Triangle(String name, String color, Point p, int base, int height) {
		// 부모 객체가 만들어지려면 String name, String color, Point p 매개 변수가 필요하다.
		// 그래서 현재 객체인 Triange에도 String name, String color, Point p 매개 변수로 받는 생성자를
		// 만들어 주고, 부모 생성자를 호출하여 부모 객체가 생성이 되도록 해야 한다.
		// String name, String color, Point p를 초기화하는 부분은 부모 생성자에 있으므로 재사용
		super(name, color, p);  // 부모 도형을 만들고
		// 부모에게 없는 멤버 변수인 base와  height는 현재 객체의 생성자에서 초기화 한다.
		this.base = base;       // 나에게 밑변와 높이를 부여한다.
		this.height = height;
	}

	// 부모 객체인 Shape에 기본 생성자가 없기 때문에 부모객체를 만들지 못해서 에러가 날 수 있다.
	// 해결 방법 : 1) 부모 객체에 기본 생성자를 만들어 준다.
	//			   2) 부모 객체가 가지고 있는 오버로딩된 생성자를 이용하여 부모객체를 만들도록 해야 한다.
	
	@Override
	public void draw() {
		// 내가(Triangle) 가지고 있는 toString() 호출
		System.out.println(this.toString() + "인 삼각형이 그려집니다.");
		// 부모가 (Shape) 가지고 있는 toString() 호출
		System.out.println(super.toString() + "인 도형이 그려집니다.");
	}

	
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	@Override
//	public String toString() {
//		// 부모 (Shape)가 물려준 toString()을 이용하지 않고 오버라이딩
//		// name 와 color는 부모가 가지고 있으면서 Triangle에게 물려준 것이기 때문에 부모로 부터 가져와야 한다.
//		// point 도 부모가 가진 객체이기 때문에 그 객체에 접근한 뒤에 객체가 가진 메서드를 호출해야한다.
//		return "Triangle [name=" + super.getName() + ", color=" + super.getColor() + ", point= (" + super.getP().getX() + "," +   super.getP().getY() + ")"
//				+ " height=" + height + ", base=" + base + "]";
//	}

	public String toString() {
		// 부모 (Shape)가 물려준 toString()을 이용해서 수정
		return super.toString() + "[ 높이 " + height + ", 밑변 " + base + "]";
	}
	
	
}

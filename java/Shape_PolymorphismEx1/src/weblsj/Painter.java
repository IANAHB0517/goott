package weblsj;

public class Painter {
	private String name;

	public Painter(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	// 화가가 각각의 원, 삼각형, 사각형을 그린다고 표현할 때, 아래의 코드로 구현하면
	// 코드의 재사용성이 떨어진다.
//	
//	public void drawTriangle(Triangle t) {
//		System.out.println("이름이 " + t.getName() + "이고, 색상은 " + t.getColor() + "이고, 원점 : "
//				+ t.getP().getX() + "," + t.getP().getY() + ", 밑변 " + t.getBase() + ", 높이 " + t.getHeight()
//				+ "인 삼각형을 그립니다.");
//		
//	}
//	
//	public void drawRectangle(Rectangle r) {
//		System.out.println("이름이 " + r.getName() + "이고, 색상은 " + r.getColor() + "이고, 원점 : "
//				+ r.getP().getX() + "," + r.getP().getY() + ", 밑변 " + r.getWidth() + ", 높이 " + r.getHeight()
//				+ "인 사각형을 그립니다.");
//		
//	}
	
	// 그럴것이 아니라
	// Shape s = c1;
	// 다형성의 2번째 법칙 (? : 이득),(제2다형성) : 부모(Shape) 이름으로 모든 자식 객체를 매개변수로 다 받을 수 있다.
	// 부모형식으로 받기 때문에 자식형식을 매개변수로 받는 메서드를 일일이 만들 필요없다.
	public void drawShape(Shape s) { 
//		if (s instanceof Circle) {
//			 Circle c = (Circle)s; // 다운캐스팅 (Down Casting)캐스팅 : 업캐스팅 된 객체가 자기 자신의 타입으로 되돌아 가는 것
//			 System.out.println(c.toString());
//		} 
//
//		if (s instanceof Triangle) {
//			Triangle t = (Triangle)s;
//			 System.out.println(t.toString());
//		} 
//
//		if (s instanceof Rectangle){
////			Rectangle r = (Rectangle)s; // 중간 변환 과정없이 아래 구문에서 한번에 처리하는 경우가 많..(r을 재사용할게 아니라면) 을 것 이다.
//			 System.out.println(((Rectangle)s).toString());
//		}
		
		// 다형성 1번째 법칙(? : 개이득) : 부모 이름으로 자식이 구현한 멤버를 사용한다.
		System.out.println(s.toString());
		// 동적로딩
		// 부모 클래스가 가지고 있던 멤버를 자식이 업데이트한 경우 부모가 사용가능 하다.
		// 부모 클래스가 원래 가지고 있던 멤버만 가능
		
		s.draw(); // 내부적으로 업캐스팅 되기전의 데이터 타입을 알고 있어서 제2다형성을 사용한 경우다.
		// s가 원래 Circle 타입이었다.
		// 부모에게 draw()가 추상 메서드 였지만,
		// 자식 객체인 Circle에서 구현 되어서 부모인 Shape의 이름으로 자식의 draw()를 사용할 수 있게 되었다.
		// 결과적으로 추상메서드를 사용하는 이유이지 않을까
	}
	
	
	}

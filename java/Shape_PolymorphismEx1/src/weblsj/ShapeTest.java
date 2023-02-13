package weblsj;

import java.awt.Graphics;

public class ShapeTest {

	public static void main(String[] args) {
//		Shape n = new Shape() {
//			
//			@Override
//			public void draw(Graphics g) { 
//				// TODO Auto-generated method stub 
//				// 객체는 생성되면 무언가를 해야하는데 Shape 는 추상 메서드만 있을 뿐 메서드가 없기 때문에
//				// 추상 메서드의 기능을 구현(오버라이딩)하면 객체를 만들어 준다 -> 객체가 분명해진다. 
//				
//			}
//		};
		
//		Shape 클래스가 추상 클래스이기 때문에 객체를 만드려면 추상 메서드를 오버라이딩 해야 만들어진다.
//		추상 메서드를 overriding한다는 의미는 객체가 분명해졌다. -> 그 객체는 더이상 Shape가 아님. (추상적이지 않다.)
//		위와 같은 논리적 모순이 생기기에 아래와 같이 쓰진 않는다.
//		Shape s = new Shape("도형", "Black", new Point(0, 0)) {
//			
//			@Override
//			public void draw(Graphics g) {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//		
//		System.out.println(s.toString());

		
		Triangle t1 = new Triangle("삼각형", "red", new Point(0, 0), 5, 10);
//		System.out.println(t1.toString());
		
//		t1.draw();
		
		Rectangle r1 = new Rectangle("사각형", "노랑색", new Point(0,0), 20, 30);
//		r1.draw();
		
		Circle c1 = new Circle("땡글이", "#fff", new Point(25, 25), 8);
//		c1.draw();
		
		// 다형성
		// c1은 Circle의 객체(하위객체)이고 sCircle은 Shape의 객체 (상위객체이다.)
		// 아래의 연산이 수행되면, 업캐스팅(upCasting)이 발생하며 수행 되게 된다.
		Shape sCircle = c1; // c1 보다 sCircle이 상위의 클래스(더 넓은 범위)를 가지고 있기 때문에 대입이 가능하다.
		//Shape sCircle = (shape)c1; 이것과 같음
		
		Painter 피카소 = new Painter("피카소");
		피카소.drawShape(c1);
		피카소.drawShape(r1);
		피카소.drawShape(t1);
		
	}

}

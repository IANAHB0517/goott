package weblsj;

import java.awt.Graphics;

//추상클래스가 되는 조건
// 1) class 키워드 앞에 abstract 키워드를 붙인다. (추상 메서드가 없어도 추상 클래스로 만들어짐)
// 2) 클래스 멤버 메서드 중 하나라도 추상 메서드가 있다면 자동으로 추상 클래스가 되어야 한다.

//public class Shape { // 추상 메서드를 포함시키기 위해서는 클래스가 abstract 이어야 한다.
public abstract class Shape { // 추상 클래스는 객체로 만들 수가 없다. // 설계시 필요하다.
	private String name;
	private String color;
	private Point p;
	
	
	public Shape(String name, String color, Point p) { // 다른곳에서 Point 타입의 p를 생성 해서 주소를 넣어줘라
		this.name = name;
		this.color = color;
		this.p = p;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Point getP() {
		return p;
	}


	public void setP(Point p) {
		this.p = p;
	}
	
	
//	public void draw() {
//		// 상위 클래스인 Shape에서는 draw 메서드를 구현 하지 못한다.
//		// 어떤 도형을 어떻게 그려야할지 모르기 (추상적) 때문이다.
//		// -> 추상 메서드로 만든다. 
//	}
	

	// 추상메서드 : 바디가 없는 메서드 
	// 언젠가는 구현이 되어야 함.
	public abstract void draw(Graphics g);
}


// 추상메서드란
// 두 개의 숫자를 입력 받아서 결과 값을 반환 한다고 해도 만드는 사람 마다 다 방법이 다를 수 있기 때문에 
// 설계자가 어떤 이름으로 어떤 값을 반환 하는지 또 어떤 매개변수를 사용하는지를 확정 지어서 실무자에게 넘겨주기 위한 형태의 메서드


package weblsj;

public class Circle extends Shape {
	private int radius;
	
	Circle(String name, String color, Point p, int radius) {
		super(name, color, p);
		this.radius = radius;	
	}

	public void draw() {
		System.out.println(this.toString());
	}
	
	int getRadius() {
		return this.radius;
	};
	
	void setRadius(int r) {
		this.radius = r;
	}
	
	public String toString() {
		return super.toString() + "[ 반지름 " + this.radius + "] 인 원이 그려집니다.";
	} 
}

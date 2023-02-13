package weblsj;

public class Rectangle extends Shape{
	private int width;
	private int height;

	Rectangle(String name, String color, Point p, int width, int height) {
		super(name, color, p);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw() {
		System.out.println( this.toString() + "인 사각형이 그려집니다."); 
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return  super.toString() + "[넓이 " + this.width + ", 높이 " + this.height + "]";
	}
	
	

	

}

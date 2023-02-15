package weblsj;

public abstract class Snack{
	private String kind;
	private Banjook b;
	private Sock c;
	private int size;
	private	int price;
	
	public Snack() {
	}
	
	public Snack(String kind, Banjook b, Sock c, int size, int price) throws IngredientDifferentException  {
		this.kind = kind;
		this.b = b;
		this.c = c;
		if (c.equals("팥") | c.equals("슈크림") | c.getIng1().equals("피자")) {
			System.out.println("맛있겠다");
		} else {
			throw new IngredientDifferentException("그런건 안팔아요");
		}
		
		this.size = size;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getKind() {
		return kind;
	}

	public Banjook getB() {
		return b;
	}

	public Sock getC() {
		return c;
	}

	public int getSize() {
		return size;
	}
	
	
	public abstract void bake();
	
	
}

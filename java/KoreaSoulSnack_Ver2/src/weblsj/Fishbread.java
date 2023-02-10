package weblsj;

public class Fishbread extends Snack{
	private String store;
	
	Fishbread(String kind, Banjook b, Sock c, int size, int price , String store) {
		super(kind, b, c, size, price);
		this.store = store;
	}
	
	
	
	@Override
	public void bake() {
		System.out.println("짠" + this.toString() + "이 다 구워졌다.");
		
	}
	
	public String toString() {
		return this.store + "에서 구운 따끈따끈한" + super.toString();
	}


}

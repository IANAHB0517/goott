package weblsj;

public class Sock {
	private String ingredient1;
	private String ingredient2;
	
	public Sock() {
	}
	
	public Sock(Sock s) {
		this.ingredient1 = s.getIng1();
		this.ingredient2 = s.getIng2();
		
	}
	
	public Sock(String ing1 , String ing2) {
		this.ingredient1 = ing1;
		this.ingredient2 = ing2;
	}
	
	public String getIng1() {
		return this.ingredient1;
	}

	public String getIng2() {
		return this.ingredient2;
	}
	
	public String toString() {
		return this.ingredient1 + " 그리고 " + this.ingredient2 + "이 황금비율로 섞여있습니다.";
	}
}

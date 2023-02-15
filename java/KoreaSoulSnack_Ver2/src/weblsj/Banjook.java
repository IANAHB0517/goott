package weblsj;

public class Banjook {
	private String ingredient1;
	private String ingredient2;
	
	public Banjook(String ing1, String ing2) {
		ingredient1 = ing1;
		ingredient2 = ing2;
	}

	public String getIngredient1() {
		return ingredient1;
	}

	public void setIngredient1(String ingredient1) {
		this.ingredient1 = ingredient1;
	}

	public String getIngredient2() {
		return ingredient2;
	}

	public void setIngredient2(String ingredient2) {
		this.ingredient2 = ingredient2;
	}

	@Override
	public String toString() {
		return  this.ingredient1 + "와(과) " + this.ingredient2 + "이 황금비율로 섞여있다.";
	}
	
	
}

package weblsj;

public class IngredientDifferentException extends Exception {

	private int errorCode = 138;
	
	public IngredientDifferentException(String msg) {
		super(msg);
		
	}

	@Override
	public String getMessage() {
		return "에러코드 : " + this.errorCode + ", " + super.getMessage()  ;
		
	}
	
}

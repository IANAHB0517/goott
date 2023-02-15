package weblsj;

public class KoreaSoulSnackTest {

	public static void main(String[] args) {
		Banjook 밀가루반죽 = new Banjook("밀가루", "물");
		System.out.println(밀가루반죽.toString());
		
		Sock 피자 = new Sock("피자", "치즈");
		System.out.println(피자.toString());
		Fishbread 피붕 = null;
		try {
			피붕 = new Fishbread("붕어빵", 밀가루반죽, 피자, 5, 1500, "이마트앞붕어빵집");
		} catch (IngredientDifferentException e) {
			System.out.println("붕어빵 속이 이상한데?");
		}
		
		System.out.println(피붕.toString());

	}

}


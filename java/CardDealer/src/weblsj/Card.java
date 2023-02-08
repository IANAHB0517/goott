package weblsj;

public class Card {
	// 카드 각각의 무늬와 숫자는 (값) 달라진다. 그래서 instance 멤버 변수로 만든다.
	private int kind; // 카드 무늬
	private int number; // 카드 숫자
	
	// 모든 카드 52장은 width와 height가 동일한 값을 가지고 있다. 모든 카드 객체가 공유하는 static 변수로 만든다.
	private static int width = 100;
	private static int height = 200;
	
	private static final int HEART = 1; // HEART 라는 상수를 1로 취급한다.
	private static final int SPADE = 2;
	private static final int CLOVER = 3;
	private static final int DIAMOND = 4;
	
	static final int KIND_MAX = 4;
	static final int NUMBER_MAX = 13;
	
	// 카드 이미지
	
	// 생성자
	public Card(int kind, int number) {
		this.kind = kind;
		this.number = number;
	}
	
	//메서드
	public String toString() {
		String kind = "";
		String number = "";
		
		switch (this.number) {
		case 1:
			number = "A";
			break;
		case 11:
			number = "J";
			break;
		case 12:
			number = "Q";
			break;
		case 13:
			number = "K";
			break;

		default:
			number = this.number + ""; // char 때 생각하면 될듯 
		}
		
		switch (this.kind) {
		case 1:
			kind = "♥";
			break;
		case 2:
			kind = "♠";
			break;
		case 3:
			kind = "♣";
			break;
		case 4:
			kind = "◆";
			break;
		}
		
		return "카드 : " + kind + number + " ";
	}
	
}

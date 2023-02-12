package weblsj;

public class Card {
	// 변수 선언
	private String pattern;
	private String cardNum;

	public static final String[] NUMBERS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	public static final String[] PATTERNS = { "♠", "◆", "♥", "♣" };

	// 생성자
	public Card(String pattern, String cardNum) {
		this.pattern = pattern;
		this.cardNum = cardNum;
	}

	// 메서드
	// getter
	public String getPatter() {
		return this.pattern;
	}

	public String getCardNum() {
		return this.cardNum;
	}

	// setter
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setCardNum(String cardnum) {
		this.cardNum = cardnum;
	}

	// toString
	public String toString() {
		return "이 카드의 무늬는 " + this.pattern + "숫자는 " + this.cardNum + "입니다.";
	}
}

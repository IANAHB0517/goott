package weblsj;

public class Dealer {
	// 변수 선언
	private String dealerId;
	private Card[] deck;

	public static final int MAX_CARD_NUM = 13;
	public static final int MAX_DECK_NUM = 52;

	// 생성자
	public Dealer(String dealerId) {
		this.dealerId = dealerId;
		this.deck = new Card[MAX_DECK_NUM];

		for (int i = 0; i < MAX_DECK_NUM;) {
			for (String p : Card.PATTERNS) {
				for (String n : Card.NUMBERS) {
					this.deck[i] = new Card(p, n);
					i++;
				}
			}
		}
		outputEntireDeck();
	}

	// 메서드
	public String outputEntireDeck() {
		String deck = this.dealerId + " 딜러의 카드 더미에는 ";
//		for (int j = 0; j < MAX_DECK_NUM; j++) {
			for (int i = 0; i < MAX_DECK_NUM; i++) {
				deck += this.deck[i].getPatter() + this.deck[i].getCardNum() + "카드 ";
			}
			System.out.println();
//		}
		deck += "가 있습니다.";
		return deck;
	}
	// getter

	public Card[] getDeck() {
		return this.deck;
	}

	public String getDealerId() {
		return this.dealerId;
	}

	// setter
	public void setDealerId(String newDealerId) {
		this.dealerId = newDealerId;
	}

	// toString
	public String toString() {
		String deck = null;

		for (int i = 0; i < MAX_DECK_NUM; i++) {
			deck += this.deck[i].getPatter() + this.deck[i].getCardNum() + "카드 ";
		}
		return deck;
	}

}

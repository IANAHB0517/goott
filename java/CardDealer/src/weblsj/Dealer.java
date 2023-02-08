package weblsj;

import java.util.Arrays;

public class Dealer {
	private final static int CARD_NUM = 52; //카드의 총갯수를 위한 상수 // 상수는 선언하자 마자 값을 부여해야 하고 수정할 수 없다.
	private	Card[] cardDeck = new Card[CARD_NUM];
	
	
	public  Dealer() {
		int cardIndex = 0;
		for (int cardKind = 1; cardKind <= Card.KIND_MAX; cardKind++) {
			for (int cardNum = 1; cardNum <= Card.NUMBER_MAX; cardNum++) {
				this.cardDeck[cardIndex++] = new Card(cardKind, cardNum);
			}
		}
	}
	
	public String displayCard() { // 리턴을 하는 이유 1. 문자열을 가져다 쓰기 위해서 	2.데이터를 입력받고 출력하는 객체를 하나로 통일(역할 분할)하기 위해서 (MVC 패턴에 익숙해지기 위해서)
		return "Cards : " + Arrays.toString(this.cardDeck); 
	}
}   

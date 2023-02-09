package weblsj;

import java.util.Arrays;

/**
 * @Class Name : Dealer,
 * @author goott,
 * @date : 2023. 2. 8.,
 * @packages : weblsj,
 * @Description : 딜러
 */
public class Dealer {
	public final static int CARD_NUM = 52; //카드의 총갯수를 위한 상수 // 상수는 선언하자 마자 값을 부여해야 하고 수정할 수 없다.
	private	Card[] cardDeck = new Card[CARD_NUM];
	
	private static int curCardCount = 0;
	
	public  Dealer() {
		int cardIndex = 0;
		curCardCount = CARD_NUM;
		for (int cardKind = 1; cardKind <= Card.KIND_MAX; cardKind++) {
			for (int cardNum = 1; cardNum <= Card.NUMBER_MAX; cardNum++) {
				this.cardDeck[cardIndex++] = new Card(cardKind, cardNum);
			}
		}
	}
// 	고정 배열 카드 뽑기
//	public Card pickCard() {
//		int index = 0;
//		Card returnCard = null;
//		do {
//			index = (int) (Math.random() * CARD_NUM);
//			returnCard = this.cardDeck[index];
//		} while (returnCard == null);
//		
//		this.cardDeck[index] = null;
//		curCardCount--;
//		return returnCard;
//	}
	
//	가변 배열 카드 뽑기
	public Card pickCard() {
		int index = 0;
		Card returnCard = null;
		do {
			index = (int) (Math.random() * CARD_NUM);
			returnCard = this.cardDeck[index];
		} while (returnCard == null);
		
		this.cardDeck[index] = null;
		curCardCount--;
		return returnCard;
	}
//	가변 배열 카드 뽑기
	public Card pickCardAndRemoveArray() {
		int index = 0; // 뽑은 카드의 인덱스 변수
		Card returnCard = null; // 뽑힌 카드
		index = (int) (Math.random() * curCardCount); //
		
		returnCard = this.cardDeck[index];
		Card[] newCardDeck = new Card[--curCardCount]; // 새로운 빈 배열생성
		
		for (int from = 0; from < index; from++) {
			newCardDeck[from] = this.cardDeck[from];
		}
		
		for (int from = index; from < curCardCount; from++) {
			newCardDeck[from] = this.cardDeck[from+1];
		}
		
		System.out.println("뽑은 카드 : " + returnCard.toString() + " 카드 갯수 : " + newCardDeck.length + " Cards : " + Arrays.toString(newCardDeck));
		
		this.cardDeck = newCardDeck;
		return returnCard;
	}
	
	
//	public Card pickCardInNewArray() {
//		int index = (int) (Math.random() * curCardCount);
//		Card returnCard = this.cardDeck[index];
//		this.cardDeck[index] = null;
//		curCardCount--;
//		
//		Card[] newCardArray = new Card[curCardCount];
//		
//		for (int i = 0; i < curCardCount; i++) {
//			
//		}
//		
//		return returnCard;
//	}
	
	
	/**
	 * @Method Name : displayCard,
	 * @작성일 : 2023. 2. 8.,
	 * @작성자 : goott,
	 * @매개변수 : Dealer,
	 * @반환값 : String,
	 * @Description : 전체 카드 반환
	 */
	public String displayCard() { // 리턴을 하는 이유 1. 문자열을 가져다 쓰기 위해서 	2.데이터를 입력받고 출력하는 객체를 하나로 통일(역할 분할)하기 위해서 (MVC 패턴에 익숙해지기 위해서)
		return "전체 카드 갯수 : " + this.cardDeck.length + "Cards : " + Arrays.toString(this.cardDeck);
		
	}
	
	/**
	 * @Method Name : shuffleCard,
	 * @작성일 : 2023. 2. 8.,
	 * @작성자 : goott,
	 * @매개변수 : 없음,
	 * @반환값 : void,
	 * @Description : 카드를 섞는다.
	 */
	public void shuffleCard() {
		for (int i = 0; i < 1000; i++) {
			int index = (int) (Math.random() * CARD_NUM);
			Card temp = this.cardDeck[0];
			this.cardDeck[0] = this.cardDeck[index];
			this.cardDeck[index] = temp;
		}
	}
}   // 클래스 종료 지점

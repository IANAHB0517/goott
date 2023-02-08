package weblsj;

public class CardDealer {

	public static void main(String[] args) {
		Dealer 김딜러 = new Dealer();
		
		System.out.println(김딜러.displayCard());
		
		김딜러.shuffleCard();
		System.out.println(김딜러.displayCard());
		
		
//		for (int i = 0; i < Dealer.CARD_NUM; i++) {
//			Card c = 김딜러.pickCard();
//			System.out.println(i + "번째 뽑힌카드 : " + c.toString());
//			
//		}
		
		System.out.println(김딜러.displayCard());
		for (int i = 0; i < Dealer.CARD_NUM; i++) {
		김딜러.pickCardAndRemoveArray();
		}
	}

}

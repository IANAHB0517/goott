package weblsj;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CardDealer extends JFrame {
	
	

	public static void main(String[] args) {
		Dealer 김딜러 = new Dealer();
		
		System.out.println(김딜러.displayCard());
		
		김딜러.shuffleCard();
		System.out.println(김딜러.displayCard());
		
		System.out.println(김딜러.displayCard());
		for (int i = 0; i < Dealer.CARD_NUM; i++) {
		김딜러.pickCardAndRemoveArray();
		}
	}

}

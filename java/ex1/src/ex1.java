import java.util.Iterator;

public class ex1 {

	public static void main(String[] args) {
//		
//		double change = 2680; // 일 필요가 없는 이유가 정수 연산 정수를 하면 소수점이 나오지 않기 때문에 데이터 타입을 정수형으로 두면 된다.
//		short c50 = 500;
//		short c10 = 100;
//		short c5 = 50;
//		short c1 = 10;
//		
//		short coin500 = ' ';
//		short coin100 = ' ';
//		short coin50  = ' ';
//		short coin10  = ' ';
//		
//		
//		coin500 = (short)(Math.floor(change/c50));
//		change = (change%c50);
//		coin100 = (short)(Math.floor(change/c10));
//		change = (change%c10);
//		coin50 = (short)(Math.floor(change/c5));
//		change = (change%c5);
//		coin10 = (short)(Math.floor(change/c1));
//		change = (change%c1);
		
		short change = 2680;
		short[] coins = {500,100,50,10};
		short[] counts = {0,0,0,0};
		
		for (int i = 0; i < coins.length; i++) {
			counts[i] = (short) (change/coins[i]); // 연산 결과가 대표타입인 int로 바뀌어서 나오기 때문에 다시 short인 counts에 넣어주기 위해서는 short 타입으로 바꿔주어야 한다.
			// 어떤 수의 연산이든 연산을 할때는 int으로 형변환을 한 뒤에 진행하기 때문에 결과값을 int로 반환한다.
			//(연산중에 중간 저장하는 resistor가 4byte이기 때문에)
			change %= coins[i];
		}
		
		

		System.out.println("500원짜리" + counts[0] + "개\t" + "100원짜리" + counts[1] + "개\t" + "50원짜리" + counts[2] + "개\t" + "10원짜리" + counts[2] + "개\t");

	}

}

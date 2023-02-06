package weblsj;

public class Koreansoulsnack {

	public static void main(String[] args) {
		
		Fishbread castardBoong = new Fishbread("두리안", 22);
		
		System.out.println(castardBoong.toString());
		
		castardBoong.setSock("슈크림");
		System.out.println(castardBoong.toString());
		castardBoong.setSize(30);
		System.out.println(castardBoong.toString());
		
		Fishbread cBoong2 = castardBoong;
		System.out.println(cBoong2.toString());
		
		Fishbread readBoong =   new Fishbread("단팥", 8);
		System.out.println(readBoong.toString());
		
		Fishbread readBoong2 = readBoong;
		System.out.println(readBoong2.getSock());
		
		readBoong2.setSock("피자");
		
		for (int i = 0; i < 10; i++) {
			Fishbread piboong  = new Fishbread("피자", i+11);
			System.out.println(piboong.toString());
		}
		
		Snack hoDduck = new Snack("호떡", "찹쌀반죽", "뜨거운 꿀", 1500, 12);
		System.out.println(hoDduck.toSting());
		System.out.println(hoDduck.getName());
		
	}

}

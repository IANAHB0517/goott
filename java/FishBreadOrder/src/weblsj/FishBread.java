package weblsj;

public class FishBread {
	// 멤버 변수
	private String sock;
	private int size;
	private int price;

	// 생성자
	FishBread(String sock, int size, int price){
		 this.sock = sock;
		 this.size = size;
		 this.price = price;
		 
		 System.out.println("붕어빵 다 구웠다!");
	}
	
	//메서드
	
	// getter
	public String getSock() {
		return this.sock;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	// setter
	
	public void setSock(String sock) {
		System.out.println("다 구웠는데 바꾸긴 뭘 바꿔!!");
	}
	
	public void setSize(int size) {
		System.out.println("다 구웠는데 바꾸긴 뭘 바꿔!!");
	}
	
	public void setPrice(int price) {
		System.out.println("안깎아줘 돌아가");
	}
	
	//toString
	public String toString() {
		return "이 " + sock + "붕어빵의 길이는 " + size + "cm 이며 가격은 " + price + "원 입니다."; 
	}

}

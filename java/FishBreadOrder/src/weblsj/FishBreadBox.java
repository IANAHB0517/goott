package weblsj;

public class FishBreadBox {
	
	//변수 선언
	private String boxNo;
	private FishBread[] breadList;
	private int totalPrice;
	
	//생성자
	public FishBreadBox(String boxNo, int cnt) {
		this.boxNo = boxNo;
		this.breadList = new FishBread[cnt];
		for (int i = 0; i < cnt; i++) {
			 this.totalPrice += breadList[i].getPrice();			
		}
	}
	
	
	//메서드
	//getter
	
	//setter
	//toString
	
	
}

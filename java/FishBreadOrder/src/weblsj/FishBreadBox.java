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
		autoInput();
		for (int i = 0; i < cnt; i++) {
			 this.totalPrice += breadList[i].getPrice();			
		}
	}
	
	
	//메서드
	//getter
	public String getBoxNo() {
		return boxNo;
	}
	
	public FishBread[] getBreadList() {
		return breadList;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	
	//setter
	
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	
	//toString
	public String toString() {
		String result = null; 
		result = this.boxNo + "박스에는 "; 
		int breadCnt = 0;
	for (FishBread f : breadList) {
		result += f.getSock() + "붕어빵, ";
		breadCnt++;
	}
	result += "모두 합해 총" + breadCnt + "개의 붕어빵이 들어있고 가격은 " + this.getTotalPrice() + "입니다.";
		return result;
	}
	
public void autoInput() {
	for (int i = 0; i < breadList.length; i++) {
		this.breadList[i] = new FishBread("팥", 10, 1000);
		
	}
}
	
}

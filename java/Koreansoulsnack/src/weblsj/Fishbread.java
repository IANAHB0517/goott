package weblsj;

public class Fishbread {
	// 멤버 변수
	private String sock;
	private  int size;
	
	
	// 생성자
	public Fishbread(String sock, int size) {
		this.sock = sock;
		this.size = size;
	};
	
	public Fishbread(Fishbread OriginFishbread) {
		this.sock = OriginFishbread.sock;
		this.size = OriginFishbread.size;
	};
	
	
	// 메서드
	public String toString() {
		return "이 붕어빵은 " + sock + "붕어빵이고 " + size + "cm 입니다.";
	}
	// getter
	public String getSock() {
		return this.sock;
	}
	public int getSize() {
		return this.size;
	}
	
	// setter
	public void setSock(String changeSock){
		this.sock = changeSock;
	}
	
	public void setSize(int changeSize){
		this.size = changeSize;
	}
	

}

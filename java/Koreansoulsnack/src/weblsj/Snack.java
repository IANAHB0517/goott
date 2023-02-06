package weblsj;

public class Snack {
	String name;
	String banjoock;
	String sock;
	int price;
	int size;
	
	public Snack(String name, String banjoock, String sock, int price, int size) {
		this.banjoock= banjoock;
		this.sock = sock;
		this.price = price;
		this.size = size;
	}
	
	public String toSting() {
		return "이 간식은 " + banjoock + "(으)로 만들어지고 " + sock + "이 들어간 개당 " + price + "원 짜리 " + name + "이며 크기는 " + size + "cm 정도 입니다.";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBanjoock() {
		return banjoock;
	}

	public void setBanjoock(String banjoock) {
		this.banjoock = banjoock;
	}

	public String getSock() {
		return sock;
	}

	public void setSock(String sock) {
		this.sock = sock;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPrice() {
		return price;
	}
	
	
	
}


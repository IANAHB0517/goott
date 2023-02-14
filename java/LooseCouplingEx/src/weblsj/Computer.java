package weblsj;

public class Computer implements ElectricDevice {
	private int mainMemory;
	
	public Computer (int mainMemory) {
		this.mainMemory = mainMemory;
		
	}

	@Override
	public void powerOn() {
		System.out.println("Ram " + mainMemory  + "GB 짜리 " + getClass() + "가 켜집니다");

	}

}

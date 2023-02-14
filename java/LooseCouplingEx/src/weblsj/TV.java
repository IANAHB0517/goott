package weblsj;

public class TV implements ElectricDevice {
	private String brandName;
	
	public TV (String brandName) {
		this.brandName = brandName;
		
	}

	
	@Override
	public void powerOn() {
		System.out.println(getClass().getName() + "이 켜집니다");

	}

}

//"TV가 켜졌다"
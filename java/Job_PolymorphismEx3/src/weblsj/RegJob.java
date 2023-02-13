package weblsj;

public class RegJob extends Job {
	private int sal;
	
	public RegJob(int no, String name, String depName, int sal) {
		super(no, name, depName);
		this.sal = sal;
	}
	

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	

	@Override
	public void takeMoney() {
		System.out.println("월급" + this.sal + "만원을 받았다.");
		
	}

	@Override
	public String toString() {
		return "[정규직]" + super.toString() + "월급 " + this.sal;
	}

	
	
	
}

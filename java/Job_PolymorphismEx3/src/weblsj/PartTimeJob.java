package weblsj;

public class PartTimeJob extends Job {
	private int workTime;
	private int wage;
	
	
	public PartTimeJob(int no, String name, String depName, int wage, int workTime) {
		super(no, name, depName);
		this.wage = wage;
		this.workTime = workTime;
	}

	public int getWorkTime() {
		return workTime;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	@Override
	public void takeMoney() {
		System.out.println("알바비" + (this.wage * this.workTime) + "원 을 받았다.");
	}
	
	@Override
	public String toString() {
		return "[알바]" + super.toString() + "시급 " + this.wage + "근무시간 " + this.workTime + "일당 " + (this.wage * this.workTime);
	}

}

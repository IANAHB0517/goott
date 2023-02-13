package weblsj;

public abstract class Job {
	private int no;
	private String name;
	private String depName;
	
	
	public Job(int no, String name, String depName ) {
		super();
		this.no = no;
		this.name = name;
		this.depName = depName;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}



	public int getNo() {
		return no;
	}

	@Override
	public String toString() {
		return "[no=" + no + ", name=" + name + ", depName=" + depName + "]\t";
	}
	
	public abstract void takeMoney();
	
	
	

}

package weblsj;

public class Korean {
	private String name;
	private String regNo;
	
	public Korean(String name, String regNo) {
		super();
		this.name = name;
		this.regNo = regNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegNo() {
		return regNo;
	}

	@Override
	public String toString() {
		return "Korean [name=" + name + ", regNo=" + regNo + "]";
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (this.name + this.regNo).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Korean) {
		Korean tmp = (Korean)obj;
		if (this.name.equals(tmp.name) && this.regNo.equals(tmp.regNo)) {
			result = true;
		}
			
		}
		return result;
	}
	
	
}

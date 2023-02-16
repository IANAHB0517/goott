package weblsj;

public class Korean {
	private String name;
	private String id;
	
	public Korean( String name,  String id ) {
		this.name = name;
		this.id = id;
	}
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getId() {
		return id;
	}



	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		// 현재객체와 넘겨받은 obj객체의 주민번호와 이름이 같다면 true를 반환, 아니면 false반환
		if (obj instanceof Korean) {
		Korean tmp = (Korean)obj;
			if (this.id.equals(tmp.id) && // id 값이 private 하지만 tmp는 같은 클래스(같은 타입의 객체) 이기 때문에 접근 가능
				this.name.equals(tmp.getName())) {
				result = true;
			}
		}
		 
		return result;
	}
	// equals()를 주석 처리 할 경우 같은 주소를 참조하는 서로 다른 객체로 판단, 하나의 set에 같은 주소값을 가진 두 객체가 들어간다.
	
	
	



	@Override
	public int hashCode() {
		// 이 메서드를 통해서 새로운 해쉬코드 값을 객체에게 할당해준다.
		return (this.name + this.id).hashCode();
				//두개의 문자열을 저장한 곳의 해쉬코드를 반환하도록한다.
	}
	// hashCode()를 주석 처리 할 경우 서로 다른 주소 값을 가진 두개의 객체로 판단, 하나의 set에 같은 주소값을 가진 두 객체가 들어간다.



	@Override
	public String toString() {
		return "Korean [name=" + name + ", id=" + id + "]";
	}
	
	
	
}

package weblsj;

public class PetDoctor {
	private String name;
	private int age;
	public PetDoctor(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public void doClinic(Pet p) { // 2다형성 : 부모타입으로 모든 자식 객체를 매개변수로 받을 수 있다.
		System.out.println(p.toString() + "을 치료합니다!");
	}

	@Override
	public String toString() {
		return "PetDoctor [name=" + name + ", age=" + age + "]";
	}
	
	
}

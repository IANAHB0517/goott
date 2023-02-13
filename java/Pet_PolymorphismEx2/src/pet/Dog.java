package pet;

import weblsj.Pet;

public class Dog extends Pet{

	public Dog(String name, String type, int age) {
		super(name, type, age);
	}

	@Override
	public void doCry() {
		System.out.println("멍멍");
		}
	
	@Override
	public String toString() {
		return "[Dog]" + super.toString();
	}

	
	
}

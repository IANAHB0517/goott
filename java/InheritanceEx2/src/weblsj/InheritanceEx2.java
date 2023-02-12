package weblsj;

class ParentA{
	private int a = 100;
	
	public int getA() {
		return this.a;
	}
}

class ParentD {
	
}

//자바에서는 다중상속을 표현하는 문법 자체가 없다. Syntax error
//class ETC extends ParentA  ParentD{//다중상속이 불가능 하도록 , 를 넣을 수 없다.
//	
//}

public class InheritanceEx2 extends ParentA{

	public static void main(String[] args) {
		System.out.println( new ParentA().getA()); // 자기 자신의 멤버를 사용

		System.out.println(new ChildA().getA()); // ChildA 객체의 멤버는 아니지만 상속 받은 멤버이기에 사용가능
		
		System.out.println(new InheritanceEx2().getA()); // 상속받은 InheritanceEx2 클래스도 사용 가능
		
		ParentA objA = new ParentA();
		
		ChildA objChild = new ChildA();
		
		if (objChild instanceof ChildA) { // objChild 가 ChildA의 객체인가? instanceof 중요
			System.out.println("녜");
		} else {
			System.out.println("띄~");
		}
		
		if (objChild instanceof ParentA) { // objChild 가 ParentA의 객체인가? (Child의 부모가 ParentA이기 때문)
			System.out.println("녜");
		} else {
			System.out.println("띄~");
		}
		
//		if (objChild instanceof ParentD) { // objChild 가 ParentD의 객체인가?
//										   //상속 관계가 아니기 때문에 데이터 타입이 맞지 않아 if문 자체가 성립하지 않는다.
//			System.out.println("녜");
//		} else {
//			System.out.println("띄~");
//		}
		
	} // 메인 메서드 종료

} 



class ChildA extends ParentA {
	
}



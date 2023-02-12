package weblsj;

import java.awt.Frame;

public class InheritanceEx1 extends Frame {

	public InheritanceEx1(String title) {
//		this(); 현재 객체의 생성자 호출
//		super(); // 부모가 가지고 있는 기본 생성자 호출
		super(title); // 부모 객체의 생성자 중 title을 요구하는 생성자 호출
	}
	
	public static void main(String[] args) {
		InheritanceEx1 myWindow = new InheritanceEx1("나의 윈도우"); // InheritanceEx1에게 title을 넣어서 호출
		System.out.println(myWindow.toString());
		
		myWindow.setVisible(true);// public 이라면 window 객체를 생성한 뒤에 접근 했어야 하지만
								  // 상속되어 InheritanceEx1에게 메서드가 내장 되어 있기 때문에
								  // 메서드를 가진 객체 생성 할 필요 없이 사용할 수 있다.
								  // 종료 버튼은 JFrame 클래스가 가지고 있어서 종료가 안된다...
		//Window myWin =new Window(myWindow); // myWindow를 넘겨준 이유는 Window 클래스의 생성자가 매개변수로 Frame을 요구하기 때문, (myWindow = Frame 관계가 성립됨을 알 수 있다.)
		//myWin. Window 클래스의 객체를 선언해서 사용할 경우 자식 객체인 Frame의 멤버를 찾을 수 없다.
		
	} // 메인 메서드 종료

}

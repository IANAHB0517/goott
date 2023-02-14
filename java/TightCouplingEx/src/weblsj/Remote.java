package weblsj;

public class Remote {
	private TV tv; // 논리적 결함의 시작
	
	public Remote() {
		tv = new TV(); // TV 클래스의 내용이 수정되었고 강결합이므로 현재 클래스도 수정해야 한다. 
		   			   // tv 객체가 리모콘을 생성해야만 만들어짐
					   // tv 객체의 생성자가 변경되면 코드도 변경되어야한다.

	}
	
	public void powerOnTv() {
//		this.tv.powerOn(); // tv 객체가 없으면 메서드가 하는 일이 없어진다.
	}
}

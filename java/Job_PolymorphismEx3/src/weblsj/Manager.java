package weblsj;

public class Manager extends RegJob {

	public Manager(int no, String name,  int sal) {
		super(no, name, "매니저", sal);
		
	}
	
	public String managing(Job e) {
		String saying = null;
		saying = e.getName() + "의 근무 태도가 좋습니다" + e.toString() + "이 아깝지 않습니다.";
	
		return saying;
	}
}

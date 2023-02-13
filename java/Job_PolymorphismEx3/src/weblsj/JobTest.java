package weblsj;

public class JobTest {

	public static void main(String[] args) {
		
		PartTimeJob albamon1 = new PartTimeJob(1, "알군", "창고", 3000, 8);
		albamon1.takeMoney();
		System.out.println(albamon1.toString());
		
		RegJob emp1 = new RegJob(0001, "정규진", "어려운일", 300);
		emp1.takeMoney();
		System.out.println(emp1.toString());
		
		
		Manager m1 = new Manager(9001, "맨이죠", 500);
		Manager m2 = new Manager(9002, "우맨이죠", 550);
		m1.takeMoney();
		
		System.out.println(m1.getName() + "의 평가 "+ m1.managing(albamon1));
		System.out.println(m2.getName() + "의 평가 "+ m1.managing(emp1));
	}
	
	

}

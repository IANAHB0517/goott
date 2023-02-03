package weblsj;

public class OOPEX2_Constructor {

	public static void main(String[] args) {
//		int mainMemory;
//		System.out.print(mainMemory);

		MobilePhone phone = new MobilePhone();
		System.out.println(phone.hashCode());
//		System.out.println(phone.brandName);
		System.out.println(phone.toString());
		
		MobilePhone galaxy = new MobilePhone("samsung", "galaxy S22", 2048);
		MobilePhone galaxy2 = new MobilePhone("삼성", "갤럭시 S22", 1024);
		
		System.out.println(galaxy.toString());
		System.out.println(galaxy2.toString());
		
	
		MobilePhone hwa = new MobilePhone("화웨이", 128);  // 오버로딩의 갯수를 늘리는 방법 -> 비추천
		System.out.println(hwa.toString());
		
		MobilePhone iphone = new MobilePhone(null , "아이폰15", 512); // 이렇게 호출하면 생성자의 오버로딩 갯수가 줄어든다.
		System.out.println(iphone.toString());
		System.out.println("😍😍😍");

	}
	
	

}

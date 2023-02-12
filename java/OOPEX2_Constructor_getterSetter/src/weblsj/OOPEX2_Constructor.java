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
		
		

//		iphone.brandName = "애플"; // 외부(다른 클래스 에서)에서 멤버 변수의 값을 변경하려고 할 때 (setter 이용)
//		System.out.println(galaxy.brandname); //외부(다른 클래스 에서)에서 멤버 변수의 값을 얻어 오려고 할 때 (getter 이용)
//		System.out.println(galaxy.getBrandName());
//		System.out.println(galaxy.getModelName());
//		System.out.println(galaxy.getMainMemory());
		System.out.println(galaxy.getAttrs());
		
		MobilePhone iphone = new MobilePhone(null , "아이폰15", 512); // 이렇게 호출하면 생성자의 오버로딩 갯수가 줄어든다.
		System.out.println(iphone.toString());
		iphone.setBrandName("apple");
		System.out.println(iphone.toString());
		
		
		if (hwa.setMainMemory(2048)) {
			System.out.println("메모리 업그레이드 성공!");
		} else {
			System.out.println("메모리 업그레이드 실패!");
		}
		System.out.println(hwa.toString());
		
		if (hwa.setMainMemory(1024)) {
			System.out.println("메모리 업그레이드 성공!");
		} else {
			System.out.println("메모리 업그레이드 실패!");
		}
		System.out.println(hwa.toString());
		
		
	}
	
	

}

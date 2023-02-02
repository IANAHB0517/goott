package weblsj;

public class Car {
	//속성
	String brandName = "현대"; // String 타입도 객체니까 id를 갖게된다
	String modelName = "그랜져";
	int price = 45000000;
	String color = "검정색";
	
	// 멤버 메서드
	// 메서드 생성법
	// [접근제한자] [static] 반환값의데이터타입|void 메서드명(매개변수1,매개변수2.....){
	//  메서드가 호출되면 할 일	
	// 	}
	
	// Car 객체의 속성 값을 화면에 출력한다.
	public void displayCar() {
		System.out.println("브랜드명 : " + brandName);
		System.out.println("모델명 : " + modelName);
		System.out.println("가격 : " + price);
		System.out.println("색상 : " + color);
	};
	
}

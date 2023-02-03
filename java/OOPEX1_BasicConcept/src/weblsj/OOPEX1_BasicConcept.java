package weblsj;

public class OOPEX1_BasicConcept {

	public static void main(String[] args) {
		// Car 객체 생성
		// 기본 생성자 : 매개변수가 하나도 없는 생성자(생략가능)
		Car car1 = new Car();  // Car 객체를 새로운 메모리를 할당하여 만들고, 기본 생성자를 호출
		Car car2 = new Car();
		
		
		// car2 객체의 속성 값 변경
		// 아래처럼 얼마든지 (접근 제한자가 허용 된다면)
		// 객체를 생성 한 이후에 객체의 속성을 변화 시킬 수 있다.
		//하지만, 객체의 속성을 프로그래머가 원하는 대로 생성하려면 아래의 방법 보다는
		// 생성자를 사용하는 것이 좋다.
		car2.brandName = "기아";
		car2.modelName = "모닝";
		car2.price = 32000000;
		car2.color = "흰색"; // car2 객체의 color 속성 값 변경
		
		
//		System.out.println("car1의 모델명 : " + car1.modelName + " 가격 : " + car1.price + " 색상 : " + car1.color);	
//		System.out.println("car2의 모델명 : " + car2.modelName + " 가격 : " + car2.price + " 색상 : " + car2.color);
		
		// Car 객체의 멤버 메서드 displayCar() 호출
		car1.displayCar();
		System.out.println("=================");
		car2.displayCar();

	}

}

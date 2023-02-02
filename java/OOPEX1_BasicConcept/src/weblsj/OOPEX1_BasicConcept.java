package weblsj;

public class OOPEX1_BasicConcept {

	public static void main(String[] args) {
		// Car 객체 생성
		Car car1 = new Car();
		Car car2 = new Car();
		
		car2.color = "흰색"; // car2 객체의 color 속성 값 변경
		
		
//		System.out.println("car1의 모델명 : " + car1.modelName + " 가격 : " + car1.price + " 색상 : " + car1.color);	
//		System.out.println("car2의 모델명 : " + car2.modelName + " 가격 : " + car2.price + " 색상 : " + car2.color);
		
		// Car 객체의 멤버 메서드 displayCar() 호출
		car1.displayCar();
		System.out.println("=================");
		car2.displayCar();

	}

}

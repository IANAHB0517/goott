package weblsj;

import pet.*; // pet 패키지 밑의 모든 클래스를 import

public class PetTest {

	public static void main(String[] args) {
		Dog dog1 = new Dog("초롱이", "말티즈", 3);
		System.out.println(dog1.toString());
		dog1.doCry();
		
		Cat cat1 = new Cat("나비", "샴", 2);
		System.out.println(cat1.toString());
		cat1.doCry();
		
		System.out.println("=====================================================");
		
		
		Pet 우리집애완동물 = new Dog("뽀삐", "충견", 1); // 업캐스팅
		// 우리집애완동물은 Pet 타입이지만 묵시적으로 Dog 타입으로 다운캐스팅 되어
		// Dog.doCry()가 호출됨
		우리집애완동물.doCry(); // 1 다형성
		
		
		Pet 우리집애완동물2 = new Cat("냥이", "코숏", 1);
		우리집애완동물2.doCry();
		
		
		 PetDoctor 두리틀 = new PetDoctor("두리틀", 40);
		 두리틀.doClinic(우리집애완동물2);
		 두리틀.doClinic(우리집애완동물);
		 두리틀.doClinic(cat1);
		 두리틀.doClinic(dog1);
		 
		 // Pet 우리집애완동물전용의사 = 두리틀 ; // 상속관계가 아니므로 업캐스팅불가
		 
		 
		 // 우리집애완동물; //Dog 타입 X Pet 타입 
		 Dog dog2= (Dog)우리집애완동물; // 다운캐스팅 // ??? 강아직 복제??
		 
	}

}


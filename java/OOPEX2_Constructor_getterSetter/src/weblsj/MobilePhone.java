package weblsj;

public class MobilePhone {
	// 속성 (멤버 변수는 자동 초기화)
	private String brandName;
	private String modelName;
	private int mainMemory;
	
	// int i =0; // 멤버변수로 적용됨
	// i++; // 메서드를 작성불가
	
	// 생성자 (속성 다음 or 비교적 위쪽)
	public MobilePhone () { // 반환값 타입이 없는 인스턴스 메서드 처럼 생겼다.
		System.out.println("폰이 개통 되었습니다!");
	}
	
//	public MobilePhone(String brand,String model, int memory) { 
//		// 속성, 초기값 할당(넘겨받은 지역변수의 값을 멤버변수에 할당)
		// 변수명을 다르게 하면 this를 안써도 파란색이 나옴
		// 하지만 변수명이 다양해지면 여러 사람이 작업할 때 헷갈릴 수 있기 때문에 같은 변수명을 쓰고 this를 붙이는 것이 더 현명하다 
//		brandName = brand; 
//		modelName = model;
//		mainMemory = memory;
//	};

	
	// 생성자 오버로딩
	public MobilePhone(String brandName,String modelName, int mainMemory) { 
		// 생성자는 생성자 처럼 생겼지만 객체를 생성할 때 단 한번만 호출 할 수 있다!
		this(); // 현재 객체의 생성자 호출. 여기에서는 매개변수가 없는 기본 생성자를 호출함
		
		// 속성, 초기값 할당(넘겨받은 지역변수의 값을 멤버변수에 할당)
		// 지역변수와 멤버 변수의 이름이 같기 때문에 애매모호한 것을 방지하기 위해
		// 맴버변수에 현재객체라는 의미를 가지고 있는 키워드 this를 사용한다.
	
		this.brandName = brandName; // this를 안써도 파란색이 나옴 물어보기
		this.modelName = modelName;
		this.mainMemory = mainMemory;
		
		// this(); 생성자에서 다른 생성자를 호출할 때는 반드시 생성자의 첫 문장에 써야 한다.
	
	};
	
	public MobilePhone(String brandName, int mainMemory) { // 모델명을 모르는 객체를 생성할 경우
		this(brandName , null, mainMemory); // 생성자 재활용
	}

	
	
	// 메서드
	
	// getter 
	String getBrandName() {
		return this.brandName;
	};
	
	String getModelName() {
		return this.modelName;
	};
	
	String getMainMemory() { // int 타입의 변수를 불러와서 문자를 붙여 출력하기 위해 타입을 바꾸고 뒤에 문자열을 붙여준다.
		return this.mainMemory + "GB";
	};
	
	String getAttrs() {
		return "brandName : " +	this.brandName + " modelName : " + this.modelName +  " mainMemory : " + this.mainMemory;
	}
	
	// setter
	void setBrandName(String brandName) {
		this.brandName = brandName;
	};
	
	void setModelName(String modelName) {
		this.modelName = modelName;
	};
	
	boolean setMainMemory(int mainMemory) {
		// 메인 메모리의 용량을 1tb까지만 허용한다면 이렇게 setter를 구현할 수 도 있다.
		boolean result = false;
		if (mainMemory <= 1024) {
			this.mainMemory = mainMemory;			
			result = true;
		} 
		return result;
	};
	
	
	
	
	
	public String toString() {
		return "[" + this.getClass().getName() + " : " + this.hashCode() + "] = brandName : " +
				this.brandName + " modelName : " + this.modelName +  " mainMemory : " + this.mainMemory;
						//this.getClass() 현재 클래서 자체를 반환함
	}
	
	
	
}

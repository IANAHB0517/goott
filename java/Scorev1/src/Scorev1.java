
public class Scorev1 {

	public static void main(String[] args) {
		String name = "둘리";
		int kor = 98, eng = 34, math = 56;
		int tot = kor + eng + math;
		float avg = tot / 3f; // avg 는 실수 타입이지만 tot /3 에서 이미 정수로 처리 되기 때문에 계산식중에 리터럴상수를 붙여서 소수점까지 계산이 되도록바꿔준다.(묵시적 형변환)
		char grade = ' '; // char는 빈문자열을 허용하지 않기 때문에 빈칸이라는 문자를 넣어준다.
		
		
		//switch (Math.floor(avg)/10) { 묵시적 형 변환이 되면서 작은 타입이 큰 타입을 따라 바뀌는 예,
		//floor는 double을 요구하고 반환 하기 때문에 키값에 d 를 붙여서 double 타입으로 바꿔준다
		switch ((int)(Math.floor(avg)/10d)) {//switch case 는 정수값을 요구하기 때문에 계산식 앞에 int를 붙여서 데이터 타입을 정수로 바꿔준다. 
		case 10:
		case 9:
			grade = 'A';
			break;
		case 8:
			grade = 'B';
			break;
		case 7:
			grade = 'C';
			break;
		case 6:
			grade = 'D';
			break;
		default:
			grade = 'F';
			break;
		}
		
		System.out.println("성적표");
		System.out.println("이름\t 국어\t 영어\t 수학\t 총점\t 평균\t 학점\t");
		System.out.println("---------------------------------------------------------");
		System.out.println(name + "\t"+ kor + "\t"+ eng + "\t"+math + "\t"+tot
				+ "\t"+avg + "\t"+grade);
	}

}

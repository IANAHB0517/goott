package weblsj;

import java.util.Scanner;

public class ScoreV3 { // 여기도 클래스 이기 때문에 변수와 메서드를 포함가능
	// 포함 관계 : 객체의 멤버 변수 안에 포함 시키고자 하는 클래스를 넣어준다.
//	private StuClass c1; 

	private static int curSavedStudentCnt = 0; // 현재 저장되어 있는 학생 수

	// getter
	int getCurSavedStudentCnt() {
		return curSavedStudentCnt;
	}

	private static void outputMenu() {
		System.out.println("***************************************************************");
		System.out.println("**************************성 적 표 V 3*************************");
		System.out.println("***************************************************************");
		System.out.println("**** 1. 반 생성                                            ****");
		System.out.println("**** 2. 생성된 반에 학생 입력       (1번 부터 수행)        ****");
		System.out.println("**** 3. 전체 학생 점수 출력                                ****");
		System.out.println("**** 4. 반,학생 자동 입력                                  ****");
		System.out.println("**** 5. 학생 성적 출력 (by 학생번호)                       ****");
		System.out.println("**** 9. 종료                                               ****");
		System.out.println("***************************************************************");
		System.out.print("메뉴 번호 입력 >>> ");
//		

	}

	private StuClass createClass() {
		Scanner noSC = new Scanner(System.in);
		System.out.print("반 번호 입력 >>> ");
		int classNo = noSC.nextInt();

		Scanner nameSC = new Scanner(System.in);
		System.out.print("반 이름 입력 >>> ");
		String className = nameSC.nextLine();

		StuClass c1 = new StuClass(classNo, className);
		System.out.println(c1.toString());
		return c1; // 지역 변수인 c1을 밖으로 내보내주기 위한 과정
	}

	private void inputStudent(StuClass stuClass) {
		if (stuClass == null) {
			System.out.println("반 부터 생성하고 오세요");
			return; // void라서 반환 되는 것이 없어야 하는데 아무것도 반환하지 않고 처음 이 메서드를 호출한 곳으로 돌려 보낸다.
		} else {
			if (curSavedStudentCnt < StuClass.STUDENT_COUNT) { // 특정 숫자가 아니라 선언된 상수와 비교 한다.
				Scanner stuNumSC = new Scanner(System.in);
				Scanner stuStringSC = new Scanner(System.in); // 각각 다른 타입을 입력 받을때 타입에 따라 스캐너를 만들어준다
				System.out.print("학생 번호 >>> ");
				String stuNo = stuStringSC.nextLine();
				System.out.print("학생 이름 >>> ");
				String stuName = stuStringSC.nextLine();
				System.out.print("국어 점수 >>> ");
				// Scanner stuNumSC = new Scanner(System.in);
				int kor = stuNumSC.nextInt();
				System.out.print("영어 점수 >>> ");
				int eng = stuNumSC.nextInt();
				System.out.print("수학 점수 >>> ");
				int math = stuNumSC.nextInt();
//				Student stu = new Student(stuNo, stuName, kor, eng, math); // 굳이 지역 변수 하나를 낭비할 필요 없다.
				stuClass.addStudent(new Student(stuNo, stuName, kor, eng, math), curSavedStudentCnt);
				System.out.println();
//				System.out.println(stu.toString());
				curSavedStudentCnt++;

			} else {
				System.out.println("학생 수가 초과 됩니다.");
			}

//			return stuClass; // 리턴이 필요 없음. stuClass 참조 타입이므로 call by reference에 의해 리턴 불필요
		}
	}

	public static void main(String[] args) {
		ScoreV3 sc = new ScoreV3();
		StuClass stuClass = null;
		while (true) {
			outputMenu();
			Scanner menuSC = new Scanner(System.in);
			int menu = menuSC.nextInt();

			switch (menu) {
			case 1:
				stuClass = sc.createClass();
				break;

			case 2:
				sc.inputStudent(stuClass); // call by reference (참조 하는 주소 값을 넘겨준다.) // call by value (참조하는 값 자체를 복사한다.
											// 값복사)

				break;

			case 3:
				sc.outputEntireStudent(stuClass);
				break;
				
			case 4:
				stuClass = sc.autoInputStudent(stuClass);
				break;
				
			case 5:
				sc.findStudentByNo(stuClass);
				break;

			case 9:
				System.exit(0);// 프로그램 종료
				break;

			default:
				break;
			}
		}
	} // main 메서드 종료 지점
	
	private void findStudentByNo(StuClass stuClass) {
		Scanner stuStringSC = new Scanner(System.in);
		System.out.print("학번 >>> ");
		String stuNo = stuStringSC.nextLine();
		
		System.out.println(stuClass.findStudentByNo(stuNo));
		
	}
	
	private StuClass autoInputStudent(StuClass stuClass) {
		Student[] stuList = {
				new Student("98001", "둘리", 99, 99, 99),
				new Student("98002", "도넛", 9, 9, 9),
				new Student("98003", "마이컬", 89, 79, 99)
		}; // 학생 생성
		curSavedStudentCnt = 3;
		return stuClass = new StuClass(6, "k디지털", stuList); // 6반 k디지털 이란 이름의 학생 배열 전달
	}
	
	private void outputEntireStudent(StuClass stuClass) {
		System.out.println("=======================================================");
		System.out.println("========================성적표=========================");
		System.out.println("=======================================================");
		System.out.println("반 번호 : " + stuClass.getNo() + ", 과정명 : " + stuClass.getClassName());
		System.out.println("-------------------------------------------------------");
		stuClass.outputEntireStudents();
		System.out.println("-------------------------------------------------------");
		System.out.println("전체 총점 : " + stuClass.calcTotalTot());
		System.out.println("전체 평균 : " + stuClass.calcTotalAvg());
		System.out.println("-------------------------------------------------------");
	}

}// 클래스 종료 지점

package weblsj;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
	public static void main(String[] args) {
//		Set<String> koerans = new TreeSet<>();
//		
//		koerans.add("한국인,980101-1000100");
//		koerans.add("김국인,980101-2000100");
//		koerans.add("이국인,980101-1000101");
//		koerans.add("박국인,980101-1000102");
//		koerans.add("최국인,980101-1000103");
//		koerans.add("정국인,980101-1000104");
//		koerans.add("김국인,980101-2000100");
//		koerans.add("김국인,000101-3000100");
//		
//		
//		System.out.println(koerans.size());
//		
//		
//		for (Iterator iterator = koerans.iterator(); iterator.hasNext();) {
//			System.out.println(iterator.next());
//			
//		}
		
		Korean k1 = new Korean("한국인","980101-1000100");
		Korean k2 = new Korean("김국인","000101-3000100");
		
		Korean k3 = new Korean("이국인","980101-1000101");
		Korean k4 = new Korean("박국인","980101-1000102");
		
		Korean k5 = new Korean("최국인","980101-1000103");
		Korean k6 = new Korean("정국인","980101-1000104");
		
		Korean k7 = new Korean("김국인","980101-2000100");
		Korean k8 = new Korean("김국인","980101-2000100");
		
		Set<Korean> set = new HashSet<>();
//		Set<Korean> set = new TreeSet<>();
		
		set.add(k1);
		set.add(k2);
		set.add(k3);
		set.add(k4);
		set.add(k5);
		set.add(k6);
		set.add(k7);
		set.add(k8);
	
		// 같은 대한민국 국민이 set에 저장된다.(논리적인 오류가 생긴다.)
		for ( Korean ko : set ) {
			System.out.println(ko.getName() + ":" + ko.hashCode());
		}
		
		// 주민번호와 이름이 같으면 같은 객체로 인식시켜야 함.
		// -> 주민번호와 이름이 같은 객체면 중복 저장이 안되도록
		
		// equals()를 오버라이딩
		System.out.println(k8.equals(k7)); // true
		
		// hashcode() 오버라이딩 하는 법
		System.out.println(k8.hashCode());
		System.out.println(k7.hashCode());
		
		System.out.println(set.size());
		
		
		
//		
//		Korean k10 = new Korean("김가","990101-1000100");
//		Korean k11 = new Korean("최가","990101-1000101");
//		Korean k12 = new Korean("김가","990101-1000100");
//		
//		System.out.println("===============");
//		System.out.println( k10.hashCode() + "\t" +	k11.hashCode() + "\t" +	k12.hashCode());
	}
}

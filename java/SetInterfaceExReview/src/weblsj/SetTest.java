package weblsj;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

	public static void main(String[] args) {
//		Set<Korean> set1 = new TreeSet<Korean>(); // TreeSet의 경우 정렬까지 되어야 하는데 지금은 정렬 기준이 없으므로 에러가 난다.
//		
//		Korean k1 = new Korean(1, "길동" , "230216-1009001");
//		Korean k2 = new Korean(2, "길동" , "230216-1009001");
//		
//		set1.add(k2); // 
//		set1.add(k1);
//		
//		System.out.println(set1.size());
//
//	}

		Set<Korean> set1 = new HashSet<Korean>(); // TreeSet의 경우 정렬까지 되어야 하는데 지금은 정렬 기준이 없으므로 에러가 난다.

		Korean k1 = new Korean("길동", "230216-1009001");
		Korean k2 = new Korean("길동", "230216-1009001");

		set1.add(k1);
		set1.add(k2);                                                                                 
		
		for ( Korean ko : set1 ) {
			System.out.println(ko.hashCode()); 
		}

		System.out.println(set1.size());

	}

}

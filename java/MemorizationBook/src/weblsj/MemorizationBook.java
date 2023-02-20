package weblsj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemorizationBook {
	
	
	Map<String, List<String>> MBook;
	
	
	public MemorizationBook() {
		 MBook = new HashMap<>();
	}
	
	
	
	
	
	// 단어 생성
	public void addWord(String key ,  List<String> value) {
		MBook.put(key, value);
	}
	
	
	// 단어로 뜻 검색
	public String searchMeaning( String key ) {
		String result = key + ":";
		
		for (String meaning : MBook.get(key)) {
			result += meaning + ",\t";
		}
		
		return result;
	}
	
	
	// 뜻으로 단어 검색
//	public String searchWord( List<String> value ) {
//		return MBook.get(value);
//	}
	
	//단어 삭제
	public void removeWord( String Key) {
		MBook.remove(Key);
	}
	
	
}

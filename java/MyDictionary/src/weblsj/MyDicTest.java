package weblsj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MyDicTest {

	Map<String, List<String>> engNote = new HashMap<>();

	public static void main(String[] args) {

		MyDicTest mt = new MyDicTest();

		while (true) {
			System.out.println(
					"======================================================================================================");
			System.out.println("1. 단어 등록 \t 2.뜻 등록 \t 3.단어로 뜻 검색 \t 4 뜻으로 단어 검색 \t 5.삭제 \t 6.전체 사전 검색 \t 7.끝");
			System.out.println(
					"======================================================================================================");
			Scanner sc = new Scanner(System.in);

			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				mt.registerWord();
				break;

			case 2:
				mt.addMean();
				break;

			case 3:
				mt.searchMeanByWord();
				break;

			case 4:
				mt.searchWordByMean();
				break;
				
			case 5:
				mt.delWord();
				break;

			case 6:
				mt.outputEngNote();
				break;

			case 7:
				System.exit(0);
				break;

			default:
				break;
			}

		}
	}

	private void delWord() {
		System.out.println("단어를 입력하세요 >>> ");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		
		if(this.engNote.containsKey(word)) {
			
			this.engNote.remove(word);
			System.out.println(word + "을(를) 삭제 했습니다.");
		} else {
			System.out.println("그런 단어가 없습니다.");
		}
		
	}

	private void searchWordByMean() {
		System.out.println("뜻 입력하세요 >>> ");
		Scanner sc = new Scanner(System.in);
		String findMean = sc.nextLine();
		String findKey = null;

		Set<String> keyList = this.engNote.keySet();
		for (String key : keyList) {
			List<String> means = this.engNote.get(key);
			for (String mean : means) {
				if (findMean.equals(mean)) {
					findKey = key;
				} else {
//					System.out.println("해당하는 단어가 없습니다");
				}
			}

		}
		System.out.println(findKey + "입니다");

		// 접근이 잘못된듯
//		Collection<List<String>> values = this.engNote.values();
//		
//		for (List<String> means : values) {
//			for (String mean : means) {
//				
//				if(findMean.equals(mean)) {
//				System.out.println("여기 까진 왔는데!!!");		
//				}
//				
//			}
//		}

	}

	private void searchMeanByWord() {
		System.out.println("단어를 입력하세요 >>> ");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();

		if (this.engNote.containsKey(word)) {
			List<String> means = this.engNote.get(word);

			for (String mean : means) {
				System.out.print(mean + "\t");
			}
		} else {
			System.out.println("해당 단어가 없습니다.");
		}

		System.out.println();

	}

	private void addMean() {
		System.out.println("단어를 입력하세요 >>> ");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();

		if (this.engNote.containsKey(word)) {
			List<String> means = this.engNote.get(word);

			System.out.println("해당 단어의 뜻 입력 >>> ");
			String mean = sc.nextLine();
			means.add(mean);

		} else {
			System.out.println("해당 단어가 없습니다.");
		}

	}

	private void outputEngNote() {
		Set<String> words = this.engNote.keySet();
		for (String word : words) {
			System.out.println("단어 : " + word);
			List<String> means = this.engNote.get(word);
			for (String mean : means) {
				System.out.print(mean + "\t");
			}
		}
		System.out.println();

	}

	private void registerWord() {
		System.out.println("단어를 입력하세요 >>> ");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();

		System.out.println("해당 단어의 뜻 입력 >>> ");
		String mean = sc.nextLine();
		List<String> means = new ArrayList<>();
		means.add(mean);

		this.engNote.put(word, means);
	}
}

package weblsj;

public class SortManager {
	// 정렬기준 메뉴값에 따라 해당하는 정렬 기준에 따른 정렬 객체를 만들고 반환하는 메서드
	public static Sortable getSortMethod(int menu) {
	Sortable result = null;
		switch(menu) {
		case 1 :
			result = new AccendingByStudentNo();
			break;
		case 2 :
			result = new DescendingByStudentName();
			break;
		default :
			result = new AscendingByStudentScore();
		}
		
		return result;
	}
}

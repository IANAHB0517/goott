package weblsj;

public class ParseManager {
	public static Parseable getParser(String fileName) {
		Parseable result = null;
		String ext = fileName.substring(fileName.indexOf(".") + 1);
		System.out.println(ext);
		if (ext.toLowerCase().equals("xml")) {
			result = new XMLParser();
		} else if (ext.toLowerCase().equals("json")) {
			result = new JSONParser();
		}
		// 팩토리 패턴
		
		return result;
	}
}

package weblsj;

public class JSONParser implements Parseable {

	@Override
	public void parse(String extension) {
		if (extension.toLowerCase().equals("json")) {
			System.out.println("파싱 가능한 json파일 입니다. 파싱시작");
		}
	}

}

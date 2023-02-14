package weblsj;

public class XMLParser implements Parseable {

	@Override
	public void parse(String extension) {
		if (extension.toLowerCase().equals("xml")) {
			System.out.println("파싱 가능한 xml파일 입니다. 파싱중");
		}
	}

}

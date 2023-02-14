package weblsj;

public class ParserTest {

		public static void main(String[] args) {
		
			Parseable parser = ParseManager.getParser("data.json");
			parser.parse("json");
		}
}


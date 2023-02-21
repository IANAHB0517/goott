package weblsj;

public class FileInOutPutTest_2 {

	public static void main(String[] args) {

		String fromPath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\ioex.txt";
		String toPath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\copyBufferedWriter2.txt";
		
		BufferedReader reader = new BufferedReader();
		BufferedWriter writer = new BufferedWriter();
		
		
		
		reader.readFile(fromPath);
		
		writer.writeFile(reader.readFile(fromPath), toPath);
		
		
	}

}

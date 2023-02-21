package weblsj;

public class FileInOutPutTest {

	public static void main(String[] args) {
		BufferedWriter copier = new BufferedWriter();
		
		String fromPath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\ioex.txt";
		String toPath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\copyBufferedWriter.txt";
		
		
		copier.copyFile(fromPath, toPath);

	}

}

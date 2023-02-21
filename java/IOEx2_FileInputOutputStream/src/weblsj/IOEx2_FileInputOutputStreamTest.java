package weblsj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOEx2_FileInputOutputStreamTest {

	public static void main(String[] args) {
		String filePath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\ioex.txt";
		String copyFile = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\copyioex.txt";

		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream(copyFile);
			
			int data = 0;
			while ((data = fis.read()) != -1) {
				System.out.print((char) data);
				fos.write(data);
			}
			
			fis.close();
			fos.close();

		} catch (FileNotFoundException e) {
			// 파일이 존재하지 않을 때
			e.printStackTrace();
		} catch (IOException e) {
			// 파일을 읽는 도중에 예외 발생
			e.printStackTrace();
		}
		
		

	}

}

package weblsj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedReader {

	FileInputStream fis = null;

	
	int data = 0;
	public int readFile(String fromPath) {
		try {
			fis = new FileInputStream(fromPath);
			
			while((data = fis.read()) != -1 ) {
				System.out.print((char)data);
			}
			

			fis.close();

		} catch (FileNotFoundException e) {
			// 파일 쓰기 과정 오류
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
}

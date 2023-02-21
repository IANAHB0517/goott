package weblsj;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedWriter {

	FileOutputStream fos = null;

	public void writeFile(int data, String toPath) {
		try {
			fos = new FileOutputStream(toPath);

			fos.write(data);

			fos.close();
			
			
		} catch (FileNotFoundException e) {
			// 파일 쓰기 과정 오류
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

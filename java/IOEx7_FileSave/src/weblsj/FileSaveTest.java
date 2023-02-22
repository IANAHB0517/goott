package weblsj;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileSaveTest {

	public static void main(String[] args) {
		String copyPath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\copy한글텍스트.txt";

		OutputStream os = null; // 파일에서 읽어오도록 하는 애

		try {
			os = new FileOutputStream(copyPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String txt = "오늘 매우 졸리고 오후에는 좀 나아질까?";
		try {
			bw.write(txt + "\r\n"); // \r\n : 텍스트 파일에서의 줄바꿈(windows)
			bw.write("현재시간10시 52분");
			
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

package weblsj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class IOEx6_BufferedInputOutputTest {

	public static void main(String[] args) {
		String filePath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\ioex.txt";
		String copyPath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\copy한글텍스트.txt";

		InputStreamReader is = null;
		OutputStream os = null; // 파일에서 읽어오도록 하는 애
		try {
			FileInputStream fis = new FileInputStream(filePath);
			is = new InputStreamReader(fis, "utf-8"); 
			os = new FileOutputStream(copyPath);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(is);
		BufferedWriter bw  = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int data = 0;
//		String data = null;
		try {
			while ((data = br.read()) != -1) { // 한 글자씩 읽어서 출력하고 파일에 씀
				System.out.print((char)data);
				bw.write(data);
//			while ((data = br.readLine()) != null) { // 한 줄씩 읽어서 출력하는 방법
//				System.out.println(data);
//				bw.write(data + "\r\n");
			}
			bw.flush(); // 다른 곳에서 만들어도 데이터 찌꺼기가 없도록 버퍼를 비운다 // 남아 있는 데이터까지 스트림을 이용하여 전송
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

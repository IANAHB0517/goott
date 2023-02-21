package weblsj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOTest {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9,}; // 원본 데이터 출발지
		byte[] outSrc = null; // 목적지
		
		
		// 입출력 스트림 객체 생성

		InputStream input = null;
		ByteArrayOutputStream output=null; // IO 패키지의 모든것은 예외가 특히 많음, try/catch블록에서 쓸 변수를 미리 선언해서 초기화 해둠
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		int data = 0;
		try { // 원본 데이터의 끝이 아닐 동안 1byte씩 읽어서(read) 출력 스트림에 1 byte씩 write
			while((data = input.read()) != -1) { //-1 : EOF(End OF File)
				output.write(data);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		outSrc = output.toByteArray(); // output에 있는 데이터를 byte[]로 변환
		
		for (byte b : outSrc) {
			System.out.print(b + "\t");
		}
		
		try {
			input.close(); // 입출력 스트림 객체는 입출력이 완료 되면, close 해줘야 한다.(객체 소멸)
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

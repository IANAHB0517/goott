package weblsj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class BefferedTest {

	public static void main(String[] args) {
		String readPath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\ioex.txt";
		String writePath = "D:\\lecture\\java\\IOEx2_FileInputOutputStream\\copyBufferedWriter4.txt";
		
		
		String content = null;
		try {
			BufferedReader readFile = new BufferedReader(new FileReader(readPath));
			BufferedWriter writeFile = new BufferedWriter(new FileWriter(writePath));
			
			while((content = readFile.readLine()) != null ) {
				System.out.println(content);
				writeFile.write(content);
				}
//			System.out.println(content);
			
			
			readFile.close();
			writeFile.close();
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

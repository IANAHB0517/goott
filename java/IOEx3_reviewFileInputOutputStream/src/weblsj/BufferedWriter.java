package weblsj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedWriter {
	
	FileInputStream fis = null;
	FileOutputStream fos = null;
	
	public void  copyFile(String filePath, String outputPath) {
		
		try {
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream(outputPath);
			
			int data = 0;
					
			while ((data = fis.read()) != -1) {
				System.out.print((char) data);
				fos.write(data);
				}
			 
			fis.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}

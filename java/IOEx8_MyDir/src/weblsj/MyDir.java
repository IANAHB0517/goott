package weblsj;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MyDir {

	public static void main(String[] args) {
		String curDir = "D:\\lecture\\java";
		String pat = "yyyy.MM.dd HH:mma";

		
		File dir = new File(curDir); // 현재 경로의 File 객체 생성
		
		File[] files = dir.listFiles();
		
		int fileCnt = 0;
		long fileSize = 0;
		long totalSize = 0;
		for (File f : files) {
			String name = f.getName();//파일 또는 디렉토리 이름
			
			
//			System.out.println(name + ", " + new Date(f.lastModified()).toLocaleString());// 사용하지 않음
			//java.text.SimpleDateFormat을 이용해 long 타입의 타임스탬프 변환
			Date lastModDate = new Date(f.lastModified()); // 파일의 마지막 수정일 객체 생성			
			SimpleDateFormat sdf = new SimpleDateFormat(pat); // 날짜 객체의 패턴 생성
			String lastModified = sdf.format(lastModDate); // 날짜 객체를 패턴으로 변환
			
			String isDir = "";
			if(f.isDirectory()) {
				isDir = "<DIR>"; // f 가 디렉토리라면
			} else { // 파일 이라면
				fileCnt++;
				fileSize = f.length();
				totalSize += fileSize;
			}
			
			
			
			System.out.println(name + "\t\t\t" + lastModified + "\t" + isDir + "\t" + fileSize);
			
			
		}
		System.out.println("총 " + fileCnt + "개 " + totalSize + "바이트");
		System.out.println("총 " + dir.getUsableSpace() + "바이트");
	}

}

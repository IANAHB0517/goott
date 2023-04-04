package com.springproj.etc;

import java.io.File;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

public class UploadFilesProc {

	public void uploadFile
	(String originFileName, String originFileType, byte[] upfilesContent, String realPath) {
		
		// 여기에서는 업로드된 파일이 업로드된 날짜의 폴더 밑에 저장 되도록 하자
		// 예)
		
		makeCalPath(realPath);
	}

	private void makeCalPath(String realPath) {
		
		
		
		Calendar cal = Calendar.getInstance();
		  
		System.out.println(cal);
		
		 int curYEAR = cal.get(cal.YEAR);
		 int curMONTH = cal.get(cal.MONTH )+1;
		 int curDate = cal.get(cal.DAY_OF_MONTH);
		 
		 System.out.println(curYEAR +" / "+curMONTH+" / "+curDate);
		
		 
		
		checkFolder(realPath, curYEAR + "\\ " + curMONTH + "\\" + curDate); // 모르겄다 파일은 어떻게 만들고 중복은 어떻게 검사하는가
		
		
		 
		 //createFolder(realPath, );
		 
		 // 날짜를 다 얻어 왔으니 파일저장 경로에 해당 연도가 같은 폴더가 있는지 확인 한다.
		
		 
		 // 같은 연도의 폴더가 있다면 연도 안으로 들어가서 월을 검사, 월도 있다면 그 안으로 들어가서 일을 검사하고 있아면 저장한다.
		 
		 // 해당 연도가 없다면 연도 파일을 생성, 그 안에 들어가 월 폴더도 생성, 마찬가지로 일 폴더도 생성한다.
		 
//		 if()
//		 File saveAddr = new File(saveAddrY);
//		 
//		 File saveAddr = new File(saveAddrY);
		 
		 
		// File.mkdir
		
	}

	private void checkFolder(String realPath, String string) {
		boolean result = false;
		
		
		
		return result;
	}

	
}

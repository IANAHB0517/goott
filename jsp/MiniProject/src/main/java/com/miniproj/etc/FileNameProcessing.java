package com.miniproj.etc;

import java.io.File;

import org.apache.commons.fileupload.FileItem;

public class FileNameProcessing {

	// 
		/**
		 * @param fi
		 * @param userId
		 * @param realPath
		 * @return 업로드된 파일의 이름을 변환하여 중복되지 않는 이름으로 반환
		 */
		public static String getNewFileName(FileItem fi, String realPath) {
			long tmpFileSize = fi.getSize();// 파일 사이즈
			String tmpFileName = fi.getName(); // 업로드된 이미지의 이름(확장자 포함)
			String newFileName = ""; // 실제 저장되는 파일명

			if (tmpFileSize > 0) {

				// 파일 이름 처리를 어떻게 할 것이냐(같은 이름의 파일이 있으 면 overwrite 되기 때문에
				// 1) 아예 처음 부터 중복되지 않을 이름으로 저장하는 방법
				// ex) "userId_유니크값.확장자"

				// newFileName = makeNewUniqueFileName(userId, tmpFileName);

				// 2) 파일을 저장하기 전에 같은 이름의 파일이 존재하는지 검사하여
				// 같은 이름의 파일이 존재한다면
				// ex) "파일명(번호).확장자" 로 처리 하는 방법

				// newFileName = makeNewFileNameWithNumbering(tmpFileName, realPath);
				int cnt = 0;
				while (duplicateFileName(tmpFileName, realPath)) { // 파일이 중복되면
					cnt++;
					tmpFileName = makeNewFileNameWithNumbering(tmpFileName, cnt);
				}

				newFileName = tmpFileName;

				System.out.println(newFileName);

			}
			return newFileName;
			
		
		}
		
		private static boolean duplicateFileName(String tmpFileName, String realPath) {
			boolean result = false;

			File tmpFileNamePath = new File(realPath);
			File[] files = tmpFileNamePath.listFiles();
			for (File f : files) {
				if (f.getName().equals(tmpFileName)) { // 파일명이 중복된다. // 여기서 카운트를 해가면 안되는건가?
					result = true;
				}
			}

			return result;

		}
		
		/**
		 * @param tmpFileName
		 * @param cnt
		 * @return 중복된 파일명에 숫자를 붙여서 중복을 피하게 해주는 메서드
		 */
		private static String makeNewFileNameWithNumbering(String tmpFileName, int cnt) {
			String newFileName = "";
			String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1); // 확장자 얻어오기
			String oldFileNameWithoutExt = tmpFileName.substring(0, tmpFileName.lastIndexOf(".")); // 원래 파일명 얻어오기

			int openPos = oldFileNameWithoutExt.indexOf("("); // 파일이름에 ( 이있으면 어쩌지?

			if (openPos == -1) { // ( 가 없다면 -> 처음 중복
				newFileName = oldFileNameWithoutExt + "(" + cnt + ")." + ext;
			} else {
				newFileName = oldFileNameWithoutExt.substring(0, openPos) + "(" + cnt + ")." + ext;
			}

			return newFileName;
		}
}

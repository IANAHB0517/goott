package com.springproj.etc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.util.FileCopyUtils;

public class UploadFilesProc {
	
	
	/**
	 * 삭제될 파일이 이미지 인지 아닌지 판단하여 이미지면 (원본 + 썸네일 삭제)
	 * 이미지가 아니라면 원본 파일을 삭제한다
	 * 
	 * @param ufi 삭제 될 파일의 정보
	 * @param realPath /resource/upFiles의 물리적 경로
	 */
	public static void deleteUpFile(UploadFileInfo ufi, String realPath) {
		// 원본 삭제
		File target = new File(realPath + ufi.getFileNameWithExt());
		
		System.out.println("파일 삭제 결과 : " + target.delete());
		target.delete();
		
		
		if (ufi.isImage()) {// 삭제할 파일이 이미지 이다
			// 썸네일 파일 삭제
			File targetThumb = new File(realPath + ufi.getThumbImgName());
			targetThumb.delete();
		}
	}
	
	

	/**
	 * @param originFileName : 업로드 된 파일 이름(확장자 포함)
	 * @param originFileType : 업로드 된 파일의 mimeType
	 * @param upfilesContent : 업로드 된 파일의 실제 contents
	 * @param realPath       : 파일이 저장 되는 디렉토리의 상위 디렉토리
	 * @throws IOException
	 */
	public static UploadFileInfo uploadFile(String originFileName, String originFileType, byte[] upfilesContent,
			String realPath) {

		// 여기에서는 업로드된 파일이 업로드된 날짜의 폴더 밑에 저장 되도록 하자
		// 예)

		String savePath = makeCalPath(realPath); // 실제 저장 경로

		String customPath = savePath.substring(realPath.length());

//		System.out.println("실제 저장 경로 : " +  savePath );

		// FileNameAndExt 객체 (unique 한 새 파일이름, 확장자)
		UploadFileInfo uniqueFileName = makeNewUniqueFileName(originFileName);
		System.out.println("실제 저장 파일명 : " + uniqueFileName.toString());
		uniqueFileName.setMimeType(originFileType);

		String savedFile = savePath + File.separator + uniqueFileName.getOriginFileName();

		uniqueFileName.setFileNameWithExt(customPath + File.separator + uniqueFileName.getOriginFileName());

		File saveTarget = new File(savedFile);

		try {

			// 실제 파일 저장
			FileCopyUtils.copy(upfilesContent, saveTarget);

			// 이미지 파일인지 아닌지 검사
			if (ImgMimeType.getMediaType(uniqueFileName.getExt()) != null) { // 이미지 파일이다
//			System.out.println("이미지 파일");
				uniqueFileName.setImage(true);
				uniqueFileName.setBase64Str(makeFiletoBase64String(savedFile));

				System.out.println();

				makeThumbnailImage(savedFile, uniqueFileName, savePath, customPath, realPath);

			} else {
//			System.out.println("이미지 파일 아님");
			}

			System.out.println(uniqueFileName.isImage());
		} catch (IOException e) {
			// 저장 실패
			uniqueFileName = null;
		}

		return uniqueFileName;
	}

	private static void makeThumbnailImage(String savedFile, UploadFileInfo uniqueFileName, String savePath,
			String customPath, String realPath) throws IOException {
		// TODO Auto-generated method stub
		BufferedImage originImg = ImageIO.read(new File(savedFile)); // 원본
		BufferedImage thumbImg = Scalr.resize(originImg, Mode.FIT_TO_HEIGHT, 50, null);

		int originHeight = originImg.getHeight();
		System.out.println("원본 이미지의 높이 : " + originHeight);
		// thumb 파일의 이름 변경
		String thumbImgName = "thumb_" + uniqueFileName.getOriginFileName();
		// 저장 위치
		File saveTarget = new File(savePath + File.separator + thumbImgName);

		if (ImageIO.write(thumbImg, uniqueFileName.getExt(), saveTarget)) {
			uniqueFileName.setThumbImgName(customPath + File.separator + thumbImgName);
		}
		;
	}

	/**
	 * @param strUpFilePath
	 * @param realPath
	 * @param userImg
	 * @return
	 * @throws IOException
	 */
	private static String makeFiletoBase64String(String savedFile) throws IOException {
		// base64 문자열 : 이진 데이터 파일을 읽어서 A-Za-z0-9+/ 문자의 조합으로 바꾼것
		// 파일 -> 문자열로 표현

		String result = null;
		File upFile = new File(savedFile);

		byte[] file = FileUtils.readFileToByteArray(upFile); // 업로드 된 파일을 읽음
		result = Base64.getEncoder().encodeToString(file); // 읽은 파일을 base64로 인코딩

//		System.out.println(result); // 인코딩된 문자열

		return result;

	}

	/**
	 * 업로드된 파일이 업로드된 날짜의 폴더 밑에 저장되도록 하자 예) 현재 날짜 : 2023-04-04 라면.
	 * resources/upFiles/2023/04/05/ 하위에 저장되도록 하자
	 * 
	 * 중요 파일을 검사하고 만들기
	 * 
	 * @param realPath : 실제 저장될 물리적 경로 return : 물리적 경로 + 년월일 하위 경로 = 파일의 실제 저장 경로
	 */
	private static String makeCalPath(String realPath) {

		Calendar cal = Calendar.getInstance();

//		System.out.println(cal);

		String year = File.separator + cal.get(cal.YEAR) + ""; // realPath\2023
		String month = year + File.separator + new DecimalFormat("00").format((cal.get(cal.MONTH) + 1)) + "";
		// ** DecimalFormat : 10 진수로 형변환 해줌 0 -> 자릿수 두자리 숫자로 바꿔줌 숫자가 없으면 0으로 매워준다
		// realPath\2023\04
		String date = month + File.separator + new DecimalFormat("00").format(cal.get(cal.DAY_OF_MONTH)) + "";
		// realPath\2023\04\05
//		 System.out.println(year + month + date);

		makeDir(realPath, year, month, date);

		return realPath + date;

	}

	/**
	 * 
	 * realPath 경로에 year, month, date 폴더가 있는지 확인하고, 없으면 directory를 생성
	 * 
	 * @param realPath
	 * @param ...paths : 가변인자 makeDir(realPath, year, month, date)에서 realPath를 제외한
	 *                 매개변수가 String 타입 변수 3개이다. year, month, date 가 paths 변수에서 모두 받게
	 *                 된다(배열 형식으로)
	 */
	private static void makeDir(String realPath, String... paths) {
		// new File(realPath + paths[paths.length - 1]).exists()
		// realPath 경로 밑에 \년\월\일 폴더가 모두 존재 => 아래의 수행을 하지 않아도 됨
		if (!new File(realPath + paths[paths.length - 1]).exists()) {

			for (String path : paths) {
				File tmp = new File(realPath + path);
				if (!tmp.exists()) { // 경로가 존재하지 않는다면
					tmp.mkdir(); // 잘만들어지면 1을 반환 아니면 -1을 반환 하지만 대부분 권한 문제로 나려면 그 이전에 오류가 난다
					System.out.println(tmp + "경로에 디렉토리 생성");
				}
				;
			}
		}

	}

	/**
	 * @param originFileName : 업로드 된 파일 이름(확장자 포함)
	 * 
	 * @return FileNameAndExt객체 (unique한 새 파일 이름 , 확장자)
	 */
	private static UploadFileInfo makeNewUniqueFileName(String originFileName) {
		String newFileName;
		String ext = originFileName.substring(originFileName.lastIndexOf(".") + 1);
		System.out.println("확장자 : " + ext);
		String uuid = UUID.randomUUID().toString(); // 암호같이 생겼지만 암호화가 아닌 임의의 값임

		newFileName = uuid + "_" + originFileName;

		System.out.println("저장될 새로운 파일 이름 : " + newFileName);

		return new UploadFileInfo(newFileName, ext);
	}



	

}

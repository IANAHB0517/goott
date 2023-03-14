package com.miniproj.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.miniproj.service.MemberService;

public class RegisterMemberService implements MemberService {

	// 파일 업로드를 위한 세팅
	// (하나의 파일 블럭이 들어오는 버퍼 사이즈) 5mb
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 5;
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 최대 파일 업로드 크기(10MB)
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 15; // 최대 request 버퍼 크기(15MB)

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.print("회원 가입 하자~");

		String upload = "\\uploadMember";
		ServletContext context = req.getServletContext(); // 현재 request에 대응하는 서블릿 객체를 얻음
		System.out.println("현재 서블릿 객체 : " + context);
		
		String realPath = context.getRealPath(upload); // 파일이 업로드 될 물리적 경로
		System.out.println("파일이 서버에 저장될 실제 경로" + realPath);

		String encoding = "utf-8"; // 텍스트 데이터, 파일 이름 인코딩을 위해

		// 파일을 저장하기 위한 File 객체 생성
		File saveFileDir = new File(realPath); // 파일을 저장할 경로 지정

		String userId = "";
		String userPwd = "";
		String userName = "";
		String userEmail = "";
		String userMobile = "";
		String userGender = "";
		String hobbies = "";
		String job = "";
		String userImg = "";
		String memo = "";

		// 파일이 저장될 공간의 경로, 사이즈 등의 정보를 가지고 있는 객체
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(saveFileDir);
		factory.setSizeThreshold(MEMORY_THRESHOLD);

		// 실제 request로 넘겨져온 매개변수를 통해 파일을 upload 할 객체
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(MAX_FILE_SIZE);
		sfu.setSizeMax(MAX_REQUEST_SIZE);
		// 실제로 파일을 저장할 객체이기 때문에 파일의 최대 사이즈와 요청의 최대 사이즈를 갖는다

		// 중요!!! - 파일 업로드시 함께 넘겨져온 다른 텍스트 데이터를 아래와 같이
		// request.getParameter(); 사용할 수 없다.

		try {
//			 FileItem 의 특징
//			 1) name 속성의 값이 이미지는 null이 아니고 파일 이름이다. (이미지가 아닌 다른 데이터는 name 속성의 값이 null이다)
//
//			 2) 이미지 파일의 isFormField 속성의 값은 false(이진 파일, 폼 데이터가 아님),
//			 이미지 파일이 아닌 데이터는 isFormField 의 속성이 true
//
//			 3) FieldName 속성의 값은 데이터가 넘겨져온 매개변수 이름이다.
			List<FileItem> lst = sfu.parseRequest(req);

			for (FileItem fi : lst) {
				// System.out.println(fi);

				if (fi.isFormField()) { // 이미지가 아닌 데이터
					if (fi.getFieldName().equals("userId")) {
						userId = fi.getString(encoding);
					} else if (fi.getFieldName().equals("pwd")) {
						userPwd = fi.getString(encoding);

					} else if (fi.getFieldName().equals("email")) {
						userEmail = fi.getString(encoding);

					} else if (fi.getFieldName().equals("mobile")) {
						userMobile = fi.getString(encoding);

					} else if (fi.getFieldName().equals("gender")) {
						userGender = fi.getString(encoding);

					} else if (fi.getFieldName().equals("job")) {
						job = fi.getString(encoding);

					} else if (fi.getFieldName().equals("memo")) {
						memo = fi.getString(encoding);

					}
				} else { // isFormField() : false -> 이미지 파일
					saveUserImg(fi, userId, realPath);
				}

			}
			;
			System.out.println(userId);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private void saveUserImg(FileItem fi, String userId, String realPath) {
		long tmpFileSize = fi.getSize();// 파일 사이즈
		String tmpFileName = fi.getName(); // 업로드된 이미지의 이름(확장자 포함)
		String newFileName = ""; // 실제 저장되는 파일명
		
		if (tmpFileSize > 0) {
			
			// 파일 이름 처리를 어떻게 할 것이냐(같은 이름의 파일이 있으면 overwrite 되기 때문에
			// 1) 아예 처음 부터 중복되지 않을 이름으로 저장하는 방법
			// ex) "userId_유니크값.확장자"
			
			//newFileName = makeNewUniqueFileName(userId, tmpFileName);
			
			
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
	}

	private String makeNewFileNameWithNumbering(String tmpFileName, int cnt) {
		String newFileName = "";
		String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1) ; // 확장자 얻어오기
		String oldFileNameWithoutExt = tmpFileName.substring(0, tmpFileName.lastIndexOf(".")); // 원래 파일명 얻어오기
		
		int openPos = oldFileNameWithoutExt.indexOf("("); // 파일이름에 ( 이있으면 어쩌지?
		
		if(openPos == -1) { // ( 가 없다면 -> 처음 중복
			newFileName = oldFileNameWithoutExt +"(" + cnt + ")." + ext;
		} else {
			newFileName = oldFileNameWithoutExt.substring(0, openPos) + "(" + cnt + ")." + ext;
		}
		
		return newFileName;
	}

	//tmpFileName의 파일이 realPath에 존재한다면 true, 아니면 false를 반환
	private boolean duplicateFileName(String tmpFileName, String realPath) {
		boolean result = false;
		
		File tmpFileNamePath = new File(realPath);
		File[] files = tmpFileNamePath.listFiles();
		for (File f : files) {
			if(f.getName().equals(tmpFileName)) { // 파일명이 중복된다. // 여기서 카운트를 해가면 안되는건가?
				result = true;
			}
		}
		
		return result;
		
	}

	private String makeNewUniqueFileName(String userId, String tmpFileName) {
		String newFileName;
		String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1) ;
		System.out.println("확장자 : " + ext);
		String uuid = UUID.randomUUID().toString(); // 암호같이 생겼지만 암호화가 아닌 임의의 값임
		
		newFileName = userId + "_" + uuid + "." + ext;
		
		System.out.println("저장될 새로운 파일 이름 : " + newFileName);
		
		return newFileName;
	}

}

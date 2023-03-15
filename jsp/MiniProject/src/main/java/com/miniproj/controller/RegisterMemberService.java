package com.miniproj.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.miniproj.dao.MemberDAO;
import com.miniproj.dao.MemberDAOImpl;
import com.miniproj.error.CommonException;
import com.miniproj.service.MemberService;
import com.miniproj.vodto.MemberDTO;

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

		MemberFactory mf = MemberFactory.getInstance();

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
		List<String> hobbyLst = new ArrayList<>();
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
				System.out.println(fi);

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

					} else if (fi.getFieldName().equals("hobby")) {
						hobbyLst.add(fi.getString(encoding));

					}

				} else { // isFormField() : false -> 이미지 파일
					userImg = getNewFileName(fi, userId, realPath);
					// 업로드된 파일을 실제 저장
					// 1) File객체 생성
					// 디렉토리(폴더) 구분자
					// 영문 windows : \
					// 한글 windows : \(원)
					// linux 계열 : /
					// File.separator : 운영체제마다 다른 디렉토리 구분자를 설정한 상수
					File uploadFilePath = new File(realPath + File.separator + userImg);
					// 2) 실제 저장
					try {
						fi.write(uploadFilePath);
					} catch (Exception e) {
						// 유저가 업로드한 파일이 저장이 안되었다.
						userImg = "";
					}
				}

			}

		} catch (FileUploadException e) {
			// request객체에 대해 파싱 에러일 수도 있으므로 이때는 회원 가입이 안되어야 함.
//			e.printStackTrace();
			mf.setRedirect(true);
			mf.setWhereisgo("register.jsp?status=fail");
			return mf;

		}

		// 취미 여러개를 한개의 컬럼에 넣기위해서
		for (int i = 0; i < hobbyLst.size(); i++) {
			if (i != hobbyLst.size() - 1) {
				hobbies += hobbyLst.get(i) + ",";

			} else {
				hobbies += hobbyLst.get(i);
			}
		}

		String dbUserImg = "";
		// DB에 insert 하기 전 업로드된 파일이 있는지?
		if (!userImg.equals("")) { // 업로드된 이미지가 존재 한다면
			dbUserImg = "uploadMember/" + userImg; // DB에 경로까지 포함해서 insert 한다.
		}

		// 만약 base64문자열로 파일을 넣고 싶다면...
		String strUpFilePath = realPath + File.separator + userImg;
		makeFiletoBase64String(strUpFilePath, realPath, userImg);

		// DAO으로 전송하기 위해
		MemberDTO member = new MemberDTO(userId, userPwd, userEmail, userMobile, userGender, hobbies, job, dbUserImg,
				memo);

		System.out.println(member.toString() + "입력한 객체 toString");

		MemberDAO dao = MemberDAOImpl.getInstance();

		// System.out.println(member + "service 단 에서 넘겨줄 member 값");

		try {
			if (dao.insertMember(member) == 1) {
				// 회원 가입 잘됨
				System.out.println("mf 값 할당!");

				mf.setRedirect(true);
				mf.setWhereisgo("../index.jsp?status=success");
			}
		} catch (NamingException | SQLException e) {

			// DB에 insert되지 않았으므로 회원 가입이 안되어야 함. -> 유저가 업로드한 파일을 삭제
			// 회원 가입 오류가 나면 일단 업로드된 파일을 삭제
			File uploadFilePath = new File(realPath + File.separator + userImg);
			System.out.println(realPath + File.separator + userImg + "를 삭제합니다");
			uploadFilePath.delete(); // 유저가 업로드한 파일 삭제

			if (e instanceof NamingException) {
//				e.printStackTrace(); // 개발자가 잘못한 경우
				// NamingException 은 개발자 실수이기 때문에 개발자만 보도록 공통 에러페이지(error.jsp)를 만들었고
				// 에러 정보를 error.jsp로 바인딩하여 error.jsp 페이지에서 에러 정보를 출력하였다.
				// forward
				CommonException ce = new CommonException(e.getMessage(), 99);
				ce.setErrorMsg(e.getMessage()); // 에러의 종류
				ce.setStackTrace(e.getStackTrace()); // 에러 기록

				req.setAttribute("error", ce); // 에러 정보를 가진 CommonException 객체 바인딩

				req.getRequestDispatcher("../error.jsp").forward(req, resp); // 페이지 이동

			} else if (e instanceof SQLException) {
				mf.setRedirect(true);
				mf.setWhereisgo("register.jsp?status=fail");
				return mf;
			}

		}

		return mf;

	}

	private String makeFiletoBase64String(String strUpFilePath, String realPath, String userImg) {
		// base64 문자열 : 이진 데이터 파일을 읽어서 A-Za-z0-9+/ 문자의 조랍으로 바꾼것
		// 파일 -> 문자열로 표현

		String result = null;
		File upFile = new File(strUpFilePath);
		try {
			byte[] file = FileUtils.readFileToByteArray(upFile); // 업로드 된 파일을 읽음
			result = Base64.getEncoder().encodeToString(file); // 읽은 파일을 base64로 인코딩
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(result); // 인코딩된 문자열

		// 디코딩
		byte[] decodeFile = Base64.getDecoder().decode(result);

		try {
			FileUtils.writeByteArrayToFile(
					new File(realPath + File.separator + "decode." + userImg.substring(userImg.lastIndexOf(".") + 1)),
					decodeFile); // 파일로 저장
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// 업로드된 파일의 이름을 중복되지 않는 이름을 반환
	private String getNewFileName(FileItem fi, String userId, String realPath) {
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

	private String makeNewFileNameWithNumbering(String tmpFileName, int cnt) {
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

	// tmpFileName의 파일이 realPath에 존재한다면 true, 아니면 false를 반환
	private boolean duplicateFileName(String tmpFileName, String realPath) {
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

	private String makeNewUniqueFileName(String userId, String tmpFileName) {
		String newFileName;
		String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1);
		System.out.println("확장자 : " + ext);
		String uuid = UUID.randomUUID().toString(); // 암호같이 생겼지만 암호화가 아닌 임의의 값임

		newFileName = userId + "_" + uuid + "." + ext;

		System.out.println("저장될 새로운 파일 이름 : " + newFileName);

		return newFileName;
	}

}

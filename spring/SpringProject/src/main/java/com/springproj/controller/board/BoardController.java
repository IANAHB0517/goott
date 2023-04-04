package com.springproj.controller.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.springproj.domain.BoardVo;
import com.springproj.etc.UploadFilesProc;
import com.springproj.service.BoardService;

@Controller // 현재 클래스가 컨트롤러 단임을 명시
@RequestMapping("/board/*") // /board로 들어오는 모든 request를 여기에서 매핑하겠다.
public class BoardController {

	@Inject
	private BoardService service; // BoardService 객체 주입

	@RequestMapping("listAll") // listAll.jsp
	public void listAll(Model model) throws Exception {
		System.out.println("컨트롤러단 : 게시판 목록 조회");

		List<BoardVo> lst = this.service.listAll();

		model.addAttribute("boardList", lst);
	}

	@RequestMapping("writeBoard")
	public void writeBoard(Model model) {
		System.out.println("컨트롤러 단 : 게시판 글쓰기");
	}

	@RequestMapping(value = "upfiles", method = RequestMethod.POST)
	public void uploadFileProcess(MultipartFile upfiles, HttpServletRequest req ) throws IOException { // 컨트롤러 단이기 때문에 request 객체가 살아있다
		System.out.println("컨트롤러 단 : 파일  업로드 처리");
		System.out.println("업로드된 파일 이름 : " + upfiles.getOriginalFilename());
		System.out.println("업로드된 파일 타입 : " + upfiles.getContentType());
		
		System.out.println("업로드된 파일이 저장되는 경로 : " + req.getSession().getServletContext().getRealPath("resources/upFiles"));
		String originFileName = upfiles.getOriginalFilename();
		String originFileType = upfiles.getContentType();
		byte[] upfilesContent = upfiles.getBytes(); // 파일의 실질적인 내용 
		// 저장될 물리적 경로
		String realPath = req.getSession().getServletContext().getRealPath("resources/upFiles");
		
		UploadFilesProc.uploadFile(originFileName, originFileType, upfilesContent, realPath);
		
	}

}

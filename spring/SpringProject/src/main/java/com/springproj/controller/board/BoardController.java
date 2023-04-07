package com.springproj.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springproj.domain.BoardImg;
import com.springproj.domain.BoardVo;
import com.springproj.etc.UploadFileInfo;
import com.springproj.etc.UploadFilesProc;
import com.springproj.service.BoardService;

@Controller // 현재 클래스가 컨트롤러 단임을 명시
@RequestMapping("/board/*") // /board로 들어오는 모든 request를 여기에서 매핑하겠다.
public class BoardController {
	private String realPath;

	@Inject
	private BoardService service; // BoardService 객체 주입

	// 업로드된 파일의 리스트
	private List<UploadFileInfo> upFileList = new ArrayList<UploadFileInfo>();

	@RequestMapping("listAll") // listAll.jsp
	public void listAll(Model model) throws Exception {
		System.out.println("컨트롤러단 : 게시판 목록 조회");

		List<BoardVo> lst = this.service.listAll();

		model.addAttribute("boardList", lst);
	}

	@RequestMapping("writeBoard")
	public void writeBoard(Model model) {
		System.out.println("컨트롤러 단 : 게시판 글쓰기");
		// get 방식으로 wirteBoard.jsp를 호출
	}

	@RequestMapping(value = "writeBoard", method = RequestMethod.POST)
	public String writeBoard(BoardVo newBoard) throws Exception {
		System.out.println("컨트롤러 단 : 글 저장");
		System.out.println(newBoard.toString());

		String redirectPage = "";

		if (this.service.saveBoard(newBoard, upFileList)) {
			if (this.upFileList.size() > 0) { // 업로드 파일이 있다면
				this.upFileList.clear(); // 글 등록이 완료되면 해당 글의 업로드 파일 리스트를 비워준다.
			}
			redirectPage = "/board/listAll";
		} else {
			redirectPage = "/board/writeBoard?status=fail";
		}
		return "redirect:" + redirectPage;
	}

	@RequestMapping(value = "upfiles", method = RequestMethod.POST)
	public @ResponseBody UploadFileInfo uploadFileProcess(MultipartFile upfiles, HttpServletRequest req)
			throws IOException { // 컨트롤러 단이기 때문에 request 객체가 살아있다
		System.out.println("컨트롤러 단 : 파일  업로드 처리");
		System.out.println("업로드된 파일 이름 : " + upfiles.getOriginalFilename());
		System.out.println("업로드된 파일 타입 : " + upfiles.getContentType());

		System.out
				.println("업로드된 파일이 저장되는 경로 : " + req.getSession().getServletContext().getRealPath("resources/upFiles"));
		String originFileName = upfiles.getOriginalFilename();
		String originFileType = upfiles.getContentType();
		byte[] upfilesContent = upfiles.getBytes(); // 파일의 실질적인 내용
		// 저장될 물리적 경로
		this.realPath = req.getSession().getServletContext().getRealPath("resources/upFiles");

		UploadFileInfo fileInfo = UploadFilesProc.uploadFile(originFileName, originFileType, upfilesContent, realPath);

		if (fileInfo != null) {
			this.upFileList.add(fileInfo);
		}

		for (UploadFileInfo ufi : upFileList) {
			System.out.println("파일 업로드 리스트 : " + ufi.toString() + "72번줄 웨않뒈");
		}

		return fileInfo;
	}

	@RequestMapping(value = "remfile")
	public ResponseEntity<String> removeFile(@RequestParam("remFileName") String delFileName) {
		System.out.println("삭제할 파일 명 : " + delFileName);

		int indexOfDeletedFile = 0; // 삭제 되는 파일의 리스트 인덱스 번호

		System.out.println("Size of List : " + this.upFileList.size());
		for (UploadFileInfo ufi : upFileList) {

			if (delFileName.equals(ufi.getOriginFileName())) { // 삭제할 파일 찾음
				System.out.println("리스트 삭제 대상 : " + ufi.toString());

				UploadFilesProc.deleteUpFile(ufi, this.realPath); // 삭제 완료
				System.out.println("삭제 완료 : " + delFileName);

				// 반복문이 돌아가는 중에 삭제를 한뒤 리스트를 수정하려고 하면 Concurrency(동시성) 예외가 발생 같은 리소스에 대해 동시적인 제어
				// this.upFileList.remove(ufi); // 리스트에서 삭제
				break;
				// 리스트에서 삭제
			}
			System.out.println("삭제할 파일의 index : " + indexOfDeletedFile);
			indexOfDeletedFile++;
		}

		upFileList.remove(indexOfDeletedFile); // 알아 놓은 인덱스 번호로 해당 리스트 멤버를 삭제

		for (UploadFileInfo ufi : upFileList) {
			System.out.println("삭제 뒤 파일 업로드 리스트 : " + ufi.toString());
		}

		ResponseEntity<String> result = new ResponseEntity<String>("success", HttpStatus.OK); // 응답의 방법 , success 라는
																								// 문자열과 Http 코드를 함께 보낼 수
																								// 있다.

		return result;

	}

	@RequestMapping("viewBoard")
	public void viewBoard(@RequestParam("no") int no, Model model) throws Exception {
		System.out.println("컨트롤러 단 : " + no + "번 글 조회");

		// 리턴된 Map으로 부터 다시 원래 객체를 얻어옴
		Map<String, Object> map = this.service.viewByBoardNo(no);
		BoardVo board = (BoardVo) map.get("board");
		List<BoardImg> lst = (List<BoardImg>) map.get("upFiles");

		// 바인딩
		model.addAttribute("board", board);
		model.addAttribute("upFiles", lst);

	}

	@RequestMapping("delete")
	public String deleteBoard(@RequestParam("no") int no) throws Exception {
		System.out.println("컨트롤러 단 : " + no + "번 글 삭제");
		String redirectPage = "";

		// 삭제
		if ((service.delBoard(no)) == 1) {
			// 삭제 후 listAll 로 이동
			redirectPage = "listAll";
		} else {

			redirectPage = "/board/viewBoard?no=" + no + "deleteStatus=fail";
		}

		return "redirect:" + redirectPage;
	}

	@RequestMapping("modiBoard")
	public void modiBoard(@RequestParam("no") int no, Model model) throws Exception {
		System.out.println("컨트롤러 단 : " + no + "번 글 수정을 위한 조회");


		// 리턴된 Map으로 부터 다시 원래 객체를 얻어옴
		Map<String, Object> map = this.service.viewByBoardNoForMod(no);
		BoardVo board = (BoardVo) map.get("board");
		List<BoardImg> lst = (List<BoardImg>) map.get("upFiles");


		System.out.println(board.getNo() + " " + no);
		
		if (board.getNo() == no) {
			// 바인딩
			model.addAttribute("board", board);
			model.addAttribute("upFiles", lst);
			
		} else {
			//redirectPage = "viewBoard?no=" + no + "modiStatus=fail";
		}


	}

	

	@RequestMapping("modi")
	public String modifyBoard(@RequestParam("no") int no , Model model) throws Exception{
		System.out.println("컨트롤러 단 : " + no + "번 글 수정");
		
		String redirectPage = "";
		
		return "redirect:" + redirectPage;

		
	}
}

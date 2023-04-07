package com.springproj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
// toString() 오버라이딩 자동생성
@ToString
//두 변수의 값이 같으면 같은 객체로 인식되도록 equals() 와 hashcode()를 오버라이딩 자동 생성
//@EqualsAndHashCode(of = {"boardimgNo", "boardNo"})

//@Data // @ToString, @EqualsAndHashCode @Getter @Setter @RequiredArgsConstructor 등 모든 어노테이션을 사용한 효과
@AllArgsConstructor
@NoArgsConstructor // Spring이 사용하기 위해 반드시 필요하다

public class BoardImg {

	private int boardImgNo;
	private int boardNo;
	private String fileType;
	private String fileExt;
	private String fileName;
	private String thumbFileName;
	private String base64file;
//
//	public BoardImg(int boardImgNo, int boardNo, String fileType, String fileExt, String fileName,
//			String thumbFileName, String base64file) {
//		super();
//		this.boardImgNo = boardImgNo;
//		this.boardNo = boardNo;
//		this.fileType = fileType;
//		this.fileExt = fileExt;
//		this.fileName = fileName;
//		this.thumbFileName = thumbFileName;
//		this.base64file = base64file;
//	}
//
//	public int getBoardImgNo() {
//		return boardImgNo;
//	}
//
//	public void setBoardImgNo(int boardImgNo) {
//		this.boardImgNo = boardImgNo;
//	}
//
//	public int getBoardNo() {
//		return boardNo;
//	}
//
//	public void setBoardNo(int boardNo) {
//		this.boardNo = boardNo;
//	}
//
//	public String getFileType() {
//		return fileType;
//	}
//
//	public void setFileType(String fileType) {
//		this.fileType = fileType;
//	}
//
//	public String getFileExt() {
//		return fileExt;
//	}
//
//	public void setFileExt(String fileExt) {
//		this.fileExt = fileExt;
//	}
//
//	public String getFileName() {
//		return fileName;
//	}
//
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//
//	public String getThumbFileName() {
//		return thumbFileName;
//	}
//
//	public void setThumbFileName(String thumbFileName) {
//		this.thumbFileName = thumbFileName;
//	}
//
//	public String getBase64file() {
//		return base64file;
//	}
//
//	public void setBase64file(String base64file) {
//		this.base64file = base64file;
//	}
//
//	@Override
//	public String toString() {
//		return "BoardImgVo [boardImgNo=" + boardImgNo + ", boardNo=" + boardNo + ", fileType=" + fileType + ", fileExt="
//				+ fileExt + ", fileName=" + fileName + ", thumbFileName=" + thumbFileName + ", base64file=" + base64file
//				+ "]";
//	}

}

package com.springproj.etc;

public class UploadFileInfo { //DB의 Table과 컬럼명이 맞지 않아서 읽어오기 위한 VO를 별도로 만들어 주어서 DTO 와 VO를 구분 하는 것이 좋다
	private String originFileName; // 경로 없는 unique한 파일 이름
	private String fileNameWithExt; // 경로 포함
	private String ext; // 확장자
	private String mimeType; // 파일 타입
	private String base64Str; // 이미지인 경우 base64
	private String thumbImgName; // 이미지인 경우 썸네일 파일 이름 (경로포함)
	private boolean isImage; // 이미지 인가
	
	

	public UploadFileInfo(String originFileName, String ext) {
		this(originFileName, "", ext, "", "", "", false);
	}



	public UploadFileInfo(String originFileName, String fileNameWithExt, String ext, String mimeType, String base64Str,
			String thumbImgName, boolean isImage) {
		super();
		this.originFileName = originFileName;
		this.fileNameWithExt = fileNameWithExt;
		this.ext = ext;
		this.mimeType = mimeType;
		this.base64Str = base64Str;
		this.thumbImgName = thumbImgName;
		this.isImage = isImage;
	}



	public String getOriginFileName() {
		return originFileName;
	}



	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}



	public String getFileNameWithExt() {
		return fileNameWithExt;
	}



	public void setFileNameWithExt(String fileNameWithExt) {
		this.fileNameWithExt = fileNameWithExt;
	}



	public String getExt() {
		return ext;
	}



	public void setExt(String ext) {
		this.ext = ext;
	}



	public String getMimeType() {
		return mimeType;
	}



	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}



	public String getBase64Str() {
		return base64Str;
	}



	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}



	public String getThumbImgName() {
		return thumbImgName;
	}



	public void setThumbImgName(String thumbImgName) {
		this.thumbImgName = thumbImgName;
	}



	public boolean isImage() {
		return isImage;
	}



	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}



	@Override
	public String toString() {
		return "UploadFileInfo [originFileName=" + originFileName + ", fileNameWithExt=" + fileNameWithExt + ", ext="
				+ ext + ", mimeType=" + mimeType + ", thumbImgName=" + thumbImgName + ", isImage=" + isImage + "]";
	}




	
}

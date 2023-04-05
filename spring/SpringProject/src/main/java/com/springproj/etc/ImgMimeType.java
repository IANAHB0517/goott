package com.springproj.etc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class ImgMimeType {
	//  RFC 2046 에 의해 정의된 인터넷에서 사용 가능한 MimeType 들 중  MediaType  
	private static Map<String, MediaType> imgMimeType;
	
//	{
//		// instance 멤버 변수를 초기화 블럭
//	}
	
	
	static {
		// static 멤버 변수 초기화 블럭
		imgMimeType = new HashMap<String, MediaType>();
		
		imgMimeType.put("jpg", MediaType.IMAGE_JPEG);
		imgMimeType.put("jpeg", MediaType.IMAGE_JPEG);
		imgMimeType.put("gif", MediaType.IMAGE_GIF);
		imgMimeType.put("png", MediaType.IMAGE_PNG);
		imgMimeType.put("jfif", MediaType.IMAGE_JPEG);
	}
	
	public static MediaType getMediaType(String ext) {
		return imgMimeType.get(ext.toLowerCase());
	}
	
	
}

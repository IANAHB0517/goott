package com.miniproj.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IPCheck {
	private static String ipAddr;

	public static String getIPAddr() throws IOException {
		URL ipCheckUrl = new URL("https://checkip.amazonaws.com");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(ipCheckUrl.openStream()));
		// BufferedReader 한줄씩 통째로 읽어옴
		// InputStreamReader 한자씩 읽어옴
		// ipCheckUrl 만들어 놓은 url 객체
		// openStream 해당 url에 접속해서 통신할 수 있도록 세션을 열어주는 메서드 단순 개방형 페이지에서만 스트림을 얻어온다.
		ipAddr =  br.readLine();
		
		return ipAddr;
	}
}

package com.jspajax.etc;

import org.json.simple.JSONObject;

public class OutputJSONForError {
	public static String outputJson(Exception e) {
		JSONObject json = new JSONObject();
		json.put("status", "fail");
		json.put("errorMsg", e.getMessage());
		String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
		json.put("outputDate", outputDate);
		
		return json.toJSONString();
	}
}

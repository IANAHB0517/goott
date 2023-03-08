package com.jspajax.vo;

public class DepartmentVo {
	private int DEPARTMENT_ID;
	private String DEPARTMENT_NAME;
	private int MANAGER_ID;
	private int LOCATION_ID;
	
	public DepartmentVo(int dEPARTMENT_ID, String dEPARTMENT_NAME, int mANAGER_ID, int lOCATION_ID) {
		super();
		DEPARTMENT_ID = dEPARTMENT_ID;
		DEPARTMENT_NAME = dEPARTMENT_NAME;
		MANAGER_ID = mANAGER_ID;
		LOCATION_ID = lOCATION_ID;
	}

	public int getDEPARTMENT_ID() {
		return DEPARTMENT_ID;
	}

	public String getDEPARTMENT_NAME() {
		return DEPARTMENT_NAME;
	}

	public int getMANAGER_ID() {
		return MANAGER_ID;
	}

	public int getLOCATION_ID() {
		return LOCATION_ID;
	}

	@Override
	public String toString() {
		return "DepartmentVo [DEPARTMENT_ID=" + DEPARTMENT_ID + ", DEPARTMENT_NAME=" + DEPARTMENT_NAME + ", MANAGER_ID="
				+ MANAGER_ID + ", LOCATION_ID=" + LOCATION_ID + "]";
	}
	
	
	
	

}

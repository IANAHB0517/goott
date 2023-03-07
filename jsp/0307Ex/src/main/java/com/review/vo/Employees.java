package com.review.vo;

import java.sql.Date;

public class Employees {
	private int EMPLOYEE_ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String EMAIL;
	private String PHONE_NUMBER;
	private Date HIRE_DATE; // DB와 연결하기때문에 sql 패키지를 임포트 할
	private String JOB_ID;
	private float SALARY;
	private float COMMISSION_PCT;
	private int MANAGER_ID;
	private int DEPARTMENT_ID;
	private String DEPARTMENT_NAME;

	public Employees(int eMPLOYEE_ID, String fIRST_NAME, String lAST_NAME, String eMAIL, String pHONE_NUMBER,
			Date hIRE_DATE, String jOB_ID, float f, float g, int mANAGER_ID, int dEPARTMENT_ID,
			String dEPARTMENT_NAME) {
		super();
		EMPLOYEE_ID = eMPLOYEE_ID;
		FIRST_NAME = fIRST_NAME;
		LAST_NAME = lAST_NAME;
		EMAIL = eMAIL;
		PHONE_NUMBER = pHONE_NUMBER;
		HIRE_DATE = hIRE_DATE;
		JOB_ID = jOB_ID;
		SALARY = f;
		COMMISSION_PCT = g;
		MANAGER_ID = mANAGER_ID;
		DEPARTMENT_ID = dEPARTMENT_ID;
		DEPARTMENT_NAME = dEPARTMENT_NAME;
	}

	public int getEMPLOYEE_ID() {
		return EMPLOYEE_ID;
	}

	public void setEMPLOYEE_ID(int eMPLOYEE_ID) {
		EMPLOYEE_ID = eMPLOYEE_ID;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}

	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}

	public Date getHIRE_DATE() {
		return HIRE_DATE;
	}

	public void setHIRE_DATE(Date hIRE_DATE) {
		HIRE_DATE = hIRE_DATE;
	}

	public String getJOB_ID() {
		return JOB_ID;
	}

	public void setJOB_ID(String jOB_ID) {
		JOB_ID = jOB_ID;
	}

	public float getSALARY() {
		return SALARY;
	}

	public void setSALARY(int sALARY) {
		SALARY = sALARY;
	}

	public float getCOMMISSION_PCT() {
		return COMMISSION_PCT;
	}

	public void setCOMMISSION_PCT(int cOMMISSION_PCT) {
		COMMISSION_PCT = cOMMISSION_PCT;
	}

	public int getMANAGER_ID() {
		return MANAGER_ID;
	}

	public void setMANAGER_ID(int mANAGER_ID) {
		MANAGER_ID = mANAGER_ID;
	}

	public int getDEPARTMENT_ID() {
		return DEPARTMENT_ID;
	}

	public void setDEPARTMENT_ID(int dEPARTMENT_ID) {
		DEPARTMENT_ID = dEPARTMENT_ID;
	}

	public String getDEPARTMENT_NAME() {
		return DEPARTMENT_NAME;
	}

	public void setDEPARTMENT_NAME(String dEPARTMENT_NAME) {
		DEPARTMENT_NAME = dEPARTMENT_NAME;
	}

	@Override
	public String toString() {
		return "Employees [EMPLOYEE_ID=" + EMPLOYEE_ID + ", FIRST_NAME=" + FIRST_NAME + ", LAST_NAME=" + LAST_NAME
				+ ", EMAIL=" + EMAIL + ", PHONE_NUMBER=" + PHONE_NUMBER + ", HIRE_DATE=" + HIRE_DATE + ", JOB_ID="
				+ JOB_ID + ", SALARY=" + SALARY + ", COMMISSION_PCT=" + COMMISSION_PCT + ", MANAGER_ID=" + MANAGER_ID
				+ ", DEPARTMENT_ID=" + DEPARTMENT_ID + ", DEPARTMENT_NAME=" + DEPARTMENT_NAME + "]";
	}

	// DTO , VO 로 공용 사용 하기 때문에 getter setter 가 모두 필요하다

}

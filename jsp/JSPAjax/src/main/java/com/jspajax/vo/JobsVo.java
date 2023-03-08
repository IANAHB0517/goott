package com.jspajax.vo;

public class JobsVo {
	private String JOB_ID;
	private String JOB_TITLE;
	private int MIN_SALARY;
	private int MAX_SALARY;
	
	public JobsVo(String jOB_ID, String jOB_TITLE, int mIN_SALARY, int mAX_SALARY) {
		super();
		JOB_ID = jOB_ID;
		JOB_TITLE = jOB_TITLE;
		MIN_SALARY = mIN_SALARY;
		MAX_SALARY = mAX_SALARY;
	}

	public String getJOB_ID() {
		return JOB_ID;
	}

	public String getJOB_TITLE() {
		return JOB_TITLE;
	}

	public int getMIN_SALARY() {
		return MIN_SALARY;
	}

	public int getMAX_SALARY() {
		return MAX_SALARY;
	}

	@Override
	public String toString() {
		return "JobsVo [JOB_ID=" + JOB_ID + ", JOB_TITLE=" + JOB_TITLE + ", MIN_SALARY=" + MIN_SALARY + ", MAX_SALARY="
				+ MAX_SALARY + "]";
	}
	
	


}

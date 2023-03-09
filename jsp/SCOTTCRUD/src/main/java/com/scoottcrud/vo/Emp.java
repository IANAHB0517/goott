package com.scoottcrud.vo;

import java.sql.Date;

public class Emp {
	private int EMPNO;
	private String ENAME;
	private String JOB;
	private int MGR;
	private Date HIREDATE;
	private float SAL;
	private float COMM;
	private int DEPTNO;
	
	public Emp(int eMPNO, String eNAME, String jOB, int mGR, Date hIREDATE, float sAL, float cOMM, int dEPTNO) {
		super();
		EMPNO = eMPNO;
		ENAME = eNAME;
		JOB = jOB;
		MGR = mGR;
		HIREDATE = hIREDATE;
		SAL = sAL;
		COMM = cOMM;
		DEPTNO = dEPTNO;
	}

	public int getEMPNO() {
		return EMPNO;
	}

	public String getENAME() {
		return ENAME;
	}

	public String getJOB() {
		return JOB;
	}

	public int getMGR() {
		return MGR;
	}

	public Date getHIREDATE() {
		return HIREDATE;
	}

	public float getSAL() {
		return SAL;
	}

	public float getCOMM() {
		return COMM;
	}

	public int getDEPTNO() {
		return DEPTNO;
	}

	@Override
	public String toString() {
		return "Emp [EMPNO=" + EMPNO + ", ENAME=" + ENAME + ", JOB=" + JOB + ", MGR=" + MGR + ", HIREDATE=" + HIREDATE
				+ ", SAL=" + SAL + ", COMM=" + COMM + ", DEPTNO=" + DEPTNO + "]";
	}
	
	
	
}

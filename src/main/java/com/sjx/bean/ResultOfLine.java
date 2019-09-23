package com.sjx.bean;



public class ResultOfLine extends Result{

	private int lineOfNum;
	
	public ResultOfLine() {
		super();
	}
	
	public ResultOfLine(String filename,int lineOfNum) {
		super();
		this.lineOfNum = lineOfNum;
		setFilename(filename);
	}

	@Override
	public String information() {
		
		return 
				"行数：" + lineOfNum;
	}
	
	
}

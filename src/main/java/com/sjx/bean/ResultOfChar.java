package com.sjx.bean;

public class ResultOfChar extends Result{

	private int charOfNum;
	
	public ResultOfChar() {
		super();
	}
	
	public ResultOfChar(String filename,int charOfNum) {
		super();
		this.charOfNum = charOfNum;
		setFilename(filename);
	}

	@Override
	public String information() {
		
		return 
				"文本字符数：" + charOfNum;
	}
	
	
}

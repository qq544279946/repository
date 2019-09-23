package com.sjx.bean;

public class ResultOfWord extends Result{

	private int wordOfNum;
	
	public ResultOfWord() {
		super();
	}
	
	public ResultOfWord(String filename,int wordOfNum) {
		super();
		this.wordOfNum = wordOfNum;
		setFilename(filename);
	}

	@Override
	public String information() {
		
		return 
				"单词数：" + wordOfNum;
	}
	
	
}

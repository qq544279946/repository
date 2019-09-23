package com.sjx.bean;

public class ResultOfComplexity extends Result{
	private int blankLine;
	private int codeLine;
	private int commentLine;
	
	public ResultOfComplexity(String filename,int blankLine, int codeLine, int commentLine) {
		super();
		this.blankLine = blankLine;
		this.codeLine = codeLine;
		this.commentLine = commentLine;
		setFilename(filename);
	}

	public ResultOfComplexity() {
		super();
	}

	@Override
	public String information() {
		// TODO Auto-generated method stub
		String imfor = 
						"空行：" + blankLine + "\n"
						+ "代码行：" + codeLine + "\n"
						+ "注释行：" + commentLine;
		return imfor;
	}
	
	
}

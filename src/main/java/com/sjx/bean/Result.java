package com.sjx.bean;

public abstract class Result {
	//输入的文件名
	private String filename;
	//输出命令的反馈信息
	public abstract String information();
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}

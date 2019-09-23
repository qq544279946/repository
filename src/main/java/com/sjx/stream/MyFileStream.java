package com.sjx.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStreamReader;

/**
 * 该类主要是提供一个返回封装好的字符流方法
 * @author sjx
 *
 */
public class MyFileStream {
	/**
	 * 提供一个返回封装好的字符流方法
	 * @param filename 文件目录
	 * @return	字符流
	 */
	public static BufferedReader getReader(String filename) {
		BufferedReader read = null;
		try {
			File f = new File(filename);
			if(f.isDirectory() || f.isFile()) {
				read = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"GBK"));  ;
			}else {
				//
			}
			
		}catch(Exception e) {
			//
			
		}
		return read;
	}
	
}

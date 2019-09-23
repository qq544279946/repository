package com.sjx.file_handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import com.sjx.bean.Result;
import com.sjx.bean.ResultOfChar;
import com.sjx.bean.ResultOfComplexity;
import com.sjx.bean.ResultOfLine;
import com.sjx.bean.ResultOfWord;
import com.sjx.stream.MyFileStream;

/**
 * 该类主要提供关于文件统计的静态方法
 * @author sjx
 *
 */
public class FileHandle {
	
	/**
	 * 内部文件递归方法
	 * @param filename 目录
	 * @param regx	查找的对象
	 * @param list	用于存储返回的结果类
	 * @param command	命令行输入的命令列表
	 */
	private static void recursionInner(String filename,String regx,List<Result> list,boolean[] command) {
		
		File[] fs = new File(filename).listFiles(new FileFilter() {
			
			public boolean accept(File pathname) {
				if(pathname.isFile() && pathname.getName().matches(regx))
					return true;
				return false;
			}
			
		});
		
		File[] fd = new File(filename).listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory())
					return true;
				return false;
			}
			
		});
		
	
		for (File file : fs) {
			if(command[0])
				list.add(numberOfChar(file.getPath()));
			if(command[1])
				list.add(numberOfWords(file.getPath()));
			if(command[2])
				list.add(numberOfLines(file.getPath()));
			if(command[3])
				list.add(numberOfComplexity(file.getPath()));
		}
		
		for(File file :fd)
			recursionInner(file.getPath(), regx, list,command);
	}
	
	/**
	 * 提供文件递归方法
	 * @param filename	父目录或者目录下的文件名
	 * @param command	命令行输入的命令列表
	 * @return	返回一个结果类的列表
	 */
	public static ArrayList<Result> recursion(String filename,boolean command[]) {
		File f = new File(filename);
		ArrayList<Result> list = new ArrayList<>();
		
		String fn = f.getName();
		String dir = f.getParent();
		String regx = changeToGrex(fn);
		if(f.isDirectory()) {
			recursionInner(filename, ".*", list,command);
		}else if(f.isFile()){
			
			recursionInner(dir, fn, list,command);
			
		}else if(hasWildcard(fn)) {
			recursionInner(dir, regx, list,command);
		}else {
			recursionInner(dir, fn, list,command);
		}
		
		return list;
	}
	
	/**
	 * 将字符串转换成正则表达式
	 * @param s 字符串
	 * @return	String 正则表达式
	 */
	public static String changeToGrex(String s) {
		if(!hasWildcard(s)) {
			return s;
		}
		
		String str = s.replaceAll("\\*", ".+").replaceAll("\\?", ".{1}");
		
		return str;
		
	
	}
	/**
	 * 判断字符串里是否有通配符
	 * @param str 字符串
	 * @return	如果有通配符，返回true，否则返回false
	 */
	public static boolean hasWildcard(String str) {
		return str.matches(".*[*|?]+.*");
		
	}
	
	/**
	 * 一个统计字符数的结果类
	 * @param filename	目录
	 * @return	返回一个统计字符数的结果类
	 */
	public static Result numberOfChar(String filename) {
		int charNum= 0;
		//建立一个文本输入流
		BufferedReader reader = MyFileStream.getReader(filename);
		if(reader != null) {
			String buff = null;
			try {
				while((buff = reader.readLine())!= null) {
					charNum += buff.replaceAll("\\s", "").length();
				}
				
				reader.close();
				
			} catch (IOException e) {
				//
				e.printStackTrace();
			}
			
			return new ResultOfChar(filename,charNum);
		}
		return null;
		
	}
	
	/**
	 * 一个统计单词数的结果类
	 * @param filename	目录
	 * @return	返回一个统计单词数的结果类
	 */
	public static Result numberOfWords(String filename) {
		int wordsNum = 0;
		BufferedReader reader = MyFileStream.getReader(filename);
		if(reader == null) 
			return null;
		String buff = null;
		try {
			while((buff = reader.readLine())!= null) {
				Pattern p = Pattern.compile("[a-zA-Z]+");
				Matcher m = p.matcher(buff);
				while(m.find()) {
					wordsNum++;
				}
				
			}
			reader.close();
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
		
		return new ResultOfWord(filename,wordsNum);
		
	}
	
	/**
	 * 一个统计行数的结果类
	 * @param filename	目录
	 * @return	返回一个统计行数的结果类
	 */
	public static Result numberOfLines(String filename) {
		int lineNum = 0;
		BufferedReader reader = MyFileStream.getReader(filename);
		if(reader == null)
			return null;
		String buff = null;
		try {
			
			while((buff = reader.readLine())!= null) {
				lineNum++;	
			}
			reader.close();
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
		
		return new ResultOfLine(filename,lineNum);
	}
	
	/**
	 * 一个统计空行，代码行，注释行的结果类
	 * @param filename	目录
	 * @return	返回一个统计空行，代码行，注释行的结果类
	 */
	public static Result numberOfComplexity(String filename) {
		int blankLine = 0;//空行
		int codeLine = 0;//代码行
		int commentLine = 0;//注释行
		int line = 0;//总行数
		boolean isMulticomm = false;
		
		BufferedReader reader = MyFileStream.getReader(filename);
		if(reader == null)
			return null;
		String buff = null;
		try {
			while((buff = reader.readLine())!= null) {
				line++;//总行数+1
				
				if(!isMulticomm) {
					if(buff.matches("^\\s*$") || buff.trim().matches("[{|}]")) {
						//空行+1
						blankLine++;
					}else if(buff.trim().matches("[{}]{0,1}//.*")) {
						
						//注释行+1
						commentLine++;
					}else if(buff.trim().startsWith("/*")) {
						
						//注释行+1
						commentLine++;
						// /*注释开始的行
						isMulticomm = true;
					}
						
				}else if(buff.startsWith("*/")){
					//还在*/的注释范围内
					commentLine++;
					isMulticomm = false;
				}else {
					
					//多行注释的范围内
					commentLine++;
				}
				
			}
			reader.close();
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
		
		codeLine = line - blankLine - commentLine;
		
		return new ResultOfComplexity(filename,blankLine, codeLine, commentLine);
		
		
	}
	
	
}

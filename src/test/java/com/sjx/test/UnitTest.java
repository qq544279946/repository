package com.sjx.test;

import java.util.List;

import org.junit.Test;

import com.sjx.bean.Result;
import com.sjx.file_handle.FileHandle;
import com.sjx.main.Main;
/**
 * 用于测试覆盖率和bug的测试类
 * @author sjx
 *
 */
public class UnitTest{
	private Main m = new Main();
	
	/*private void testBase(String filename) {
		Result r ;
		System.out.println(filename);
		r = FileHandle.numberOfChar(filename);
		if(r != null)
			System.out.println(r.information());
		else
			System.out.println("文件不存在");
		r = FileHandle.numberOfLines(filename);
		if(r != null)
			System.out.println( r.information());
		else
			System.out.println("文件不存在");
		r = FileHandle.numberOfWords(filename);
		if(r != null)
			System.out.println(r.information());
		else
			System.out.println("文件不存在");
		r = FileHandle.numberOfComplexity(filename);
		if(r != null)
			System.out.println(r.information());
		else
			System.out.println("文件不存在");
		
	}
	
	@Test
	public void testBaseFunction() {
		
		String filename = "D:\\qweqe.c";
		testBase(filename);
		
		String filename1 = "D:\\text.c";
		testBase(filename1);
	}
	
	
	@Test
	public void testRecursive() {
		String filename = "D://123//t*.txt";
		testRecursiveInner(filename);
		
		filename = "D://123//text.txt";
		testRecursiveInner(filename);
	}
	
	private void testRecursiveInner(String filename) {
		boolean[] c = new boolean[] {true,true,true,true};
		List<Result> rs = FileHandle.recursion(filename, c);
		for (Result r : rs) {
			if(r == null)
				System.out.println("文件不存在");
			System.out.println(r.getFilename());
			System.out.println(r.information());
		}
	}
	*/
	
	
	/**
	 * 测试方法
	 */
	@Test
	public void testMain() {
		//测试参数错误
		String[] args9 = {"-m","-c","-l","-a","D://123//texqqq.txt"};
		m.main(args9);
		
		//测试文件不存在
		String[] args4 = {"-w","-c","-l","-a","D://123//texqqq.txt"};
		m.main(args4);
		
		//测试文件的所有命令
		String[] args = {"-w","-c","-l","-a","D://123//text.txt"};
		m.main(args);
		
		//测试调用递归且使用通配符后文件的所有命令
		String[] args2 = {"-s","-w","-c","-l","-a","D://123//te*.txt"};
		m.main(args2);
		
		String[] args5 = {"-s","-w","-c","-l","-a","D://123//tqqqq*.txt"};
		m.main(args5);
		
		//测试调用递归文件的所有命令
		String[] args8 = {"-s","-w","-c","-l","-a","D://123//tq.txt"};
		m.main(args8);
		
		String[] args6 = {"-s","-w","-c","-l","-a","D://123//qqq.txt"};
		m.main(args6);
		
		//测试调用递归当前目录下所有文件的所有命令
		String[] args7 = {"-s","-w","-c","-l","-a","D://123"};
		m.main(args7);
		
		//测试窗体
		String[] args3 = {"-x"};
		m.main(args3);
		
		//测试命令输入格式错误
		String[] args10 = {"-x","-l"};
		m.main(args10);
		
	}
}
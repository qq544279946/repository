package com.sjx.main;

import java.util.List;

import org.apache.commons.cli.CommandLine;

import com.sjx.bean.Result;
import com.sjx.file_handle.FileHandle;
/**
 * 主方法，用于处理命令行输入的命令，和界面信息的显示
 * @author sjx
 *
 */
public class Main {
	/**
	 * 处理命令，main
	 * @param args	参数列表
	 */
	public static void main(String[] args) {
	
		CommandLine command = null;
		if(args.length > 1) {
			String[] ar = new String[args.length-1];
			for(int i = 0;i < ar.length;i++)
				ar[i] = args[i];
			
			CommandLineUtil c  = new CommandLineUtil(ar);
			command = c.getCommandLine();
		}
		
		try {
			
			//
			Result r = null;
			if(args[0].equals("-x")) {
				if(args.length >= 2) {
					System.out.println("输入的参数有误		-x：这个参数单独使用");
				}else {
					new MyGUI();
				}
				
			}else {
				if(command.hasOption("s")) {
					boolean[] flag = new boolean[4];
					if(command.hasOption("c"))
						flag[0] = true;
					if(command.hasOption("w"))
						flag[1] = true;
					if(command.hasOption("l"))
						flag[2] = true;
					if(command.hasOption("a"))
						flag[3] = true;
					List<Result> rs = FileHandle.recursion(args[args.length - 1],flag);
					for (Result result : rs) {
						
						System.out.println(result.getFilename() + '\n' + result.information());
					}
				}else {
					if(command.hasOption("c")) {
						r = FileHandle.numberOfChar(args[args.length - 1]);
						if(r !=null) {
							System.out.println(r.getFilename() + '\n' + r.information());
						}else {
							System.out.println("文件不存在");
						}
						
					}
					
					if(command.hasOption("w")) {
						r = FileHandle.numberOfWords(args[args.length - 1]);
						
						if(r !=null) {
							System.out.println(r.getFilename() + '\n' + r.information());
						}else {
							System.out.println("文件不存在");
						}
					}
					
					if(command.hasOption("l")) {
						r = FileHandle.numberOfLines(args[args.length - 1]);
						if(r !=null) {
							System.out.println(r.getFilename() + '\n' + r.information());
						}else {
							System.out.println("文件不存在");
						}
					}
						
					if(command.hasOption("a")) {
						r = FileHandle.numberOfComplexity(args[args.length - 1]);
						if(r !=null) {
							System.out.println(r.getFilename() + '\n' + r.information());
						}else {
							System.out.println("文件不存在");
						}
					}
				}
			}
			//
		}catch(Exception e) {
			System.out.println("输入的参数有误");
			System.out.println("参数列表：-w -c -l -a -s -x");
		}
		
		
		
		
		
		
		
		
	}
}

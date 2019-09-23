package com.sjx.main;

import org.apache.commons.cli.*;


public class CommandLineUtil {
	private static final Options opts = new Options();
	private CommandLine commandLine ;
	static {
		opts.addOption("c",false,"char");
		opts.addOption("w",false,"word");
		opts.addOption("l",false,"line");
		opts.addOption("s",false,"recursion");
		opts.addOption("a",false,"complexity data");
		opts.addOption("x",false,"GUI");
		
	}
	
	public CommandLineUtil(String[] args) {
		CommandLineParser parser = new DefaultParser();
		try {
			commandLine = parser.parse(opts, args);
		} catch (ParseException e) {
			//
			
		}
	}

	

	public CommandLine getCommandLine() {
		return commandLine;
	}
	
	
}

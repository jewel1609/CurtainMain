package com.ktds.curtain.history.vo;

public class BuildDescription {
	
	public static String get(String format, String ... args) {
		
		String desc = String.format(format, args);
		return desc;
	}

}

package net.steepout.http;

import java.util.HashMap;

public class Attributes extends HashMap<String,String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -605939549450050825L;

	public void put(String str){
		if(str.trim().equals("")||!str.contains(":"))
			return;
		String arg[] = str.split(":");
		if(arg[1].trim().equals("")){
			arg[1]=null;
		}
		put(arg[0].trim(),arg[1].trim());
	}
	
	public String put(String key,String value){
		return super.put(key.toLowerCase(), value);
	}
	
	public String get(String key){
		return super.get(key.toLowerCase());
	}

	protected synchronized String getHeader() {
		return get("request_header");
	}

	protected synchronized void setHeader(String header) {
		put("request_header:"+header);
	}
}
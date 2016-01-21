package net.steepout.http;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import config.Config;

public class ServerSystem{
	static Properties property;
	static{
		property = new Properties();
		try {
			property.load(Config.createStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logger.i("Server config loaded", ServerSystem.class);
	}
	
	static boolean isConsoleLog(){
		return property.get("console_log").equals("true");
	}
	
	static boolean isFileLog(){
		return property.get("file_log").equals("true");
	}
	
	static File logDirectory(){
		return new File(property.getProperty("log_file_dir"));
	}
	
	public static File findByRequest(String name){
		String dir = name.substring(0,name.lastIndexOf('/')+1);
		File directory = new File(property.getProperty("target_file_dir")+dir);
		name = name.replace(dir, "").trim();
		if(name.equals("")||name.equals("index")){
			for(File x:directory.listFiles()){
				if(x.isFile()&&(x.getName().startsWith("index.")||x.getName().equalsIgnoreCase("index")))
					return x;
			}
		}else{
			for(File x:directory.listFiles()){
				if(x.isFile()&&x.getName().equals(name))
					return x;
			}
		}
		return null;
	}
}
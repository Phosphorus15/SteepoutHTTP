package net.steepout.http;

public class Logger{
	
	public static void i(String name,Class<? extends Object> clazz){
		String content = "[INFO] ["+Thread.currentThread().getName()+"] ["+clazz.getName()+"] "+name;
		write(content);
	}
	
	public static void i(String name,Object obj){
		i(name,obj.getClass());
	}
	
	public static void e(String name,Class<? extends Object> clazz){
		String content = "[ERROR] ["+Thread.currentThread().getName()+"] ["+clazz.getName()+"] "+name;
		write(content);
	}
	
	public static void e(String name,Object obj){
		e(name,obj.getClass());
	}
	
	private static void write(String name){
		if(ServerSystem.isConsoleLog())
			System.out.println(name);
	}
	
}
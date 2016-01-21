package net.steepout.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HTTProtocol{
	
	String version;
	
	private HTTProtocol(){
		version = "1.1";
	}
	
	public static HTTProtocol newInstance(){
		return new HTTProtocol();
	}
	
	Attributes createRequest(Socket socket) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String tmp="";
		Attributes attr = new Attributes();
		attr.setHeader(reader.readLine());
		while((tmp=reader.readLine())!=null&&!tmp.trim().equals("")){
			attr.put(tmp);
		}
		return attr;
	}
	
	Attributes parseRequest(Attributes attr){
		String header = attr.getHeader();
		String arg[] = header.split(" ");
		attr.put("request_method",arg[0]);
		attr.put("request_object",arg[1]);
		attr.put("http_version",arg[2]);
		return attr;
	}
	
	ServerSocket commonServer() throws IOException{
		return new ServerSocket(80);
	}
	
	String genRespond(HTTPStatus status,MimeType mime){
		Logger.i("Respond with code "+status.id+" generated", this);
		String respond = "HTTP/"+version+" "+status.id+" "+status.name+"\n";
		respond+="Server: SteepoutHTTP/1.0\n";
		respond+="Date: "+new Date().toString()+"\n";
		respond+="Content-Type: "+mime.mine;
		return respond;
	}
	
	void sendFile(PrintStream ps , File f) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String tmp = "";
		while((tmp=reader.readLine())!=null){
			ps.println(tmp);
		}
	}
	
	MimeType analyzeMime(String name){
		if(!name.contains("."))
			return MimeType.PLAIN_TEXT;
		String type = name.substring(name.lastIndexOf('.'),name.length());
		if(type.equalsIgnoreCase("txt")||type.equalsIgnoreCase("log")){
			return MimeType.PLAIN_TEXT;
		}else if(type.equalsIgnoreCase("html")||type.equalsIgnoreCase("jsp")){
			return MimeType.HTML;
		}else if(type.equalsIgnoreCase("gif")){
			return MimeType.GIF;
		}else if(type.equalsIgnoreCase("jpg")){
			return MimeType.JPEG;
		}else{
			return MimeType.PLAIN_TEXT;
		}
	}
	
}
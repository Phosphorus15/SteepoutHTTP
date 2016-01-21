package net.steepout.http;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThread extends Thread{
	
	HTTProtocol protocol;
	
	Socket target;
	
	PrintStream out;
	
	public ClientThread(Socket socket) throws IOException{
		Logger.i("Client connected["+socket.toString()+"]", this);
		protocol = HTTProtocol.newInstance();
		target = socket;
		out = new PrintStream(socket.getOutputStream());
	}
	
	public void run(){
		try{
			Logger.i("Client thread started ("+target.getInetAddress().getHostAddress()+")", this);
			Attributes attr = protocol.createRequest(target);
			attr = protocol.parseRequest(attr);
			Logger.i("Resource requested :"+attr.get("request_object")+" ("+target.getInetAddress().getHostAddress()+")", this);
			File f = ServerSystem.findByRequest(attr.get("request_object"));
			if(f==null){
				String a =protocol.genRespond(HTTPStatus.CLIENT_ERROR, MimeType.PLAIN_TEXT);
				target.close();
				return;
			}
			MimeType mime = protocol.analyzeMime(f.getName());
			String a =protocol.genRespond(HTTPStatus.OK, mime);
			out.println(a);
			out.println();
			protocol.sendFile(out, f);
			target.close();
			Logger.i("Response ended ("+target.getInetAddress().getHostAddress()+")", this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
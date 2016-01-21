package net.steepout.http;

import java.io.*;

import java.net.*;

public class MainThread implements Runnable{
	public static void main(String[] args) throws IOException{
		HTTProtocol protocol = HTTProtocol.newInstance();
		try {
			Class.forName("net.steepout.http.ServerSystem");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Thread(new MainThread()));
		Logger.i("Server started,Protocol version : "+protocol.version, MainThread.class);
		ServerSocket ss = protocol.commonServer();
		Logger.i("Server socket established,waiting for client", MainThread.class);
		while(true){
			Socket s = ss.accept();
			new ClientThread(s).start();
		}
	}
	
	@Override
	public void run() {
		Logger.i("Server is shutting down...", this);
	}
}
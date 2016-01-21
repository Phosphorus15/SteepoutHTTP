package net.steepout.http;

public enum MimeType{
	HTML("text/html") , PLAIN_TEXT("text/plain") , JPEG("image/jpeg") , GIF("image/gif");
	String mine;
	MimeType(String t){
		mine = t;
	}
}
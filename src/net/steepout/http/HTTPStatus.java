package net.steepout.http;

public enum HTTPStatus{
	CONTINUE(115,"Continue") , OK(200,"OK") , REDIRECT(304,"Moved Temporarily") , CLIENT_ERROR(404,"Error") , SERVER_ERROR(500,"Error") ;
	int id;
	String name;
	HTTPStatus(int id,String n){
		this.id = id;
		this.name = n;
	}
}
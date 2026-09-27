package com.merkit.exception;

public class ArticleNotPublishException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ArticleNotPublishException(){
		super();
	}

	public ArticleNotPublishException(String msg){
		super(msg);
	}

}

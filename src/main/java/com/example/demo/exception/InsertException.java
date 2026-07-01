package com.example.demo.exception;

//登録例外
public class InsertException extends RuntimeException{

	public InsertException(String errorMassage) {
		
		super(errorMassage);
	}
}

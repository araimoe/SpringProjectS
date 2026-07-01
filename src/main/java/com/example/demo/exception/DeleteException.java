package com.example.demo.exception;

//削除例外
public class DeleteException extends RuntimeException{

	public DeleteException(String errorMassage) {
		
		super(errorMassage);
	}
}

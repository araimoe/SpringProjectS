package com.example.demo.exception;

//ログインエラー
public class LoginException extends RuntimeException{

	public LoginException(String loginError) {
		
		super(loginError);
	}
}

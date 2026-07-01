package com.example.demo.exception;

/*
 * 新しく作成した例外を例外と認識させるためのクラス
 * この後に共通例外処理で処理が行われる
 */
public class MemberNotFoundException extends RuntimeException{

	 public MemberNotFoundException(String message) {
		 
	        super(message);
	    }
}

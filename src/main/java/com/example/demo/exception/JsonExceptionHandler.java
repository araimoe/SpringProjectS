package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.controller.JsonController;

//JSON方式での例外処理のときに使う assignableTypesは同じ例外を使うときに使う
@RestControllerAdvice(assignableTypes = JsonController.class)
public class JsonExceptionHandler {

	//独自例外が来れば実行
	 @ExceptionHandler(MemberNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleNotFound(MemberNotFoundException e) {
		 
		 //serviceで感知したエラーを例外専用DTOに詰める
		 ErrorResponse error = new ErrorResponse();
		 error.setCode("NOT_FOUND");
		 error.setMessage(e.getMessage());
		 error.setTimestamp(LocalDateTime.now());
		 
		 //HttpStatus.NOT_FOUN：画面に404（見つかりませんでした）を返すイメージ
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	 }
	 
	 //更新エラー
	 @ExceptionHandler(UpdateException.class)
	    public ResponseEntity<ErrorResponse> UpdateNotFound(UpdateException e) {
		 
		 //serviceで感知したエラーを例外専用DTOに詰める
		 ErrorResponse error = new ErrorResponse();
		 error.setCode("UPDATE_ERROR");
		 error.setMessage(e.getMessage());
		 error.setTimestamp(LocalDateTime.now());
		 
		 //サーバー系のためHTTPステータスは404でOK
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	 }
	 
	 //登録エラー
	 @ExceptionHandler(InsertException.class)
	    public ResponseEntity<ErrorResponse> InsertNotFound(InsertException e) {
		 
		 //serviceで感知したエラーを例外専用DTOに詰める
		 ErrorResponse error = new ErrorResponse();
		 error.setCode("INSERT_ERROR");
		 error.setMessage(e.getMessage());
		 error.setTimestamp(LocalDateTime.now());
		 
		 //サーバー系のためHTTPステータスは404でOK
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	 }
	 
	 //削除エラー
	 @ExceptionHandler(DeleteException.class)
	    public ResponseEntity<ErrorResponse> DeleteNotFound(DeleteException e) {
		 
		 //serviceで感知したエラーを例外専用DTOに詰める
		 ErrorResponse error = new ErrorResponse();
		 error.setCode("INSERT_ERROR");
		 error.setMessage(e.getMessage());
		 error.setTimestamp(LocalDateTime.now());
		 
		 return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	 }
	 
	 //システムエラー
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> SystemNotFound(Exception e) {
		 
		 //serviceで感知したエラーを例外専用DTOに詰める
		 ErrorResponse error = new ErrorResponse();
		 error.setCode("INTERNAL_SERVER_ERROR");
		 error.setMessage("システムエラー");
		 error.setTimestamp(LocalDateTime.now());
		 
		 return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
}
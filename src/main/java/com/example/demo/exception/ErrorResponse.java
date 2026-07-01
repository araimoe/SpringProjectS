package com.example.demo.exception;

import java.time.LocalDateTime;

import lombok.Data;

//例外コードと例外メッセージを保管する
//JSONの形をプロジェクト全体で統一させたいために使う

@Data
public class ErrorResponse {

	private String code;
	private String message;
	private LocalDateTime timestamp;
}

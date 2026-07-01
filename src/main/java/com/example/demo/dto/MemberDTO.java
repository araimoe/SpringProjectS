package com.example.demo.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//検索・画面一覧表示・ソート機能・ページングを行うDTO

@Data
public class MemberDTO {
	
	private String userId;
	private String name;
	private String kana;
	private String email;
	private String password;
	private String gender;
	private String phoneNumber;
	private String postalCode;
	private String address;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	
	private Integer hobby;
	private String hobbyName;
	
	//ページング管理
	private int offset;
	
	//JSON用のページ数を管理
	private Integer page;
	
	//ソート機能管理
	private String sort;
	private String order;
}

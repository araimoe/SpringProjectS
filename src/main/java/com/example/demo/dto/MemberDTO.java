package com.example.demo.dto;

import java.time.LocalDate;

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
	private LocalDate  birthday;
	
	//ページング管理
	private int offset;
	
	//ソート機能管理
	private String sort;
	private String order;
}

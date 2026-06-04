package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

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
	
	//ページング
	private Integer offset;
}

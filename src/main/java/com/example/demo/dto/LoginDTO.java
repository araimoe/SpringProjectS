package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginDTO {

	private String userId;
	
	private String name;
	
	@NotBlank(message = "Eメールを入力してください" )
	@Email(message="メールの形式が不正です。")
	private String email;
	
	@NotBlank(message = "パスワードを入力してください" )
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).*$", message = "英字と数字を含めてください")
	@Size(min = 8, max = 20, message = "8文字以上20文字以内で入力してください")
	private String password;
}

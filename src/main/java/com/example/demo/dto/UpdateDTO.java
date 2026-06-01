package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateDTO {

	private String userId;
	
	@NotBlank(message = "名前を入力してください" )
	@Pattern(regexp = "^[ぁ-んァ-ヶ一-龥々ー　]+$", message = "形式が不正です。全角で入力してください。")
	private String name;
	
	@NotBlank(message = "名前（フリガナ）を入力してください" )
	@Pattern(regexp = "^[ァ-ヶ-]+$", message="形式が不正です。カタカナで入力してください" )
	private String kana;
	
	@NotBlank(message = "Eメールを入力してください" )
	@Email(message="メールの形式が不正です。")
	private String email;
	
	@NotBlank(message = "パスワードを入力してください" )
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).*$", message = "英字と数字を含めてください")
	@Size(min = 8, max = 20, message = "8文字以上20文字以内で入力してください")
	private String password;
	
	@NotBlank(message = "性別を入力してください" )
	private String gender;
	
	@NotBlank(message = "電話番号を入力してください" )
	@Pattern(regexp = "^[0-9-]+$",message = "半角数字で入力してください" )
	private String phoneNumber;
	
	@NotBlank(message = "郵便番号を入力してください" )
	@Pattern(regexp = "^[0-9-]+$",message = "半角数字で入力してください" )
	private String postalCode;
	
	@NotBlank(message = "住所を入力してください" )
	private String address;
	
	@NotNull(message = "誕生日を選んでください" )
	private LocalDate  birthday;
}

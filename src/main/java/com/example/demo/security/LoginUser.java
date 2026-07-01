package com.example.demo.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dto.LoginDTO;

//SpringSecurityに値を返すためのクラス
//【UserDetails】と依存関係があることでSpringSecurityに値を渡す事ができる

public class LoginUser implements UserDetails {

	private LoginDTO dto;
	
	//LoginDTOから結果を取り出す
	public LoginUser(LoginDTO dto) {
	    this.dto = dto;
	}
	
	//UserDetailsにあるユーザー権限の付与を管理する
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//今回は権限の設定はないため空のリストを返す
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
	
		return dto.getPassword();
	}

	@Override
	public String getUsername() {
		
		return dto.getName();
	}

}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.exception.LoginException;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.security.LoginUser;

/*
 * 【LoginUserDetailsService】はSpringSecurityを検知すると自動的に実行される
 * UserDetails：ユーザー情報を認証させる
 * 
 */

@Service
public class LoginUserDetailsService implements UserDetailsService{

	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		
		LoginDTO user = loginMapper.securityLogin(email);

		//エラー処理
        if (user == null) {
            throw new LoginException("ユーザーが存在しません");
        }

        //UserDetailsと依存関係のあるクラスに入れてあげ、Springsecurityに返す
        return new LoginUser(user);
	}

}

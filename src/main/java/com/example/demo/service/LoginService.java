package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.LoginException;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.mapper.UserMapper;

@Service
public class LoginService {

	//mapperを使う場合は【@Autowired】を使うこと
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDTO login(LoginDTO logindto) {

		//UserDTOに結果を入れる
		UserDTO dto = loginMapper.login(logindto);

		if (dto == null) {
			throw new LoginException("ログインできませんでした。もう一度確認の上、ログインをしてください。");
		}

		return dto;
	}

	public int insert(UserDTO dto) {

		//登録する前にパスワードをハッシュ化させる
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		int result = userMapper.insert(dto);

		return result;
	}
}

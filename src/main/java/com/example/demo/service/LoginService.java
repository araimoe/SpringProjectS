package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;

@Service
public class LoginService {

	//mapperを使う場合は【@Autowired】を使うこと
	@Autowired
    private UserMapper userMapper;
	
	public UserDTO login(LoginDTO form) {
		
		UserDTO dto = null;
		
		//UserDTOに結果を入れる
		dto = userMapper.login(form);
		
		return dto;
	}
	
	public int insert(UserDTO dto) {
		
		
		int result = userMapper.insert(dto);
		
		
		return result;
	}
}

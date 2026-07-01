package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.InsertDTO;
import com.example.demo.dto.UserDTO;

@Mapper
public interface  UserMapper {
	
	//登録
	int insert(UserDTO dto);
	
	//JSON方式登録
	int insertJson(InsertDTO insertDto);
}

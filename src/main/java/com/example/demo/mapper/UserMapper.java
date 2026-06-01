package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;

@Mapper
public interface  UserMapper {

	//ログイン入力
	//【結果を渡す場所】＋【メソッド名】＋【入力値】
	//入力値が複数の場合は@Prameをつける
	UserDTO login(LoginDTO form);
	
	//登録
	int insert(UserDTO sto);
}

package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;

@SpringBootTest
class UserServiceTest {

	@Autowired
    LoginService loginService;
	
	@Test
	void ログイン成功() {
		
		LoginDTO loginDto = new LoginDTO();
		UserDTO userDto = new UserDTO();
		
		String email = "taro.yamada@example.com";
		String password = "pass1234";
		
		loginDto.setEmail(email);
		loginDto.setPassword(password);
		
		
		userDto = loginService.login(loginDto);
		
		
		System.out.println(userDto.getUserId());
		System.out.println(userDto.getName());
		assertNotNull(userDto);
	}

//	@Test
//	void 登録成功() {
//		
//		UserDTO dto = new UserDTO();
//		dto.setName("大原優香");
//		dto.setKana("オオハラユウカ");
//		dto.setEmail("oohara@example.com");
//		dto.setPassword("sayaka0711");
//		dto.setBirthDate(LocalDate.of(2000, 5, 22));
//		
//		
//		int result = 0;
//		
//		result = loginService.insert(dto);
//		
//		if(result == 0) {
//			
//			System.out.println("登録失敗");
//			
//		}else {
//			System.out.println("登録成功");
//		}
//		
//	}
	
}

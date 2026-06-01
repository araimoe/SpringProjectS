package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.LoginService;

//【@WebMvcTest】はコントローラ側をテストするときに使う
//【@WebMvcTest】＋(テストを行うcontrollerクラスを指定）
@WebMvcTest(LoginController.class)
class UserControllerTest {
	
	//疑似サーバー
	@Autowired
	private MockMvc mockMvc;
	
	//仮のサービス・エラーを防ぐためのもの
	@MockBean
    LoginService loginService;
	
	@Test
	void ログインコントローラー接続成功() throws Exception{
		
		 mockMvc.perform(get("/login"))
	   //→疑似サーバーでGET /login を実行（http://localhost:8080/loginにいくのと同じになる）
         .andExpect(status().isOk())
       //→正常に遷移しているか
         .andExpect(view().name("Login"));
	   //→Login.htmlに遷移したか
	}

	@Test
	void 登録画面移動() throws Exception {
		 mockMvc.perform(post("/insert"))
		 .andExpect(status().isOk())
		 .andExpect(view().name("insert"));
	}
	
	@Test
	void 登録の形式チェック() throws Exception {
		
		//paramはHTML側で入力したのと同じ状態になる
		 mockMvc.perform(post("/insert-click")
		        .param("name", "")
		        .param("kana", "あいうえお")
		        .param("email", "ootuka@examplecom")
		        .param("password", "pass12")
		        .param("birthDate", "1994-09-11"))
		       .andExpect(status().isOk())
               .andExpect(model().hasErrors())
              //→controller側のDTOがエラーを持っていたら 
               .andExpect(view().name("insert"));
	}
}

package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.InsertDTO;
import com.example.demo.exception.InsertException;
import com.example.demo.service.MemberListServise;

@WebMvcTest(JsonController.class)
class JsonControllerTest {

	//疑似サーバー
		@Autowired
		private MockMvc mockMvc;
		
		//仮のサービス・エラーを防ぐためのもの
		@MockBean
		MemberListServise memberServise;
		
//		@Test
//		void 登録の形式チェック() throws Exception {
//			
//			//この部分でJSON入力をする ※ブラウザから送信されるJSONを再現している
//			  String json = """
//				        {
//				            "name":"飯田昂輝",
//				            "kana":"イイダ コウキ",
//				            "gender":"男性",
//				            "birthday":"1992-04-12",
//				            "email":"iida@example.com",
//				            "password":"pass1234",
//				            "phoneNumber":"09011112222",
//				            "postalCode":"0600001",
//				            "address":"北海道札幌市中央区北一条西1-1",
//				            "hobby":"2"
//				        }
//				        """;
//			  			//controller呼び出し
//				        mockMvc.perform(post("/insertSuccessJson")
//				        		//JSON送信を行う
//				                .contentType(MediaType.APPLICATION_JSON)
//				                //入力値を入れ込む
//				                .content(json))
//				                .andExpect(status().isOk())
//				                //異常系があれば「-1」が返って来るイメージ
//				                .andExpect(jsonPath("$.result").value(-1))
//				                //提示したエラー項目があるかを確認、あればTRUEを返す
//				                .andExpect(jsonPath("$.errors.kana").exists());
//				    }

//		@Test
//		void 例外処理実行() throws Exception {
//			
//
//			//このメソッドが呼ばれた瞬間に例外を出させている。
//			//any(MemberDTO.class)：DTOの中身はなんでも良いとするために入れる（DTOの中身はそこまで重要ではないため）
//		    when(memberServise.serchMember(any(MemberDTO.class)))
//		            .thenThrow(
//		                    new MemberNotFoundException("接続エラーです"));
//
//		    mockMvc.perform(post("/memberListJson")
//		            .contentType(MediaType.APPLICATION_JSON)
//		         //空のJSONを送っている。(検索結果の中身が入っていない状態)
//		         //   →DTOに入る
//		            .content("{}"))
//		    		//リクエスト内容・レスポンス内容を表示 求めている値や何が返ってきているかを確認
//		            .andDo(print())
//		       //Httpステータスの確認をする→求めている例外が返ってきているかを確認     
//		            .andExpect(status().isNotFound())
//		       //エラーコードとエラーメッセージを入れ込む
//		            .andExpect(jsonPath("$.code")
//		                    .value("NOT_FOUND"))
//		            .andExpect(jsonPath("$.message")
//		                    .value("接続エラーです"));
		    
//		}
		
		@Test
		void 登録例外() throws Exception {
			
			//登録・更新例外を作成するときもDTOに値をセットする。
			//※バリテーションとの競合を避けるため
			  String json = """
	        {
	            "name":"飯田昂輝",
	            "kana":"イイダコウキ",
	            "gender":"男性",
	            "birthday":"1992-04-12",
	            "email":"iida@example.com",
	            "password":"pass1234",
	            "phoneNumber":"09011112222",
	            "postalCode":"0600001",
	            "address":"北海道札幌市中央区北一条西1-1",
	            "hobby":"2"
	        }
	        """;
	
	    when(memberServise.insertJson(any(InsertDTO.class)))
	            .thenThrow(
	                    new InsertException("登録に失敗しました"));

	    mockMvc.perform(post("/insertSuccessJson")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(json))
	    		//リクエスト内容・レスポンス内容を表示 求めている値や何が返ってきているかを確認
	            .andDo(print())
	       //Httpステータスの確認をする→求めている例外が返ってきているかを確認     
	            .andExpect(status().isNotFound())
	       //エラーコードとエラーメッセージを入れ込む
	            .andExpect(jsonPath("$.code")
	                    .value("INSERT_ERROR"))
	            .andExpect(jsonPath("$.message")
	                    .value("登録に失敗しました"));
	    
	}
}

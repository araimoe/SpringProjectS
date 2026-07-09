package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberListServise;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

	//疑似サーバー
		@Autowired
		private MockMvc mockMvc;
			
		@MockBean
		MemberListServise memberServise;	
	
	@Test
	//SpringSecurityが実行されずに画面遷移の確認ができる
	@WithMockUser
	void レシポンス検証() throws Exception {
		
		MemberDTO member1 = new MemberDTO();
		member1.setName("山田太郎");
		
		List<MemberDTO> memberList = List.of(member1);
		
		//any(MemberDTO.class)：MemberDTOであればなんでもOKということ
	    when(memberServise.serchMember(any(MemberDTO.class))).thenReturn(memberList);
		
	    mockMvc.perform(get("/selectMember"))
	    .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("MemberList"))                        // ①画面名の検証
        .andExpect(model().attributeExists("memberList"))            // ②Modelにデータがあるか
        .andExpect(model().attribute("memberList", memberList));     // ③Modelの中身の検証
	}
}

package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.MemberDTO;

@SpringBootTest
class MemberListServiseTest {

	@Autowired
	MemberListServise memberServise;
	

//	@Test
//	void IDから一件取得() {
//		
//		String userId = "U001";
//		
//		MemberDTO dto = memberServise.memberList(userId);
//		
//		System.out.println(dto);
//		assertNotNull(dto);
//	}

//	@Test
//	void 会員情報更新() {
//		
//		UpdateDTO update = new UpdateDTO();
//		
//		update.setUserId("U008");
//		update.setName("飯田晴美");
//		update.setKana("イイダハルミ");
//		update.setGender("女性");
//		update.setBirthday(LocalDate.of(1992, 2, 25));
//		update.setEmail("ootsuka@example.com");
//		update.setPassword("harumi0225");
//		update.setPhoneNumber("09050007777");
//		update.setPostalCode("980-0021");
//		update.setAddress("宮城県仙台市青葉区中央1-8-1");
//		
//		int i = 0;
//		
//		i = memberServise.memberUpdate(update);
//		
//		if(i != 0) {
//			
//			System.out.println("更新成功！");
//			
//		}else {
//			
//			System.out.println("更新失敗…");
//		}
// 	}
	
	@Test
	void 曖昧検索() {
		
		MemberDTO dto = new MemberDTO();
		dto.setName("");
		dto.setGender("");
		dto.setEmail("");
		dto.setAddress("");
		
		dto.setOffset(10);;
		
		List<MemberDTO> list = memberServise.serchMember(dto);
		
		System.out.println(list);
		//assertFalse(list.isEmpty());
		assertNotNull(list);
	}
}

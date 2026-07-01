package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.MemberDTO;

@SpringBootTest
class MemberListServiseTest {

	@Autowired
	MemberListServise memberServise;
	

	@Test
	void IDから一件取得() {
		
		MemberDTO member = new MemberDTO();
		
		member.setUserId("U001");
		
		MemberDTO dto = memberServise.memberList(member);
		
		System.out.println(dto);
		assertNotNull(dto);
	}

//	@Test
//	void 会員情報更新() {
//		
//		UpdateDTO update = new UpdateDTO();
//		
//		update.setUserId("U015");
//		update.setName("青木翔太");
//		update.setKana("アオキショウタ");
//		update.setGender("男性");
//		update.setBirthday(LocalDate.of(1992, 4, 12));
//		update.setEmail("aoki.shota@example.com");
//		update.setPassword("shota1234");
//		update.setPhoneNumber("09011112222");
//		update.setPostalCode("0600001");
//		update.setAddress("北海道札幌市中央区北一条西1-1");
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
	
//	@Test
//	void 会員情報登録() {
//		
//		InsertDTO insert = new InsertDTO();
//		
//		insert.setName("藤原愛");
//		insert.setKana("フジワラアイ");
//		insert.setGender("女性");
//		insert.setBirthday(LocalDate.of(1990, 11, 11));
//		insert.setEmail("fujiwara@example.com");
//		insert.setPassword("pass1234");
//		insert.setPhoneNumber("09011110019");
//		insert.setPostalCode("7300001");
//		insert.setAddress("広島県広島市中区基町1-1");
//		insert.setHobby(3);
//		
//		int i = 0;
//		
//		i = memberServise.insertJson(insert);
//		
//		if(i != 0) {
//			
//			System.out.println("登録成功！");
//			
//		}else {
//			
//			System.out.println("登録失敗…");
//		}
//	}
	
//	@Test
//	void 曖昧検索() {
//		
//		MemberDTO dto = new MemberDTO();
//		dto.setName("");
//		dto.setGender("");
//		dto.setEmail("");
//		dto.setAddress("");
//		
//		dto.setOffset(10);;
//		
//		List<MemberDTO> list = memberServise.serchMember(dto);
//		
//		System.out.println(list);
//		//assertFalse(list.isEmpty());
//		assertNotNull(list);
//	}
}

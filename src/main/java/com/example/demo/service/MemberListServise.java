package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.UpdateDTO;
import com.example.demo.mapper.MemberMapper;

@Service
public class MemberListServise {

	@Autowired
	MemberMapper memberMapper;
	
	//全件メンバーリスト検索
	public List<MemberDTO> listget() {
		
		List<MemberDTO> memberlist = null;
		
		memberlist = memberMapper.findAll();
		
		return memberlist;
	}
	
	//曖昧検索
	public List<MemberDTO>  serchMember(MemberDTO dto){
		
		List<MemberDTO> serchlist = memberMapper.serchMember(dto);
		
		return serchlist;
	}
	
	//userIdから1件だけ呼び出し
	public MemberDTO memberList(String userId) {
		
		MemberDTO member = memberMapper.memberList(userId);
				
		return member;
	}
	
	//更新実行
	public int memberUpdate(UpdateDTO updateDto) {
		
		int result = 0;
		
		result = memberMapper.updateMember(updateDto);
		
		return result;
		
	}
	
	//1件削除
	public int memberDelete(String userId) {
		
		int result = 0;
		
		result = memberMapper.deleteMember(userId);
		
		return result;
	}
	
	public List<MemberDTO> selectMember(MemberDTO dto) {
		
		List<MemberDTO> list = null;
		
		
		
		return list;
	}
}

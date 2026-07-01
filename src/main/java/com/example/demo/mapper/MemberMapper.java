package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.UpdateDTO;

@Mapper
public interface MemberMapper {
//
//	//会員情報一覧を表示
	List<MemberDTO> findAll();
//	
	
	//曖昧検索
	List<MemberDTO> serchMember(MemberDTO serchdto);
	
	//詳細検索
//	List<MemberDTO> selectMember(MemberDTO dto);
	
	//更新画面に移動
	MemberDTO memberList(MemberDTO updateUser);
	
	//更新実行
	int updateMember(UpdateDTO updateDto);

	//論理削除実行
	int deleteMember(MemberDTO deleteUser);
	
}

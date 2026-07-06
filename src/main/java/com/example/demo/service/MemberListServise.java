package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.InsertDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.UpdateDTO;
import com.example.demo.exception.DeleteException;
import com.example.demo.exception.InsertException;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.exception.UpdateException;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.mapper.UserMapper;

@Service
public class MemberListServise {

//	@Autowired
//	MemberMapper memberMapper;
//
//	@Autowired
//	private UserMapper userMapper;
	
	private final MemberMapper memberMapper;
	private final UserMapper userMapper;

	// ① コンストラクタインジェクションに変更
	public MemberListServise(MemberMapper memberMapper, UserMapper userMapper) {
		this.memberMapper = memberMapper;
		this.userMapper = userMapper;
	}


	//曖昧検索・一覧表示
	public List<MemberDTO> serchMember(MemberDTO dto) {

		List<MemberDTO> serchlist = memberMapper.serchMember(dto);

		//リストの中身が取れなかったら例外を投げる
		if (serchlist == null) {
			throw new MemberNotFoundException(

					"接続エラーです");
		}

		return serchlist;
	}

	//userIdから1件だけ呼び出し
	public MemberDTO memberList(MemberDTO dto) {

		MemberDTO member = memberMapper.memberList(dto);

		return member;
	}

	//更新実行
	public int memberUpdate(UpdateDTO updateDto) {

		int result = 0;

		result = memberMapper.updateMember(updateDto);

		if (result == 0) {

			throw new UpdateException("更新に失敗しました。");
		}

		return result;

	}

	//1件削除
	public int memberDelete(MemberDTO dto) {

		int result = 0;

		result = memberMapper.deleteMember(dto);

		if (result == 0) {

			throw new DeleteException("削除に失敗しました。");
		}

		return result;
	}

	//CSV出力
	public List<MemberDTO> exportCsv() {

		List<MemberDTO> csvlist = memberMapper.findAll();

		return csvlist;
	}

	public int insertJson(InsertDTO insert) {

		int result = 0;

		result = userMapper.insertJson(insert);

		if (result == 0) {

			throw new InsertException("登録に失敗しました。");
		}

		return result;

	}
}

package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.InsertDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.exception.InsertException;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.mapper.UserMapper;

/*
 * Springの単体テスト
 * 
 * @ExtendWith(MockitoExtension.class)
 * ➡@Mock・@@InjectMocks を使うための宣言
 * 
 * @Mock　➡　偽物（モック）のオブジェクトを作成する
 * 
 * @@InjectMocks　➡　テスト対象のServiceを生成し、@Mockを自動で注入する
 * 
 * ※　Mockは空っぽのため、自分で戻り値や入力値を設定してあげる必要があり、
 * 　　whenを使ってMockに求める動作を設定する
 */

@ExtendWith(MockitoExtension.class)
class MemberListServiseTest {

	@Mock
	private MemberMapper memberMapper;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private MemberListServise memberListServise;
	
	@Test
	void 単体テスト実行() {
		
		//入力値を入れる箱
		MemberDTO searchCondition = new MemberDTO();
		
		//検索結果を入れる箱
		MemberDTO member1 = new MemberDTO();
		
		//検索結果はリストに入れる
		List<MemberDTO> mockList = List.of(member1);
		
		//Mapperが呼ばれたときに検索結果の箱に返すように設定する（モックの動作を設定する）
	    //(mockList)は期待値をいれるイメージ
		when(memberMapper.serchMember(searchCondition)).thenReturn(mockList);
		
		//テスト対象となっているserviceを実行させる
		List<MemberDTO> result = memberListServise.serchMember(searchCondition);
		
		//検索結果から値が返ってきているか
		assertThat(result).hasSize(1);
		
		//Mapperのメソッドが呼ばれているか確認
		verify(memberMapper).serchMember(searchCondition);
		
	}
	
	@Test
	void 登録単体テスト() {
		
		InsertDTO insert = new InsertDTO();
		
		when(userMapper.insertJson(insert)).thenReturn(1);
		
		int result = memberListServise.insertJson(insert);
		
		assertThat(result).isEqualTo(1);
		verify(userMapper).insertJson(insert);
	}

	@Test
	void 登録失敗して例外を返す() {
		
		InsertDTO insert = new InsertDTO();
		
		when(userMapper.insertJson(insert)).thenReturn(0);
	
		//どの処理で例外が発生するか
	    assertThatThrownBy(() -> memberListServise.insertJson(insert))
	    
	    //どんな例外が発生するか
        .isInstanceOf(InsertException.class)
        
        //その例外のエラーメッセージは合っているか
        .hasMessage("登録に失敗しました。");

	    verify(userMapper).insertJson(insert);
	}
			
}

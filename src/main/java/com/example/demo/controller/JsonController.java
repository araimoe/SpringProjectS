package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.InsertDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.UpdateDTO;
import com.example.demo.service.MemberListServise;

@RestController
public class JsonController {

	@Autowired
	MemberListServise memberService;

	@PostMapping("/executeJsonTest")
	public Map<String, String> test(
			@RequestBody MemberDTO dto) {

		System.out.println(dto.getName());

		//ここにMap＜HTMLから受け取ったvalue「name = "name"」,入力値＞が入っている
		Map<String, String> result = new HashMap<>();

		result.put("message", "受信成功");

		return result;
	}

	@PostMapping("/memberListJson")
	@ResponseBody
	public List<MemberDTO> selectMembers(@RequestParam(defaultValue = "1") int page, @RequestBody MemberDTO dto) {

		//jsから送られてくるのはIntegerで返ってくるため、ここでint型に代入しなおす
		if (dto.getPage() != null && dto.getPage() != 0) {

			page = dto.getPage();
		}

		int offset = (page - 1) * 10;

		dto.setOffset(offset);
		List<MemberDTO> list = memberService.serchMember(dto);

		//ここでjs側にデータだけを送る 
		return list;

	}

	//Javascriptから文字列を受け取る際に、「@RequestBody String userId」で
	//受け取ると userId =  "U012" となって渡され、「""」が入った状態になる
	//その場合はreplace関数を使って取るか、DTOにいれてあげるとなくすことができる

	@PostMapping("/updateJson")
	@ResponseBody
	public MemberDTO updateJson(@RequestBody MemberDTO dto) {

		MemberDTO updateList = memberService.memberList(dto);

		return updateList;
	}

	@PostMapping("/updateSuccessJson")
	@ResponseBody
	public Map<String,Object> updateSuccessJson(@Valid @RequestBody UpdateDTO update, BindingResult error) {

		Map<String,Object> response = new HashMap<>();

		if (error.hasErrors()) {

			Map<String,String> errorsMap = new HashMap<>();
			
			for (FieldError fieldError : error.getFieldErrors()) {
				
				errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			
			response.put("result", -1);
			response.put("errors", errorsMap);
			return response;
		}

		int result = memberService.memberUpdate(update);

		response.put("result",result );
		return response;
	}

	@PostMapping("/deleteJson")
	@ResponseBody
	public int deleteJson(@RequestBody MemberDTO delete) {

		int deleteflag = 0;

		deleteflag = memberService.memberDelete(delete);

		return deleteflag;
	}

	/*
	 * JSON方式でのバリテーションについて
	 * ・js側にエラーメッセージと項目名を合わせて渡したい場合はMap<String,Object>で返す
	 * ※serviceとmapperは変えず、返ってきた数字ごとにエラー表示の分岐を行う
	 * ・返す値はエラーメッセージと項目名が合わさったMapと異常系かを判別させる数字のみ
	 */
	
	@PostMapping("/insertSuccessJson")
	public Map<String, Object> insertSuccessJson(@Valid @RequestBody InsertDTO insert, BindingResult error) {
		//メッセージと分岐条件を受け取る際にまとめておくための箱
		Map<String, Object> response = new HashMap<>();

		if (error.hasErrors()) {

			//エラーをまとめて置くための箱
			Map<String, String> errors = new HashMap<>();

			//FieldError：エラーとなったフィールド名＋メッセージを一覧で取得
			//error.getFieldErrors：エラーが起きたプロパティ名（例：name・kana・birthdayなど）
			for (FieldError fieldError : error.getFieldErrors()) {

				//error.getDefaultMessage()：DTOで指定したメッセージが入る
				//エラーを入れる箱にデータを入れ込む
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}

			//箱の中にエラーをまとめたものと、異常値を返す
			response.put("result", -1);
			response.put("errors", errors);
			return response;

		}

		int result = memberService.insertJson(insert);

		//登録が出来れば、数字を返しエラーメッセージは無しで返す
		response.put("result", result);
		return response;
	}
}

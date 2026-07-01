package com.example.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.UpdateDTO;
import com.example.demo.service.MemberListServise;

@Controller
public class MemberController {

	@Autowired
	MemberListServise memberService;
	
	/*ページングの処理について
	 * ・@RequestParam(defaultValue = "1")は初期表示を出す際に使う。
	 * 　初期表示ではまだページ数がないためデフォルト値を入れる
	 * ・int pageにはユーザーがクリックしたページ番号が入るため変化していくため入力値となる
	 * ・int offset = (page - 1) * 10;で先に表示をした件数をスキップをする件数を取得する
	 */
	
//	検索機能・一覧画面表示
	@GetMapping("/selectMember")
	public String selectMember(@ModelAttribute MemberDTO form,@RequestParam(defaultValue = "1") int page,Model model) {
		
		int offset = (page - 1) * 10;
		
		form.setOffset(offset);
		
		List<MemberDTO> memberList = memberService.serchMember(form);
		
		if(memberList.isEmpty()) {
			
			model.addAttribute("MemberDTO",form);
			model.addAttribute("serchError","該当するものが見つかりませんでした。");
		}
		
		model.addAttribute("MemberDTO",form);
		model.addAttribute("memberList",memberList);
		
		return "MemberList";
	}
	
	@PostMapping("/update")
	public String updateSelect(@ModelAttribute MemberDTO form, Model model) {
		
		MemberDTO member = memberService.memberList(form);
		
		if(member != null) {
			
			model.addAttribute("MemberDTO",member);
			
			return "Update";
		}
		
		model.addAttribute("userIdError","データの取得に失敗しました。");
		return "redirect:/selectMember";
	}
	
	@PostMapping("/updateSuccess")
	public String updateMember(@Valid @ModelAttribute UpdateDTO form,BindingResult result, Model model) {
		
	
		 
		 //ここでDTOで設定したバリテーションが発生すれば動く仕組み
		 if(result.hasErrors()) {
			 
			 
			 model.addAttribute("UpdateDTO",form);
			 model.addAttribute("MemberDTO",new MemberDTO());
			 return "Update";
		 }
		 
		int updateFlag = memberService.memberUpdate(form);

			model.addAttribute("UpdateDTO",form);
			return "UpdateSuccess";

	}

	//redirect:でコントローラへ遷移する
	@PostMapping("/delete")
	public String deleteMember(@ModelAttribute MemberDTO form,Model model) {
		
		int result = memberService.memberDelete(form);
		
			
			model.addAttribute("deleteSuccess","正常に削除されました");
			return "redirect:/selectMember";
	
	}
	
}

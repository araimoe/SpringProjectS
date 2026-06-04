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
	
	@GetMapping("/memberList")
	public String memberList(@RequestParam(defaultValue = "1") int page,Model model) {
		
		int offset = (page - 1) * 10;
		
		List<MemberDTO> memberlist = null;
		
		memberlist = memberService.listget(offset);
		model.addAttribute("memberlist", memberlist);
		model.addAttribute("MemberDTO",new MemberDTO());
		
		return "MemberList";
		
		
	}
	
	@GetMapping("/selectMember")
	public String selectMember(@ModelAttribute MemberDTO form,@RequestParam(defaultValue = "1") int page,Model model) {
		
		int offset = (page - 1) * 10;
		
		form.setOffset(offset);
		
		List<MemberDTO> memberList = memberService.serchMember(form);
		
		if(memberList.isEmpty()) {
			
			model.addAttribute("MemberDTO",new MemberDTO());
			model.addAttribute("serchError","該当するものが見つかりませんでした。");
		}
		
		model.addAttribute("MemberDTO",new MemberDTO());
		model.addAttribute("memberList",memberList);
		
		return "memberSearchResult";
	}
	
	@PostMapping("/update")
	public String updateSelect(@RequestParam String userId, Model model) {
		
		MemberDTO member = memberService.memberList(userId);
		
		if(member != null) {
			
			model.addAttribute("MemberDTO",member);
			
			return "Update";
		}
		
		model.addAttribute("userIdError","データの取得に失敗しました。");
		return "MemberList";
	}
	
	@PostMapping("/updateSuccess")
	public String updateMember(@Valid @ModelAttribute UpdateDTO form,BindingResult result, Model model) {
		
		int i = 0;
		 
		 //ここでDTOで設定したバリテーションが発生すれば動く仕組み
		 if(result.hasErrors()) {
			 
			 //HTML側でUserDTOを使っているためエラー防止で入れる
			 model.addAttribute("UpdateDTO",form);
			 return "Update";
		 }
		 
		i = memberService.memberUpdate(form);
		
		if( i != 0) {
			
			model.addAttribute("UpdateDTO",form);
			return "UpdateSuccess";
		}
		
		model.addAttribute("UpdateError","更新に失敗しました");
		return "Update";
	}

	//redirect:でコントローラへ遷移する
	@PostMapping("/delete")
	public String deleteMember(@RequestParam String userId,Model model) {
		
		int i = 0;
		
		i = memberService.memberDelete(userId);
		
		if(i != 0) {
			
			model.addAttribute("deleteSuccess","正常に削除されました");
			return "redirect:/memberList";
		}
		
		model.addAttribute("deleteError","削除に失敗しました");
		return "redirect:/memberList";
	}
	
}

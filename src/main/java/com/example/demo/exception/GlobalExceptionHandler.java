package com.example.demo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.controller.MemberController;
import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.UpdateDTO;

/*
 * 個々の例外を整理して、次の処理を行う
 */

@ControllerAdvice(assignableTypes= MemberController.class)
public class GlobalExceptionHandler {

//	@ExceptionHandler(LoginException.class)
//	public String loginError(LoginException e,Model model) {
//		
//		model.addAttribute("LoginError", e.getMessage());
//		model.addAttribute("LoginDTO", new LoginDTO());
//		
//		return "Login";
//	}
//	
	
	//会員情報一覧エラー
	//例外と認識させた個々の例外を分けて書いていく
	@ExceptionHandler(MemberNotFoundException.class)
	public String selectMemberError(MemberNotFoundException e,Model model) {
		
		model.addAttribute("message", e.getMessage());

	    return "result";
	}
	
	//削除エラー
	@ExceptionHandler(DeleteException.class)
	public String deleteError(Exception e,Model model) {
		
		model.addAttribute("deleteError", e.getMessage());
		model.addAttribute("MemberDTO",new MemberDTO());
		
		return "MemberList";
	}
	
	//更新エラー
	@ExceptionHandler(UpdateException.class)
	public String updateError(Exception e,Model model) {
		
		model.addAttribute("updateError", e.getMessage());
		model.addAttribute("UpdateDTO",new UpdateDTO());
		
		return "Update";
	}
	
	//システムエラー（共通）
	@ExceptionHandler(Exception.class)
	public String systemError( Exception e,Model model) {

	    model.addAttribute("message","システムエラーが発生しました");

	    return "error";
	}
}

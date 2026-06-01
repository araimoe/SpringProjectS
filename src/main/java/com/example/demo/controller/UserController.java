package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.LoginService;

@Controller
public class UserController {

	@Autowired
    LoginService loginService;
	
	 @PostMapping("/insert")
	 public String insertTable(Model model) {
		 
		 //ここでインスタンス化をしてデータを初期化する
		 //UserDTO ＝ HTML側「th:object="${UserDTO}」のこと
		 model.addAttribute("UserDTO", new UserDTO());
		 
		 return "insert";
	 }
	 
	 
	 //BindingResultでエラーメッセージを格納する
	 //public String insert(@Valid @ModelAttribute("UserDTO"),BindingResult result, Model model)にしても良い
	 //この場合、addAttributeは入れなくても良くなる
	 @PostMapping("/insert-click")
	 public String insert(@Valid @ModelAttribute UserDTO form,BindingResult result, Model model) {
		 
		 int i = 0;
		 
		 //ここでDTOで設定したバリテーションが発生すれば動く仕組み
		 if(result.hasErrors()) {
			 
			 //HTML側でUserDTOを使っているためエラー防止で入れる
			 model.addAttribute("UserDTO",form);
			 return "insert";
		 }
		
		//DAOと同じでSQLには正確なものを入れる
		 i = loginService.insert(form);
		 
		 if(i !=1) {
			 
			 model.addAttribute("UserDTO",form);
			 model.addAttribute("insertError", "登録できませんでした");

			 return "insert";
		 }
		 
		 model.addAttribute("UserDTO",form);
		 return "insertSuccess";
	 }
}

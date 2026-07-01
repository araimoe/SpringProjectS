package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.LoginService;

/*
 * 【Spring Security】の流れ
 * ※ログインcontroller側は遷移のみを実装させる（そのため@GetMapping）
 * HTMLでは「method = "post"」になっているがログイン認証は【Spring Security】が行う
 */

@Controller
public class LoginController {

	@Autowired
    LoginService loginService;
	
	 @GetMapping("/login")
	    public String login() {

	        return "Login";
	    }
	 
	 @GetMapping("/")
	 public String memu() {
		 
		 return "result";
	 }
	 
	 //@RequestParamは【request.setAttribute】と同じ働きをしている
	 
	 @PostMapping("/login-review")
	 public String login(@Valid @ModelAttribute LoginDTO form,Model model,BindingResult result ,HttpSession session) {
		 
		 UserDTO userdto = null;
		 
		 if(result.hasErrors()) {
			 
			 //HTML側でUserDTOを使っているためエラー防止で入れる
			 model.addAttribute("LoginDTO",form);
			 
			 return "Login";
		 }
		 
		 
		 userdto =  loginService.login(form);
		 
		 //ログインが出来ればセッションを行う
		 if(userdto != null) {
			 
			 //UserDTOにあるuserIdとnameがセッション状態となる
			 session.setAttribute("loginUser", userdto);
			 return "result"; 
		 }
		 
		 //ログイン失敗
		 model.addAttribute("LoginDTO", form);
		 model.addAttribute("loginError", "ログインに失敗しました");
		 return  "Login";
		 
	 }
	 //ログアウト
	 @PostMapping("/logout")
	 public String logout(HttpSession session,Model model) {
		 
		 session.invalidate();
		 model.addAttribute("LoginDTO", new LoginDTO());
		 
		return  "Login";
		 
	 }
	 
	 @GetMapping("/jsonTest")
	 public String jsonTest() {
		 
		 return "JsonTest";
	 }
	
}

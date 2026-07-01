package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangeJsonController {

	 //画面遷移用
	 @GetMapping("/change")
	 public String change() {
		 
		 return "MemberListJson";
	 }
	 
	 @GetMapping("/updateChage")
	 public String updateJson() {
		 
		 return "UpdateJson";
	 }
	 
	 @GetMapping("/insertChange")
	 public String insertChange() {
		 
		 return "InsertJson";
	 }
	 
	 @GetMapping("/resultChange")
	 public String resultChange() {
		 
		 return "JsonTest";
	 }
}

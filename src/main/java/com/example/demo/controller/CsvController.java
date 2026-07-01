package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberListServise;

@Controller
public class CsvController {

	@Autowired
	MemberListServise memberService;
	/*　CSVについて
	 * ・【HttpServletResponse response】で直接httpにデータを送っているため、返値がなくても良い
	 * 
	 */
	@GetMapping("/csv")
	public void csvController(@ModelAttribute MemberDTO form,@RequestParam(defaultValue = "1") int page,HttpServletResponse response) throws IOException {
		
		int offset = (page - 1) * 10;
		
		form.setOffset(offset);
		
		//文字化け防止
		 response.setContentType("text/csv; charset=Shift_JIS");
		 response.setCharacterEncoding("Shift_JIS");

		
		// CSV設定
	    response.setHeader(
	        "Content-Disposition",
	        "attachment; filename=member.csv"
	    );

	    //文字列を書き込むためのもの
	    PrintWriter writer =   new PrintWriter(
	            new OutputStreamWriter(
	                    response.getOutputStream(),
	                    "Shift_JIS"
	                ));
		
	 // ヘッダー 
	    writer.println("ID,名前,名前（フリガナ）,性別,誕生日,Eメール,パスワード,電話番号,郵便番号,住所");
	    
	    DateTimeFormatter formatter =
	    	    DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    
	    List<MemberDTO> memberlist = memberService.serchMember(form);
	   
	    //CSV出力したいもの　
	    //※「，」ではなく「,」で半角カンマにすること　全角カンマだとExcelがCSVとしてうまく認識しないことがある。
	    for(MemberDTO dto : memberlist) {
	    	
	    		writer.println(
	    				dto.getUserId()+ "," +
	    				dto.getName()+ "," +
	    				dto.getKana()+ "," +
	    				dto.getGender()+ "," +
	    				dto.getBirthday().format(formatter)+ "," +
	    				dto.getEmail()+ "," +
	    				dto.getPassword()+ "," +
	    				dto.getPhoneNumber()+ "," +
	    				dto.getPostalCode()+ "," +
	    				dto.getAddress() 
	    		);		
	    }
	    //CSVの実行
	    writer.flush();
	    
	    //CSVの終了
	    writer.close();
	}
}

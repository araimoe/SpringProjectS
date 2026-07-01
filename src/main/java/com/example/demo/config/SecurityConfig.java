package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//＊このクラスはSpring Securityのルールブックとして扱う

/*
 * 用語解説
 * @Configuration：設定クラスであると定義させる
 * @Bean：Springがこのメソッドの戻り値を管理する（今回はSecurityFilterChain）
 */

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//Spring Securityの設定本体
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		/*
		 * 「.anyRequest()」：全てのURLに適用
		 * 「.authenticated()」：ログインしている人のみアクセス可能
		 * 「Customizer.withDefaults()」：Spring Securityのデフォルト設定を使う
		 * 								(デフォルト画面に遷移する)
		 */

		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
								"/login",
								"/insert",
								"insert-click")
						.permitAll() //指定した画面は誰でも入れる
						.requestMatchers("/css/error.css").permitAll() //CSSファイルはログインしていなくても取得（アクセス）できる
						.anyRequest().authenticated() //それ以外はログイン必須
				)
				//ログインしたときの画面遷移場所を指定し、
				//  その画面でどの値を受け取るかを提示する

				.formLogin(form -> form
						.loginPage("/login")
						.usernameParameter("email") //ログイン画面で受け取る値
						.permitAll());

		//ここまで設定した内容をSpring Securityへ渡す
		return http.build();
	}
}

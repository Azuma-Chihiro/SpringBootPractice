package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
	
	@Autowired
	CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
	
	public WebSecurityConfig(CustomUserDetailsServiceImpl customUserDetailsServiceImpl) {
		this.customUserDetailsServiceImpl = customUserDetailsServiceImpl;
	}
	
	//passwordのハッシュ化
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
		.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/admin/signup","/admin/signin", "/css/**", "/contact/**" )
				.permitAll()
				.anyRequest()
				.authenticated()
				)

		.formLogin(login -> login
				.loginPage("/admin/signin")//ログインページ指定
				.usernameParameter("email")
				.passwordParameter("password")
				.loginProcessingUrl("/admin/signin")
				
				.defaultSuccessUrl("/admin/contacts", true)//ログイン成功時のページ指定
				.permitAll()
				)
        .logout(logout -> logout
                .logoutUrl("/admin/signout") // ログアウト用のURL
                .logoutSuccessUrl("/admin/signin") // ログアウト成功後のリダイレクト先
            );
		
		return http.build();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsServiceImpl).passwordEncoder(passwordEncoder());
		
	}

}

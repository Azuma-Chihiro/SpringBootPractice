package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
        return config.getAuthenticationManager();
    }
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
		
		.authorizeHttpRequests(
				request -> request
				.requestMatchers("/admin/signup","/admin/signin","/css/**","/contact/**","/admin/contacts","/admin/signout","/admin/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				);
		/*		
		
		.formLogin(login -> login
				.loginPage("/admin/signin")
				
				.loginProcessingUrl("/admin/signin")
				
				.defaultSuccessUrl("/admin/contacts").permitAll());
		*/
		return http.build();
	}
}

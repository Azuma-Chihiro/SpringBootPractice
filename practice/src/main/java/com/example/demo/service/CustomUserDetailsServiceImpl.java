package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;


@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	AdminRepository adminRepository;
	
	public CustomUserDetailsServiceImpl(@Lazy AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByEmail(email);
		
		List<GrantedAuthority> grant = new ArrayList<GrantedAuthority>();
		
		GrantedAuthority auth = new SimpleGrantedAuthority("ADMIN");
		
		grant.add(auth);
		
		UserDetails detail = (UserDetails) new User(email, admin.getPassword() , grant);
		
		System.out.println(detail);
		
		return detail;
		
	}

}

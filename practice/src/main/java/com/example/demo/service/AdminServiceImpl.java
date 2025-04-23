package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminUserForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

		@Autowired
		private AdminRepository adminRepository;
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//管理者の新規登録
		@Override
		public void saveAccount(AdminUserForm adminUserForm) {
			Admin admin = new Admin();
			
			admin.setLastName(adminUserForm.getLastName());
			admin.setFirstName(adminUserForm.getFirstName());
			admin.setEmail(adminUserForm.getEmail());
			admin.setPassword(encoder.encode(adminUserForm.getPassword()));
			
			adminRepository.save(admin);
			
		}
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		/*
		@Override
		public Optional<Admin>adminLogin(String email, String password) {
		    Optional<Admin> adminOpt = adminRepository.findByEmail(email);
		    
		    
		    }

		    		/*
		        .map(admin -> encoder.matches(password, admin.getPassword()))
		        .orElse(false);
		        */
		
		
		
		@Override
		public boolean adminLogin(String email, String password) {
			Optional<Admin> adminOpt = adminRepository.findByEmail(email);
			
			if(adminOpt.isPresent()) {
				Admin admin = adminOpt.get();
				
				System.out.println(encoder.encode(password));
				System.out.println(admin.getPassword());
				
				if(encoder.matches(password, admin.getPassword())) {
					
					return true;
				}
			}
			
			return false;
			
			/*
			if(adminOpt != null && encoder.matches(password,
					adminOpt.getPassword())) {
				return true;
			}
			return false;
		
			
	    public void adminLogin(String rawPassword) {
	        String encodedPassword = passwordEncoder.encode(rawPassword);
	        // エンコードしたパスワードで登録処理を行う
	    }
	    */
		}
}

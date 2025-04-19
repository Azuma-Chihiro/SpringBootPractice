package com.example.demo.service;

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
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
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
		
		@Override
		public boolean adminLogin(String email, String password) {
			Admin admin = adminRepository.findByEmail(email);
			System.out.println(encoder.encode(password));
			System.out.println(admin.getPassword());
			
			
			if(admin != null && encoder.matches(password,
					admin.getPassword())) {
				return true;
			}
			return false;
		}
}

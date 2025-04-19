package com.example.demo.service;

import com.example.demo.form.AdminUserForm;

public interface AdminService {
	
	void saveAccount(AdminUserForm adminUserForm);
	
	boolean adminLogin(String email,String password);

}

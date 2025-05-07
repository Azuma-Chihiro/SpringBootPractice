package com.example.demo.controller;



import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AdminUserForm;
import com.example.demo.service.AdminService;

@Controller
public class AdminUserController {
	
	@Autowired
	private AdminService adminService;

	/*adminUser新規登録の画面表示
	 * return contact情報一覧; */
	
	@GetMapping("/admin/signup")
	public String getSignup(@ModelAttribute AdminUserForm adminUserForm) {
		
		return "signup";
	}
	
	//新規登録
	
	  @PostMapping("/admin/signup")
	  public String saveAccount(@ModelAttribute AdminUserForm adminUserForm) {

		  System.out.println("新規登録の処理開始");
		  /*
		  HttpSession session = request.getSession();
		  AdminUserForm adminUserForm = (AdminUserForm) session.getAttribute("adminUserForm");
		  */
		  System.out.println(adminUserForm);
		  
	        adminService.saveAccount(adminUserForm);
		  
	        
	        System.out.println(adminUserForm.getLastName());
	        System.out.println(adminUserForm.getEmail());
	        System.out.println(adminUserForm.getFirstName());
	        System.out.println("アカウント作成されました。");
	        
	       // session.invalidate();

	        
	    return "redirect:/admin/signin";
	  }
	
	/*adminUserログイン画面の実装
	  return ログイン;*/
	 
	
	@GetMapping("/admin/signin")
	public String getLogin() {
		
		System.out.println("戻ったよ");
		
		return "signin";
	}
	
	
	@GetMapping("/admin/signout")
	public String getLogout(HttpSession session) {
		session.invalidate();
		
		System.out.println("出たよ");
		
		return "redirect:/admin/signin";
	}
	
}

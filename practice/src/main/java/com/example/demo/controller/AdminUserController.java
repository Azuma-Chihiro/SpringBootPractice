package com.example.demo.controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping(value = "/admin/signup")
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
	 * return ログイン;
	 */
	
	@GetMapping(value="/admin/signin")
	public String getLogin(Model model, HttpServletRequest request) {
		
		
		AdminUserForm adminUserForm = new AdminUserForm();
		model.addAttribute("adminUserForm", adminUserForm);
		
		return "signin";
	}
	
	@PostMapping("/admin/signin")
	public String postLogin(@ModelAttribute("adminUserForm") AdminUserForm adminUserForm, HttpSession session, Model model) {
	    // セッションから保存された情報を取得
	    //AdminUserForm savedForm = (AdminUserForm) session.getAttribute("adminUserForm");

		
		System.out.println("ログイン処理開始");
		System.out.println(adminUserForm.getEmail());
		System.out.println(adminUserForm.getPassword());
		boolean Login = adminService.adminLogin(adminUserForm.getEmail(),adminUserForm.getPassword());
		System.out.println(Login);
		
	    // 認証失敗時
	    if (!Login){
	    	
	    	System.err.println("ログイン失敗しました。");
	    	
	    	//失敗時
	        return "redirect:/admin/signin";
	    }else{

	    // 認証成功時
	    System.out.println("ログイン成功しました。");
	    return "redirect:/admin/contacts";
	    }
	}
}
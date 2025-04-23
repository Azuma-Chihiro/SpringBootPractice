package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.service.ContactService;

//管理画面

@Controller
public class AdminController {
	
	@Autowired
	private ContactService contactService;
	
	/*contactsDBから一覧を表示
	*findAll();
	*contact情報一覧画面 return 情報一覧;
	*/
	
	@GetMapping(value = "/admin/contacts")
	public String index(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("isLoggedIn") == null) {
			return "redirect:/admin/signin"; // ログインしていなければ、再度ログイン画面にリダイレクト
		}
		
		//ログイン状態が確認できれば、お問い合わせ一覧を表示
		//ServiceのgetAllを呼び出してデータを取得
		List<Contact> contactlist = contactService.getAll();
		
		//モデルでーたを追加してビューに渡す
		model.addAttribute("contactlist", contactlist);
		return "index";
	}
	
	/*contact情報詳細一覧の画面表示
	 * findById();
	 * return 情報詳細画面*/
	
	@GetMapping(value ="/admin/contacts/{id}")
	public String contactDetail(@PathVariable Long id, Model model) {
		Contact contactslist = contactService.findById(id);
		model.addAttribute("contactslist", contactslist);
		
		return "contactDetail";
	}
	
	/*お問い合わせ詳細を編集する画面
	 * 処理
	 * findById();でDBから詳細情報を取得
	 */
	
	@GetMapping(value = "/admin/contacts/{id}/edit")
	public String displayEdit(@PathVariable Long id,Model model) {
		Contact contactEdit = contactService.findById(id);
		model.addAttribute("contactEdit", contactEdit);

		return "edit";
	}
	
	/*
	 * DBへ編集画面から受け取った情報を更新
	 * 	    Contact contact = findById(contactUpdateRequest.getId());
	 */
	
	@PostMapping(value ="/admin/contacts/edit")
	public String contactEdit(@Validated @ModelAttribute ("contactForm") Contact contact ,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			return "contactFrom";
		}
		
		contactService.update(contact);
		
		return "redirect:/admin/contacts";
	}
	
    //削除機能の追加
    @PostMapping("/admin/contacts/{id}/delete")
    public String delete(@PathVariable Long id) {
    	

    	contactService.delete(id);
      return "redirect:/admin/contacts";
    }
}
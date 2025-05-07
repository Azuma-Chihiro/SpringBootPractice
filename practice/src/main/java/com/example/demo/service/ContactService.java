package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {

    void saveContact(ContactForm contactForm);

	static List<ContactForm> findAll() {
		return null;
	}

	//全取得
	List<Contact> getAll();

	
	Contact findById(Long id);

	void update(ContactForm form);
	
	public ContactForm giveContact(Contact contact);
	
	void delete(Long id);
	
}
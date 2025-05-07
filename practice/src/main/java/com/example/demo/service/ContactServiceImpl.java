package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
      private ContactRepository contactRepository;

    @Override
    public void saveContact(ContactForm contactForm) {
        Contact contact = new Contact();

        contact.setLastName(contactForm.getLastName());
        contact.setFirstName(contactForm.getFirstName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhone(contactForm.getPhone());
        contact.setZipCode(contactForm.getZipCode());
        contact.setAddress(contactForm.getAddress());
        contact.setBuildingName(contactForm.getBuildingName());
        contact.setContactType(contactForm.getContactType());
        contact.setBody(contactForm.getBody());

        contactRepository.save(contact);
	}

    @Override
	public List<Contact> getAll(){
    	
    	//Contactテーブルの全件を取得しcontactsに格納
    	 List<Contact> contacts = contactRepository.findAll();
    	    
    	return contacts;
    }
 
	@Override
	public Contact findById(Long id){

		return contactRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("idが見つかりません"));
		
	}
	
	//ContactをFormに渡す
	@Override
	public ContactForm giveContact(Contact contact) {
		ContactForm contactForm = new ContactForm();
		
		contactForm.setId(contact.getId());
		contactForm.setLastName(contact.getLastName());
		contactForm.setFirstName(contact.getFirstName());
		contactForm.setEmail(contact.getEmail());
        contactForm.setPhone(contact.getPhone());
        contactForm.setZipCode(contact.getZipCode());
        contactForm.setAddress(contact.getAddress());
        contactForm.setBuildingName(contact.getBuildingName());
        contactForm.setContactType(contact.getContactType());
        contactForm.setBody(contact.getBody());
        contactForm.setCreated_at(contact.getCreated_at());
        contactForm.setUpdated_at(contact.getUpdated_at());
		
		return contactForm;
	}
	
	//更新のsaveメソッド
	
	@Override
    public void update(ContactForm form) {
		
		//既存のデータを取得
		Contact con = contactRepository.findById(form.getId())
				.orElseThrow(() -> new NoSuchElementException("idが見つかりません。"));

		con.setLastName(form.getLastName());
		con.setFirstName(form.getFirstName());
		con.setEmail(form.getEmail());
        con.setPhone(form.getPhone());
        con.setZipCode(form.getZipCode());
        con.setAddress(form.getAddress());
        con.setBuildingName(form.getBuildingName());
        con.setContactType(form.getContactType());
        con.setBody(form.getBody());
		
		contactRepository.save(con);
	}

	
	//contactの物理削除
	@Override
	  public void delete(Long id) {
		
		    contactRepository.deleteById(id);
	}
}
package com.example.demo.service;

import java.util.List;

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

		return contactRepository.findById(id).orElse(null);
		
	}
	
	//更新のsaveメソッド
	
	@Override
	  public void update(Contact contact) {

	    contactRepository.save(contact);
	  }
	
	//contactの物理削除
	@Override
	  public void delete(Long id) {
		  
		    contactRepository.deleteById(id);
		  }
}
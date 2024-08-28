package com.swiftlend.agiospring.domain.application.service.impl;

import com.swiftlend.agiospring.domain.application.dto.ContactDTO;
import com.swiftlend.agiospring.domain.application.model.Contact;
import com.swiftlend.agiospring.domain.application.repository.ContactRepository;
import com.swiftlend.agiospring.domain.application.service.facade.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.time.ZoneOffset;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<ContactDTO> findAll() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map(ContactDTO::new).toList();
    }

    @Override
    public Contact findById(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            return contact;
        }
        return null;
    }


    @Override
    public ContactDTO create(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        contact.setCpf(contactDTO.getCpf());
        contact.setLastUpdate(LocalDateTime.now(ZoneOffset.ofHours(-3)));
        contactRepository.save(contact);
        return contactDTO;
    }

    @Override
    public void delete(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            contactRepository.delete(contact);
        }

    }

    @Override
    public ContactDTO update(Long id, ContactDTO contactDTO) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            contact.setCpf(contactDTO.getCpf());
            contact.setEmail(contactDTO.getEmail());
            contact.setPhone(contactDTO.getPhone());
            contact.setFirstName(contactDTO.getFirstName());
            contact.setLastName(contactDTO.getLastName());
            contact.setLastUpdate(LocalDateTime.now(ZoneOffset.ofHours(-3)));
            contactRepository.save(contact);
            return new ContactDTO(contact);
        }
        return null;

    }
}

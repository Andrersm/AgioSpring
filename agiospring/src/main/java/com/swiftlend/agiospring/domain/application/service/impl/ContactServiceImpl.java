package com.swiftlend.agiospring.domain.application.service.impl;

import com.swiftlend.agiospring.domain.application.dto.ContactDTO;
import com.swiftlend.agiospring.domain.application.model.Contact;
import com.swiftlend.agiospring.domain.application.repository.ContactRepository;
import com.swiftlend.agiospring.domain.application.service.facade.ContactService;
import com.swiftlend.agiospring.domain.security.model.User;
import com.swiftlend.agiospring.domain.security.repository.UserRepository;
import com.swiftlend.agiospring.domain.security.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final TokenService tokenService;
    private final HttpServletRequest request;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Override
    public List<ContactDTO> findAll() {
        User user = tokenService.getUserFromToken();
        List<Contact> contacts = contactRepository.findAllByOwnerUser(user);
        return contacts.stream().map(ContactDTO::new).toList();
    }

    @Override
    public ContactDTO findById(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            return new ContactDTO(contact);
        }
        return null;
    }

    @Override
    public Contact findContactByID(Long id) {
        return contactRepository.findById(id).orElse(null);
    }


    @Override
    public ContactDTO create(ContactDTO contactDTO) {
        User user = tokenService.getUserFromToken();
        Contact contact = new Contact();
        contact.setOwnerUser(user);
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

    @Override
    public ContactDTO findByCpf(String cpf) {
        Contact contact = contactRepository.findByCpf(cpf);
        return new ContactDTO(contact);
    }
}

package com.swiftlend.agiospring.domain.application.service.facade;

import com.swiftlend.agiospring.domain.application.dto.ContactDTO;
import com.swiftlend.agiospring.domain.application.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<ContactDTO> findAll();
    Contact findById(Long id);
    ContactDTO create(ContactDTO contactDTO);
    void delete(Long id);
    ContactDTO update(Long id, ContactDTO contactDTO);

}

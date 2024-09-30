package com.swiftlend.agiospring.domain.application.repository;

import com.swiftlend.agiospring.domain.application.model.Contact;
import com.swiftlend.agiospring.domain.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByOwnerUser(User user);
    Contact findByCpf(String cpf);
}

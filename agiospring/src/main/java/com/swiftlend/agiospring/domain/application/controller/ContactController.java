package com.swiftlend.agiospring.domain.application.controller;


import com.swiftlend.agiospring.domain.application.dto.ContactDTO;
import com.swiftlend.agiospring.domain.application.model.Contact;
import com.swiftlend.agiospring.domain.application.service.facade.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/contacts")
public class ContactController {

    @Autowired
    private  ContactService contactService;

    @Operation(summary = "Lista todos os contatos")
    @GetMapping("/list")
    public ResponseEntity<List<ContactDTO>> findAll() {
        List<ContactDTO> list = contactService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id) {
        Contact contact = contactService.findById(id);
        return ResponseEntity.ok().body(contact);
    }

    @Operation(summary = "Cria um novo contato")
    @PostMapping("/save")
    public ResponseEntity<ContactDTO> create(@RequestBody ContactDTO contactDTO) {
        contactService.create(contactDTO);
        return ResponseEntity.ok().body(contactDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactDTO> update(@PathVariable Long id, @RequestBody ContactDTO contactDTO){
        ContactDTO upContactDTO = contactService.update(id, contactDTO);
        return ResponseEntity.ok().body(upContactDTO);
    }

}

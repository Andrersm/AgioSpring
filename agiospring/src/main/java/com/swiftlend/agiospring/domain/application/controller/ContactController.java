package com.swiftlend.agiospring.domain.application.controller;


import com.swiftlend.agiospring.domain.application.dto.ContactDTO;
import com.swiftlend.agiospring.domain.application.dto.SimpleContactDTO;
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
    public ResponseEntity<List<SimpleContactDTO>> findAll() {
        List<SimpleContactDTO> list = contactService.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ContactDTO> findById(@PathVariable Long id) {
        ContactDTO contactDTO = contactService.findById(id);
        return ResponseEntity.ok().body(contactDTO);
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

package com.swiftlend.agiospring.domain.application.controller;


import com.swiftlend.agiospring.domain.application.dto.ContactDTO;
import com.swiftlend.agiospring.domain.application.dto.LoanDTO;
import com.swiftlend.agiospring.domain.application.service.facade.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @Operation(summary = "Lista todos os emprestimos")
    @GetMapping("/list")
    public ResponseEntity<List<LoanDTO>> findAll() {
        List<LoanDTO> list = loanService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Resgata um Emprestimo via ID")
    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> findById(@PathVariable Long id) {
        LoanDTO loanDTO = loanService.findById(id);
        return ResponseEntity.ok().body(loanDTO);
    }

    @Operation(summary = "Cria um novo emprestimo via ID do dono do emprestimo")
    @PostMapping("/save/{id}")
    public ResponseEntity<LoanDTO> create(@PathVariable Long id, @RequestBody LoanDTO loanDTO) {
        loanService.create(id, loanDTO);
        return ResponseEntity.ok().body(loanDTO);
    }

    @Operation(summary = "Deleta um emprestimo via ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um emprestimo via ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<LoanDTO> update(@PathVariable Long id, @RequestBody LoanDTO loanDTO){
        LoanDTO upLoanDTO = loanService.update(id, loanDTO);
        return ResponseEntity.ok().body(upLoanDTO);
    }






}

package com.swiftlend.agiospring.domain.application.controller;

import com.swiftlend.agiospring.domain.application.dto.InstallmentDTO;
import com.swiftlend.agiospring.domain.application.service.facade.InstallmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/Installments")
public class InstallmentsController {

    @Autowired
    private InstallmentService installmentService;

    @Operation(summary = "Lista todos as parcelas")
    @GetMapping("/list")
    public ResponseEntity<List<InstallmentDTO>> findAll() {
        List<InstallmentDTO> list = installmentService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperar uma parcela pelo ID")
    public ResponseEntity<InstallmentDTO> findById(@PathVariable Long id) {
        InstallmentDTO installmentDTO = installmentService.findById(id);
        return ResponseEntity.ok().body(installmentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        installmentService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

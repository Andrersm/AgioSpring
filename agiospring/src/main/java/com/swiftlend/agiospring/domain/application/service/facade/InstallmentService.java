package com.swiftlend.agiospring.domain.application.service.facade;

import com.swiftlend.agiospring.domain.application.dto.InstallmentDTO;
import com.swiftlend.agiospring.domain.application.model.Loan;

import java.util.List;

public interface InstallmentService {

    List<InstallmentDTO> findAll();
    InstallmentDTO findById(Long id);
    void delete(Long id);
    List<InstallmentDTO> findByLoan(Long id);
    void creatEachInstallment(Loan loan);
}
